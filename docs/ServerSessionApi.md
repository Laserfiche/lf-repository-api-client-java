# ServerSessionApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createServerSession**](ServerSessionApi.md#createServerSession) | **POST** v2-alpha/Repositories/{repoId}/ServerSession/Create | 
[**invalidateServerSession**](ServerSessionApi.md#invalidateServerSession) | **POST** v2-alpha/Repositories/{repoId}/ServerSession/Invalidate | 
[**refreshServerSession**](ServerSessionApi.md#refreshServerSession) | **POST** v2-alpha/Repositories/{repoId}/ServerSession/Refresh | 

<a name="createServerSession"></a>
# **createServerSession**
> ODataValueOfBoolean createServerSession(repoId)



- Deprecated. This function is a no-op, always returns 200.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.client.ServerSessionApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


ServerSessionApi apiInstance = new ServerSessionApi();
String repoId = "repoId_example"; // String | The requested repository ID.
try {
    ODataValueOfBoolean result = apiInstance.createServerSession(repoId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServerSessionApi#createServerSession");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |

### Return type

[**ODataValueOfBoolean**](ODataValueOfBoolean.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="invalidateServerSession"></a>
# **invalidateServerSession**
> ODataValueOfBoolean invalidateServerSession(repoId)



- Invalidates the server session. - Acts as a \&quot;logout\&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.client.ServerSessionApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


ServerSessionApi apiInstance = new ServerSessionApi();
String repoId = "repoId_example"; // String | The requested repository ID.
try {
    ODataValueOfBoolean result = apiInstance.invalidateServerSession(repoId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServerSessionApi#invalidateServerSession");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |

### Return type

[**ODataValueOfBoolean**](ODataValueOfBoolean.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="refreshServerSession"></a>
# **refreshServerSession**
> ODataValueOfDateTime refreshServerSession(repoId)



- Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires. - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.client.ServerSessionApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


ServerSessionApi apiInstance = new ServerSessionApi();
String repoId = "repoId_example"; // String | The requested repository ID.
try {
    ODataValueOfDateTime result = apiInstance.refreshServerSession(repoId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServerSessionApi#refreshServerSession");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |

### Return type

[**ODataValueOfDateTime**](ODataValueOfDateTime.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

