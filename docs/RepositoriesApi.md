# RepositoriesApi

All URIs are relative to *https://api.a.clouddev.laserfiche.com/repository*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getRepositoryList**](RepositoriesApi.md#getRepositoryList) | **GET** v2-alpha/Repositories | 

<a name="getRepositoryList"></a>
# **getRepositoryList**
> List&lt;RepositoryInfo&gt; getRepositoryList()



- Get the repository resource list that current user has access to.

### Example
```java
// Import classes:
//import com.laserfiche.repository.api.ApiClient;
//import com.laserfiche.repository.api.ApiException;
//import com.laserfiche.repository.api.Configuration;
//import com.laserfiche.repository.api.auth.*;
//import com.laserfiche.repository.api.client.RepositoriesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


// Configure OAuth2 access token for authorization: OAuth2 Authorization Code Flow
OAuth OAuth2 Authorization Code Flow = (OAuth) defaultClient.getAuthentication("OAuth2 Authorization Code Flow");
OAuth2 Authorization Code Flow.setAccessToken("YOUR ACCESS TOKEN");


RepositoriesApi apiInstance = new RepositoriesApi();
try {
    List<RepositoryInfo> result = apiInstance.getRepositoryList();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoriesApi#getRepositoryList");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;RepositoryInfo&gt;**](RepositoryInfo.md)

### Authorization

[Authorization](../README.md#Authorization)[OAuth2 Authorization Code Flow](../README.md#OAuth2 Authorization Code Flow)[OAuth2 Client Credentials Flow](../README.md#OAuth2 Client Credentials Flow)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

