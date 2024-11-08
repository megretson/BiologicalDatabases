# swagger_client.ProteinApi

All URIs are relative to *https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_protein**](ProteinApi.md#create_protein) | **POST** /proteins | Enter a new protein into the database

# **create_protein**
> ProteinEntry create_protein(body)

Enter a new protein into the database

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.ProteinApi()
body = swagger_client.ProteinEntry() # ProteinEntry | 

try:
    # Enter a new protein into the database
    api_response = api_instance.create_protein(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ProteinApi->create_protein: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ProteinEntry**](ProteinEntry.md)|  | 

### Return type

[**ProteinEntry**](ProteinEntry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

