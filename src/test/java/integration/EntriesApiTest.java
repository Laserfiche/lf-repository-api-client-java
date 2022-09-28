package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class EntriesApiTest extends BaseTest {
    EntriesClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @Test
    void getEntry_ReturnRootFolder() {
        CompletableFuture<Entry> future = client.getEntry(repoId, 1, null);
        Entry entry = future.join();

        assertNotNull(entry);
    }

    @Test
    void getEntryListing_ReturnEntries() {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);
    }

    @Test
    void getEntryListing_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        String nextLink = entryList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(entryList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfEntry> nextLinkResponse = client.getEntryListingNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfEntry nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);

    }

    @Test
    void getEntryListing_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfEntry futureResult = data.join();
            if (futureResult.getAtOdataNextLink() != null) {
                assertNotEquals(0, futureResult.getValue().size());
                assertTrue(futureResult.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getEntryListingForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getFieldValues_ReturnFields() {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);
    }

    @Test
    void getFieldValues_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);

        String nextLink = fieldValueList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(fieldValueList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfFieldValue> nextLinkResponse = client.getFieldValuesNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfFieldValue nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getFieldValues_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfFieldValue futureResult = data.join();
            if (futureResult.getAtOdataNextLink() != null) {
                assertNotEquals(0, futureResult.getValue().size());
                assertTrue(futureResult.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getFieldValuesForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLinkValuesFromEntry_ReturnLinks() {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);
    }

    @Test
    void getLinkValuesFromEntry_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);

        String nextLink = linkInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(linkInfoList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> nextLinkResponse = client.getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWEntryLinkInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getLinkValuesFromEntry_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWEntryLinkInfo futureResult = data.join();
            if (futureResult.getAtOdataNextLink() != null) {
                assertNotEquals(0, futureResult.getValue().size());
                assertTrue(futureResult.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getLinkValuesFromEntryForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteEntry_ReturnOperationToken() {
        Entry entryToDelete = createEntry(createEntryClient,
                "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true)
                .join();

        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, entryToDelete.id,
                new DeleteEntryWithAuditReason());

        String token = deleteEntryResponse.join().getToken();

        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(tagInfoList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getTagsAssignedToEntry_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWTagInfo futureResult = data.join();
            if (futureResult.getAtOdataNextLink() != null) {
                assertNotEquals(0, futureResult.getValue().size());
                assertTrue(futureResult.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTagsAssignedToEntryForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTagsAssignedToEntry_ReturnTags() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getDynamicFieldsEntry_ReturnDynamicFields() {
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null)
                .join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        GetDynamicFieldLogicValueRequest request = new GetDynamicFieldLogicValueRequest();
        request.setTemplateId(templateDefinitions.get(0).getId());

        Map<String, String[]> dynamicFieldValueResponse = client
                .getDynamicFieldValues(repoId, 1, request)
                .join();
        assertNotNull(dynamicFieldValueResponse);
    }
}
