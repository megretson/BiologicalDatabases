from __future__ import print_function
import sqlite3
from openapi_client.models.citation import Citation
from openapi_client.models.version_entry import VersionEntry
import openapi_client
from openapi_client.rest import ApiException
from openapi_client.models.protein_entry import ProteinEntry
from pprint import pprint
from records.pubmed_record import PubMedRecord
from records.uniprot_record import UniProtRecord
from records.crossref_record import CrossRefRecord
import requests
from datetime import datetime
from dateutil import parser
import os


database_url = "http://localhost:8080/ProteinVersionCitations/1.0.0"
configuration = openapi_client.Configuration(
    host=database_url
)
# To start with, my proteins of interest will be read in from my old homework database


def main():
    database_name = "./script2db.db"
    pubmed_table_name = "pubmedRecords"
    pdb_table_name = "uniprotIdToPDB"
    return_email = get_email_from_env()

    with sqlite3.connect(database_name) as conn:
        cursor = conn.cursor()
        PubMedRecord.setup_tables(cursor, database_name, pubmed_table_name)
        records = UniProtRecord.read_from_pdb_table(cursor, pdb_table_name)
        if records is not None and len(records) > 0:
            unique_pdb_ids = set([x[1] for x in records])
            for pdb_id in unique_pdb_ids:
                print(
                    f"\n------------------------------\nRunning queries for {pdb_id}\n------------------------------")

                ''''
                Steps:
                1. Reads in the pdb Ids present in our homework SQLLite database
                2. For each id: 
                    1. Finds the common name of the protein from the RCSB api
                    2. Finds the available versions of the protein from the RCSB api 
                    3. Uploads the protein and its version to the server 
                    4. Finds citations which reference the protein by Id or common name from the CrossRef API
                    5. For each citation:
                        1. Extracts information about the citation, including the publisher's website
                        2. Predicts which version of the protein this citation references based on its publication date
                        3. Makes a call to the publisher's website
                        4. Extracts all `mailto` tags from the html code of the publisher's website in order to find contact information for the publisher. 
                        5. Uploads the citation and the contact information to the server. 
                '''

                versions = get_protein_versions(pdb_id)

                if versions:
                    print(f"> Found Available versions for PDB ID {pdb_id}:")
                    for version in versions:
                        revision_string = version["revision_date"].strftime("%m/%d/%Y")
                        version_string = str(version["major_version"]) + "." + str(version["minor_version"])
                        print(f"\t> Version: {version_string}, Uploaded: {revision_string}")

                print(
                    f"\nQuerying pdb for protein common name")
                protein_common_name = get_protein_common_name(pdb_id)
                print(
                    f"\nQuerying crossref for citations referencing protein")

                records = CrossRefRecord.query_crossref(
                    pdb_id, protein_common_name, rows=100, email=return_email)
                if records != None and len(records) > 0:
                    upload_protein_and_versions_to_database(pdb_id, versions)
                    for record in records:
                        citation = record.create_citation_from_crossref_record(versions)
                        upload_protein_version_citation_to_database(
                            pdb_id, citation.referenced_protein_version.to_formatted_string(), citation)
                else:
                    print(f"Failed to retrieve data from pdb ids")


def get_protein_common_name(pdb_id):
    url = f"https://data.rcsb.org/rest/v1/core/entry/{pdb_id}"
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        # Retrieve the title or common name of the protein
        protein_name = data.get("struct", {}).get("title", "No name found")
        print(f"> Common protein name found to be '{protein_name}'.")
        return protein_name
    else:
        print(f"Error: PDB ID '{pdb_id}' not found.")
        return None


def get_protein_versions(pdb_id):
    print(f"Querying crossref for protein versions")

    url = f"https://data.rcsb.org/rest/v1/core/entry/{pdb_id}"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            data = response.json()
            versions = data.get("pdbx_audit_revision_history", [])

            # Collect version details
            version_details = []
            for version in versions:
                date = version.get("revision_date", "N/A")
                if version.get("revision_date", "N/A") != "N/A":
                    date = parser.parse(date)
                version_info = {
                    "major_version": version.get("major_revision", "N/A"),
                    "minor_version": version.get("minor_revision", "N/A"),
                    "revision_date": date
                    # "description": version.get("description", "No description available")
                }
                version_details.append(version_info)

            return version_details
        else:
            print(f"Error: PDB ID '{pdb_id}' not found.")
            return None
    except Exception as e:
        print(f"Failed to retrieve version date from pdb id: {pdb_id}")


def get_email_from_env():
    # This function exists almost entirely to prevent my real email from being on github.
    # Crossref prefers you send requests with your email attached to them, so I am setting
    # my email as an env var.
    # To run this script, set EMAIL in advance.
    email = os.getenv("EMAIL")
    if email:
        return email
    else:
        print("Environment variable 'EMAIL' is not set.")
        return "example_email@notreal.org"


def upload_protein_and_versions_to_database(pdb_id, versions):
    with openapi_client.ApiClient(configuration) as api_client:
        # Create an instance of the API class
        api_instance = openapi_client.ProteinApi(api_client)
        version_entries = [VersionEntry.from_dict(version) for version in versions]
        protein_entry = ProteinEntry.from_dict({"pdb_id": pdb_id, "versions": version_entries})

        try:
            # Get all proteins cited by this article
            if protein_entry is not None:
                api_response = api_instance.create_protein(protein_entry)
                print(f"\nSuccessfully uploaded protein {pdb_id}")
        except ApiException as e:
            print(f"Exception when uploading protein {pdb_id}: %s\n" % e)


def upload_protein_version_citation_to_database(pdb_id, version_number, citation: Citation):
    with openapi_client.ApiClient(configuration) as api_client:
        # Create an instance of the API class
        api_instance = openapi_client.ProteinsApi(api_client)
        try:
            # Get all proteins cited by this article
            if citation is not None:
                # type: ignore            print("The response of CitationApi->get_proteins_cited:\n")
                api_response = api_instance.create_protein_citation(
                    pdb_id, version_number, citation)
                if api_response is not None:
                    print(
                        f"Successfully uploaded citation of protein {api_response.referenced_protein_id} with DOI {api_response.doi}")
        except ApiException as e:
            print("Exception when uploading citation: %s\n" % e)


if __name__ == "__main__":
    main()
