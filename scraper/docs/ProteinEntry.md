# ProteinEntry


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**pdb_id** | **int** |  | [optional] 
**versions** | [**List[VersionEntry]**](VersionEntry.md) |  | 

## Example

```python
from openapi_client.models.protein_entry import ProteinEntry

# TODO update the JSON string below
json = "{}"
# create an instance of ProteinEntry from a JSON string
protein_entry_instance = ProteinEntry.from_json(json)
# print the JSON string representation of the object
print(ProteinEntry.to_json())

# convert the object into a dict
protein_entry_dict = protein_entry_instance.to_dict()
# create an instance of ProteinEntry from a dict
protein_entry_from_dict = ProteinEntry.from_dict(protein_entry_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


