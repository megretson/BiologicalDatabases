import requests

def query_crossref(query, rows=10, email="your_email@example.com"):
    url = "https://api.crossref.org/works"
    params = {
        'query': query,
        'rows': rows
    }
    
    headers = {
        'User-Agent': f"YourAppName/1.0 (mailto:{email})"
    }

    response = requests.get(url, params=params, headers=headers)
    if response.status_code != 200:
        print(f"Failed to retrieve data: {response.status_code}")
        return []

    data = response.json()
    results = []

    # Parse each item in the results
    for item in data.get('message', {}).get('items', []):
        # Extract metadata
        title = item.get('title', [None])[0]  # Titles are in a list
        doi = item.get('DOI', None)
        issn = item.get('ISSN', [None])[0] if 'ISSN' in item else None
        pubmed_id = item.get('PMID', None)  # CrossRef does not always provide PubMed IDs

        result_data = {
            'title': title,
            'doi': doi,
            'issn': issn,
            'pmid': pubmed_id
        }
        results.append(result_data)

    return results

# Example usage
query = "machine learning in healthcare"
results = query_crossref(query)
for result in results:
    print(result)
