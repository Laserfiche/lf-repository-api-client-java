# TemplateDefinitionsApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTemplateDefinitionById**](TemplateDefinitionsApi.md#getTemplateDefinitionById) | **GET** /v1/Repositories/{repoId}/TemplateDefinitions/{templateId} | 
[**getTemplateDefinitions**](TemplateDefinitionsApi.md#getTemplateDefinitions) | **GET** /v1/Repositories/{repoId}/TemplateDefinitions | 
[**getTemplateFieldDefinitions**](TemplateDefinitionsApi.md#getTemplateFieldDefinitions) | **GET** /v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/fields | 
[**getTemplateFieldDefinitionsByTemplateName**](TemplateDefinitionsApi.md#getTemplateFieldDefinitionsByTemplateName) | **GET** /v1/Repositories/{repoId}/TemplateDefinitions/Fields | 

<a name="getTemplateDefinitionById"></a>
# **getTemplateDefinitionById**
> WTemplateInfo getTemplateDefinitionById(repoId, templateId, culture, $select)



- Returns a single template definition (including field definitions, if relevant). - Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed. - Allowed OData query options: Select

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TemplateDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TemplateDefinitionsApi apiInstance = new TemplateDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer templateId = 56; // Integer | The requested template definition ID.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
try {
    WTemplateInfo result = apiInstance.getTemplateDefinitionById(repoId, templateId, culture, $select);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplateDefinitionsApi#getTemplateDefinitionById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **templateId** | **Integer**| The requested template definition ID. |
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]

### Return type

[**WTemplateInfo**](WTemplateInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTemplateDefinitions"></a>
# **getTemplateDefinitions**
> ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitions(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned. - Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TemplateDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TemplateDefinitionsApi apiInstance = new TemplateDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String templateName = "templateName_example"; // String | An optional query parameter. Can be used to get a single template definition using the template name.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfWTemplateInfo result = apiInstance.getTemplateDefinitions(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplateDefinitionsApi#getTemplateDefinitions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **templateName** | **String**| An optional query parameter. Can be used to get a single template definition using the template name. | [optional]
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfWTemplateInfo**](ODataValueContextOfIListOfWTemplateInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTemplateFieldDefinitions"></a>
# **getTemplateFieldDefinitions**
> ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitions(repoId, templateId, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns the field definitions assigned to a template definition. - Provide a template definition ID, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TemplateDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TemplateDefinitionsApi apiInstance = new TemplateDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer templateId = 56; // Integer | The requested template definition ID.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfTemplateFieldInfo result = apiInstance.getTemplateFieldDefinitions(repoId, templateId, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplateDefinitionsApi#getTemplateFieldDefinitions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **templateId** | **Integer**| The requested template definition ID. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfTemplateFieldInfo**](ODataValueContextOfIListOfTemplateFieldInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTemplateFieldDefinitionsByTemplateName"></a>
# **getTemplateFieldDefinitionsByTemplateName**
> ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateName(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns the field definitions assigned to a template definition. - Provide a template definition name, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.TemplateDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TemplateDefinitionsApi apiInstance = new TemplateDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String templateName = "templateName_example"; // String | A required query parameter for the requested template name.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfTemplateFieldInfo result = apiInstance.getTemplateFieldDefinitionsByTemplateName(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TemplateDefinitionsApi#getTemplateFieldDefinitionsByTemplateName");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **templateName** | **String**| A required query parameter for the requested template name. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfTemplateFieldInfo**](ODataValueContextOfIListOfTemplateFieldInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

