package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        ODataValueContextOfIListOfEntry entryList = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false).join();

        assertNotNull(entryList);
    }

    @Test
    void getFieldValues_Success() {
        ODataValueContextOfIListOfFieldValue fieldValueList = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false).join();

        assertNotNull(fieldValueList);
    }

    @Test
    void getLinkValuesFromEntry_Success() {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client.getLinkValuesFromEntry(repoId, 1, null, null, null, null, null, false).join();

        assertNotNull(linkInfoList);
    }

    @Test
    void deleteEntry_Success() {
        CompletableFuture<Entry> deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true);
        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> deleteEntryResponse = client.deleteEntryInfo(repoId, deleteEntry.join().id, body);
        String token = deleteEntryResponse.join().token;
        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_Success() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client.getTagsAssignedToEntry(repoId, 1, null, null, null, null, null, false).join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getDynamicFieldsEntry_Success() {
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null).join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.value;

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        GetDynamicFieldLogicValueRequest request = new GetDynamicFieldLogicValueRequest();
        request.templateId = templateDefinitions.get(0).id;

        String[] dynamicFieldValueResponse = client.getDynamicFieldValues(repoId, 1, request).join();
        assertNotNull(dynamicFieldValueResponse);
    }
}
