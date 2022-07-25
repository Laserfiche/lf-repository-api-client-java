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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        for (int i = 0; i < createdEntries.size(); i++) {
            if (createdEntries.get(i) != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                Integer num = createdEntries.get(i).getId();
                repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, num, body);
            }
        }
        createdEntries.clear();
        client = null;
    }

    @Test
    void createCopyEntryCreateFolder_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);
        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        assertNotNull(createOrCopyEntryResponse.join());
        createdEntries.add(createOrCopyEntryResponse.join());
        assertTrue(parentEntryId == createOrCopyEntryResponse.join().getParentId());
        assertTrue(createOrCopyEntryResponse.join().getEntryType() == EntryType.FOLDER);
        assertTrue(Folder.class.getName().equals(createOrCopyEntryResponse.join().getClass().getName()));
    }

    @Test
    void createCopyEntryCreateShortcut_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);
        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry targetEntry = createOrCopyEntryResponse.join();
        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);
        assertTrue(targetEntry.getParentId() == parentEntryId);
        assertTrue(targetEntry.getEntryType() == EntryType.FOLDER);
        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        CompletableFuture<Entry> shortCutResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry shortCut = shortCutResponse.join();
        assertNotNull(shortCut);
        createdEntries.add(shortCut);
        assertTrue(parentEntryId == shortCut.getParentId());
        assertTrue(EntryType.SHORTCUT == shortCut.getEntryType());
        assertTrue(shortCut.getClass().getName().equals(Shortcut.class.getName()));
    }

    @Test
    void createCopyEntryCopyEntry_Success() throws InterruptedException {
        String testFolderName = "RepositoryApiClientIntegrationTest Java CreateCopyEntry_CopyEntry_test_folder";
        CompletableFuture<Entry> testFolder = createEntry(createEntryClient, testFolderName, 1, true);
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);
        CompletableFuture<Entry> targetEntry = client.createOrCopyEntry(repoId, testFolder.join().getId(), request, true, null);
        assertNotNull(targetEntry.join());
        createdEntries.add(targetEntry.join());
        assertTrue(targetEntry.join().getParentId().equals(testFolder.join().getId()));
        assertTrue(targetEntry.join().getEntryType() == EntryType.FOLDER);
        CopyAsyncRequest copyAsyncRequest = new CopyAsyncRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.join().getId());
        CompletableFuture<AcceptedOperation> copyEntryResponse = client.copyEntryAsync(repoId, testFolder.join().getId(), copyAsyncRequest, true, null);
        String opToken = copyEntryResponse.join().getToken();
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<OperationProgress> getOperationStatusAndProgressResponse = repositoryApiClient.getTasksClient().getOperationStatusAndProgress(repoId, opToken);
        assertTrue(getOperationStatusAndProgressResponse.join().getStatus() == OperationStatus.COMPLETED);
        DeleteEntryWithAuditReason deleteEntryWithAuditReason = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, testFolder.join().getId(), deleteEntryWithAuditReason);
        assertNotNull(deleteEntryResponse.join());
    }

    @Test
    void createCopyEntryCopyShortCut_Success() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";
        Integer parentEntryId = 1;
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(newEntryName);
        CompletableFuture<Entry> createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        Entry targetEntry = createOrCopyEntryResponse.join();
        assertNotNull(targetEntry);
        createdEntries.add(targetEntry);
        assertTrue(targetEntry.getParentId().equals(parentEntryId));
        assertTrue(targetEntry.getEntryType() == EntryType.FOLDER);
        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        createOrCopyEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        assertNotNull(createOrCopyEntryResponse.join());
        createdEntries.add(createOrCopyEntryResponse.join());
        assertTrue(parentEntryId.equals(createOrCopyEntryResponse.join().getParentId()));
        assertTrue(createOrCopyEntryResponse.join().getEntryType().equals(EntryType.SHORTCUT));
        request = new PostEntryChildrenRequest();
        request.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        request.setSourceId(createOrCopyEntryResponse.join().getId());
        CompletableFuture<Entry> newEntryResponse = client.createOrCopyEntry(repoId, parentEntryId, request, true, null);
        createdEntries.add(newEntryResponse.join());
        assertTrue(newEntryResponse.join().getParentId().equals(parentEntryId));
        assertTrue(newEntryResponse.join().getEntryType() == createOrCopyEntryResponse.join().getEntryType());
    }

    @Test
    void moveAndRenameEntry_Success() {
        CompletableFuture<Entry> parentFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ParentFolder", 1, true);
        createdEntries.add(parentFolder.join());
        CompletableFuture<Entry> childFolder = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java ChildFolder", 1, true);
        createdEntries.add(childFolder.join());
        PatchEntryRequest request = new PatchEntryRequest();
        request.setParentId(parentFolder.join().getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");
        CompletableFuture<Entry> movedEntryResponse = client.moveOrRenameDocument(repoId, childFolder.join().getId(), request, true, null);
        assertNotNull(movedEntryResponse);
        assertTrue(movedEntryResponse.join().getId().equals(childFolder.join().getId()));
        assertTrue(movedEntryResponse.join().getParentId().equals(parentFolder.join().getId()));
        assertTrue(movedEntryResponse.join().getName().equals(request.getName()));
    }

    @Test
    void setLinks_Success() {
        CompletableFuture<Entry> sourceEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java SetLinks Source", 1, true);
        createdEntries.add(sourceEntry.join());
        CompletableFuture<Entry> targetEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java SetLinks Target", 1, true);
        createdEntries.add(targetEntry.join());
        PutLinksRequest putLinks = new PutLinksRequest();
        putLinks.setTargetId(targetEntry.join().getId());
        putLinks.setLinkTypeId(1);
        List<PutLinksRequest> request = new ArrayList<PutLinksRequest>();
        request.add(putLinks);
        CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinksResponse = client.assignEntryLinks(repoId, sourceEntry.join().getId(), request);
        List<WEntryLinkInfo> links = assignEntryLinksResponse.join().getValue();
        assertNotNull(links);
        assertTrue(request.size() == links.size());
        assertTrue(sourceEntry.join().getId().equals(links.get(0).getSourceId()));
        assertTrue(targetEntry.join().getId().equals(links.get(0).getTargetId()));
    }
}
