# swagger_client.CitationApi

All URIs are relative to *https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_proteins_cited**](CitationApi.md#get_proteins_cited) | **GET** /citations/{issn} | Get all proteins cited by this article

# **get_proteins_cited**
> ProteinEntry get_proteins_cited(issn, limit=limit)

Get all proteins cited by this article

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.CitationApi()
issn = 'issn_example' # str | the issn of interest
limit = 56 # int | How many items to return at one time (max 100) (optional)

try:
    # Get all proteins cited by this article
    api_response = api_instance.get_proteins_cited(issn, limit=limit)
    pprint(api_response)
except ApiException as e:
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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

