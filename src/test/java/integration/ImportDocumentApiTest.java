package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportDocumentApiTest extends BaseTest {
    EntriesClient client;

    int createdEntryId;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createdEntryId = 0;
    }

    @AfterEach
    public void deleteEntries() {
        if (createdEntryId != 0) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            client.deleteEntryInfo(repoId, createdEntryId, body);
        }
    }

    @Test
    void importDocument_Success() throws IOException {
        String fileName = "RepositoryApiClientIntegrationTest Java ImportDocument";
        PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
        RequestBody electronicDocument = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {

            }
        };
        File file = new File("test.pdf");
        BufferedSink writer = Okio.buffer(Okio.sink(file));
        electronicDocument.writeTo(writer);

        CompletableFuture<CreateEntryResult> importDocumentResponse = client.importDocument(repoId, 1, fileName, electronicDocument, request, true, null);
        CreateEntryOperations operations = importDocumentResponse.join().getOperations();
        assertNotNull(operations);
        assertTrue(operations.getEntryCreate().getExceptions().size() == 0);
        assertTrue(!operations.getEntryCreate().getEntryId().equals(0));
        assertTrue(operations.getSetEdoc().getExceptions().size() == 0);
        assertTrue(importDocumentResponse.join().getDocumentLink() != null);
        createdEntryId = operations.getEntryCreate().getEntryId();
    }

    @Test
    void importDocumentCreatedWithTemplate_Success() throws IOException, InterruptedException, ExecutionException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionResponse.join().getValue();
        assertNotNull(templateDefinitions);
        for (int i = 0; i < templateDefinitions.size(); i++) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinitions.get(i).getId(), null, null, null, null, null, null, null, null);
            if (templateDefinitionsFieldsResponse.join().getValue() != null && allFalse(templateDefinitionsFieldsResponse.join().getValue()).get()) {
                template = templateDefinitions.get(i);
                break;
            }
        }

        int parentEntryId = 1;
        String fileName = "RepositoryApiClientIntegrationTest Java ImportDocument";
        RequestBody electronicDocument = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {

            }
        };
        File file = new File("test.pdf");
        BufferedSink writer = Okio.buffer(Okio.sink(file));
        electronicDocument.writeTo(writer);
        PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
        request.setTemplate(template.getName());
        CompletableFuture<CreateEntryResult> importDocumentResponse = client.importDocument(repoId, parentEntryId, fileName, electronicDocument, request, true, null);
        CreateEntryOperations operations = importDocumentResponse.join().getOperations();
        assertNotNull(operations);
        assertTrue(operations.getEntryCreate().getExceptions().size() == 0);
        assertTrue(operations.getEntryCreate().getEntryId() != 0);
        assertTrue(operations.getSetEdoc().getExceptions().size() == 0);
        assertTrue(importDocumentResponse.join().getDocumentLink() != null);
        assertTrue(operations.getSetTemplate().getExceptions().size() == 0);
        assertTrue(operations.getSetTemplate().getTemplate().equals(template.getName()));
        createdEntryId = operations.getEntryCreate().getEntryId();
    }
}
