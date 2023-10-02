package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.time.Duration;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCopyEntryApiTest extends BaseTest {
    static Entry testClassParentFolder;
    EntriesClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @BeforeAll
    static void classSetup() {
        String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
        testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
    }

    @AfterAll
    static void classCleanUp() throws InterruptedException {
        deleteEntry(testClassParentFolder.getId());
    }

    @Test
    void createCopyEntry_CreateFolder() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.FOLDER);
        request.setName(newEntryName);
        request.setAutoRename(true);

        Entry createdEntry = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(createdEntry);

        assertEquals(testClassParentFolder.getId(), createdEntry.getParentId());
        assertEquals(createdEntry.getEntryType(), EntryType.FOLDER);
        assertEquals(Folder.class.getName(), createdEntry.getClass().getName());
    }

    @Test
    void createCopyEntry_CreateShortcut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(createEntryClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);

        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        request.setAutoRename(true);

        Entry shortCut = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(shortCut);
        assertEquals(testClassParentFolder.getId(), shortCut.getParentId());
        assertEquals(EntryType.SHORTCUT, shortCut.getEntryType());
        assertEquals(shortCut.getClass().getName(), Shortcut.class.getName());
    }

    @Test
    void copyEntryAsync_CopyEntry() throws InterruptedException {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(createEntryClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);
        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        StartCopyEntryRequest copyAsyncRequest = new StartCopyEntryRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.getId());
        copyAsyncRequest.setAutoRename(true);

        StartTaskResponse copyEntryResponse = client.startCopyEntry(new ParametersForStartCopyEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyAsyncRequest));
        String taskId = copyEntryResponse.getTaskId();
        waitUntilTaskEnds(taskId, Duration.ofMillis(100));

        TaskCollectionResponse response = repositoryApiClient
                .getTasksClient()
                .listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskId));

        assertEquals(1, response.getValue().size());
        assertEquals(response.getValue().get(0).getStatus(), TaskStatus.COMPLETED);
    }

    @Test
    void createCopyEntry_CopyShortCut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(createEntryClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);
        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        request.setAutoRename(true);

        Entry createOrCopyEntryResponse = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(createOrCopyEntryResponse);
        assertEquals(testClassParentFolder.getId(), createOrCopyEntryResponse.getParentId());
        assertEquals(createOrCopyEntryResponse.getEntryType(), EntryType.SHORTCUT);

        CopyEntryRequest copyRequest = new CopyEntryRequest();
        copyRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyRequest.setSourceId(createOrCopyEntryResponse.getId());
        copyRequest.setAutoRename(true);
        Entry copyEntryResponse = client.copyEntry(new ParametersForCopyEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyRequest));

        assertEquals(copyEntryResponse.getParentId(), testClassParentFolder.getId());
        assertEquals(EntryType.SHORTCUT, copyEntryResponse.getEntryType());
        assertEquals(copyEntryResponse.getEntryType(), createOrCopyEntryResponse.getEntryType());
    }

    @Test
    void Copying_Folder_Not_Supported_By_Sync_Version() {
        Entry parentFolder = createEntry(
                createEntryClient,
                "RepositoryApiClientIntegrationTest Java ParentFolder",
                testClassParentFolder.getId(),
                true);

        Entry childFolder = createEntry(
                createEntryClient,
                "RepositoryApiClientIntegrationTest Java ChildFolder",
                testClassParentFolder.getId(),
                true);

        CopyEntryRequest request = new CopyEntryRequest();
        request.setSourceId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");
        request.setAutoRename(true);

        ApiException exception = assertThrows(ApiException.class, () -> {
                    client.copyEntry(new ParametersForCopyEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(childFolder.getId())
                            .setRequestBody(request));
                });

        assertEquals(400, exception.getStatusCode());
        assertEquals(exception.getStatusCode(), exception.getProblemDetails().getStatus());
    }

    @Test
    void moveAndRenameEntry_ReturnsCorrectErrorMessage_For_InvalidRepoId() {
        Entry parentFolder = createEntry(
                createEntryClient,
                "RepositoryApiClientIntegrationTest Java ParentFolder",
                testClassParentFolder.getId(),
                true);

        Entry childFolder = createEntry(
                createEntryClient,
                "RepositoryApiClientIntegrationTest Java ChildFolder",
                testClassParentFolder.getId(),
                true);

        CopyEntryRequest request = new CopyEntryRequest();
        request.setSourceId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");
        request.setAutoRename(true);

        String invalidRepoId = String.format("%s-%s", repositoryId, repositoryId);
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.copyEntry(new ParametersForCopyEntry()
                        .setRepositoryId(invalidRepoId)
                        .setEntryId(childFolder.getId())
                        .setRequestBody(request)));

        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertEquals(
                "Error: Repository with the given Id not found or no connection could be made.",
                apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }
}
