## 2.0.0

- **[BREAKING]** For every API, the parameters are now encapsulated into a parameter class.
- **[BREAKING]** Enum values are now following Java naming convention.
- **[BREAKING]** Every async API are replaced with normal blocking API.
- The ApiClient class is now abstract.
- Improved documentation.

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
