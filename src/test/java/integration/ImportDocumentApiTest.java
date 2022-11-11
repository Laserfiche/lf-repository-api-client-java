package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
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
            client
                    .deleteEntryInfo(repoId, createdEntryId, body)
                    .join();
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
                .importDocument(repoId, 1, fileName, true, null,
                        new FileInputStream(toUpload), new PostEntryWithEdocMetadataRequest())
                .join();

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
        assertTrue(createdEntryId > 0);
    }

    @Test
    void importDocument_DocumentCreated_FromFile_WithTemplate() throws ExecutionException, InterruptedException, FileNotFoundException {
        WTemplateInfo template = null;
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null)
                .join();
        List<WTemplateInfo> templateDefinitions = templateDefinitionResult.getValue();
        assertNotNull(templateDefinitions);
        Assertions.assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionFieldsResult = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(repoId,
                            templateDefinition.getId(), null, null, null, null, null, null, null)
                    .join();
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

        CreateEntryResult result = client
                .importDocument(repoId, parentEntryId, fileName,
                        true, null, new FileInputStream(toUpload), request)
                .join();

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
        assertTrue(createdEntryId > 0);
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
                .importDocument(repoId, 1, fileName, true, null,
                        inputStream, new PostEntryWithEdocMetadataRequest())
                .join();

        assertNotNull(result);
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
        assertTrue(createdEntryId > 0);
    }

    @Test
    void importDocument_DocumentCreated_FromString() throws IOException {
        String fileName = "myFile";
        CreateEntryResult result = null;
        String fileContent = "This is the file content";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        assertNotNull(inputStream);

        result = client
                .importDocument(repoId, 1, fileName, true, null,
                        inputStream, new PostEntryWithEdocMetadataRequest())
                .join();

        assertNotNull(result);
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
        assertTrue(createdEntryId > 0);
    }

    @Test
    void importDocument_Returns_Detail_When_PartialSuccess_Happens() throws IOException {
        String fileName = "myFile";
        CreateEntryResult result = null;
        String fileContent = "This is the file content";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        assertNotNull(inputStream);

        PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
        request.setTemplate("invalidTemplateName");
        result = client
                .importDocument(repoId, 1, fileName, true, null,
                        inputStream, request)
                .join();

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
        assertEquals(HttpURLConnection.HTTP_CONFLICT, setTemplateException.getStatusCode());
        assertEquals(ErrorSource.LASERFICHE_SERVER.getName(), setTemplateException.getErrorSource());
    }
}
