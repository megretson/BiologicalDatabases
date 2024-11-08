# coding: utf-8

"""
    Protein Version Citation API 

    An API for looking at literature which cites a given protein version   # noqa: E501

    OpenAPI spec version: 1.0.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

import pprint
import re  # noqa: F401

import six

class Citation(object):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    """
    Attributes:
      swagger_types (dict): The key is attribute name
                            and the value is attribute type.
      attribute_map (dict): The key is attribute name
                            and the value is json key in definition.
    """
    swagger_types = {
        'issn': 'str',
        'doi': 'str',
        'title': 'str',
        'pmd_id': 'str',
        'referenced_protein_id': 'str',
        'referenced_protein_version': 'VersionEntry',
        'authors': 'list[Author]',
        'version_presumed': 'bool'
    }

    attribute_map = {
        'issn': 'issn',
        'doi': 'doi',
        'title': 'title',
        'pmd_id': 'pmd_id',
        'referenced_protein_id': 'referenced_protein_id',
        'referenced_protein_version': 'referenced_protein_version',
        'authors': 'authors',
        'version_presumed': 'version_presumed'
    }

    def __init__(self, issn=None, doi=None, title=None, pmd_id=None, referenced_protein_id=None, referenced_protein_version=None, authors=None, version_presumed=None):  # noqa: E501
        """Citation - a model defined in Swagger"""  # noqa: E501
        self._issn = None
        self._doi = None
        self._title = None
        self._pmd_id = None
        self._referenced_protein_id = None
        self._referenced_protein_version = None
        self._authors = None
        self._version_presumed = None
        self.discriminator = None
        if issn is not None:
            self.issn = issn
        if doi is not None:
            self.doi = doi
        if title is not None:
            self.title = title
        if pmd_id is not None:
            self.pmd_id = pmd_id
        if referenced_protein_id is not None:
            self.referenced_protein_id = referenced_protein_id
        if referenced_protein_version is not None:
            self.referenced_protein_version = referenced_protein_version
        if authors is not None:
            self.authors = authors
        if version_presumed is not None:
            self.version_presumed = version_presumed

    @property
    def issn(self):
        """Gets the issn of this Citation.  # noqa: E501


        :return: The issn of this Citation.  # noqa: E501
        :rtype: str
        """
        return self._issn

    @issn.setter
    def issn(self, issn):
        """Sets the issn of this Citation.


        :param issn: The issn of this Citation.  # noqa: E501
        :type: str
        """

        self._issn = issn

    @property
    def doi(self):
        """Gets the doi of this Citation.  # noqa: E501


        :return: The doi of this Citation.  # noqa: E501
        :rtype: str
        """
        return self._doi

    @doi.setter
    def doi(self, doi):
        """Sets the doi of this Citation.


        :param doi: The doi of this Citation.  # noqa: E501
        :type: str
        """

        self._doi = doi

    @property
    def title(self):
        """Gets the title of this Citation.  # noqa: E501


        :return: The title of this Citation.  # noqa: E501
        :rtype: str
        """
        return self._title

    @title.setter
    def title(self, title):
        """Sets the title of this Citation.


        :param title: The title of this Citation.  # noqa: E501
        :type: str
        """

        self._title = title

    @property
    def pmd_id(self):
        """Gets the pmd_id of this Citation.  # noqa: E501


        :return: The pmd_id of this Citation.  # noqa: E501
        :rtype: str
        """
        return self._pmd_id

    @pmd_id.setter
    def pmd_id(self, pmd_id):
        """Sets the pmd_id of this Citation.


        :param pmd_id: The pmd_id of this Citation.  # noqa: E501
        :type: str
        """

        self._pmd_id = pmd_id

    @property
    def referenced_protein_id(self):
        """Gets the referenced_protein_id of this Citation.  # noqa: E501


        :return: The referenced_protein_id of this Citation.  # noqa: E501
        :rtype: str
        """
        return self._referenced_protein_id

    @referenced_protein_id.setter
    def referenced_protein_id(self, referenced_protein_id):
        """Sets the referenced_protein_id of this Citation.


        :param referenced_protein_id: The referenced_protein_id of this Citation.  # noqa: E501
        :type: str
        """

        self._referenced_protein_id = referenced_protein_id

    @property
    def referenced_protein_version(self):
        """Gets the referenced_protein_version of this Citation.  # noqa: E501


        :return: The referenced_protein_version of this Citation.  # noqa: E501
        :rtype: VersionEntry
        """
        return self._referenced_protein_version

    @referenced_protein_version.setter
    def referenced_protein_version(self, referenced_protein_version):
        """Sets the referenced_protein_version of this Citation.


        :param referenced_protein_version: The referenced_protein_version of this Citation.  # noqa: E501
        :type: VersionEntry
        """

        self._referenced_protein_version = referenced_protein_version

    @property
    def authors(self):
        """Gets the authors of this Citation.  # noqa: E501


        :return: The authors of this Citation.  # noqa: E501
        :rtype: list[Author]
        """
        return self._authors

    @authors.setter
    def authors(self, authors):
        """Sets the authors of this Citation.


        :param authors: The authors of this Citation.  # noqa: E501
        :type: list[Author]
        """

        self._authors = authors

    @property
    def version_presumed(self):
        """Gets the version_presumed of this Citation.  # noqa: E501


        :return: The version_presumed of this Citation.  # noqa: E501
        :rtype: bool
        """
        return self._version_presumed

    @version_presumed.setter
    def version_presumed(self, version_presumed):
        """Sets the version_presumed of this Citation.


        :param version_presumed: The version_presumed of this Citation.  # noqa: E501
        :type: bool
        """

        self._version_presumed = version_presumed

    def to_dict(self):
        """Returns the model properties as a dict"""
        result = {}

        for attr, _ in six.iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value
        if issubclass(Citation, dict):
            for key, value in self.items():
                result[key] = value

        return result

    def to_str(self):
        """Returns the string representation of the model"""
        return pprint.pformat(self.to_dict())

    def __repr__(self):
        """For `print` and `pprint`"""
        return self.to_str()

    def __eq__(self, other):
        """Returns true if both objects are equal"""
        if not isinstance(other, Citation):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
