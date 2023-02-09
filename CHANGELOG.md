## 2.0.0

- **[BREAKING]** For every API, the parameters are now encapsulated into a parameter class.
- **[BREAKING]** Enum values are now following Java naming convention.
- **[BREAKING]** Every async API are replaced with normal blocking API.
- The ApiClient class is now abstract.
- Improved documentation.
- Only throw the `ApiException` type when error API responses occur.
- **[BREAKING]** `EntriesClient.importDocument` API v1 can succeed in creating a document, but fail in setting some or all of its metadata components. To retrieve errors in the case of a partial success, inspect the content of the `ProblemDetails.getExtensions()`. See example below.
  ```java
  try {
    repositoryApiClient.getEntriesClient().importDocument(...);
  } catch (ApiException e) {
    e.printStackTrace();
    if (e.getProblemDetails() != null && e.getProblemDetails().getExtensions() != null) {
      Object obj = e.getProblemDetails().getExtensions().getOrDefault(CreateEntryResult.class.getSimpleName(), null);
      CreateEntryResult result = obj instanceof CreateEntryResult ? (CreateEntryResult) obj : null;
      if (result != null && result.getOperations() != null && result.getOperations().getEntryCreate() != null) {
        Integer createdEntryId = result.getOperations().getEntryCreate().getEntryId();
      }
    }
  }
  ```
- **[BREAKING]** Removed `OAuthInterceptor` and `SelfHostedInterceptors` 
- **[BREAKING]** Added a `beforeSend()` method in the `ApiClient` class which will call the `beforeSend()` method in the [lf-api-client-core-java](https://github.com/Laserfiche/lf-api-client-core-java) and modify the request URL
- **[BREAKING]** Added additional helper functions in the `ApiClient` class such as: `isRetryableStatusCode()`, `combineURLs`, `isJsonResponse`, `createApiException`, `shouldThrowException`
- **[BREAKING]** Added the `private HttpRequestHandler httpRequestHandler;` field to each `clientImpl` class and passed it in as a parameter from the `RepositoryApiClientImpl` class
- **[BREAKING]** Added a common sendAsync type of method named `sendRequestParseResponse` in the `ApiClient` class that almost all API methods will call, see method signature below:
```java
protected static<TResponse> TResponse sendRequestParseResponse(
        UnirestInstance httpClient,
        ObjectMapper objectMapper,Class<TResponse> deserializedResponseType,
        HttpRequestHandler httpRequestHandler,String url,String requestMethod,
        String contentType,
        Object requestBody,
        String queryStringFields,
        Collection<?> queryStringFieldList,
        Map<String, Object> queryParameters,Map<String, Object> pathParameters,
        Map<String, String> headerParametersWithStringTypeValue,
        boolean isDynamicFieldValues)
```
- **[BREAKING]** Added the `private HttpRequestHandler httpRequestHandler;` field to each `clientImpl` class and passed it in as a parameter from the `RepositoryApiClientImpl` class
- **[BREAKING]** Added `afterSend()` and retry implementation in the newly created `sendRequestParseResponse()` method in the `ApiClient` class which includes
  - 401 status code check to call the `afterSend()` function
  - `isIdempotent()` check to call the `afterSend()` function 
  - Wrapped a while loop around each API implementation for retry logic and call `beforeSend()` in the beginning of the while loop
  - Refactored almost all API methods to call `sendRequestParseResponse()`
- Refactored implementation for `importDocument` and `exportDocument` API edge cases
- Refactored the `beforeSend()` method to the `exportDocument` API edge case (the `afterSend()` method will be done either in this release or another one)

## 1.2.0

### Features

- Add support for AutoCloseable for automatic managing of resources held by the underlying HTTP client.

## 1.1.1

### Fixes

- Serialization on models where there's at least one boolean getter no longer fails.
- Return type of `createSimpleSearchOperation ` becomes `CompletableFuture<ODataValueContextOfIListOfEntry>` to contain more OData information compared to the previous `CompletableFuture<ODataValueOfIListOfEntry>` class.
- FuzzyType `enum` now has a better named constants.
- **[BREAKING]** Boolean getter names have been updated to follow Java's convention.

## 1.1.0

### Features

- Add support for self-hosted API Server.

### Fixes

- Improve error reporting by prioritize detailed error message from API Server.
- Array parameters with one element are handled properly.
- Improve exception handling.
