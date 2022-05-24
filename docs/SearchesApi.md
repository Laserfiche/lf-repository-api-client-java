# SearchesApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelOrCloseSearch**](SearchesApi.md#cancelOrCloseSearch) | **DELETE** /v1/Repositories/{repoId}/Searches/{searchToken} | Cancel or close an advanced search.
[**createSearchOperation**](SearchesApi.md#createSearchOperation) | **POST** /v1/Repositories/{repoId}/Searches | Run a search in the specified repository.
[**getSearchContextHits**](SearchesApi.md#getSearchContextHits) | **GET** /v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits | 
[**getSearchResults**](SearchesApi.md#getSearchResults) | **GET** /v1/Repositories/{repoId}/Searches/{searchToken}/Results | Get the search results listing of a search.
[**getSearchStatus**](SearchesApi.md#getSearchStatus) | **GET** /v1/Repositories/{repoId}/Searches/{searchToken} | Get the status of a search using a token.

<a name="cancelOrCloseSearch"></a>
# **cancelOrCloseSearch**
> ODataValueOfBoolean cancelOrCloseSearch(repoId, searchToken)

Cancel or close an advanced search.

- Cancels a currently running search. - Closes a completed search.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SearchesApi apiInstance = new SearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String searchToken = "searchToken_example"; // String | The requested searchToken.
try {
    ODataValueOfBoolean result = apiInstance.cancelOrCloseSearch(repoId, searchToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchesApi#cancelOrCloseSearch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **searchToken** | **String**| The requested searchToken. |

### Return type

[**ODataValueOfBoolean**](ODataValueOfBoolean.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="createSearchOperation"></a>
# **createSearchOperation**
> AcceptedOperation createSearchOperation(repoId, body)

Run a search in the specified repository.

- Runs a search operation on the repository. - Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage). The status for search operations must be checked via the Search specific status checking route.         

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SearchesApi apiInstance = new SearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
AdvancedSearchRequest body = new AdvancedSearchRequest(); // AdvancedSearchRequest | The Laserfiche search command to run, optionally include fuzzy search settings.
try {
    AcceptedOperation result = apiInstance.createSearchOperation(repoId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchesApi#createSearchOperation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **body** | [**AdvancedSearchRequest**](AdvancedSearchRequest.md)| The Laserfiche search command to run, optionally include fuzzy search settings. | [optional]

### Return type

[**AcceptedOperation**](AcceptedOperation.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getSearchContextHits"></a>
# **getSearchContextHits**
> ODataValueContextOfIListOfContextHit getSearchContextHits(repoId, searchToken, rowNumber, prefer, $select, $orderby, $top, $skip, $count)



- Returns the context hits associated with a search result entry. - Given a searchToken, and rowNumber associated with a search entry in the listing, return the context hits for that entry. - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SearchesApi apiInstance = new SearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String searchToken = "searchToken_example"; // String | The requested searchToken.
Integer rowNumber = 56; // Integer | The search result listing row number to get context hits for.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfContextHit result = apiInstance.getSearchContextHits(repoId, searchToken, rowNumber, prefer, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchesApi#getSearchContextHits");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **searchToken** | **String**| The requested searchToken. |
 **rowNumber** | **Integer**| The search result listing row number to get context hits for. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfContextHit**](ODataValueContextOfIListOfContextHit.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getSearchResults"></a>
# **getSearchResults**
> ODataValueContextOfIListOfODataGetSearchResults getSearchResults(repoId, searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count)

Get the search results listing of a search.

- Returns a search result listing if the search is completed. - Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type. - Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values. - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. sort order can be either \&quot;asc\&quot; or \&quot;desc\&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving the results again. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SearchesApi apiInstance = new SearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String searchToken = "searchToken_example"; // String | The requested searchToken.
Boolean groupByEntryType = true; // Boolean | An optional query parameter used to indicate if the result should be grouped by entry type or not.
Boolean refresh = true; // Boolean | If the search listing should be refreshed to show updated values.
List<String> fields = Arrays.asList("fields_example"); // List<String> | Optional array of field names. Field values corresponding to the given field names will be returned for each search result. 
Boolean formatFields = true; // Boolean | Boolean for if field values should be formatted. Only applicable if Fields are specified.
String prefer = "prefer_example"; // String | An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfODataGetSearchResults result = apiInstance.getSearchResults(repoId, searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchesApi#getSearchResults");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **searchToken** | **String**| The requested searchToken. |
 **groupByEntryType** | **Boolean**| An optional query parameter used to indicate if the result should be grouped by entry type or not. | [optional]
 **refresh** | **Boolean**| If the search listing should be refreshed to show updated values. | [optional]
 **fields** | [**List&lt;String&gt;**](String.md)| Optional array of field names. Field values corresponding to the given field names will be returned for each search result.  | [optional]
 **formatFields** | **Boolean**| Boolean for if field values should be formatted. Only applicable if Fields are specified. | [optional]
 **prefer** | **String**| An optional odata header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfODataGetSearchResults**](ODataValueContextOfIListOfODataGetSearchResults.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getSearchStatus"></a>
# **getSearchStatus**
> OperationProgress getSearchStatus(repoId, searchToken)

Get the status of a search using a token.

- Returns search status. - Provide a token (returned in the create search asynchronous route), and get the search status, progress, and any errors that may have occurred. When the search is completed, the Location header can be inspected as a link to the search results. - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or Canceled.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.SearchesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


SearchesApi apiInstance = new SearchesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
String searchToken = "searchToken_example"; // String | The requested searchToken.
try {
    OperationProgress result = apiInstance.getSearchStatus(repoId, searchToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchesApi#getSearchStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **searchToken** | **String**| The requested searchToken. |

### Return type

[**OperationProgress**](OperationProgress.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

