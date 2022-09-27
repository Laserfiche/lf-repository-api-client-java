package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class ImportDocumentApiTest extends BaseTest {
    EntriesClient client;

    int createdEntryId;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createdEntryId = 0;
    }

    @AfterEach
    public void deleteEntries() {
        if (createdEntryId != 0) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            client
                    .deleteEntryInfo(repoId, createdEntryId, body)
                    .join();
        }
    }

    @Test
    void importDocument_DocumentCreated() {
        String fileName = "myFile";
        File toUpload = null;
        try {
            toUpload = File.createTempFile(fileName, "txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(toUpload);

        CreateEntryResult result = client.importDocument(repoId, 1, fileName, true, null,
                toUpload, new PostEntryWithEdocMetadataRequest()).join();

        CreateEntryOperations operations = result.operations;

        assertNotNull(result);
        assertNotNull(operations);
        assertNotNull(result.documentLink);
        assertNotEquals(0, operations.entryCreate.entryId);
        assertEquals(0, operations.entryCreate.exceptions.size());
        assertEquals(0, operations.setEdoc.exceptions.size());
        createdEntryId = operations.entryCreate.entryId;
    }

    @Test
    void importDocument_DocumentCreatedWithTemplate() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null).join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionResult.value;
        assertNotNull(templateDefinitions);
        Assertions.assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionFieldsResult = repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinition.id, null, null, null, null, null, null, null).join();
            if (templateDefinitionFieldsResult.value != null && allFalse(
                    templateDefinitionFieldsResult.value).get()) {
                template = templateDefinition;
                break;
            }
        }
        assertNotNull(template);

        int parentEntryId = 1;
        String fileName = "myFile";
        File toUpload = null;
        try {
            toUpload = File.createTempFile(fileName, "txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(toUpload);
        PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
        request.template = template.name;

        CreateEntryResult result = client.importDocument(repoId, parentEntryId, fileName,
                true, null, toUpload, request).join();

        CreateEntryOperations operations = result.operations;
        assertNotNull(operations);
        assertNotNull(result.documentLink);
        assertEquals(0, operations.entryCreate.exceptions.size());
        assertNotEquals(0, operations.entryCreate.entryId);
        assertEquals(0, operations.setEdoc.exceptions.size());
        assertEquals(0, operations.setTemplate.exceptions.size());
        assertEquals(template.name, operations.setTemplate.template);
        createdEntryId = operations.entryCreate.entryId;
    }
}
