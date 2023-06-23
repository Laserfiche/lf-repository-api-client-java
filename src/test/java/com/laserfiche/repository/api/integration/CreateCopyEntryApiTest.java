package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.time.Duration;
import org.junit.jupiter.api.*;

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

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry createdEntry = client.createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                .setRepoId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request)
                .setAutoRename(true));

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
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());

        Entry shortCut = client.createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                .setRepoId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request)
                .setAutoRename(true));

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

        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.getId());

        AcceptedOperation copyEntryResponse = client.copyEntryAsync(new ParametersForCopyEntryAsync()
                .setRepoId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyAsyncRequest)
                .setAutoRename(true));
        String opToken = copyEntryResponse.getToken();
        WaitUntilTaskEnds(copyEntryResponse, Duration.ofMillis(100), Duration.ofSeconds(30));

        OperationProgress getOperationStatusAndProgressResponse = repositoryApiClient
                .getTasksClient()
                .getOperationStatusAndProgress(new ParametersForGetOperationStatusAndProgress()
                        .setRepoId(repositoryId)
                        .setOperationToken(opToken));

        assertEquals(getOperationStatusAndProgressResponse.getStatus(), OperationStatus.COMPLETED);
    }

    @Test
    void createCopyEntry_CopyShortCut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(createEntryClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);
        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());

        Entry createOrCopyEntryResponse = client.createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                .setRepoId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request)
                .setAutoRename(true));

        assertNotNull(createOrCopyEntryResponse);
        assertEquals(testClassParentFolder.getId(), createOrCopyEntryResponse.getParentId());
        assertEquals(createOrCopyEntryResponse.getEntryType(), EntryType.SHORTCUT);

        request = new PostEntryChildrenRequest();
        request.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        request.setSourceId(createOrCopyEntryResponse.getId());

        Entry newEntryResponse = client.createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                .setRepoId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request)
                .setAutoRename(true));

        assertEquals(newEntryResponse.getParentId(), testClassParentFolder.getId());
        assertEquals(newEntryResponse.getEntryType(), createOrCopyEntryResponse.getEntryType());
    }

    @Test
    void moveAndRenameEntry_ReturnEntry() {
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

        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");

        Entry movedEntry = client.moveOrRenameEntry(new ParametersForMoveOrRenameEntry()
                .setRepoId(repositoryId)
                .setEntryId(childFolder.getId())
                .setRequestBody(request)
                .setAutoRename(true));

        assertNotNull(movedEntry);
        assertEquals(movedEntry.getId(), childFolder.getId());
        assertEquals(movedEntry.getParentId(), parentFolder.getId());
        assertEquals(movedEntry.getName(), request.getName());
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

        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");

        String invalidRepoId = String.format("%s-%s", repositoryId, repositoryId);
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.moveOrRenameEntry(new ParametersForMoveOrRenameEntry()
                        .setRepoId(invalidRepoId)
                        .setEntryId(childFolder.getId())
                        .setRequestBody(request)
                        .setAutoRename(true)));

        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertEquals(
                "Error: Repository with the given Id not found or no connection could be made.",
                apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }
}
