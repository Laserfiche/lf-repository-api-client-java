package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForCreateEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;
import com.laserfiche.repository.api.clients.params.ParametersForStartDeleteEntry;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

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
    protected static final String SMALL_PDF_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/test.pdf";
    protected static final String LARGE_PDF_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/60MB.pdf";

    @BeforeAll
    public static void setUp() {
        Dotenv dotenv = Dotenv.configure()
                .filename(".env")
                .systemProperties()
                .ignoreIfMissing()
                .load();
        try {
            authorizationType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
            testHeaderValue = getEnvironmentVariable(TEST_HEADER);
        } catch (EnumConstantNotPresentException e) {
            throw new EnumConstantNotPresentException(
                    AuthorizationType.class, getEnvironmentVariable(AUTHORIZATION_TYPE));
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

    @AfterAll
    public static void tearDown() {
        repositoryApiClient.close();
        repositoryApiClient = null;
    }

    public static RepositoryApiClient createClient() {
        if (repositoryApiClient == null) {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                if (nullOrEmpty(servicePrincipalKey) || accessKey == null) return null;
                repositoryApiClient = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                if (nullOrEmpty(repositoryId) || nullOrEmpty(username) || nullOrEmpty(password) || nullOrEmpty(baseUrl))
                    return null;
                repositoryApiClient =
                        RepositoryApiClientImpl.createFromUsernamePassword(repositoryId, username, password, baseUrl);
            }
            repositoryApiClient.setDefaultRequestHeaders(testHeaders);
        }
        return repositoryApiClient;
    }

    public static Entry createEntry(RepositoryApiClient client, String entryName, Integer parentEntryId, Boolean autoRename) {
        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.FOLDER);
        request.setName(entryName);
        request.setAutoRename(autoRename);

        return client.getEntriesClient()
                .createEntry(new ParametersForCreateEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request));
    }

    public static Boolean noRequiredFieldDefinitionsInTemplate(List<TemplateFieldDefinition> templateFieldDefinitions) {
        return templateFieldDefinitions.stream().noneMatch(FieldDefinition::isRequired);
    }

    public static boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static void WaitUntilTaskEnds(String taskId) throws InterruptedException {
        WaitUntilTaskEnds(taskId, Duration.ofMillis(500));
    }

    public static void WaitUntilTaskEnds(String taskId, Duration interval)
            throws InterruptedException {
        int maxIteration = 5;
        int count = 0;
        while (count < maxIteration) {
            TaskCollectionResponse response = repositoryApiClient
                    .getTasksClient()
                    .listTasks(new ParametersForListTasks()
                            .setRepositoryId(repositoryId)
                            .setTaskIds(taskId));
            TaskProgress progress = response.getValue().get(0);
            if (progress.getStatus() != TaskStatus.IN_PROGRESS) {
                return;
            }
            TimeUnit.MILLISECONDS.sleep(interval.toMillis());
            count++;
        }
        throw new RuntimeException("WaitUntilTaskEnds timeout");
    }

    public static void deleteEntry(int entryId) throws InterruptedException {
        if (entryId != 0) {
            StartTaskResponse startTaskResponse = repositoryApiClient
                    .getEntriesClient()
                    .startDeleteEntry(new ParametersForStartDeleteEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(entryId)
                            .setRequestBody(new StartDeleteEntryRequest()));
            WaitUntilTaskEnds(startTaskResponse.getTaskId());
        }
    }

    public static void deleteEntries(List<Entry> entries) throws InterruptedException {
        for (Entry entry : entries) {
            if (entry != null) {
                deleteEntry(entry.getId());
            }
        }
    }
}
