# Pet


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **int** |  | 
**name** | **str** |  | 
**tag** | **str** |  | [optional] 

## Example

```python
from openapi_client.models.pet import Pet

# TODO update the JSON string below
json = "{}"
# create an instance of Pet from a JSON string
pet_instance = Pet.from_json(json)
# print the JSON string representation of the object
print(Pet.to_json())

# convert the object into a dict
pet_dict = pet_instance.to_dict()
# create an instance of Pet from a dict
pet_from_dict = Pet.from_dict(pet_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


