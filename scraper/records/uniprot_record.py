import re
import sqlite3
import requests
from bs4 import BeautifulSoup
import lxml

class UniProtRecord:

    def __init__(self, id, name, full_name, sequence, organism, xray_pdbs=None, go_ids=None) -> None:
        self.id = id
        self.name = name
        self.full_name = full_name
        self.sequence = sequence
        self.organism = organism
        self.xray_pdbs = xray_pdbs if xray_pdbs else []
        self.go_ids = go_ids if go_ids else []
    
    def __str__(self):
        return "Name: {}, Recommended Name: {}, Sequence: {}, Organism: {}".format(self.name, self.full_name, self.sequence, self.sequence)
    
    def write_to_fasta(self):
        if self.id and self.name and self.full_name and self.sequence:
            file_name = "{}.fasta".format(self.id)
            fasta_string = ">Name:{} FullName:{}\n{}".format(self.name, self.full_name, self.sequence)
            with open(file_name, 'w') as f:
                f.write(fasta_string)
        else:
            print("Unable to output fasta for {}.fasta due to missing information".format(self.id))

    
    def write_to_db(self, cursor, database_name, uniprot_table_name, pdb_table_name, go_table_name):
        
        # 11. Print to the screen a statement when data is inserted.
        print("Inserting record {} into uniprot table: {}".format(self.id, uniprot_table_name))
        self.insert_into_uniprot_table(cursor, uniprot_table_name)

        print("Inserting records for {} into pdb table: {}".format(self.id, pdb_table_name))
        self.insert_into_pdb_table(cursor, pdb_table_name)

        print("Inserting records for {} into go table: {}".format(self.id, go_table_name))
        self.insert_into_go_table(cursor, go_table_name)

    def insert_into_pdb_table(self, cursor, pdb_table_name):
        pdb_tuples = [(self.id, _) for _ in self.xray_pdbs]
        pdb_insertion_string = "INSERT INTO {}(uniprot_id, pdb_id) VALUES (?,?)".format(pdb_table_name)
        
        # 13. Have some error handling if the database calls don’t succeed.

        if len(self.xray_pdbs) > 0:
            try:
                cursor.executemany(pdb_insertion_string, pdb_tuples)
            except Exception as e:
                print("Unable to complete db call")
                print(e)
    
    @staticmethod
    def read_from_pdb_table_by_pdb_id(cursor, pdb_table_name, pdb_id):
        # code from https://pynative.com/python-mysql-select-query-to-fetch-data/
        sql_select_query = "SELECT * FROM {} WHERE pdb_id = ?".format(pdb_table_name)
        try:
            cursor.execute(sql_select_query, (pdb_id,))
            record = cursor.fetchone()
            return record
        except Exception as e:
            print("Unable to complete db call")
            print(e)

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

    
    def insert_into_go_table(self, cursor, go_table_name):
        go_tuples = [(self.id, _[0], _[1]) for _ in self.go_ids]        
        go_insertion_string = "INSERT INTO {} VALUES (?,?,?)".format(go_table_name)
        if len(self.go_ids) > 0:
            try:
                cursor.executemany(go_insertion_string, go_tuples)
            except Exception as e:
                print("Unable to complete db call")
                print(e)
    
    @staticmethod
    def read_from_go_table_by_uniprot_id(cursor, go_table_name, uniprot_id):
        # code from https://pynative.com/python-mysql-select-query-to-fetch-data/
        sql_select_query = "SELECT * FROM {} WHERE uniprot_id = ?".format(go_table_name)
        try:
            cursor.execute(sql_select_query, (uniprot_id,))
            records = cursor.fetchall()
            return records
        except Exception as e:
            print("Unable to complete db call")
            print(e)

    def insert_into_uniprot_table(self, cursor, uniprot_table_name):
        uniprot_tuple = (self.id, self.full_name, self.name, self.organism)
        uniprot_insertion_string = "INSERT INTO {} VALUES (?,?,?,?)".format(uniprot_table_name)
        try:
            cursor.execute(uniprot_insertion_string, uniprot_tuple)
        except Exception as e:
            print("Unable to complete db call")
            print(e)

    @staticmethod
    def read_from_uniprot_table_by_uniprot_id(cursor, uniprot_table_name, uniprot_id):
        # code from https://pynative.com/python-mysql-select-query-to-fetch-data/
        sql_select_query = "SELECT * FROM {} WHERE uniprot_id = ?".format(uniprot_table_name)
        try:
            cursor.execute(sql_select_query, (uniprot_id,))
            record = cursor.fetchone()
            return record
        except Exception as e:
            print("Unable to complete db call")
            print(e)

        
    @staticmethod
    def make_record_from_xml(id, root):

        full_name = root.find("recommendedName").find("fullName").text
        name = root.find("entry").find("name").text
        organism = root.find("organism").find("name", {"type": "scientific"}).text
        sequence = UniProtRecord.get_sequence_from_xml(root)
        xray_pdbs = UniProtRecord.get_xray_pdbs_from_xml(root)
        go_ids = UniProtRecord.get_go_ids_from_xml(root)
        return UniProtRecord(id, name, full_name, sequence, organism, xray_pdbs, go_ids)

    @staticmethod
    def get_sequence_from_xml(root):
        sequences = root.find_all("sequence")
        actual_sequences = []
        version_1_sequence = None
        for sequence in sequences:
            if sequence.get("length"):
                actual_sequences.append(sequence)
        if len(actual_sequences) > 1:
            for sequence in actual_sequences:
                if sequence.get("version") == "1":
                    version_1_sequence = sequence
                    break
        if version_1_sequence is None:
            version_1_sequence = actual_sequences[0]
        sequence = version_1_sequence.text
        return sequence

    @staticmethod
    def get_uniprot_xml(id):
        url = "https://rest.uniprot.org/uniprotkb/{}.xml".format(id)
        try: 
            response = requests.get(url, timeout=60)
            if response.status_code != requests.codes.ok:
                print("Bad request")
                response.raise_for_status()
            else:
                # print(response.text)
                # e = ET.ElementTree(ET.fromstring(xml_string))
                bs_data = BeautifulSoup(response.text, "xml")
                return bs_data
        except lxml.etree.XMLSyntaxError as pe:
            print("Unable to parse returned xml", pe)
        except requests.exceptions.HTTPError as he:
            print("Unable to get data from url", he)
        except ConnectionError as ce:
            print("Unable to connect on url", url)

    @staticmethod
    def get_go_ids_from_xml(root):

        # <dbReference type="GO" id="GO:0030425">
        # <property type="term" value="C:dendrite"/>
        # <property type="evidence" value="ECO:0000318"/>
        # <property type="project" value="GO_Central"/>
        # </dbReference>

        go_ids = []
        ids = root.find_all("dbReference", {"type":"GO"})
        for id in ids:
            term = id.find("property", {"type": "term"})
            go_ids.append((id.get("id"), term.get("value")))
        return go_ids

    @staticmethod
    def get_xray_pdbs_from_xml(root):

        xray_pds = []
        pdbs = root.find_all("dbReference", {"type":"PDB"})
        for pdb in pdbs:
            for child in pdb.contents:
                if "X-ray" in str(child):
                    xray_pds.append(pdb.get("id"))
                    break
        return xray_pds
    
    
    @staticmethod
    def setup_tables(cursor, database_name, uniprot_table_name, pdb_table_name, go_table_name):
        # Connecting to sqlite 
        print("Connecting to database: {}".format(database_name))
        
        # Creating table 
        try:
            # 3. Create an SQLite table with columns: UniProtID, name, recommendedName, organism. If the table or database already exists, keep going (do not have an error).
            uniprot_table ="""CREATE TABLE {}(uniprot_id VARCHAR(255), recommended_name VARCHAR(255), name VARCHAR(255), organism VARCHAR(255));""".format(uniprot_table_name)
            # 10. Print to the screen a statement when a table is created.
            print("Creating uniprot table: {}".format(uniprot_table_name))
            cursor.execute(uniprot_table) 
        
        except sqlite3.OperationalError:
            print("Table already exists")

        try:
            pdb_table = """CREATE TABLE {}(uniprot_id VARCHAR(255), pdb_id VARCHAR(255));""".format(pdb_table_name)
            print("Creating pdb table: {}".format(pdb_table_name))
            cursor.execute(pdb_table) 
        
        except sqlite3.OperationalError:
            print("Table already exists")

        try:
            go_table = """CREATE TABLE {}(uniprot_id VARCHAR(255), go_id VARCHAR(255), term VARCHAR(255));""".format(go_table_name)
            print("Creating go table: {}".format(go_table_name))
            cursor.execute(go_table)
        
        except sqlite3.OperationalError:
            print("Table already exists")


