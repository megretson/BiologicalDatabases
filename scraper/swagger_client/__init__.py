# coding: utf-8

# flake8: noqa

"""
    Protein Version Citation API 

    An API for looking at literature which cites a given protein version   # noqa: E501

    OpenAPI spec version: 1.0.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

from __future__ import absolute_import

# import apis into sdk package
from swagger_client.api.citation_api import CitationApi
from swagger_client.api.protein_api import ProteinApi
from swagger_client.api.proteins_api import ProteinsApi
# import ApiClient
from swagger_client.api_client import ApiClient
from swagger_client.configuration import Configuration
# import models into sdk package
from swagger_client.models.author import Author
from swagger_client.models.citation import Citation
from swagger_client.models.citations import Citations
from swagger_client.models.contact_information import ContactInformation
from swagger_client.models.error import Error
from swagger_client.models.pdbid import PDBId
from swagger_client.models.pdb_ids import PDBIds
from swagger_client.models.pet import Pet
from swagger_client.models.pets import Pets
from swagger_client.models.protein_entry import ProteinEntry
from swagger_client.models.version_entry import VersionEntry
