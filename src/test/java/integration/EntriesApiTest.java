package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntriesApiTest extends BaseTest {
    EntriesClient client;

    private final int maxPageSize = 1;


    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
    }

    @Test
    void getEntry_Success() {
        CompletableFuture<Entry> future = client.getEntry(repoId, 1, null);
        Entry entry = future.join();

        assertNotNull(entry);
    }

    @Test
    void getEntryListing_Success() {
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfEntry entryList = future.join();
        assertNotNull(entryList);
    }

    @Test
    void getEntryListingNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
        assertNotNull(entryList.getAtOdataNextLink());

        String nextLink = entryList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfEntry> newFuture = client.getEntryListingNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfEntry newEntryList = newFuture.join();

        assertNotNull(newEntryList);
    }

    @Test
    void getEntryListingForEachTest_Success() {
        client.getEntryListingForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfEntry entryList = future.join();
            if (entryList != null) {
                assertNotNull(entryList.getValue());
            }
            return entryList != null; // Stop asking if there's no data.
        }), repoId, 571, false, null, false, null, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    void getFieldValues_Success() {
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
    }

    @Test
    void getFieldValuesNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
        assertNotNull(fieldValueList.getAtOdataNextLink());

        String nextLink = fieldValueList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfFieldValue> newFuture = client.getFieldValuesNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfFieldValue newFieldValueList = newFuture.join();

        assertNotNull(newFieldValueList);
    }

    @Test
    void getFieldValuesForEach_Success() {
        client.getFieldValuesForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfFieldValue fieldValueList = future.join();
            if (fieldValueList != null) {
                assertNotNull(fieldValueList.getValue());
            }
            return fieldValueList != null; // Stop asking if there's no data.
        }), repoId, 1, null, null, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    void getLinkValuesFromEntry_Success() {
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> future = client.getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = future.join();

        assertNotNull(linkInfoList);
    }

    @Test
    void getLinkValuesFromEntryNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> future = client.getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = future.join();

        assertNotNull(linkInfoList);
        assertNotNull(linkInfoList.getAtOdataNextLink());

        String nextLink = linkInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> newFuture = client.getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWEntryLinkInfo newLinkInfoList = newFuture.join();

        assertNotNull(newLinkInfoList);
    }

    @Test
    void getLinkValuesFromEntryForEach_Success() {
        client.getLinkValuesFromEntryForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWEntryLinkInfo linkValueList = future.join();
            if (linkValueList != null) {
                assertNotNull(linkValueList.getValue());
            }
            return linkValueList != null; // Stop asking if there's no data.
        }), repoId, 1, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    void deleteEntry_Success() {
        RepositoryApiClient client2 = repositoryApiClient;
        CompletableFuture<Entry> deleteEntry = createEntry(client2, "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true);
        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, deleteEntry.join().getId(), body);
        String token = deleteEntryResponse.join().getToken();
        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getTagsAssignedToEntryNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
        assertNotNull(tagInfoList.getAtOdataNextLink());

        String nextLink = tagInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> newFuture = client.getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWTagInfo newTagInfoList = newFuture.join();

        assertNotNull(newTagInfoList);
    }

    @Test
    void getTagsAssignedToEntryForEach_Success() {
        client.getTagsAssignedToEntryForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWTagInfo tagList = future.join();
            if (tagList != null) {
                assertNotNull(tagList.getValue());
            }
            return tagList != null; // Stop asking if there's no data.
        }), repoId, 1, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    void getDynamicFieldsEntry_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        GetDynamicFieldLogicValueRequest request = new GetDynamicFieldLogicValueRequest();
        request.setTemplateId(templateDefinitions.get(0).getId());
        CompletableFuture<Map<String, List<String>>> dynamicFieldValueResponse = client.getDynamicFieldValues(repoId, 1, request);
        assertNotNull(dynamicFieldValueResponse.join());
    }
}
