# coding: utf-8

"""
    Protein Version Citation API 

    An API for looking at literature which cites a given protein version 

    The version of the OpenAPI document: 1.0.0
    Generated by OpenAPI Generator (https://openapi-generator.tech)

    Do not edit the class manually.
"""  # noqa: E501


from __future__ import annotations
import pprint
import re  # noqa: F401
import json

from pydantic import BaseModel, ConfigDict, StrictBool, StrictStr
from typing import Any, ClassVar, Dict, List, Optional
from openapi_client.models.author import Author
from openapi_client.models.version_entry import VersionEntry
from openapi_client.models.protein_entry import ProteinEntry
from typing import Optional, Set
from typing_extensions import Self

class Citation(BaseModel):
    """
    Citation
    """ # noqa: E501
    issn: Optional[StrictStr] = None
    doi: Optional[StrictStr] = None
    title: Optional[StrictStr] = None
    pmd_id: Optional[StrictStr] = None
    referenced_protein_id: Optional[StrictStr] = None
    referenced_protein_version: Optional[VersionEntry] = None
    authors: Optional[List[Author]] = None
    version_presumed: Optional[StrictBool] = None
    publisher: Optional[StrictStr] = None
    publisher_url: Optional[StrictStr] = None
    publisher_email: Optional[StrictStr] = None
    __properties: ClassVar[List[str]] = ["issn", "doi", "title", "pmd_id", "referenced_protein_id", "referenced_protein_version", "authors", "version_presumed", "publisher", "publisher_url", "publisher_email"]

    model_config = ConfigDict(
        populate_by_name=True,
        validate_assignment=True,
        protected_namespaces=(),
    )


    def to_str(self) -> str:
        """Returns the string representation of the model using alias"""
        return pprint.pformat(self.model_dump(by_alias=True))

    def to_json(self) -> str:
        """Returns the JSON representation of the model using alias"""
        # TODO: pydantic v2: use .model_dump_json(by_alias=True, exclude_unset=True) instead
        return json.dumps(self.to_dict())

    @classmethod
    def from_json(cls, json_str: str) -> Optional[Self]:
        """Create an instance of Citation from a JSON string"""
        return cls.from_dict(json.loads(json_str))

    def to_dict(self) -> Dict[str, Any]:
        """Return the dictionary representation of the model using alias.

        This has the following differences from calling pydantic's
        `self.model_dump(by_alias=True)`:

        * `None` is only added to the output dict for nullable fields that
          were set at model initialization. Other fields with value `None`
          are ignored.
        """
        excluded_fields: Set[str] = set([
        ])

        _dict = self.model_dump(
            by_alias=True,
            exclude=excluded_fields,
            exclude_none=True,
        )
        # override the default output from pydantic by calling `to_dict()` of referenced_protein_version
        if self.referenced_protein_version:
            _dict['referenced_protein_version'] = self.referenced_protein_version.to_dict()
        # override the default output from pydantic by calling `to_dict()` of each item in authors (list)
        _items = []
        if self.authors:
            for _item_authors in self.authors:
                if _item_authors:
                    _items.append(_item_authors.to_dict())
            _dict['authors'] = _items
        return _dict

    @classmethod
    def from_dict(cls, obj: Optional[Dict[str, Any]]) -> Optional[Self]:
        """Create an instance of Citation from a dict"""
        if obj is None:
            return None

        if not isinstance(obj, dict):
            return cls.model_validate(obj)

        _obj = cls.model_validate({
            "issn": obj.get("issn"),
            "doi": obj.get("doi"),
            "title": obj.get("title"),
            "pmd_id": obj.get("pmd_id"),
            "referenced_protein_id": ProteinEntry.from_dict(obj.get("referenced_protein_id")).pdb_id if obj.get("referenced_protein_id") is not None else None,
            "referenced_protein_version": VersionEntry.from_dict(obj["referenced_protein_version"]) if obj.get("referenced_protein_version") is not None else None,
            "authors": [Author.from_dict(_item) for _item in obj["authors"]] if obj.get("authors") is not None else None,
            "version_presumed": obj.get("version_presumed"),
            "publisher": obj.get("publisher"),
            "publisher_url": obj.get("publisher_url"),
            "publisher_email": obj.get("publisher_email")
        })
        return _obj


