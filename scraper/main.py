from __future__ import print_function
import sqlite3
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint
from records.pubmed_record import PubMedRecord
from records.uniprot_record import UniProtRecord
from records.crossref_record import CrossRefRecord
import requests
from datetime import datetime
from dateutil import parser

RESPONSE_EMAIL = "example@example.org"

# To start with, my proteins of interest will be read in from my old homework database 

def main():
    database_name = "./script2db.db"
    # database_name = 'script2db.db'
    pubmed_table_name = "pubmedRecords"
    pdb_table_name = "uniprotIdToPDB"

    with sqlite3.connect(database_name) as conn:
        cursor = conn.cursor()
        PubMedRecord.setup_tables(cursor, database_name, pubmed_table_name)
        unique_pdb_ids = set([x[1] for x in UniProtRecord.read_from_pdb_table(cursor, pdb_table_name)])
        for pdb_id in unique_pdb_ids:
            print(pdb_id)

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
            records =  CrossRefRecord.query_crossref(pdb_id, protein_common_name, rows= 100, email=RESPONSE_EMAIL)
            if records != None and len(records) > 0:
                for record in records:
                    print(record)
                    print("predicted_version: ", predict_protein_version(versions, record.created_date))
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
        return protein_name
    else:
        print(f"Error: PDB ID '{pdb_id}' not found.")
        return None

def predict_protein_version(versions, date_of_citation):
    
    # Sort versions by date in ascending order
    sorted_versions = sorted(versions, key=lambda v: v['date'])
    
    # Find the most recent version on or before the check_date
    # TODO: document the assumption that if the citation date is prior to the first version release date
    # that implies it used the first revision 
    last_version = sorted_versions[0]
    for version in sorted_versions:
        version_release_date = version['date']
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
                    "date": date
                    # "description": version.get("description", "No description available")
                }
                version_details.append(version_info)
            
            return version_details
        else:
            print(f"Error: PDB ID '{pdb_id}' not found.")
            return None
    except Exception as e:
        print(f"Failed to retrieve version date from pdb id: {pdb_id}")
    


if __name__ == "__main__":
    main()