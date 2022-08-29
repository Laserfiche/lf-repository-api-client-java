package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCopyEntryApiTest extends BaseTest {
    List<Entry> createdEntries = new ArrayList<Entry>();
    EntriesClient client;

    RepositoryApiClient createEntryClient;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @AfterEach
    void deleteEntries() {
        for (Entry createdEntry : createdEntries) {
            if (createdEntry != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                Integer num = createdEntry.id;
                repositoryApiClient
                        .getEntriesClient()
                        .deleteEntryInfo(repoId, num, body);
            }
        }
        createdEntries.clear();
    }

    @Test
    void createCopyEntryCreateFolder_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);

        assertNotNull(createOrCopyEntryResponse.join());
        createdEntries.add(createOrCopyEntryResponse.join());

        assertEquals(parentEntryId, createOrCopyEntryResponse.join().parentId);
        assertEquals(createOrCopyEntryResponse.join().entryType, EntryType.FOLDER);
        assertEquals(Folder.class.getName(), createOrCopyEntryResponse
                .join()
                .getClass()
                .getName());
    }

    @Test
    void createCopyEntryCreateShortcut_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry targetEntry = createOrCopyEntryResponse.join();

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.parentId, parentEntryId);
        assertEquals(targetEntry.entryType, EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.SHORTCUT;
        request.name = newEntryName;
        request.targetId = targetEntry.id;

        CompletableFuture<Entry> shortCutResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry shortCut = shortCutResponse.join();

        assertNotNull(shortCut);
        createdEntries.add(shortCut);
        assertEquals(parentEntryId, shortCut.parentId);
        assertEquals(EntryType.SHORTCUT, shortCut.entryType);
        assertEquals(shortCut
                .getClass()
                .getName(), Shortcut.class.getName());
    }

    @Test
    @Disabled("Test is failing: Weird exception is being thrown")
    void createCopyEntryCopyEntry_Success() throws InterruptedException {
        String testFolderName = "RepositoryApiClientIntegrationTest Java CreateCopyEntry_CopyEntry_test_folder";
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        CompletableFuture<Entry> testFolder = createEntry(createEntryClient, testFolderName, 1, true);

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        CompletableFuture<Entry> targetEntry = client.createOrCopyEntry(repoId, testFolder.join().id, request, true, null);

        assertNotNull(targetEntry.join());
        createdEntries.add(targetEntry.join());

        assertEquals(targetEntry.join().parentId, testFolder.join().id);
        assertEquals(targetEntry
                .join()
                .entryType, EntryType.FOLDER);

        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.name = "RepositoryApiClientIntegrationTest Java CopiedEntry";
        copyAsyncRequest.sourceId = targetEntry.join().id;

        CompletableFuture<AcceptedOperation> copyEntryResponse = client.copyEntryAsync(repoId, testFolder.join().id, copyAsyncRequest, true, null);
        String opToken = copyEntryResponse.join().token;

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<OperationProgress> getOperationStatusAndProgressResponse = repositoryApiClient.getTasksClient().getOperationStatusAndProgress(repoId, opToken);

        assertEquals(getOperationStatusAndProgressResponse.join().status, OperationStatus.COMPLETED);

        DeleteEntryWithAuditReason deleteEntryWithAuditReason = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, testFolder.join().id, deleteEntryWithAuditReason);
        assertNotNull(deleteEntryResponse.join());
    }

    @Test
    void createCopyEntryCopyShortCut_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry targetEntry = createOrCopyEntryResponse.join();

        assertNotNull(targetEntry);

        createdEntries.add(targetEntry);

        assertEquals(targetEntry.parentId, parentEntryId);
        assertEquals(targetEntry.entryType, EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";

        request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.SHORTCUT;
        request.name = newEntryName;
        request.targetId = targetEntry.id;

        createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);

        assertNotNull(createOrCopyEntryResponse.join());

        createdEntries.add(createOrCopyEntryResponse.join());

        assertEquals(parentEntryId, createOrCopyEntryResponse.join().parentId);
        assertEquals(createOrCopyEntryResponse.join().entryType, EntryType.SHORTCUT);

        request = new PostEntryChildrenRequest();
        request.name = "RepositoryApiClientIntegrationTest Java CopiedEntry";
        request.sourceId = createOrCopyEntryResponse.join().id;

        CompletableFuture<Entry> newEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);

        createdEntries.add(newEntryResponse.join());

        assertEquals(newEntryResponse.join().parentId, parentEntryId);
        assertEquals(newEntryResponse.join().entryType, createOrCopyEntryResponse.join().entryType);
    }

    @Test
    void moveAndRenameEntry_Success() {
        CompletableFuture<Entry> parentFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ParentFolder", 1, true);

        createdEntries.add(parentFolder.join());

        CompletableFuture<Entry> childFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ChildFolder", 1, true);

        createdEntries.add(childFolder.join());

        PatchEntryRequest request = new PatchEntryRequest();
        request.parentId = parentFolder.join().id;
        request.name = "RepositoryApiClientIntegrationTest Java MovedFolder";

        CompletableFuture<Entry> movedEntryResponse = client.moveOrRenameDocument(repoId, childFolder.join().id, request, true, null);

        assertNotNull(movedEntryResponse);
        assertEquals(movedEntryResponse.join().id, childFolder.join().id);
        assertEquals(movedEntryResponse.join().parentId, parentFolder.join().id);
        assertEquals(movedEntryResponse.join().name, request.name);
    }
}
