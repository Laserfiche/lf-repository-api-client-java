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
    void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @Test
    void getEntry_Success() {
        CompletableFuture<Entry> future = client.getEntry(repoId, 1, null);
        Entry entry = future.join();

        assertNotNull(entry);
    }

    @Test
    void getEntryListing_Success() {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);
    }

    @Test
    void getEntryListing_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        String nextLink = entryList._atOdataNextLink;
        assertNotNull(nextLink);
        assertTrue(entryList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfEntry> nextLinkResponse = client.getEntryListingNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfEntry nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);

    }

    @Test
    void getEntryListing_ForEach() throws InterruptedException {
        int maxPageSize = 10;
        Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfEntry futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getEntryListingForEach(callback, maxPageSize, repoId, 1, false, null, false, null, null, null, null, null, null, false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getFieldValues_Success() {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);
    }

    @Test
    void getFieldValues_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);

        String nextLink = fieldValueList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(fieldValueList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfFieldValue> nextLinkResponse = client.getFieldValuesNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfFieldValue nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getFieldValues_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfFieldValue futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getFieldValuesForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, null, null, false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLinkValuesFromEntry_Success() {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);
    }

    @Test
    void getLinkValuesFromEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1,  String.format("maxpagesize=%d", maxPageSize), null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);

        if (linkInfoList.value.isEmpty())
        {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = linkInfoList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(linkInfoList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> nextLinkResponse = client.getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWEntryLinkInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getLinkValuesFromEntry_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWEntryLinkInfo futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getLinkValuesFromEntryForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteEntry_Success() {
        Entry entryToDelete = createEntry(createEntryClient,
                "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true)
                .join();

        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, entryToDelete.id,
                new DeleteEntryWithAuditReason());

        String token = deleteEntryResponse.join().token;

        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        if (tagInfoList.value.isEmpty())
        {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = tagInfoList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(tagInfoList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getTagsAssignedToEntry_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWTagInfo futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTagsAssignedToEntryForEach(callback, maxPageSize, repoId, 1, null, null, null, null, null, false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTagsAssignedToEntry_Success() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getDynamicFieldsEntry_Success() {
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null)
                .join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.value;

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        GetDynamicFieldLogicValueRequest request = new GetDynamicFieldLogicValueRequest();
        request.templateId = templateDefinitions.get(0).id;

        Map<String, String[]> dynamicFieldValueResponse = client
                .getDynamicFieldValues(repoId, 1, request)
                .join();
        assertNotNull(dynamicFieldValueResponse);
    }
}
