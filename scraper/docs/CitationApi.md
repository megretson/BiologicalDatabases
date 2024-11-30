# openapi_client.CitationApi

All URIs are relative to *https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_proteins_cited**](CitationApi.md#get_proteins_cited) | **GET** /citations/{issn} | Get all proteins cited by this article


# **get_proteins_cited**
> ProteinEntry get_proteins_cited(issn, limit=limit)

Get all proteins cited by this article

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
    api_instance = openapi_client.CitationApi(api_client)
    issn = 'issn_example' # str | the issn of interest
    limit = 56 # int | How many items to return at one time (max 100) (optional)

    try:
        # Get all proteins cited by this article
        api_response = api_instance.get_proteins_cited(issn, limit=limit)
        print("The response of CitationApi->get_proteins_cited:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling CitationApi->get_proteins_cited: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **issn** | **str**| the issn of interest | 
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

