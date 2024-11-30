# Citation


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**issn** | **str** |  | [optional] 
**doi** | **str** |  | [optional] 
**title** | **str** |  | [optional] 
**pmd_id** | **str** |  | [optional] 
**referenced_protein_id** | **str** |  | [optional] 
**referenced_protein_version** | [**VersionEntry**](VersionEntry.md) |  | [optional] 
**authors** | [**List[Author]**](Author.md) |  | [optional] 
**version_presumed** | **bool** |  | [optional] 
**publisher** | **str** |  | [optional] 
**publisher_url** | **str** |  | [optional] 
**publisher_email** | **str** |  | [optional] 

## Example

```python
from openapi_client.models.citation import Citation

# TODO update the JSON string below
json = "{}"
# create an instance of Citation from a JSON string
citation_instance = Citation.from_json(json)
# print the JSON string representation of the object
print(Citation.to_json())

# convert the object into a dict
citation_dict = citation_instance.to_dict()
# create an instance of Citation from a dict
citation_from_dict = Citation.from_dict(citation_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


