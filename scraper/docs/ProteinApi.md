# openapi_client.ProteinApi

All URIs are relative to *https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_protein**](ProteinApi.md#create_protein) | **POST** /proteins | Enter a new protein into the database


# **create_protein**
> ProteinEntry create_protein(protein_entry)

Enter a new protein into the database

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
    api_instance = openapi_client.ProteinApi(api_client)
    protein_entry = openapi_client.ProteinEntry() # ProteinEntry | 

    try:
        # Enter a new protein into the database
        api_response = api_instance.create_protein(protein_entry)
        print("The response of ProteinApi->create_protein:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ProteinApi->create_protein: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **protein_entry** | [**ProteinEntry**](ProteinEntry.md)|  | 

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
**201** | Null response |  -  |
**0** | unexpected error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

