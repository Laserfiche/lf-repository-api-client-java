package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCopyEntryApiTest extends BaseTest {
    List<Entry> createdEntries = new ArrayList<>();
    EntriesClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @AfterEach
    void deleteEntries() throws InterruptedException {
        for (Entry createdEntry : createdEntries) {
            if (createdEntry != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                Integer num = createdEntry.getId();
                repositoryApiClient
                        .getEntriesClient()
                        .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                                .setRepoId(repositoryId)
                                .setEntryId(num)
                                .setRequestBody(body));
            }
            TimeUnit.SECONDS.sleep(10);
        }
        createdEntries.clear();
    }

    @Test
    void createCopyEntry_CreateFolder() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry createdEntry = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(createdEntry);
        createdEntries.add(createdEntry);

        assertEquals(parentEntryId, createdEntry.getParentId());
        assertEquals(createdEntry.getEntryType(), EntryType.FOLDER);
        assertEquals(Folder.class.getName(), createdEntry
                .getClass()
                .getName());
    }

    @Test
    void createCopyEntry_CreateShortcut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry targetEntry = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.getParentId(), parentEntryId);
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());

        Entry shortCut = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(shortCut);
        createdEntries.add(shortCut);
        assertEquals(parentEntryId, shortCut.getParentId());
        assertEquals(EntryType.SHORTCUT, shortCut.getEntryType());
        assertEquals(shortCut
                .getClass()
                .getName(), Shortcut.class.getName());
    }

    @Test
    void createCopyEntry_CopyEntry() throws InterruptedException {
        String testFolderName = "RepositoryApiClientIntegrationTest Java CreateCopyEntry_CopyEntry_test_folder";
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry testFolder = createEntry(createEntryClient, testFolderName, 1, true);

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry targetEntry = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(testFolder.getId())
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.getParentId(), testFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.getId());

        AcceptedOperation copyEntryResponse = client.copyEntryAsync(new ParametersForCopyEntryAsync()
                .setRepoId(repositoryId)
                .setEntryId(testFolder.getId())
                .setRequestBody(copyAsyncRequest)
                .setAutoRename(true));
        String opToken = copyEntryResponse
                .getToken();

        TimeUnit.SECONDS.sleep(5);

        OperationProgress getOperationStatusAndProgressResponse = repositoryApiClient
                .getTasksClient()
                .getOperationStatusAndProgress(new ParametersForGetOperationStatusAndProgress()
                        .setRepoId(repositoryId)
                        .setOperationToken(opToken));

        assertEquals(getOperationStatusAndProgressResponse.getStatus(), OperationStatus.COMPLETED);

        DeleteEntryWithAuditReason deleteEntryWithAuditReason = new DeleteEntryWithAuditReason();
        AcceptedOperation deleteEntryResponse = client
                .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                        .setRepoId(repositoryId)
                        .setEntryId(testFolder.getId())
                        .setRequestBody(deleteEntryWithAuditReason));
        assertNotNull(deleteEntryResponse);
    }

    @Test
    void createCopyEntry_CopyShortCut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry targetEntry = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(targetEntry);

        createdEntries.add(targetEntry);

        assertEquals(targetEntry.getParentId(), parentEntryId);
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";

        request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());

        Entry createOrCopyEntryResponse = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        assertNotNull(createOrCopyEntryResponse);

        createdEntries.add(createOrCopyEntryResponse);

        assertEquals(parentEntryId, createOrCopyEntryResponse.getParentId());
        assertEquals(createOrCopyEntryResponse.getEntryType(), EntryType.SHORTCUT);

        request = new PostEntryChildrenRequest();
        request.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        request.setSourceId(createOrCopyEntryResponse.getId());

        Entry newEntryResponse = client
                .createOrCopyEntry(new ParametersForCreateOrCopyEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(parentEntryId)
                        .setRequestBody(request)
                        .setAutoRename(true));

        createdEntries.add(newEntryResponse);

        assertEquals(newEntryResponse.getParentId(), parentEntryId);
        assertEquals(newEntryResponse.getEntryType(), createOrCopyEntryResponse.getEntryType());
    }

    @Test
    void moveAndRenameEntry_ReturnEntry() {
        Entry parentFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ParentFolder", 1,
                true);

        createdEntries.add(parentFolder);

        Entry childFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ChildFolder", 1,
                true);

        createdEntries.add(childFolder);

        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");

        Entry movedEntry = client
                .moveOrRenameEntry(new ParametersForMoveOrRenameEntry()
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
        Entry parentFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ParentFolder", 1,
                true);

        createdEntries.add(parentFolder);

        Entry childFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ChildFolder", 1,
                true);

        createdEntries.add(childFolder);

        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");

        String invalidRepoId = String.format("%s-%s", repositoryId, repositoryId);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> client
                .moveOrRenameEntry(new ParametersForMoveOrRenameEntry()
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
