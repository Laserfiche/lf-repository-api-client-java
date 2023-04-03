## 2.0.3

### Features

- Added `getSelfHostedRepositoryList(String url)` to `RepositoryApiClientImpl` for self hosted users

## 2.0.2

### Fixes

- Updated the [Java Client Core Repository](https://github.com/Laserfiche/lf-api-client-core-java) dependency to release version 2.2.0

## 2.0.0

### Features

- **[BREAKING]** For every API, the parameters are now encapsulated into a parameter class.
- **[BREAKING]** Enum values are now following Java naming convention.
- **[BREAKING]** Every async API are replaced with normal blocking API.
- Improved documentation.
- Added retry implementation when making an API call which retries when:
  - 401 status code response is being returned
  - Idempotent http request
- Added an option when constructing the `RepositoryApiClient` to specify the requested scope(s) for the access token.
- Added both `exportDocumentAsStream` and `exportDocumentWithAuditReasonAsStream` API methods in the `EntriesClientImpl` object along with the `EntriesClient` interface

### Fixes
- The ApiClient class is now abstract.
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
