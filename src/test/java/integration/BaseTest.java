package integration;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.PostEntryChildrenEntryType;
import com.laserfiche.repository.api.clients.impl.model.PostEntryChildrenRequest;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldInfo;
import com.laserfiche.repository.api.clients.params.ParametersForCreateOrCopyEntry;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum AuthorizationType {
    CLOUD_ACCESS_KEY,
    API_SERVER_USERNAME_PASSWORD
}

public class BaseTest {
    protected static String servicePrincipalKey;
    protected static AccessKey accessKey;
    protected static String repositoryId;
    private static Map<String, String> testHeaders;
    protected static RepositoryApiClient repositoryApiClient;
    private static String testHeaderValue;
    protected static String username;
    protected static String password;
    protected static String baseUrl;
    private static final String TEST_HEADER = "TEST_HEADER";
    private static final String ACCESS_KEY = "ACCESS_KEY";
    private static final String SERVICE_PRINCIPAL_KEY = "SERVICE_PRINCIPAL_KEY";
    private static final String REPOSITORY_ID = "REPOSITORY_ID";
    private static final String USERNAME = "APISERVER_USERNAME";
    private static final String PASSWORD = "APISERVER_PASSWORD";
    private static final String BASE_URL = "APISERVER_REPOSITORY_API_BASE_URL";
    protected static final String AUTHORIZATION_TYPE = "AUTHORIZATION_TYPE";
    protected static AuthorizationType authorizationType;
    private static final boolean IS_NOT_GITHUB_ENVIRONMENT = nullOrEmpty(System.getenv("GITHUB_WORKSPACE"));
    @BeforeEach
    public void setUp() {
        Dotenv dotenv = Dotenv
                .configure()
                .filename(".env")
                .systemProperties()
                .ignoreIfMissing()
                .load();
        try {
            authorizationType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
            testHeaderValue = getEnvironmentVariable(TEST_HEADER);
        } catch (EnumConstantNotPresentException e) {
            throw new EnumConstantNotPresentException(AuthorizationType.class,
                    getEnvironmentVariable(AUTHORIZATION_TYPE));
        }
        repositoryId = getEnvironmentVariable(REPOSITORY_ID);
        if (authorizationType == AuthorizationType.CLOUD_ACCESS_KEY) {
            servicePrincipalKey = getEnvironmentVariable(SERVICE_PRINCIPAL_KEY);
            String accessKeyBase64 = getEnvironmentVariable(ACCESS_KEY);
            accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        } else if (authorizationType == AuthorizationType.API_SERVER_USERNAME_PASSWORD) {
            username = getEnvironmentVariable(USERNAME);
            password = getEnvironmentVariable(PASSWORD);
            baseUrl = getEnvironmentVariable(BASE_URL);
        } else {
            throw new IllegalStateException("Invalid Authorization Type Value");
        }
        testHeaders = new HashMap<>();
        testHeaders.put(testHeaderValue, "true");
        repositoryApiClient = createClient();
    }

    protected static String getEnvironmentVariable(String environmentVariableName) {
        String environmentVariable = System.getenv(environmentVariableName);
        if (nullOrEmpty(environmentVariable)) {
            environmentVariable = System.getProperty(environmentVariableName);
            if (nullOrEmpty(environmentVariable) && IS_NOT_GITHUB_ENVIRONMENT)
                throw new IllegalStateException(
                        "Environment variable '" + environmentVariableName + "' does not exist.");
        }
        return environmentVariable;
    }

    @AfterEach
    public void tearDown() {
        repositoryApiClient.close();
        repositoryApiClient = null;
    }

    public static RepositoryApiClient createClient() {
        if (repositoryApiClient == null) {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                if (nullOrEmpty(servicePrincipalKey) || accessKey == null)
                    return null;
                repositoryApiClient = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                if (nullOrEmpty(repositoryId) || nullOrEmpty(username) || nullOrEmpty(password) || nullOrEmpty(baseUrl))
                    return null;
                repositoryApiClient = RepositoryApiClientImpl.createFromUsernamePassword(repositoryId, username,
                        password,
                        baseUrl);
            }
            repositoryApiClient.setDefaultRequestHeaders(testHeaders);
        }
        return repositoryApiClient;
    }

    public static Entry createEntry(RepositoryApiClient client, String entryName,
            Integer parentEntryId, Boolean autoRename) {
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(entryName);

        return client
                .getEntriesClient()
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(autoRename));
    }

    public static Boolean allFalse(List<TemplateFieldInfo> arr) {
        for (TemplateFieldInfo templateFieldInfo : arr) {
            if (templateFieldInfo.isRequired()) {
                return false;
            }
        }
        return true;
    }

    public static boolean nullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}