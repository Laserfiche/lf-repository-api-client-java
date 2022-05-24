# FieldDefinitionsApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getFieldDefinitionById**](FieldDefinitionsApi.md#getFieldDefinitionById) | **GET** /v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId} | 
[**getFieldDefinitions**](FieldDefinitionsApi.md#getFieldDefinitions) | **GET** /v1/Repositories/{repoId}/FieldDefinitions | 

<a name="getFieldDefinitionById"></a>
# **getFieldDefinitionById**
> WFieldInfo getFieldDefinitionById(repoId, fieldDefinitionId, culture, $select)



- Returns a single field definition associated with the specified ID.  - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed. - Allowed OData query options: Select

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FieldDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


FieldDefinitionsApi apiInstance = new FieldDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer fieldDefinitionId = 56; // Integer | The requested field definition ID.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
try {
    WFieldInfo result = apiInstance.getFieldDefinitionById(repoId, fieldDefinitionId, culture, $select);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FieldDefinitionsApi#getFieldDefinitionById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **fieldDefinitionId** | **Integer**| The requested field definition ID. |
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]

### Return type

[**WFieldInfo**](WFieldInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getFieldDefinitions"></a>
# **getFieldDefinitions**
> ODataValueContextOfIListOfWFieldInfo getFieldDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.FieldDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


FieldDefinitionsApi apiInstance = new FieldDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfWFieldInfo result = apiInstance.getFieldDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FieldDefinitionsApi#getFieldDefinitions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfWFieldInfo**](ODataValueContextOfIListOfWFieldInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

