# Entry

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Integer** | The ID of the entry. |  [optional]
**name** | **String** | The name of the entry. |  [optional]
**parentId** | **Integer** | The ID of the parent entry. |  [optional]
**fullPath** | **String** | The full path in the Laserfiche repository to the entry. |  [optional]
**folderPath** | **String** | The path in the Laserfiche repository to the parent folder. |  [optional]
**creator** | **String** | The name of the user that created this entry. |  [optional]
**creationTime** | [**OffsetDateTime**](OffsetDateTime.md) | The creation time of the entry. |  [optional]
**lastModifiedTime** | [**OffsetDateTime**](OffsetDateTime.md) | The last modification time of the entry. |  [optional]
**entryType** | **OneOfEntryEntryType** | The type of the entry. |  [optional]
**isContainer** | **Boolean** | A boolean indicating if this entry is a container object; it can have other entries as children. |  [optional]
**isLeaf** | **Boolean** | A boolean indicating if this entry is a leaf object; it cannot have other entries as children. |  [optional]
**templateName** | **String** | The name of the template assigned to this entry. |  [optional]
**templateId** | **Integer** | The id of the template assigned to this entry. |  [optional]
**templateFieldNames** | **List&lt;String&gt;** | The names of the fields assigned to the template assigned to this entry. |  [optional]
**volumeName** | **String** | The name of the volume that this entry is associated with. |  [optional]
**rowNumber** | **Integer** | Row number assigned to this entry in the listing. |  [optional]
**fields** | [**List&lt;EntryFieldValue&gt;**](EntryFieldValue.md) | The fields assigned to this entry. |  [optional]
