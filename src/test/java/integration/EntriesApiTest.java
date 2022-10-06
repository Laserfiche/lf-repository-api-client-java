package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
                .getEntryListing(repoId, 1, false, null, false, "maxpagesize=5", null, null, null, null, null, false)
                .join();
        assertNotNull(entryList);
    }

    @Test
    void getEntryListing_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, String.format("maxpagesize=%d", maxPageSize), null,
                        null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        String nextLink = entryList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(entryList
                .getValue()
                .size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfEntry> nextLinkResponse = client.getEntryListingNextLink(nextLink,
                maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfEntry nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getEntryListing_ForEach() throws InterruptedException {
        int maxPageSize = 10;
        Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfEntry futureResult = data.join();
            if (futureResult.getOdataNextLink() != null) {
                assertNotEquals(0, futureResult
                        .getValue()
                        .size());
                assertTrue(futureResult
                        .getValue()
                        .size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        client.getEntryListingForEach(callback, maxPageSize, repoId, 1, false, null, false, null, null, null, null,
                null, null, false);
    }

    @Test
    void getFieldValues_ReturnFields() {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);
    }

    @Test
    void getLinkValuesFromEntry_ReturnLinks() {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);
    }

    @Test
    void getFieldValues_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null,
                        null, false)
                .join();

        assertNotNull(fieldValueList);

        String nextLink = fieldValueList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(fieldValueList
                .getValue()
                .size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfFieldValue> nextLinkResponse = client.getFieldValuesNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfFieldValue nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getFieldValues_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfFieldValue futureResult = data.join();
            if (futureResult.getOdataNextLink() != null) {
                assertNotEquals(0, futureResult
                        .getValue()
                        .size());
                assertTrue(futureResult
                        .getValue()
                        .size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        client.getFieldValuesForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, null, null,
                false);
    }


    @Test
    void getLinkValuesFromEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, String.format("maxpagesize=%d", maxPageSize), null, null, null, null,
                        false)
                .join();

        assertNotNull(linkInfoList);

        if (linkInfoList
                .getValue()
                .isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = linkInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(linkInfoList
                .getValue()
                .size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> nextLinkResponse = client.getLinkValuesFromEntryNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWEntryLinkInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getLinkValuesFromEntry_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWEntryLinkInfo futureResult = data.join();
            if (futureResult.getOdataNextLink() != null) {
                assertNotEquals(0, futureResult
                        .getValue()
                        .size());
                assertTrue(futureResult
                        .getValue()
                        .size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        client.getLinkValuesFromEntryForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, false);
    }

    @Test
    void deleteEntry_ReturnOperationToken() {
        Entry entryToDelete = createEntry(createEntryClient,
                "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true)
                .join();

        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, entryToDelete.getId(),
                new DeleteEntryWithAuditReason());

        String token = deleteEntryResponse
                .join()
                .getToken();

        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, String.format("maxpagesize=%d", maxPageSize), null, null, null, null,
                        false)
                .join();

        assertNotNull(tagInfoList);

        if (tagInfoList
                .getValue()
                .isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = tagInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList
                .getValue()
                .size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagsAssignedToEntryNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTagsAssignedToEntry_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWTagInfo futureResult = data.join();
            if (futureResult.getOdataNextLink() != null) {
                assertNotEquals(0, futureResult
                        .getValue()
                        .size());
                assertTrue(futureResult
                        .getValue()
                        .size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        client.getTagsAssignedToEntryForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, false);
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
        request.setTemplateId(templateDefinitions
                .get(0)
                .getId());

        Map<String, String[]> dynamicFieldValueResponse = client
                .getDynamicFieldValues(repoId, 1, request)
                .join();
        assertNotNull(dynamicFieldValueResponse);
    }

    @Test
    void getDocumentContentType_ReturnsExpectedHeaders() throws InterruptedException {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, "maxpagesize=100", null, null, null, null, null, false)
                .join();
        assertNotNull(entryList);

        Optional<Entry> optionalEntry = entryList
                .getValue()
                .stream()
                .filter(entry -> entry.getEntryType() == EntryType.DOCUMENT)
                .findFirst();
        Entry entry = optionalEntry.get();
        assertNotNull(entry);

        Map<String, String> headers = client
                .getDocumentContentType(repoId, entry.getId())
                .join();
        assertNotNull(headers.get("Content-Type"));
        assertNotNull(headers.get("Content-Length"));
    }

}
