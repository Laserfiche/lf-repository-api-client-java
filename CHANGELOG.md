## 1.1.1

### Fixes

- Serialization on models where there's at least one boolean getter no longer fails.
- Return type of `createSimpleSearchOperation ` becomes `ODataValueContextOfIListOfEntry` to contain more OData information compared to the previous `ODataValueOfIListOfEntry` class.
- FuzzyType `enum` now has a better named constants.
- **[BREAKING]** Boolean getter names have been updated to follow Java's convention.

## 1.1.0

### Features

Add support for self-hosted API Server.

### Fixes

- Improve error reporting by prioritize detailed error message from API Server.
- Array parameters with one element are handled properly.
- Improve exception handling.
