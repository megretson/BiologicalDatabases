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

class VersionEntry(object):
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
        'major_version': 'int',
        'minor_version': 'int',
        'revision_type': 'str',
        'revision_date': 'date'
    }

    attribute_map = {
        'major_version': 'major_version',
        'minor_version': 'minor_version',
        'revision_type': 'revision_type',
        'revision_date': 'revision_date'
    }

    def __init__(self, major_version=None, minor_version=None, revision_type=None, revision_date=None):  # noqa: E501
        """VersionEntry - a model defined in Swagger"""  # noqa: E501
        self._major_version = None
        self._minor_version = None
        self._revision_type = None
        self._revision_date = None
        self.discriminator = None
        if major_version is not None:
            self.major_version = major_version
        if minor_version is not None:
            self.minor_version = minor_version
        if revision_type is not None:
            self.revision_type = revision_type
        if revision_date is not None:
            self.revision_date = revision_date

    @property
    def major_version(self):
        """Gets the major_version of this VersionEntry.  # noqa: E501


        :return: The major_version of this VersionEntry.  # noqa: E501
        :rtype: int
        """
        return self._major_version

    @major_version.setter
    def major_version(self, major_version):
        """Sets the major_version of this VersionEntry.


        :param major_version: The major_version of this VersionEntry.  # noqa: E501
        :type: int
        """

        self._major_version = major_version

    @property
    def minor_version(self):
        """Gets the minor_version of this VersionEntry.  # noqa: E501


        :return: The minor_version of this VersionEntry.  # noqa: E501
        :rtype: int
        """
        return self._minor_version

    @minor_version.setter
    def minor_version(self, minor_version):
        """Sets the minor_version of this VersionEntry.


        :param minor_version: The minor_version of this VersionEntry.  # noqa: E501
        :type: int
        """

        self._minor_version = minor_version

    @property
    def revision_type(self):
        """Gets the revision_type of this VersionEntry.  # noqa: E501


        :return: The revision_type of this VersionEntry.  # noqa: E501
        :rtype: str
        """
        return self._revision_type

    @revision_type.setter
    def revision_type(self, revision_type):
        """Sets the revision_type of this VersionEntry.


        :param revision_type: The revision_type of this VersionEntry.  # noqa: E501
        :type: str
        """

        self._revision_type = revision_type

    @property
    def revision_date(self):
        """Gets the revision_date of this VersionEntry.  # noqa: E501


        :return: The revision_date of this VersionEntry.  # noqa: E501
        :rtype: date
        """
        return self._revision_date

    @revision_date.setter
    def revision_date(self, revision_date):
        """Sets the revision_date of this VersionEntry.


        :param revision_date: The revision_date of this VersionEntry.  # noqa: E501
        :type: date
        """

        self._revision_date = revision_date

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
        if issubclass(VersionEntry, dict):
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
        if not isinstance(other, VersionEntry):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
