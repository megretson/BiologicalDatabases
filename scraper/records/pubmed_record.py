import re
import sqlite3
import time
import requests
from bs4 import BeautifulSoup
import lxml


class PubMedRecord:

    def __init__(self, pubmed_id, referenced_protein) -> None:
        self.pubmed_id = pubmed_id
        self.referenced_protein = referenced_protein


    def __str__(self):
        return "PubMed ID: {}, Referenced Protein: {}".format(self.pubmed_id, self.referenced_protein)


    def append_to_file(self, file_name):
        try:
            with open(file_name, "a") as output_file:
                output_file.write("PubmedId: {} Protein: {}\n".format(
                    self.pubmed_id, self.referenced_protein))
        except IOError as e:
            print("Unable to append ot file")


    def write_to_db(self, cursor, database_name, pubmed_table_name):
        try:
            print("Inserting record {} into pubmed table: {}".format(
                self.pubmed_id, pubmed_table_name))
            self.insert_into_pubmed_table(cursor, pubmed_table_name)
        except Exception as e:
            print("Unable to insert into database", e)


    @staticmethod
    def read_from_pdb_table(cursor, pdb_table_name):
        # code from https://pynative.com/python-mysql-select-query-to-fetch-data/
        sql_select_query = "SELECT * FROM {} ".format(pdb_table_name)
        try:
            cursor.execute(sql_select_query)
            record = cursor.fetchall()
            return record
        except Exception as e:
            print("Unable to complete db call")
            print(e)
    
    @staticmethod
    def read_from_pubmed_table_by_pdb_id(cursor, pdb_table_name, pdb_id):
        # code from https://pynative.com/python-mysql-select-query-to-fetch-data/
        sql_select_query = "SELECT * FROM {} WHERE pdb_id = ?".format(pdb_table_name)
        try:
            cursor.execute(sql_select_query, (pdb_id,))
            records = cursor.fetchall()
            return records
        except Exception as e:
            print("Unable to complete db call")
            print(e)


    def insert_into_pubmed_table(self, cursor, pubmed_table_name):
        pubmed_tuple = (self.pubmed_id, self.referenced_protein)
        pubmed_insertion_string = "INSERT INTO {} VALUES (?,?)".format(
            pubmed_table_name)
        try:
            cursor.execute(pubmed_insertion_string, pubmed_tuple)
        except Exception as e:
            print("Unable to complete db call")
            print(e)

    @staticmethod
    def get_articles_that_reference_protein_by_pdb(pdb_id, retry = 0):
        # example 10CE
        if retry:
            print("Retrying request")
        print("Requesting data from eutils on pdb id {}".format(pdb_id))
        url = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term={}".format(pdb_id)
        try: 
            response = requests.get(url, timeout=60)
            if response.status_code != requests.codes.ok:
                if response.status_code == 429 and retry < 2:
                    print("Eutils not pleased with number of requests. Sleeping and retrying")
                    time.sleep(3)
                    if retry is None:
                        retry = 0
                    retry += 1 
                    return PubMedRecord.get_articles_that_reference_protein_by_pdb(pdb_id, retry)
                else:
                    print("Bad request")
                    response.raise_for_status()
            else:
                print("Request executed successfully")
                if (retry > 0):
                    print(response.text)
                bs_data = BeautifulSoup(response.text, "xml")
                return bs_data
        except lxml.etree.XMLSyntaxError as pe:
            print("Unable to parse returned xml", pe)
        except requests.exceptions.HTTPError as he:
            print("Unable to get data from url", he)
        except ConnectionError as ce:
            print("Unable to connect on url", url)
    
    @staticmethod
    def get_pubmed_ids_from_xml(root):
        # XML results reference 
        # <eSearchResult>
        # <Count>0</Count>
        # <RetMax>0</RetMax>
        # <RetStart>0</RetStart>
        # <IdList/>
        # <TranslationSet/>
        # <QueryTranslation>8WKW</QueryTranslation>
        # <ErrorList>
        # <PhraseNotFound>8WKW</PhraseNotFound>
        # </ErrorList>
        # <WarningList>
        # <OutputMessage>No items found.</OutputMessage>
        # </WarningList>
        # </eSearchResult>

        # <eSearchResult>
        # <Count>15</Count>
        # <RetMax>15</RetMax>
        # <RetStart>0</RetStart>
        # <IdList>
        # <Id>39286541</Id>
        # <Id>39203177</Id>
        # <Id>36540242</Id>
        # <Id>35084416</Id>
        # <Id>34516086</Id>
        # <Id>34386809</Id>
        # <Id>34097386</Id>
        # <Id>28624869</Id>
        # <Id>27932702</Id>
        # <Id>27761846</Id>
        # <Id>27435051</Id>
        # <Id>22287319</Id>
        # <Id>18571716</Id>
        # <Id>17140218</Id>
        # <Id>12161119</Id>
        # </IdList>
        # <TranslationSet/>
        # <QueryTranslation>"10CE"[All Fields]</QueryTranslation>
        # </eSearchResult>
        pdb_ids = []
        count = int(root.find("Count").text)
        if count == 0:
            raise ValueError("Unable to find any pubmed entries for this pdb id")
        ids = root.find_all("Id")
        for id in ids:
            pdb_ids.append(id.text)
        return pdb_ids


    @staticmethod
    def setup_tables(cursor, database_name, pubmed_table_name):
        # Connecting to sqlite
        print("Connecting to database: {}".format(database_name))

        # Creating table
        # a. Create a new table with columns AccessionID, UniProtID, EValue, and
        #  Identity. Do not create the table again if it already exists.
        try:
            pubmed_table = """CREATE TABLE {}(pubmed_id VARCHAR(255), pdb_id VARCHAR(255));""".format(
                pubmed_table_name)
            # 10. Print to the screen a statement when a table is created.
            print("Creating pubmed table: {}".format(pubmed_table_name))
            cursor.execute(pubmed_table)

        except sqlite3.OperationalError:
            print("Table already exists")

