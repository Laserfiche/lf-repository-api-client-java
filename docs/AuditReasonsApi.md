# AuditReasonsApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAuditReasons**](AuditReasonsApi.md#getAuditReasons) | **GET** v2-alpha/Repositories/{repoId}/AuditReasons | Get the audit reasons associated with the authenticated user.

<a name="getAuditReasons"></a>
# **getAuditReasons**
> AuditReasons getAuditReasons(repoId)

Get the audit reasons associated with the authenticated user.

- Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.client.AuditReasonsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


AuditReasonsApi apiInstance = new AuditReasonsApi();
String repoId = "repoId_example"; // String | The requested repository ID.
try {
    AuditReasons result = apiInstance.getAuditReasons(repoId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuditReasonsApi#getAuditReasons");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **repoId** | **String**| The requested repository ID. |

### Return type

[**AuditReasons**](AuditReasons.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

