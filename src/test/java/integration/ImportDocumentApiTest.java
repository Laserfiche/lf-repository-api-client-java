package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
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

        CreateEntryOperations operations = result.getOperations();

        assertNotNull(result);
        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        assertNotEquals(0, operations
                .getEntryCreate()
                .getEntryId());
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
    }

    @Test
    void importDocument_DocumentCreatedWithTemplate() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null).join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionResult.getValue();
        assertNotNull(templateDefinitions);
        Assertions.assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionFieldsResult = repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId,
                    templateDefinition.getId(), null, null, null, null, null, null, null).join();
            if (templateDefinitionFieldsResult.getValue() != null && allFalse(
                    templateDefinitionFieldsResult.getValue()).get()) {
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
        request.setTemplate(template.getName());

        CreateEntryResult result = client.importDocument(repoId, parentEntryId, fileName,
                true, null, toUpload, request).join();

        CreateEntryOperations operations = result.getOperations();
        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertNotEquals(0, operations
                .getEntryCreate()
                .getEntryId());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetTemplate()
                .getExceptions()
                .size());
        assertEquals(template.getName(), operations
                .getSetTemplate()
                .getTemplate());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
    }
}
