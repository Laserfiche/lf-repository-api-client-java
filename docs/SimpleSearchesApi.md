# SimpleSearchesApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSimpleSearchOperation**](SimpleSearchesApi.md#createSimpleSearchOperation) | **POST** /v1/Repositories/{repoId}/SimpleSearches | 

<a name="createSimpleSearchOperation"></a>
# **createSimpleSearchOperation**
> ODataValueOfIListOfODataGetEntryChildren createSimpleSearchOperation(repoId, body, fields, formatFields, culture, $select, $orderby, $count)



- Runs a \&quot;simple\&quot; search operation on the repository. - Returns a truncated search result listing. - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SimpleSearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SimpleSearchesApi apiInstance = new SimpleSearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
SimpleSearchRequest body = new SimpleSearchRequest(); // SimpleSearchRequest | The Laserfiche search command to run.
List<String> fields = Arrays.asList("fields_example"); // List<String> | Optional array of field names. Field values corresponding to the given field names will be returned for each search result. 
Boolean formatFields = true; // Boolean | Boolean for if field values should be formatted. Only applicable if Fields are specified.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueOfIListOfODataGetEntryChildren result = apiInstance.createSimpleSearchOperation(repoId, body, fields, formatFields, culture, $select, $orderby, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SimpleSearchesApi#createSimpleSearchOperation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **body** | [**SimpleSearchRequest**](SimpleSearchRequest.md)| The Laserfiche search command to run. | [optional]
 **fields** | [**List&lt;String&gt;**](String.md)| Optional array of field names. Field values corresponding to the given field names will be returned for each search result.  | [optional]
 **formatFields** | **Boolean**| Boolean for if field values should be formatted. Only applicable if Fields are specified. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueOfIListOfODataGetEntryChildren**](ODataValueOfIListOfODataGetEntryChildren.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

