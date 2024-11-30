# openapi_client.ProteinsApi

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
> Citation create_protein_citation(pdb_id, version_number, citation)

Enter a new protein citation

### Example


```python
import openapi_client
from openapi_client.models.citation import Citation
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    pdb_id = 'pdb_id_example' # str | the pdb id of interest
    version_number = 'version_number_example' # str | the version_number of interest
    citation = openapi_client.Citation() # Citation | 

    try:
        # Enter a new protein citation
        api_response = api_instance.create_protein_citation(pdb_id, version_number, citation)
        print("The response of ProteinsApi->create_protein_citation:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinsApi->create_protein_citation: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **version_number** | **str**| the version_number of interest | 
 **citation** | [**Citation**](Citation.md)|  | 

### Return type

[**Citation**](Citation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An new citation |  -  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_protein_version**
> ProteinEntry create_protein_version(pdb_id, version_entry)

Create a new version of this pdb_id

### Example


```python
import openapi_client
from openapi_client.models.protein_entry import ProteinEntry
from openapi_client.models.version_entry import VersionEntry
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    pdb_id = 'pdb_id_example' # str | the pdb id of interest
    version_entry = openapi_client.VersionEntry() # VersionEntry | 

    try:
        # Create a new version of this pdb_id
        api_response = api_instance.create_protein_version(pdb_id, version_entry)
        print("The response of ProteinsApi->create_protein_version:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinsApi->create_protein_version: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **version_entry** | [**VersionEntry**](VersionEntry.md)|  | 

### Return type

[**ProteinEntry**](ProteinEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An update protein entry with new version |  -  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein**
> ProteinEntry get_protein(pdb_id, limit=limit)

Get the  entry for this pdb_id

### Example


```python
import openapi_client
from openapi_client.models.protein_entry import ProteinEntry
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    pdb_id = 'pdb_id_example' # str | the pdb id of interest
    limit = 56 # int | How many items to return at one time (max 100) (optional)

    try:
        # Get the  entry for this pdb_id
        api_response = api_instance.get_protein(pdb_id, limit=limit)
        print("The response of ProteinsApi->get_protein:\n")
        pprint(api_response)
    except Exception as e:
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

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A paged array of proteins |  * x-next - A link to the next page of responses <br>  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein_citations**
> List[Citation] get_protein_citations(pdb_id, limit=limit)

Get the all citation entries for this pdb_id

### Example


```python
import openapi_client
from openapi_client.models.citation import Citation
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    pdb_id = 'pdb_id_example' # str | the pdb id of interest
    limit = 56 # int | How many items to return at one time (max 100) (optional)

    try:
        # Get the all citation entries for this pdb_id
        api_response = api_instance.get_protein_citations(pdb_id, limit=limit)
        print("The response of ProteinsApi->get_protein_citations:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinsApi->get_protein_citations: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**List[Citation]**](Citation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A paged array of protein citations |  * x-next - A link to the next page of responses <br>  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_protein_citations_by_version**
> List[Citation] get_protein_citations_by_version(pdb_id, version_number, limit=limit)

Get the all citation entries for this pdb_id version

### Example


```python
import openapi_client
from openapi_client.models.citation import Citation
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    pdb_id = 'pdb_id_example' # str | the pdb id of interest
    version_number = 'version_number_example' # str | the version_number of interest
    limit = 56 # int | How many items to return at one time (max 100) (optional)

    try:
        # Get the all citation entries for this pdb_id version
        api_response = api_instance.get_protein_citations_by_version(pdb_id, version_number, limit=limit)
        print("The response of ProteinsApi->get_protein_citations_by_version:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinsApi->get_protein_citations_by_version: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pdb_id** | **str**| the pdb id of interest | 
 **version_number** | **str**| the version_number of interest | 
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

[**List[Citation]**](Citation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A paged array of protein citations |  * x-next - A link to the next page of responses <br>  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_proteins**
> List[str] list_proteins(limit=limit)

List the pdb ids currently available in the database

### Example


```python
import openapi_client
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ProteinsApi(api_client)
    limit = 56 # int | How many items to return at one time (max 100) (optional)

    try:
        # List the pdb ids currently available in the database
        api_response = api_instance.list_proteins(limit=limit)
        print("The response of ProteinsApi->list_proteins:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinsApi->list_proteins: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **int**| How many items to return at one time (max 100) | [optional] 

### Return type

**List[str]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A paged array of proteins |  * x-next - A link to the next page of responses <br>  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

