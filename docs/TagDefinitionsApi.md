# TagDefinitionsApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTagDefinitionById**](TagDefinitionsApi.md#getTagDefinitionById) | **GET** v2-alpha/Repositories/{repoId}/TagDefinitions/{tagId} | 
[**getTagDefinitions**](TagDefinitionsApi.md#getTagDefinitions) | **GET** v2-alpha/Repositories/{repoId}/TagDefinitions | 

<a name="getTagDefinitionById"></a>
# **getTagDefinitionById**
> WTagInfo getTagDefinitionById(repoId, tagId, culture, $select)



- Returns a single tag definition. - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed. - Allowed OData query options: Select

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.TagDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TagDefinitionsApi apiInstance = new TagDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer tagId = 56; // Integer | The requested tag definition ID.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
try {
    WTagInfo result = apiInstance.getTagDefinitionById(repoId, tagId, culture, $select);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TagDefinitionsApi#getTagDefinitionById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **tagId** | **Integer**| The requested tag definition ID. |
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]

### Return type

[**WTagInfo**](WTagInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTagDefinitions"></a>
# **getTagDefinitions**
> ODataValueContextOfIListOfWTagInfo getTagDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns all tag definitions in the repository. - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.TagDefinitionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TagDefinitionsApi apiInstance = new TagDefinitionsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfWTagInfo result = apiInstance.getTagDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TagDefinitionsApi#getTagDefinitions");
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

[**ODataValueContextOfIListOfWTagInfo**](ODataValueContextOfIListOfWTagInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

