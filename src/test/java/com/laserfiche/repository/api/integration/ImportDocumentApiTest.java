package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForDeleteEntryInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForImportDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
            client.deleteEntryInfo(new ParametersForDeleteEntryInfo()
                    .setRepoId(repositoryId)
                    .setEntryId(createdEntryId)
                    .setRequestBody(body));
        }
    }

    @Test
    void importDocument_DocumentCreated_FromFile() throws FileNotFoundException {
        String fileName = "myFile";
        File toUpload = null;
        try {
            toUpload = File.createTempFile(fileName, "txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(toUpload);

        CreateEntryResult result = client
                .importDocument(new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(1)
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(new FileInputStream(toUpload))
                        .setRequestBody(new PostEntryWithEdocMetadataRequest()));

        assertNotNull(result);
        CreateEntryOperations operations = result.getOperations();

        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
        assertTrue(createdEntryId > 0);
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
    }

    @Test
    void importDocument_DocumentCreated_FromFile_WithTemplate() throws ExecutionException, InterruptedException, FileNotFoundException {
        WTemplateInfo template = null;
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
        List<WTemplateInfo> templateDefinitions = templateDefinitionResult.getValue();
        assertNotNull(templateDefinitions);
        Assertions.assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionFieldsResult = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(new ParametersForGetTemplateFieldDefinitions()
                            .setRepoId(repositoryId)
                            .setTemplateId(templateDefinition.getId()));
            if (templateDefinitionFieldsResult.getValue() != null && allFalse(
                    templateDefinitionFieldsResult.getValue())) {
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

        CreateEntryResult result = client
                .importDocument(new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(parentEntryId)
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(new FileInputStream(toUpload))
                        .setRequestBody(request));

        CreateEntryOperations operations = result.getOperations();
        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
        assertTrue(createdEntryId > 0);
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
    }

    @Test
    void importDocument_DocumentCreated_FromURL() throws IOException {
        String fileName = "myFile";
        CreateEntryResult result = null;
        URL googleLogoUrl = new URL(
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
        InputStream inputStream = googleLogoUrl.openStream();
        assertNotNull(inputStream);

        result = client
                .importDocument(new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(1)
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(inputStream)
                        .setRequestBody(new PostEntryWithEdocMetadataRequest()));

        assertNotNull(result);
        CreateEntryOperations operations = result.getOperations();

        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
        assertTrue(createdEntryId > 0);
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
    }

    @Test
    void importDocument_DocumentCreated_FromString() {
        String fileName = "myFile";
        CreateEntryResult result = null;
        String fileContent = "This is the file content";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        assertNotNull(inputStream);

        result = client
                .importDocument(new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(1)
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(inputStream)
                        .setRequestBody(new PostEntryWithEdocMetadataRequest()));

        assertNotNull(result);
        CreateEntryOperations operations = result.getOperations();

        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
        assertTrue(createdEntryId > 0);
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
    }

    @Test
    void importDocument_Returns_Detail_When_PartialSuccess_Happens() {
        String fileName = "myFile";
        String fileContent = "This is the file content";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        assertNotNull(inputStream);

        PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
        request.setTemplate("invalidTemplateName");
        ApiException apiException = assertThrows(ApiException.class, () -> client
                .importDocument(new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(1)
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(inputStream)
                        .setRequestBody(request)));

        assertEquals(409, apiException.getStatusCode());
        assertEquals(apiException.getStatusCode(), apiException.getProblemDetails().getStatus());
        assertEquals(apiException.getMessage(), apiException.getProblemDetails().getTitle());
        assertTrue(apiException.getHeaders().size() > 0);
        assertTrue(apiException.getProblemDetails().getExtensions().size() > 0);

        Object obj = apiException.getProblemDetails().getExtensions().get(CreateEntryResult.class.getSimpleName());
        CreateEntryResult result = obj instanceof CreateEntryResult ? (CreateEntryResult) obj : null;

        assertNotNull(result);
        CreateEntryOperations operations = result.getOperations();

        assertNotNull(operations);
        assertNotNull(result.getDocumentLink());
        createdEntryId = operations
                .getEntryCreate()
                .getEntryId();
        assertTrue(createdEntryId > 0);
        assertEquals(0, operations
                .getEntryCreate()
                .getExceptions()
                .size());
        assertEquals(0, operations
                .getSetEdoc()
                .getExceptions()
                .size());
        assertEquals(1, operations
                .getSetTemplate()
                .getExceptions()
                .size());
        APIServerException setTemplateException = operations
                .getSetTemplate()
                .getExceptions()
                .get(0);
        assertTrue(setTemplateException
                .getMessage()
                .startsWith("Template not found."), setTemplateException.getMessage());
        assertEquals(apiException.getMessage(), String.format("EntryId=%s. %s", createdEntryId, setTemplateException.getMessage()));
        assertEquals(HttpURLConnection.HTTP_CONFLICT, setTemplateException.getStatusCode());
        assertEquals(ErrorSource.LASERFICHE_SERVER.getName(), setTemplateException.getErrorSource());
    }
}
