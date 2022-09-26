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
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        String nextLink = entryList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
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
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false)
                .join();

        assertNotNull(entryList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfEntry, CompletableFuture<Boolean>> callback = data -> {
            if (data._atOdataNextLink != null) {
                assertNotEquals(0, data.value.size());
                assertTrue(data.value.size() <= maxPageSize);
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
    void getFieldValues_Success() {
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

        String nextLink = fieldValueList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
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
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(repoId, 1, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldValueList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfFieldValue, CompletableFuture<Boolean>> callback = data -> {
            if (data._atOdataNextLink != null) {
                assertNotEquals(0, data.value.size());
                assertTrue(data.value.size() <= maxPageSize);
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
    void getLinkValuesFromEntry_Success() {
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

        String nextLink = linkInfoList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
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
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(linkInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWEntryLinkInfo, CompletableFuture<Boolean>> callback = data -> {
            if (data._atOdataNextLink != null) {
                assertNotEquals(0, data.value.size());
                assertTrue(data.value.size() <= maxPageSize);
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
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
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
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWTagInfo, CompletableFuture<Boolean>> callback = data -> {
            if (data._atOdataNextLink != null) {
                assertNotEquals(0, data.value.size());
                assertTrue(data.value.size() <= maxPageSize);
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
