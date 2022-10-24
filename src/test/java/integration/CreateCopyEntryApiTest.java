package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    void deleteEntries() {
        for (Entry createdEntry : createdEntries) {
            if (createdEntry != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                Integer num = createdEntry.getId();
                repositoryApiClient
                        .getEntriesClient()
                        .deleteEntryInfo(repoId, num, body);
            }
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
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

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
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

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
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

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

        Entry testFolder = createEntry(createEntryClient, testFolderName, 1, true).join();

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);

        Entry targetEntry = client
                .createOrCopyEntry(repoId, testFolder.getId(), request, true, null)
                .join();

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.getParentId(), testFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.getId());

        CompletableFuture<AcceptedOperation> copyEntryResponse = client.copyEntryAsync(repoId, testFolder.getId(),
                copyAsyncRequest, true, null);
        String opToken = copyEntryResponse
                .join()
                .getToken();

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<OperationProgress> getOperationStatusAndProgressResponse = repositoryApiClient
                .getTasksClient()
                .getOperationStatusAndProgress(repoId, opToken);

        assertEquals(getOperationStatusAndProgressResponse
                .join()
                .getStatus(), OperationStatus.COMPLETED);

        DeleteEntryWithAuditReason deleteEntryWithAuditReason = new DeleteEntryWithAuditReason();
        AcceptedOperation deleteEntryResponse = client
                .deleteEntryInfo(repoId, testFolder.getId(), deleteEntryWithAuditReason)
                .join();
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
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

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
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(createOrCopyEntryResponse);

        createdEntries.add(createOrCopyEntryResponse);

        assertEquals(parentEntryId, createOrCopyEntryResponse.getParentId());
        assertEquals(createOrCopyEntryResponse.getEntryType(), EntryType.SHORTCUT);

        request = new PostEntryChildrenRequest();
        request.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        request.setSourceId(createOrCopyEntryResponse.getId());

        Entry newEntryResponse = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        createdEntries.add(newEntryResponse);

        assertEquals(newEntryResponse.getParentId(), parentEntryId);
        assertEquals(newEntryResponse.getEntryType(), createOrCopyEntryResponse.getEntryType());
    }

    @Test
    void moveAndRenameEntry_ReturnEntry() {
        Entry parentFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ParentFolder", 1,
                true).join();

        createdEntries.add(parentFolder);

        Entry childFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ChildFolder", 1,
                true).join();

        createdEntries.add(childFolder);

        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");

        Entry movedEntry = client
                .moveOrRenameEntry(repoId, childFolder.getId(), request, true, null)
                .join();

        assertNotNull(movedEntry);
        assertEquals(movedEntry.getId(), childFolder.getId());
        assertEquals(movedEntry.getParentId(), parentFolder.getId());
        assertEquals(movedEntry.getName(), request.getName());
    }
}
