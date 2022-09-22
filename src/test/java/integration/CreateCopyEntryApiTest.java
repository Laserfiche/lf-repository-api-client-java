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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCopyEntryApiTest extends BaseTest {
    List<Entry> createdEntries = new ArrayList<>();
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
    @Disabled("Ignore for now because of the APIServer's entry related serialization bug.")
    void createCopyEntry_CreateFolder() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        Entry createdEntry = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(createdEntry);
        createdEntries.add(createdEntry);

        assertEquals(parentEntryId, createdEntry.parentId);
        assertEquals(createdEntry.entryType, EntryType.FOLDER);
        assertEquals(Folder.class.getName(), createdEntry
                .getClass()
                .getName());
    }

    @Test
    @Disabled("Ignore for now because of the APIServer's entry related serialization bug.")
    void createCopyEntry_CreateShortcut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        Entry targetEntry = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.parentId, parentEntryId);
        assertEquals(targetEntry.entryType, EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.SHORTCUT;
        request.name = newEntryName;
        request.targetId = targetEntry.id;

        Entry shortCut = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(shortCut);
        createdEntries.add(shortCut);
        assertEquals(parentEntryId, shortCut.parentId);
        assertEquals(EntryType.SHORTCUT, shortCut.entryType);
        assertEquals(shortCut
                .getClass()
                .getName(), Shortcut.class.getName());
    }

    @Test
    @Disabled("Test is failing: We need to update it to match the new implementation.")
    void createCopyEntry_CopyEntry() throws InterruptedException {
        String testFolderName = "RepositoryApiClientIntegrationTest Java CreateCopyEntry_CopyEntry_test_folder";
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry testFolder = createEntry(createEntryClient, testFolderName, 1, true).join();

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        Entry targetEntry = client
                .createOrCopyEntry(repoId, testFolder.id, request, true, null)
                .join();

        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);

        assertEquals(targetEntry.parentId, testFolder.id);
        assertEquals(targetEntry.entryType, EntryType.FOLDER);

        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.name = "RepositoryApiClientIntegrationTest Java CopiedEntry";
        copyAsyncRequest.sourceId = targetEntry.id;

        CompletableFuture<AcceptedOperation> copyEntryResponse = client.copyEntryAsync(repoId, testFolder.id,
                copyAsyncRequest, true, null);
        String opToken = copyEntryResponse.join().token;

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<OperationProgress> getOperationStatusAndProgressResponse = repositoryApiClient
                .getTasksClient()
                .getOperationStatusAndProgress(repoId, opToken);

        assertEquals(getOperationStatusAndProgressResponse.join().status, OperationStatus.COMPLETED);

        DeleteEntryWithAuditReason deleteEntryWithAuditReason = new DeleteEntryWithAuditReason();
        AcceptedOperation deleteEntryResponse = client
                .deleteEntryInfo(repoId, testFolder.id, deleteEntryWithAuditReason)
                .join();
        assertNotNull(deleteEntryResponse);
    }

    @Test
    void createCopyEntry_CopyShortCut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;

        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = newEntryName;

        Entry targetEntry = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(targetEntry);

        createdEntries.add(targetEntry);

        assertEquals(targetEntry.parentId, parentEntryId);
        assertEquals(targetEntry.entryType, EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";

        request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.SHORTCUT;
        request.name = newEntryName;
        request.targetId = targetEntry.id;

        Entry createOrCopyEntryResponse = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        assertNotNull(createOrCopyEntryResponse);

        createdEntries.add(createOrCopyEntryResponse);

        assertEquals(parentEntryId, createOrCopyEntryResponse.parentId);
        assertEquals(createOrCopyEntryResponse.entryType, EntryType.SHORTCUT);

        request = new PostEntryChildrenRequest();
        request.name = "RepositoryApiClientIntegrationTest Java CopiedEntry";
        request.sourceId = createOrCopyEntryResponse.id;

        Entry newEntryResponse = client
                .createOrCopyEntry(repoId, parentEntryId, request, true, null)
                .join();

        createdEntries.add(newEntryResponse);

        assertEquals(newEntryResponse.parentId, parentEntryId);
        assertEquals(newEntryResponse.entryType, createOrCopyEntryResponse.entryType);
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
        request.parentId = parentFolder.id;
        request.name = "RepositoryApiClientIntegrationTest Java MovedFolder";

        Entry movedEntry = client
                .moveOrRenameDocument(repoId, childFolder.id, request, true, null)
                .join();

        assertNotNull(movedEntry);
        assertEquals(movedEntry.id, childFolder.id);
        assertEquals(movedEntry.parentId, parentFolder.id);
        assertEquals(movedEntry.name, request.name);
    }
}
