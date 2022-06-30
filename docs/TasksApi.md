# TasksApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelOperation**](TasksApi.md#cancelOperation) | **DELETE** v2-alpha/Repositories/{repoId}/Tasks/{operationToken} | 
[**getOperationStatusAndProgress**](TasksApi.md#getOperationStatusAndProgress) | **GET** v2-alpha/Repositories/{repoId}/Tasks/{operationToken} | 

<a name="cancelOperation"></a>
# **cancelOperation**
> Void cancelOperation(repoId, operationToken)



- Cancels an operation. - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary. - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.TasksApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TasksApi apiInstance = new TasksApi();
String repoId = "repoId_example"; // String | The requested repository ID
String operationToken = "operationToken_example"; // String | The operation token
try {
    Void result = apiInstance.cancelOperation(repoId, operationToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TasksApi#cancelOperation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID |
 **operationToken** | **String**| The operation token |

### Return type

[**Void**](.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getOperationStatusAndProgress"></a>
# **getOperationStatusAndProgress**
> OperationProgress getOperationStatusAndProgress(repoId, operationToken)



- Returns the status of an operation. - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant). - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.clients.impl.TasksApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


TasksApi apiInstance = new TasksApi();
String repoId = "repoId_example"; // String | The requested repository ID
String operationToken = "operationToken_example"; // String | The operation token
try {
    OperationProgress result = apiInstance.getOperationStatusAndProgress(repoId, operationToken);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TasksApi#getOperationStatusAndProgress");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID |
 **operationToken** | **String**| The operation token |

### Return type

[**OperationProgress**](OperationProgress.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

