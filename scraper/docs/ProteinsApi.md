# swagger_client.ProteinsApi

All URIs are relative to *https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_protein_citation**](ProteinsApi.md#create_protein_citation) | **POST** /proteins/{pdb_id}/versions/{version_number}/citations | Enter a new protein citation
[**create_protein_version**](ProteinsApi.md#create_protein_version) | **POST** /proteins/{pdb_id}/versions/ | Create a new version of this pdb_id
[**get_protein**](ProteinsApi.md#get_protein) | **GET** /proteins/{pdb_id} | Get the  entry for this pdb_id
[**get_protein_citations**](ProteinsApi.md#get_protein_citations) | **GET** /proteins/{pdb_id}/citations | Get the all citation entries for this pdb_id
[**get_protein_citations_by_version**](ProteinsApi.md#get_protein_citations_by_version) | **GET** /proteins/{pdb_id}/versions/{version_number}/citations | Get the all citation entries for this pdb_id version
[**list_proteins**](ProteinsApi.md#list_proteins) | **GET** /proteins | List the pdb ids currently available in the database

# **create_protein_citation**
> Citation create_protein_citation(pdb_id, version_number)

Enter a new protein citation

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
pdb_id = 'pdb_id_example' # str | the pdb id of interest
version_number = 'version_number_example' # str | the version_number of interest

try:
    # Enter a new protein citation
    api_response = api_instance.create_protein_citation(pdb_id, version_number)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->create_protein_citation: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **version_number** | **str**| the version_number of interest | 

### Return type

[**Citation**](Citation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_protein_version**
> ProteinEntry create_protein_version(pdb_id)

Create a new version of this pdb_id

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
pdb_id = 'pdb_id_example' # str | the pdb id of interest

try:
    # Create a new version of this pdb_id
    api_response = api_instance.create_protein_version(pdb_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->create_protein_version: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 

### Return type

[**ProteinEntry**](ProteinEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein**
> ProteinEntry get_protein(pdb_id, limit=limit)

Get the  entry for this pdb_id

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
pdb_id = 'pdb_id_example' # str | the pdb id of interest
limit = 56 # int | How many items to return at one time (max 100) (optional)

try:
    # Get the  entry for this pdb_id
    api_response = api_instance.get_protein(pdb_id, limit=limit)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->get_protein: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**ProteinEntry**](ProteinEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein_citations**
> Citations get_protein_citations(pdb_id, limit=limit)

Get the all citation entries for this pdb_id

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
pdb_id = 'pdb_id_example' # str | the pdb id of interest
limit = 56 # int | How many items to return at one time (max 100) (optional)

try:
    # Get the all citation entries for this pdb_id
    api_response = api_instance.get_protein_citations(pdb_id, limit=limit)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->get_protein_citations: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**Citations**](Citations.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein_citations_by_version**
> Citations get_protein_citations_by_version(pdb_id, version_number, limit=limit)

Get the all citation entries for this pdb_id version

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
pdb_id = 'pdb_id_example' # str | the pdb id of interest
version_number = 'version_number_example' # str | the version_number of interest
limit = 56 # int | How many items to return at one time (max 100) (optional)

try:
    # Get the all citation entries for this pdb_id version
    api_response = api_instance.get_protein_citations_by_version(pdb_id, version_number, limit=limit)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->get_protein_citations_by_version: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **version_number** | **str**| the version_number of interest | 
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**Citations**](Citations.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_proteins**
> PDBIds list_proteins(limit=limit)

List the pdb ids currently available in the database

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinsApi()
limit = 56 # int | How many items to return at one time (max 100) (optional)

try:
    # List the pdb ids currently available in the database
    api_response = api_instance.list_proteins(limit=limit)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinsApi->list_proteins: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**PDBIds**](PDBIds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

