import requests
import datetime
from dateutil import parser
from bs4 import BeautifulSoup


LIMIT_CITATIONS = True


class CrossRefRecord:

    def __init__(self, title, doi, issn, pubmed_id, pdb_id, protein_common_name, created_date, publisher, publisher_url) -> None:
        self.pdb_id = pdb_id
        self.title = title
        self.doi = doi
        self.issn = issn
        self.pubmed_id = pubmed_id
        self.created_date = created_date
        self.protein_common_name = protein_common_name
        self.publisher = publisher
        self.publisher_url = publisher_url
        self.publisher_contact = self.attempt_to_find_publisher_contact()

    def __str__(self):
        date = self.created_date.strftime('%Y-%m-%d')
        return "Title: {}, DOI: {}, Publication: {}, PDB Id: {}".format(self.title, self.doi, date, self.pdb_id)

    def __repr__(self):
        return self.__str__()
    
    def attempt_to_find_publisher_contact(self):
        if self.publisher_url != None:
            try:
                print(f"Attempting to scrape contact info from {self.publisher_url}")
                response = requests.get(self.publisher_url, timeout=10)
                if response.status_code != 200:
                    return None
                
                soup = BeautifulSoup(response.text, 'html.parser')
                
                # Look for contact information (email addresses, phone numbers, etc.)
                contact_email = None
                for a_tag in soup.find_all('a', href=True):
                    if "mailto:" in a_tag['href']:
                        contact_email = a_tag['href'].replace("mailto:", "")
                        print(f"Found publisher contact email {contact_email}")
                        break
                
                return contact_email
            except Exception as e:
                print(f"Error scraping contact info from {self.publisher_url}: {e}")
                return None

    @staticmethod
    def query_crossref(pdb_id, protein_common_name, rows=10, email="your_email@example.com"):
        url = "https://api.crossref.org/works"
        params = {
            'query.title': pdb_id,
            'rows': rows
        }

        headers = {
            'User-Agent': f"YourAppName/1.0 (mailto:{email})"
        }

        try:
            id_response = requests.get(url, params=params, headers=headers)

            if id_response.status_code != 200:
                print(f"Failed to retrieve data from pdb id: {id_response.status_code}")
                return
        except Exception as e:
            print(f"Error scraping contact info from {url}: {e}")
            return None

        pdb_id_data = id_response.json()

        params = {
            'query.title': protein_common_name,
            'rows': rows
        }
        try:
            name_response = requests.get(url, params=params, headers=headers)
            if name_response.status_code != 200:
                print(
                    f"Failed to retrieve data from common name: {name_response.status_code}")
        except Exception as e:
            print(f"Error scraping contact info from {url}: {e}")
            return None

        protein_common_name_data = name_response.json()
        results = []

        common_name_results = protein_common_name_data.get('message', {}).get('items', [])
        id_results = pdb_id_data.get('message', {}).get('items', [])

        if LIMIT_CITATIONS:
            common_name_results = common_name_results[:5]
            id_results = id_results[:5]

        # Parse each item in the results
        for item in common_name_results:
            CrossRefRecord.create_record_from_crossref_response(
                pdb_id, protein_common_name, results, item)
        for item in id_results:
            CrossRefRecord.create_record_from_crossref_response(
                pdb_id, protein_common_name, results, item)
        return results

    @staticmethod
    def create_record_from_crossref_response(pdb_id, protein_common_name, results, item):
        created_date = parser.parse(
            item.get('created', None).get("date-time", None))
        title = item.get('title', [None])[0]  # Titles are in a list
        doi = item.get('DOI', None)
        issn = item.get('ISSN', [None])[0] if 'ISSN' in item else None
        # CrossRef does not always provide PubMed IDs
        pubmed_id = item.get('PMID', None)
        publisher = item.get('publisher', None)
        publisher_url = item.get('URL', None)  # Sometimes includes the publisher's page
        print(f"Found citation {doi}")
        record = CrossRefRecord(title, doi, issn, pubmed_id,
                       pdb_id, protein_common_name, created_date, publisher, publisher_url)
        results.append(record)
    


