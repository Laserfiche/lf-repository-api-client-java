# AttributesApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTrusteeAttributeKeyValuePairs**](AttributesApi.md#getTrusteeAttributeKeyValuePairs) | **GET** v2-alpha/Repositories/{repoId}/Attributes | Get the attribute key value pairs associated with the authenticated user.
[**getTrusteeAttributeValueByKey**](AttributesApi.md#getTrusteeAttributeValueByKey) | **GET** v2-alpha/Repositories/{repoId}/Attributes/{attributeKey} | Get an attribute object by key associated with the authenticated user.

<a name="getTrusteeAttributeKeyValuePairs"></a>
# **getTrusteeAttributeKeyValuePairs**
> ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, $select, $orderby, $top, $skip, $count)

Get the attribute key value pairs associated with the authenticated user.

- Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the \&quot;Everyone\&quot; group. - Attribute keys can be used with subsequent calls to get specific attribute values. - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the \&quot;Everyone\&quot; group. Note when this is true, the response does not include both the \&quot;Everyone\&quot; groups attribute and the currently authenticated user, but only the \&quot;Everyone\&quot; groups.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.AttributesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


AttributesApi apiInstance = new AttributesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Boolean everyone = true; // Boolean | Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfListOfAttribute result = apiInstance.getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AttributesApi#getTrusteeAttributeKeyValuePairs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **everyone** | **Boolean**| Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user. | [optional]
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfListOfAttribute**](ODataValueContextOfListOfAttribute.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTrusteeAttributeValueByKey"></a>
# **getTrusteeAttributeValueByKey**
> Attribute getTrusteeAttributeValueByKey(repoId, attributeKey, everyone)

Get an attribute object by key associated with the authenticated user.

- Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within \&quot;Everyone\&quot; group. - Optional query parameters: everyone (bool, default false). When true, the server only searches for the attribute value with the given key upon the authenticated users attributes. If false, only the authenticated users attributes will be queried.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.AttributesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


AttributesApi apiInstance = new AttributesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String attributeKey = "attributeKey_example"; // String | The requested attribute key.
Boolean everyone = true; // Boolean | Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
try {
    Attribute result = apiInstance.getTrusteeAttributeValueByKey(repoId, attributeKey, everyone);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AttributesApi#getTrusteeAttributeValueByKey");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **attributeKey** | **String**| The requested attribute key. |
 **everyone** | **Boolean**| Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user. | [optional]

### Return type

[**Attribute**](Attribute.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

