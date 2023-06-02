package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForImportDocument;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.*;

public class ImportDocumentApiTest extends BaseTest {
  static Entry testClassParentFolder;

  EntriesClient client;

  @BeforeEach
  public void perTestSetup() {
    client = repositoryApiClient.getEntriesClient();
  }

  @BeforeAll
  static void classSetup() {
    String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
    testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
  }

  @AfterAll
  static void classCleanUp() throws InterruptedException {
    deleteEntry(testClassParentFolder.getId());
  }

  @Test
  void importDocument_DocumentCreated_FromFile_WithTemplate()
      throws ExecutionException, InterruptedException, FileNotFoundException {
    WTemplateInfo template = null;
    ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult =
        repositoryApiClient
            .getTemplateDefinitionClient()
            .getTemplateDefinitions(
                new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
    List<WTemplateInfo> templateDefinitions = templateDefinitionResult.getValue();
    assertNotNull(templateDefinitions);
    Assertions.assertTrue(templateDefinitions.size() > 0);
    for (WTemplateInfo templateDefinition : templateDefinitions) {
      ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionFieldsResult =
          repositoryApiClient
              .getTemplateDefinitionClient()
              .getTemplateFieldDefinitions(
                  new ParametersForGetTemplateFieldDefinitions()
                      .setRepoId(repositoryId)
                      .setTemplateId(templateDefinition.getId()));
      if (templateDefinitionFieldsResult.getValue() != null
          && noRequiredFieldDefinitionsInTemplate(templateDefinitionFieldsResult.getValue())) {
        template = templateDefinition;
        break;
      }
    }
    assertNotNull(template);

    String fileName = "RepositoryApiClientIntegrationTest Java ImportTest";
    File fileToImport = new File(TEST_FILE_PATH);
    PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
    request.setTemplate(template.getName());
    CreateEntryResult result =
        client.importDocument(
            new ParametersForImportDocument()
                .setRepoId(repositoryId)
                .setParentEntryId(testClassParentFolder.getId())
                .setFileName(fileName)
                .setAutoRename(true)
                .setInputStream(new FileInputStream(fileToImport))
                .setContentType("application/pdf")
                .setRequestBody(request));

    CreateEntryOperations operations = result.getOperations();
    assertNotNull(operations);
    assertNotNull(result.getDocumentLink());
    assertEquals(0, operations.getEntryCreate().getExceptions().size());
    int createdEntryId = operations.getEntryCreate().getEntryId();
    assertTrue(createdEntryId > 0);
    assertEquals(0, operations.getSetEdoc().getExceptions().size());
    assertEquals(0, operations.getSetTemplate().getExceptions().size());
    assertEquals(template.getName(), operations.getSetTemplate().getTemplate());
  }

  @Test
  void importDocument_DocumentCreated_FromURL() throws IOException {
    String fileName = "myFile";
    CreateEntryResult result = null;
    URL googleLogoUrl =
        new URL(
            "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
    InputStream inputStream = googleLogoUrl.openStream();
    assertNotNull(inputStream);

    result =
        client.importDocument(
            new ParametersForImportDocument()
                .setRepoId(repositoryId)
                .setParentEntryId(testClassParentFolder.getId())
                .setFileName(fileName)
                .setAutoRename(true)
                .setInputStream(inputStream)
                .setRequestBody(new PostEntryWithEdocMetadataRequest()));

    assertNotNull(result);
    CreateEntryOperations operations = result.getOperations();

    assertNotNull(operations);
    assertNotNull(result.getDocumentLink());
    int createdEntryId = operations.getEntryCreate().getEntryId();
    assertTrue(createdEntryId > 0);
    assertEquals(0, operations.getEntryCreate().getExceptions().size());
    assertEquals(0, operations.getSetEdoc().getExceptions().size());
  }

  @Test
  void importDocument_DocumentCreated_FromString() {
    String fileName = "myFile";
    CreateEntryResult result = null;
    String fileContent = "This is the file content";
    InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
    assertNotNull(inputStream);

    result =
        client.importDocument(
            new ParametersForImportDocument()
                .setRepoId(repositoryId)
                .setParentEntryId(testClassParentFolder.getId())
                .setFileName(fileName)
                .setAutoRename(true)
                .setInputStream(inputStream)
                .setRequestBody(new PostEntryWithEdocMetadataRequest()));

    assertNotNull(result);
    CreateEntryOperations operations = result.getOperations();

    assertNotNull(operations);
    assertNotNull(result.getDocumentLink());
    int createdEntryId = operations.getEntryCreate().getEntryId();
    assertTrue(createdEntryId > 0);
    assertEquals(0, operations.getEntryCreate().getExceptions().size());
    assertEquals(0, operations.getSetEdoc().getExceptions().size());
  }

  @Test
  void importDocument_Returns_Detail_When_PartialSuccess_Happens() {
    String fileName = "myFile";
    String fileContent = "This is the file content";
    InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
    assertNotNull(inputStream);

    PostEntryWithEdocMetadataRequest request = new PostEntryWithEdocMetadataRequest();
    request.setTemplate("invalidTemplateName");
    ApiException apiException =
        assertThrows(
            ApiException.class,
            () ->
                client.importDocument(
                    new ParametersForImportDocument()
                        .setRepoId(repositoryId)
                        .setParentEntryId(testClassParentFolder.getId())
                        .setFileName(fileName)
                        .setAutoRename(true)
                        .setInputStream(inputStream)
                        .setRequestBody(request)));

    assertEquals(409, apiException.getStatusCode());
    assertEquals(apiException.getStatusCode(), apiException.getProblemDetails().getStatus());
    assertEquals(apiException.getMessage(), apiException.getProblemDetails().getTitle());
    assertTrue(apiException.getHeaders().size() > 0);
    assertTrue(apiException.getProblemDetails().getExtensions().size() > 0);

    Object obj =
        apiException
            .getProblemDetails()
            .getExtensions()
            .get(CreateEntryResult.class.getSimpleName());
    CreateEntryResult result = obj instanceof CreateEntryResult ? (CreateEntryResult) obj : null;

    assertNotNull(result);
    CreateEntryOperations operations = result.getOperations();

    assertNotNull(operations);
    assertNotNull(result.getDocumentLink());
    int createdEntryId = operations.getEntryCreate().getEntryId();
    assertTrue(createdEntryId > 0);
    assertEquals(0, operations.getEntryCreate().getExceptions().size());
    assertEquals(0, operations.getSetEdoc().getExceptions().size());
    assertEquals(1, operations.getSetTemplate().getExceptions().size());
    APIServerException setTemplateException = operations.getSetTemplate().getExceptions().get(0);
    assertTrue(
        setTemplateException.getMessage().startsWith("Template not found."),
        setTemplateException.getMessage());
    assertEquals(
        apiException.getMessage(),
        String.format("EntryId=%s. %s", createdEntryId, setTemplateException.getMessage()));
    assertEquals(HttpURLConnection.HTTP_CONFLICT, setTemplateException.getStatusCode());
    assertEquals(ErrorSource.LASERFICHE_SERVER.getName(), setTemplateException.getErrorSource());
  }
}
