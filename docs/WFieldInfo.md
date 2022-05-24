# WFieldInfo

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | The name of the field. |  [optional]
**displayName** | **String** | The localized name of the field. |  [optional]
**id** | **Integer** | The ID of the field. |  [optional]
**description** | **String** | The description of the field. |  [optional]
**fieldType** | **OneOfWFieldInfoFieldType** | The type of the field. |  [optional]
**length** | **Integer** | The length of the field for variable length data types. |  [optional]
**defaultValue** | **String** | The default value of the field for new entries that are assigned to a template the represented field is a member of. |  [optional]
**isMultiValue** | **Boolean** | A boolean indicating if the represented template field supports multiple values. |  [optional]
**isRequired** | **Boolean** | A boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of. |  [optional]
**constraint** | **String** | The constraint for values stored in the represented field. |  [optional]
**constraintError** | **String** | The error string that will be returned when the field constraint is violated when setting a value for this field. |  [optional]
**listValues** | **List&lt;String&gt;** | The list of items assigned to the represented field. |  [optional]
**format** | **OneOfWFieldInfoFormat** | The display format of the represented field. |  [optional]
**currency** | **String** | The name of the currency that will be using when formatting the represented field when the Format property is set to the Currency member of the WFieldFormat enumeration. |  [optional]
**formatPattern** | **String** | The custom format pattern for fields that are configured to use a custom format. |  [optional]
