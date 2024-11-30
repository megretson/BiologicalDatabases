from __future__ import print_function
import sqlite3
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
    host = database_url
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
                print(f"\n------------------------------\nRunning queries for {pdb_id}\n------------------------------")

                ''''
                Steps:
                1. Get the protein id in question
                2. Find out its common name
                3. Get the revisions of the protein 
                4. Make a query to cross ref requesting citations for both pdb id and common name 
                5. Predict the version of the protein based on its date
                6. Make a call to the database to create the protein,
                    6a. then its versions 
                    6b. then its citations 
                    # TODO: document assumption that most recent protein verison always used. 
                '''

                versions = get_protein_versions(pdb_id)

                if versions:
                    print(f"Available versions for PDB ID {pdb_id}:")
                    for version in versions:
                        print(version)

                protein_common_name = get_protein_common_name(pdb_id)
                records =  CrossRefRecord.query_crossref(pdb_id, protein_common_name, rows= 100, email=return_email)
                if records != None and len(records) > 0:
                    upload_protein_and_versions_to_database(pdb_id, versions)
                    for record in records:
                        print(record)
                        print(f"Predicted version cited by DOI {record.doi} is {predict_protein_version(versions, record.created_date)} ")
                        # upload_protein_version_citation_to_database()
                else:
                    print(f"Failed to retrieve data from pdb ids")


# # create an instance of the API class
# api_instance = swagger_client.CitationApi(swagger_client.ApiClient())

# issn = 'issn_example' # str | the issn of interest
# limit = 56 # int | How many items to return at one time (max 100) (optional)

# try:
#     # Get all proteins cited by this article
#     api_response = api_instance.get_proteins_cited(issn, limit=limit)
#     pprint(api_response)
# except ApiException as e:
#     print("Exception when calling CitationApi->get_proteins_cited: %s\n" % e)

def get_protein_common_name(pdb_id):
    url = f"https://data.rcsb.org/rest/v1/core/entry/{pdb_id}"
    response = requests.get(url)
    
    if response.status_code == 200:
        data = response.json()
        # Retrieve the title or common name of the protein
        protein_name = data.get("struct", {}).get("title", "No name found")
        print(f"Common protein name found to be '{protein_name}'.")
        return protein_name
    else:
        print(f"Error: PDB ID '{pdb_id}' not found.")
        return None

def predict_protein_version(versions, date_of_citation):
    
    # Sort versions by date in ascending order
    sorted_versions = sorted(versions, key=lambda v: v['revision_date'])
    
    # Find the most recent version on or before the check_date
    # TODO: document the assumption that if the citation date is prior to the first version release date
    # that implies it used the first revision 
    last_version = sorted_versions[0]
    for version in sorted_versions:
        version_release_date = version['revision_date']
        if version_release_date <= date_of_citation:
            last_version = version
        else:
            break

    return last_version

def get_protein_versions(pdb_id):
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
                api_response = api_instance.create_protein(protein_entry) # type: ignore            print("The response of CitationApi->get_proteins_cited:\n")
                pprint(api_response)
        except ApiException as e:
            print("Exception when calling ProteinApi -> create_protein: %s\n" % e)

    


if __name__ == "__main__":
    main()