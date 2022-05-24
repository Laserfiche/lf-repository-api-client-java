# EntriesApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**assignEntryLinks**](EntriesApi.md#assignEntryLinks) | **PUT** /v1/Repositories/{repoId}/Entries/{entryId}/links | 
[**assignFieldValues**](EntriesApi.md#assignFieldValues) | **PUT** /v1/Repositories/{repoId}/Entries/{entryId}/fields | 
[**assignTags**](EntriesApi.md#assignTags) | **PUT** /v1/Repositories/{repoId}/Entries/{entryId}/tags | 
[**copyEntryAsync**](EntriesApi.md#copyEntryAsync) | **POST** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync | 
[**createOrCopyEntry**](EntriesApi.md#createOrCopyEntry) | **POST** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children | 
[**deleteAssignedTemplate**](EntriesApi.md#deleteAssignedTemplate) | **DELETE** /v1/Repositories/{repoId}/Entries/{entryId}/template | 
[**deleteDocument**](EntriesApi.md#deleteDocument) | **DELETE** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc | 
[**deleteEntryInfo**](EntriesApi.md#deleteEntryInfo) | **DELETE** /v1/Repositories/{repoId}/Entries/{entryId} | 
[**deletePages**](EntriesApi.md#deletePages) | **DELETE** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages | 
[**exportDocument**](EntriesApi.md#exportDocument) | **GET** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc | 
[**exportDocumentWithAuditReason**](EntriesApi.md#exportDocumentWithAuditReason) | **POST** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason | 
[**getDocumentContentType**](EntriesApi.md#getDocumentContentType) | **HEAD** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc | 
[**getDynamicFieldValues**](EntriesApi.md#getDynamicFieldValues) | **POST** /v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue | 
[**getEntry**](EntriesApi.md#getEntry) | **GET** /v1/Repositories/{repoId}/Entries/{entryId} | 
[**getEntryListing**](EntriesApi.md#getEntryListing) | **GET** /v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children | 
[**getFieldValues**](EntriesApi.md#getFieldValues) | **GET** /v1/Repositories/{repoId}/Entries/{entryId}/fields | 
[**getLinkValuesFromEntry**](EntriesApi.md#getLinkValuesFromEntry) | **GET** /v1/Repositories/{repoId}/Entries/{entryId}/links | 
[**getTagsAssignedToEntry**](EntriesApi.md#getTagsAssignedToEntry) | **GET** /v1/Repositories/{repoId}/Entries/{entryId}/tags | 
[**importDocument**](EntriesApi.md#importDocument) | **POST** /v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName} | 
[**moveOrRenameDocument**](EntriesApi.md#moveOrRenameDocument) | **PATCH** /v1/Repositories/{repoId}/Entries/{entryId} | 
[**writeTemplateValueToEntry**](EntriesApi.md#writeTemplateValueToEntry) | **PUT** /v1/Repositories/{repoId}/Entries/{entryId}/template | 

<a name="assignEntryLinks"></a>
# **assignEntryLinks**
> ODataValueOfIListOfWEntryLinkInfo assignEntryLinks(repoId, entryId, body)



- Assign links to an entry. - Provide an entry ID and a list of links to assign to that entry. - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The request repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
List<PutLinksRequest> body = Arrays.asList(new PutLinksRequest()); // List<PutLinksRequest> | 
try {
    ODataValueOfIListOfWEntryLinkInfo result = apiInstance.assignEntryLinks(repoId, entryId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#assignEntryLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The request repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **body** | [**List&lt;PutLinksRequest&gt;**](PutLinksRequest.md)|  | [optional]

### Return type

[**ODataValueOfIListOfWEntryLinkInfo**](ODataValueOfIListOfWEntryLinkInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="assignFieldValues"></a>
# **assignFieldValues**
> ODataValueOfIListOfFieldValue assignFieldValues(repoId, entryId, body, culture)



- Update field values assigned to an entry. - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.  - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The entry ID of the entry that will have its fields updated.
Map<String, FieldToUpdate> body = new Map(); // Map<String, FieldToUpdate> | 
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    ODataValueOfIListOfFieldValue result = apiInstance.assignFieldValues(repoId, entryId, body, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#assignFieldValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The entry ID of the entry that will have its fields updated. |
 **body** | [**Map&lt;String, FieldToUpdate&gt;**](Map.md)|  | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**ODataValueOfIListOfFieldValue**](ODataValueOfIListOfFieldValue.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="assignTags"></a>
# **assignTags**
> ODataValueOfIListOfWTagInfo assignTags(repoId, entryId, body)



- Assign tags to an entry. - Provide an entry ID and a list of tags to assign to that entry. - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
PutTagRequest body = new PutTagRequest(); // PutTagRequest | The tags to add.
try {
    ODataValueOfIListOfWTagInfo result = apiInstance.assignTags(repoId, entryId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#assignTags");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **body** | [**PutTagRequest**](PutTagRequest.md)| The tags to add. | [optional]

### Return type

[**ODataValueOfIListOfWTagInfo**](ODataValueOfIListOfWTagInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="copyEntryAsync"></a>
# **copyEntryAsync**
> AcceptedOperation copyEntryAsync(repoId, entryId, body, autoRename, culture)



- Copy a new child entry in the designated folder async, and potentially return an operationToken. - Provide the parent folder ID, and copy an entry as a child of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.  - The status of the operation can be checked via the Tasks/{operationToken} route.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The folder ID that the entry will be created in.
CopyAsyncRequest body = new CopyAsyncRequest(); // CopyAsyncRequest | Copy entry request.
Boolean autoRename = true; // Boolean | An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    AcceptedOperation result = apiInstance.copyEntryAsync(repoId, entryId, body, autoRename, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#copyEntryAsync");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The folder ID that the entry will be created in. |
 **body** | [**CopyAsyncRequest**](CopyAsyncRequest.md)| Copy entry request. | [optional]
 **autoRename** | **Boolean**| An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**AcceptedOperation**](AcceptedOperation.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createOrCopyEntry"></a>
# **createOrCopyEntry**
> Entry createOrCopyEntry(repoId, entryId, body, autoRename, culture)



- Create/copy a new child entry in the designated folder. - Provide the parent folder ID, and based on the request body, copy or create a folder/shortcut as a child entry of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The folder ID that the entry will be created in.
PostEntryChildrenRequest body = new PostEntryChildrenRequest(); // PostEntryChildrenRequest | The entry to create.
Boolean autoRename = true; // Boolean | An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    Entry result = apiInstance.createOrCopyEntry(repoId, entryId, body, autoRename, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#createOrCopyEntry");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The folder ID that the entry will be created in. |
 **body** | [**PostEntryChildrenRequest**](PostEntryChildrenRequest.md)| The entry to create. | [optional]
 **autoRename** | **Boolean**| An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**Entry**](Entry.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteAssignedTemplate"></a>
# **deleteAssignedTemplate**
> Entry deleteAssignedTemplate(repoId, entryId)



- Remove the currently assigned template from the specified entry. - Provide an entry ID to clear template value on. - If the entry does not have a template assigned, no change will be made.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The ID of the entry that will have its template removed.
try {
    Entry result = apiInstance.deleteAssignedTemplate(repoId, entryId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#deleteAssignedTemplate");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The ID of the entry that will have its template removed. |

### Return type

[**Entry**](Entry.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteDocument"></a>
# **deleteDocument**
> ODataValueOfBoolean deleteDocument(repoId, entryId)



- Delete the edoc associated with the provided entry ID.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested document ID.
try {
    ODataValueOfBoolean result = apiInstance.deleteDocument(repoId, entryId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#deleteDocument");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested document ID. |

### Return type

[**ODataValueOfBoolean**](ODataValueOfBoolean.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteEntryInfo"></a>
# **deleteEntryInfo**
> AcceptedOperation deleteEntryInfo(repoId, entryId, body)



- Begins a task to delete an entry, and returns an operationToken. - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately. - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason(); // DeleteEntryWithAuditReason | The submitted audit reason.
try {
    AcceptedOperation result = apiInstance.deleteEntryInfo(repoId, entryId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#deleteEntryInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **body** | [**DeleteEntryWithAuditReason**](DeleteEntryWithAuditReason.md)| The submitted audit reason. | [optional]

### Return type

[**AcceptedOperation**](AcceptedOperation.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deletePages"></a>
# **deletePages**
> ODataValueOfBoolean deletePages(repoId, entryId, pageRange)



- Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted. - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: \&quot;1,2,3\&quot;, \&quot;1-3,5\&quot;, \&quot;2-7,10-12.\&quot;

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested document ID.
String pageRange = "pageRange_example"; // String | The pages to be deleted.
try {
    ODataValueOfBoolean result = apiInstance.deletePages(repoId, entryId, pageRange);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#deletePages");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested document ID. |
 **pageRange** | **String**| The pages to be deleted. | [optional]

### Return type

[**ODataValueOfBoolean**](ODataValueOfBoolean.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="exportDocument"></a>
# **exportDocument**
> File exportDocument(repoId, entryId, range)



- Get an entry&#x27;s edoc resource in a stream format. - Provide an entry ID, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested document ID.
String range = "range_example"; // String | An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit.
try {
    File result = apiInstance.exportDocument(repoId, entryId, range);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#exportDocument");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested document ID. |
 **range** | **String**| An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. | [optional]

### Return type

[**File**](File.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, application/json

<a name="exportDocumentWithAuditReason"></a>
# **exportDocumentWithAuditReason**
> File exportDocumentWithAuditReason(repoId, entryId, body, range)



- Get an entry&#x27;s edoc resource in a stream format while including an audit reason. - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested document ID.
GetEdocWithAuditReasonRequest body = new GetEdocWithAuditReasonRequest(); // GetEdocWithAuditReasonRequest | 
String range = "range_example"; // String | An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit.
try {
    File result = apiInstance.exportDocumentWithAuditReason(repoId, entryId, body, range);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#exportDocumentWithAuditReason");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested document ID. |
 **body** | [**GetEdocWithAuditReasonRequest**](GetEdocWithAuditReasonRequest.md)|  | [optional]
 **range** | **String**| An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. | [optional]

### Return type

[**File**](File.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream, application/json

<a name="getDocumentContentType"></a>
# **getDocumentContentType**
> getDocumentContentType(repoId, entryId)



- Get information about the edoc content of an entry, without downloading the edoc in its entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers. - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested document ID.
try {
    apiInstance.getDocumentContentType(repoId, entryId);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getDocumentContentType");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested document ID. |

### Return type

null (empty response body)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getDynamicFieldValues"></a>
# **getDynamicFieldValues**
> Map&lt;String, List&lt;String&gt;&gt; getDynamicFieldValues(repoId, entryId, body)



- Get dynamic field logic values with the current values of the fields in the template. - Provide an entry ID and field values in the JSON body to get dynamic field logic values.  Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
GetDynamicFieldLogicValueRequest body = new GetDynamicFieldLogicValueRequest(); // GetDynamicFieldLogicValueRequest | 
try {
    Map<String, List<String>> result = apiInstance.getDynamicFieldValues(repoId, entryId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getDynamicFieldValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **body** | [**GetDynamicFieldLogicValueRequest**](GetDynamicFieldLogicValueRequest.md)|  | [optional]

### Return type

[**Map&lt;String, List&lt;String&gt;&gt;**](List.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getEntry"></a>
# **getEntry**
> Entry getEntry(repoId, entryId, $select)



- Returns a single entry object. - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc. - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
String $select = "$select_example"; // String | Limits the properties returned in the result.
try {
    Entry result = apiInstance.getEntry(repoId, entryId, $select);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getEntry");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **$select** | **String**| Limits the properties returned in the result. | [optional]

### Return type

[**Entry**](Entry.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getEntryListing"></a>
# **getEntryListing**
> ODataValueContextOfIListOfODataGetEntryChildren getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count)



- Returns the children entries of a folder in the repository. - Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. Sort order can be either value \&quot;asc\&quot; or \&quot;desc\&quot;. Optional query parameters: groupByOrderType (bool). This query parameter decides if results are returned in groups based on their entry type. Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route. - Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The folder ID.
Boolean groupByEntryType = true; // Boolean | An optional query parameter used to indicate if the result should be grouped by entry type or not.
List<String> fields = Arrays.asList("fields_example"); // List<String> | Optional array of field names. Field values corresponding to the given field names will be returned for each entry. 
Boolean formatFields = true; // Boolean | Boolean for if field values should be formatted. Only applicable if Fields are specified.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfODataGetEntryChildren result = apiInstance.getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getEntryListing");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The folder ID. |
 **groupByEntryType** | **Boolean**| An optional query parameter used to indicate if the result should be grouped by entry type or not. | [optional]
 **fields** | [**List&lt;String&gt;**](String.md)| Optional array of field names. Field values corresponding to the given field names will be returned for each entry.  | [optional]
 **formatFields** | **Boolean**| Boolean for if field values should be formatted. Only applicable if Fields are specified. | [optional]
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfODataGetEntryChildren**](ODataValueContextOfIListOfODataGetEntryChildren.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getFieldValues"></a>
# **getFieldValues**
> ODataValueContextOfIListOfFieldValue getFieldValues(repoId, entryId, prefer, formatValue, culture, $select, $orderby, $top, $skip, $count)



- Returns the fields assigned to an entry. - Provide an entry ID, and get a paged listing of all fields assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
Boolean formatValue = false; // Boolean | An optional query parameter used to indicate if the field values should be formatted.             The default value is false.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise             culture will not be used for formatting.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfFieldValue result = apiInstance.getFieldValues(repoId, entryId, prefer, formatValue, culture, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getFieldValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **formatValue** | **Boolean**| An optional query parameter used to indicate if the field values should be formatted.             The default value is false. | [optional] [default to false]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise             culture will not be used for formatting. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfFieldValue**](ODataValueContextOfIListOfFieldValue.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getLinkValuesFromEntry"></a>
# **getLinkValuesFromEntry**
> ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count)



- Get the links assigned to an entry. - Provide an entry ID, and get a paged listing of links assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
String prefer = "prefer_example"; // String | An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfWEntryLinkInfo result = apiInstance.getLinkValuesFromEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getLinkValuesFromEntry");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **prefer** | **String**| An optional odata header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
 **$select** | **String**| Limits the properties returned in the result. | [optional]
 **$orderby** | **String**| Specifies the order in which items are returned. The maximum number of expressions is 5. | [optional]
 **$top** | **Integer**| Limits the number of items returned from a collection. | [optional]
 **$skip** | **Integer**| Excludes the specified number of items of the queried collection from the result. | [optional]
 **$count** | **Boolean**| Indicates whether the total count of items within a collection are returned in the result. | [optional]

### Return type

[**ODataValueContextOfIListOfWEntryLinkInfo**](ODataValueContextOfIListOfWEntryLinkInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTagsAssignedToEntry"></a>
# **getTagsAssignedToEntry**
> ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count)



- Get the tags assigned to an entry. - Provide an entry ID, and get a paged listing of tags assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
String prefer = "prefer_example"; // String | An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
String $select = "$select_example"; // String | Limits the properties returned in the result.
String $orderby = "$orderby_example"; // String | Specifies the order in which items are returned. The maximum number of expressions is 5.
Integer $top = 56; // Integer | Limits the number of items returned from a collection.
Integer $skip = 56; // Integer | Excludes the specified number of items of the queried collection from the result.
Boolean $count = true; // Boolean | Indicates whether the total count of items within a collection are returned in the result.
try {
    ODataValueContextOfIListOfWTagInfo result = apiInstance.getTagsAssignedToEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#getTagsAssignedToEntry");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **prefer** | **String**| An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. | [optional]
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

<a name="importDocument"></a>
# **importDocument**
> CreateEntryResult importDocument(repoId, parentEntryId, fileName, electronicDocument, request, autoRename, culture)



- Creates a new document in the specified folder. - Optionally sets metadata and electronic document component. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed. With this route, partial success is possible. The response returns multiple operation (entryCreate operation, setEdoc operation, setLinks operation, etc..) objects, which contain information about any errors that may have occurred during the creation. As long as the entryCreate operation succeeds, the entry will be created, even if all other operations fail.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer parentEntryId = 56; // Integer | The entry ID of the folder that the document will be created in.
String fileName = "fileName_example"; // String | The created document's file name.
File electronicDocument = new File("electronicDocument_example"); // File | 
PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest(); // PostEntryWithEdocMetadataRequest | 
Boolean autoRename = true; // Boolean | An optional query parameter used to indicate if the new document should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    CreateEntryResult result = apiInstance.importDocument(repoId, parentEntryId, fileName, electronicDocument, request, autoRename, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#importDocument");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **parentEntryId** | **Integer**| The entry ID of the folder that the document will be created in. |
 **fileName** | **String**| The created document&#x27;s file name. |
 **electronicDocument** | **File**|  | [optional]
 **request** | [**PostEntryWithEdocMetadataRequest**](.md)|  | [optional]
 **autoRename** | **Boolean**| An optional query parameter used to indicate if the new document should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**CreateEntryResult**](CreateEntryResult.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="moveOrRenameDocument"></a>
# **moveOrRenameDocument**
> Entry moveOrRenameDocument(repoId, entryId, body, autoRename, culture)



- Moves and/or renames an entry. - Move and/or rename an entry by passing in the new parent folder ID or name in the JSON body. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The requested entry ID.
PatchEntryRequest body = new PatchEntryRequest(); // PatchEntryRequest | The request containing the folder ID that the entry will be moved to and the new name
            the entry will be renamed to.
Boolean autoRename = true; // Boolean | An optional query parameter used to indicate if the entry should be automatically             renamed if another entry already exists with the same name in the folder. The default value is false.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    Entry result = apiInstance.moveOrRenameDocument(repoId, entryId, body, autoRename, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#moveOrRenameDocument");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The requested entry ID. |
 **body** | [**PatchEntryRequest**](PatchEntryRequest.md)| The request containing the folder ID that the entry will be moved to and the new name
            the entry will be renamed to. | [optional]
 **autoRename** | **Boolean**| An optional query parameter used to indicate if the entry should be automatically             renamed if another entry already exists with the same name in the folder. The default value is false. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**Entry**](Entry.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="writeTemplateValueToEntry"></a>
# **writeTemplateValueToEntry**
> Entry writeTemplateValueToEntry(repoId, entryId, body, culture)



- Assign a template to an entry. - Provide an entry ID, template name, and a list of template fields to assign to that entry. - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.EntriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


EntriesApi apiInstance = new EntriesApi();
String repoId = "repoId_example"; // String | The requested repository ID.
Integer entryId = 56; // Integer | The ID of entry that will have its template updated.
PutTemplateRequest body = new PutTemplateRequest(); // PutTemplateRequest | The template and template fields that will be assigned to the entry.
String culture = ""; // String | An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag.
try {
    Entry result = apiInstance.writeTemplateValueToEntry(repoId, entryId, body, culture);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EntriesApi#writeTemplateValueToEntry");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |
 **entryId** | **Integer**| The ID of entry that will have its template updated. |
 **body** | [**PutTemplateRequest**](PutTemplateRequest.md)| The template and template fields that will be assigned to the entry. | [optional]
 **culture** | **String**| An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. | [optional]

### Return type

[**Entry**](Entry.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

