package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForGetEntry;
import com.laserfiche.repository.api.clients.params.ParametersForImportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImportDocumentApiTest extends BaseTest {
    static Entry testClassParentFolder;

    private EntriesClient client;

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
    static void classCleanUp() {
        deleteEntry(testClassParentFolder.getId());
    }

    @Test
    void importEntryCanImportFileAndAssignTemplateAndGeneratePages()
            throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            TemplateDefinition template = null;
            TemplateDefinitionCollectionResponse templateCollectionResponse = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .listTemplateDefinitions(new ParametersForListTemplateDefinitions()
                            .setRepositoryId(repositoryId));
            List<TemplateDefinition> templateDefinitions = templateCollectionResponse.getValue();
            assertNotNull(templateDefinitions);
            assertFalse(templateDefinitions.isEmpty());

            for (TemplateDefinition templateDefinition : templateDefinitions) {
                TemplateFieldDefinitionCollectionResponse fieldCollectionResponse = repositoryApiClient
                        .getTemplateDefinitionClient()
                        .listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                                .setRepositoryId(repositoryId)
                                .setTemplateId(templateDefinition.getId()));
                if (fieldCollectionResponse.getValue() != null
                        && noRequiredFieldDefinitionsInTemplate(fieldCollectionResponse.getValue())) {
                    template = templateDefinition;
                    break;
                }
            }
            assertNotNull(template);

            String fileName = "RepositoryApiClientIntegrationTest Java ImportTest.pdf";
            File fileToImport = new File(SMALL_PDF_FILE_PATH);
            int pageCount = 2;
            fileInputStream = new FileInputStream(fileToImport);
            ImportEntryRequestMetadata metadata = new ImportEntryRequestMetadata();
            metadata.setTemplateName(template.getName());
            ImportEntryRequest request = new ImportEntryRequest();
            request.setMetadata(metadata);
            request.setName(fileName);
            request.setAutoRename(true);
            ImportEntryRequestPdfOptions pdfOptions = new ImportEntryRequestPdfOptions();
            pdfOptions.setGeneratePages(true);
            pdfOptions.setKeepPdfAfterImport(true);
            pdfOptions.setGeneratePagesImageType(GeneratePagesImageType.STANDARD_COLOR);
            request.setPdfOptions(pdfOptions);
            Entry resultEntry = client.importEntry(new ParametersForImportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(testClassParentFolder.getId())
                    .setInputStream(fileInputStream)
                    .setContentType("application/pdf")
                    .setRequestBody(request));

            assertNotNull(resultEntry);
            int createdEntryId = resultEntry.getId();
            assertTrue(createdEntryId > 0);
            assertEquals(template.getName(), resultEntry.getTemplateName());
            assertTrue(resultEntry instanceof Document);
            Document document = (Document) resultEntry;
            assertEquals(pageCount, document.getPageCount());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void importEntryCanImportTextFileAsEDoc()
            throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ImportTest.txt";
            File fileToImport = new File(SMALL_TEXT_FILE_PATH);
            fileInputStream = new FileInputStream(fileToImport);
            String mimeType = "text/plain";
            String extension = "txt";
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            request.setImportAsElectronicDocument(true);
            Entry resultEntry = client.importEntry(new ParametersForImportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(testClassParentFolder.getId())
                    .setInputStream(fileInputStream)
                    .setContentType(mimeType)
                    .setRequestBody(request));

            assertNotNull(resultEntry);
            int createdEntryId = resultEntry.getId();
            assertTrue(createdEntryId > 0);

            // Verify imported entry
            assertTrue(resultEntry instanceof Document);
            Document document = (Document) resultEntry;
            assertEquals(extension, document.getExtension());
            assertEquals(0, document.getPageCount());
            assertEquals(new File(SMALL_TEXT_FILE_PATH).length(), document.getElectronicDocumentSize());
            assertTrue(document.isElectronicDocument());
            assertEquals(mimeType, document.getMimeType());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void importEntryCanImportImageFileAsEDoc()
            throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ImportTest.jpg";
            File fileToImport = new File(SMALL_JPEG_FILE_PATH);
            fileInputStream = new FileInputStream(fileToImport);
            String mimeType = "image/jpeg";
            String extension = "jpg";
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            request.setImportAsElectronicDocument(true);
            Entry resultEntry = client.importEntry(new ParametersForImportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(testClassParentFolder.getId())
                    .setInputStream(fileInputStream)
                    .setContentType(mimeType)
                    .setRequestBody(request));

            assertNotNull(resultEntry);
            int createdEntryId = resultEntry.getId();
            assertTrue(createdEntryId > 0);

            // Verify imported entry
            assertTrue(resultEntry instanceof Document);
            Document document = (Document) resultEntry;
            assertEquals(extension, document.getExtension());
            assertEquals(0, document.getPageCount());
            assertEquals(new File(SMALL_JPEG_FILE_PATH).length(), document.getElectronicDocumentSize());
            assertTrue(document.isElectronicDocument());
            assertEquals(mimeType, document.getMimeType());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void importEntryImportsTextFileAsPagesByDefault()
            throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ImportTest.txt";
            File fileToImport = new File(SMALL_TEXT_FILE_PATH);
            fileInputStream = new FileInputStream(fileToImport);
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            Entry resultEntry = client.importEntry(new ParametersForImportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(testClassParentFolder.getId())
                    .setInputStream(fileInputStream)
                    .setRequestBody(request));

            assertNotNull(resultEntry);
            int createdEntryId = resultEntry.getId();
            assertTrue(createdEntryId > 0);

            // Verify imported entry
            assertTrue(resultEntry instanceof Document);
            Document document = (Document) resultEntry;
            assertEquals("", document.getExtension());
            assertEquals(2, document.getPageCount());
            assertEquals(0, document.getElectronicDocumentSize());
            assertFalse(document.isElectronicDocument());
            assertEquals("", document.getMimeType());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void importEntryImportsImageFileAsPagesByDefault()
            throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ImportTest.jpg";
            File fileToImport = new File(SMALL_JPEG_FILE_PATH);
            fileInputStream = new FileInputStream(fileToImport);
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            Entry resultEntry = client.importEntry(new ParametersForImportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(testClassParentFolder.getId())
                    .setInputStream(fileInputStream)
                    .setRequestBody(request));

            assertNotNull(resultEntry);
            int createdEntryId = resultEntry.getId();
            assertTrue(createdEntryId > 0);

            // Verify imported entry
            assertTrue(resultEntry instanceof Document);
            Document document = (Document) resultEntry;
            assertEquals("", document.getExtension());
            assertEquals(1, document.getPageCount());
            assertEquals(0, document.getElectronicDocumentSize());
            assertFalse(document.isElectronicDocument());
            assertEquals("", document.getMimeType());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    void importEntryCanImportFromURL() throws IOException {
        String fileName = "myFile";
        Entry resultEntry = null;
        URL googleLogoUrl =
                new URL("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
        InputStream inputStream = googleLogoUrl.openStream();
        assertNotNull(inputStream);

        ImportEntryRequest request = new ImportEntryRequest();
        request.setName(fileName);
        request.setAutoRename(true);

        resultEntry = client.importEntry(new ParametersForImportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setInputStream(inputStream)
                .setRequestBody(request));

        assertNotNull(resultEntry);
        int createdEntryId = resultEntry.getId();
        assertTrue(createdEntryId > 0);
    }

    @Test
    void importEntryCanImportFromString() {
        String fileName = "myFile";
        Entry resultEntry = null;
        String fileContent = "This is the file content";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());
        assertNotNull(inputStream);

        ImportEntryRequest request = new ImportEntryRequest();
        request.setName(fileName);
        request.setAutoRename(true);

        resultEntry = client.importEntry(new ParametersForImportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setInputStream(inputStream)
                .setRequestBody(request));

        assertNotNull(resultEntry);
        int createdEntryId = resultEntry.getId();
        assertTrue(createdEntryId > 0);
    }
}
