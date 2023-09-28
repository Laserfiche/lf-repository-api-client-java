package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForExportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForImportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListAuditReasons;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

import java.io.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class ExportDocumentApiTest extends BaseTest {
    private EntriesClient client;
    private AuditReasonsClient auditReasonsClient;
    private static int testEntryId;
    private static long testEntryFileSize;
    private static File exportedFile;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        auditReasonsClient = repositoryApiClient.getAuditReasonsClient();
        exportedFile = null;
    }

    @AfterEach
    void perTestCleanUp() throws InterruptedException {
        if (exportedFile != null) {
            exportedFile.delete();
        }
    }

    @BeforeAll
    static void classSetup() {
        try {
            // Import a document that will be exported by tests in this class
            String fileName = "RepositoryApiClientIntegrationTest Java ExportDocumentApiTest";
            File fileToImport = new File(TEST_FILE_PATH);
            testEntryFileSize = fileToImport.length();
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            Entry resultEntry = repositoryApiClient
                    .getEntriesClient()
                    .importEntry(new ParametersForImportEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(1)
                            .setInputStream(new FileInputStream(fileToImport))
                            .setContentType("application/pdf")
                            .setRequestBody(request));

            testEntryId = resultEntry.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void classCleanUp() throws InterruptedException {
        deleteEntry(testEntryId);
    }

    @Test
    @DisabledIf("isThereAnyAuditReasonForExport")
    void exportDocument_Returns_Exported_File() {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId));
        String uri = response.getValue();
        assertNotNull(uri);
        exportedFile = new File(FILE_NAME);
        boolean downloaded = downloadExportedEntry(uri, exportedFile);
        assertTrue(downloaded);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    @EnabledIf("isThereAnyAuditReasonForExport")
    void exportDocumentWithAuditReason_Returns_Exported_File() {
        AuditReasonCollectionResponse auditReasons =
                auditReasonsClient.listAuditReasons(new ParametersForListAuditReasons().setRepositoryId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        exportedFile = new File(FILE_NAME);
        ExportEntryRequest requestBody = new ExportEntryRequest();
        AuditReason exportAuditReason = auditReasons.getValue().stream().filter(auditReason -> auditReason.getAuditEventType() == AuditEventType.EXPORT_DOCUMENT).findFirst().get();
        requestBody.setAuditReasonId(exportAuditReason.getId());
        requestBody.setAuditReasonComment(exportAuditReason.getName());
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(requestBody));
        String uri = response.getValue();
        assertNotNull(uri);
        exportedFile = new File(FILE_NAME);
        boolean downloaded = downloadExportedEntry(uri, exportedFile);
        assertTrue(downloaded);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    void exportDocument_Returns_Error_For_Invalid_EntryId() {
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportEntry(new ParametersForExportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(-1));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
    }

    @Test
    @EnabledIf("isThereAnyAuditReasonForExport")
    void exportDocumentWithAuditReason_Returns_Error_For_Invalid_EntryId() {
        AuditReasonCollectionResponse auditReasons =
                auditReasonsClient.listAuditReasons(new ParametersForListAuditReasons().setRepositoryId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        exportedFile = new File(FILE_NAME);
        ExportEntryRequest requestBody = new ExportEntryRequest();
        AuditReason exportAuditReason = auditReasons.getValue().stream().filter(auditReason -> auditReason.getAuditEventType() == AuditEventType.EXPORT_DOCUMENT).findFirst().get();
        requestBody.setAuditReasonId(exportAuditReason.getId());
        requestBody.setAuditReasonComment(exportAuditReason.getName());
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportEntry(new ParametersForExportEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(-1)
                    .setRequestBody(requestBody));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
    }

    boolean isThereAnyAuditReasonForExport() {
        try {
            AuditReasonCollectionResponse auditReasons = repositoryApiClient
                    .getAuditReasonsClient()
                    .listAuditReasons(
                            new ParametersForListAuditReasons().setRepositoryId(repositoryId));
            long count = auditReasons.getValue().stream().filter(auditReason -> auditReason.getAuditEventType() == AuditEventType.EXPORT_DOCUMENT).count();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean downloadExportedEntry(String uri, File destinationFile) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(uri).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
