# com.laserfiche.api.client
Laserfiche Repository API

- API version: 1.0.0
  - Build date: 2022-05-20T17:24:07.137-04:00[America/New_York]
 
An ASP.NET Core web API for Laserfiche Repository API Server

**Build#** : 4f7a879120e1a11fb1d3772c18859787d28d355b_.20220404.1

Automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)

## Requirements
Building the API client library requires:

1. Java 1.7+
2. Maven/Gradle

## Installation
To install the API client library to your local Maven repository, simply execute:

```
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```
mvn clean deploy
```

Refer to the [OSSRH Guide](https://central.sonatype.org/publish/publish-guide/) for more information.

### Maven users

Add this dependency to your project's POM:

```
<dependency>
  <groupId>io.swagger</groupId>
  <artifactId>com.laserfiche.api.client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users
Add this dependency to your project's build file:

```
compile "io.swagger:com.laserfiche.api.client:1.0.0"
```

### Others
At first generate the JAR by executing:

```
mvn clean package
```

Then manually install the following JARs:
- `target/com.laserfiche.api.client-1.0.0.jar`
- `target/lib/*.jar`

## Getting Started
Please follow the [installation](https://github.com/Laserfiche/lf-api-client-core-java#installation) instructions and execute the following Java code:

```java
import com.laserfiche.api.client.*;
import com.laserfiche.api.client.auth.*;
import com.laserfiche.api.client.model.*;
import com.laserfiche.api.client.oauth.TokenApi;

import java.io.File;
import java.util.*;

public class TokenApiExample {

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Configure HTTP basic authorization: Basic Authorization
        HttpBasicAuth Basic Authorization = (HttpBasicAuth) defaultClient.getAuthentication("Basic Authorization");
        Basic Authorization.setUsername("YOUR USERNAME");
        Basic Authorization.setPassword("YOUR PASSWORD");

        TokenApi apiInstance = new TokenApi();
        Object clientId = null; // Object | 
        String grantType = "grantType_example"; // String | 
        Object code = null; // Object | 
        Object redirectUri = null; // Object | 
        Object scope = null; // Object | 
        Object refreshToken = null; // Object | 
        Object codeVerifier = null; // Object | 
        String authorization = "authorization_example"; // String | 
        try {
            GetAccessTokenResponse result = apiInstance.tokenGetAccessToken(clientId, grantType, code, redirectUri, scope, refreshToken, codeVerifier, authorization);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TokenApi#tokenGetAccessToken");
            e.printStackTrace();
        }
    }
}
```

# Documentation for Models
You can find the documentation for each model in the [docs folder](https://github.com/Laserfiche/lf-repository-api-client-java/tree/1.x/docs)

# Documentation for Authorization
Authentication schemes defined for the API:

# Authorization #

**Basic Authorization**
- **Type:** HTTP basic authentication

# Recommendation
It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

# Author















