package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForCreateEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;
import com.laserfiche.repository.api.clients.params.ParametersForStartDeleteEntry;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

enum AuthorizationType {
    CLOUD_ACCESS_KEY,
    API_SERVER_USERNAME_PASSWORD
}

public class BaseTest {
    static String servicePrincipalKey;
    static AccessKey accessKey;
    static String repositoryId;
    private static Map<String, String> testHeaders;
    static volatile RepositoryApiClient repositoryApiClient;
    static String username;
    static String password;
    static String baseUrl;
    private static final String TEST_HEADER = "TEST_HEADER";
    private static final String ACCESS_KEY = "ACCESS_KEY";
    private static final String SERVICE_PRINCIPAL_KEY = "SERVICE_PRINCIPAL_KEY";
    private static final String REPOSITORY_ID = "REPOSITORY_ID";
    private static final String USERNAME = "APISERVER_USERNAME";
    private static final String PASSWORD = "APISERVER_PASSWORD";
    private static final String BASE_URL = "APISERVER_REPOSITORY_API_BASE_URL";
    protected static final String AUTHORIZATION_TYPE = "AUTHORIZATION_TYPE";
    static AuthorizationType authorizationType;
    private static final boolean IS_NOT_GITHUB_ENVIRONMENT = nullOrEmpty(System.getenv("GITHUB_WORKSPACE"));
    protected static final String SMALL_PDF_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/test.pdf";
    protected static final String LARGE_PDF_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/test2.pdf";
    protected static final String SMALL_TEXT_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/test.txt";
    protected static final String SMALL_JPEG_FILE_PATH = "src/test/java/com/laserfiche/repository/api/integration/testFiles/test.jpg";

    protected static int testFolderEntryId = -1;

    @BeforeAll
    public static void setUp() {
        Dotenv.configure()
                .filename(".env")
                .systemProperties()
                .ignoreIfMissing()
                .load();
        String testHeaderName;
        try {
            authorizationType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
            testHeaderName = getEnvironmentVariable(TEST_HEADER);
        } catch (EnumConstantNotPresentException e) {
            throw new EnumConstantNotPresentException(
                    AuthorizationType.class, getEnvironmentVariable(AUTHORIZATION_TYPE));
        }
        repositoryId = getEnvironmentVariable(REPOSITORY_ID);
        testFolderEntryId = Integer.parseInt(getEnvironmentVariable("TEST_FOLDER_ENTRY_ID"));
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
        testHeaders.put(testHeaderName, "true");
        createRepositoryApiClient();
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

    public static void createRepositoryApiClient() {
        if (repositoryApiClient == null) {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                if (!nullOrEmpty(servicePrincipalKey) && accessKey != null)
                    repositoryApiClient = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                if (!nullOrEmpty(repositoryId) && !nullOrEmpty(username) && !nullOrEmpty(password) && !nullOrEmpty(baseUrl))
                    repositoryApiClient =
                            RepositoryApiClientImpl.createFromUsernamePassword(repositoryId, username, password, baseUrl);
            }
            repositoryApiClient.setDefaultRequestHeaders(testHeaders);
        }
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

    public static void waitUntilTaskEnds(String taskId) {
        waitUntilTaskEnds(taskId, Duration.ofMillis(1000));
    }

    private static void waitUntilTaskEnds(String taskId, Duration interval) {
        int maxIteration = 5;
        int count = 0;
        while (count < maxIteration) {
            TaskCollectionResponse collectionResponse = repositoryApiClient
                    .getTasksClient()
                    .listTasks(new ParametersForListTasks()
                            .setRepositoryId(repositoryId)
                            .setTaskIds(taskId));
            TaskProgress progress = collectionResponse.getValue().get(0);
            if (progress.getStatus() != TaskStatus.IN_PROGRESS) {
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(interval.toMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        throw new RuntimeException("WaitUntilTaskEnds timeout");
    }

    public static void deleteEntry(int entryId) {
        if (entryId != 0) {
            StartTaskResponse startTaskResponse = repositoryApiClient
                    .getEntriesClient()
                    .startDeleteEntry(new ParametersForStartDeleteEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(entryId)
                            .setRequestBody(new StartDeleteEntryRequest()));
            waitUntilTaskEnds(startTaskResponse.getTaskId());
        }
    }

    public static void deleteEntries(List<Entry> entries) {
        for (Entry entry : entries) {
            if (entry != null) {
                deleteEntry(entry.getId());
            }
        }
    }

    protected boolean downloadFileFromURI(String uri, File destinationFile) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(uri).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void printProblemDetails(ProblemDetails problemDetails) {
        System.out.printf("ProblemDetails: (Title: %s, Status: %d, Detail: %s, Type: %s, Instance: %s, ErrorCode: %d, ErrorSource: %s)%n",
                problemDetails.getTitle(),
                problemDetails.getStatus(),
                problemDetails.getDetail(),
                problemDetails.getType(),
                problemDetails.getInstance(),
                problemDetails.getErrorCode(),
                problemDetails.getErrorSource());
    }

}
