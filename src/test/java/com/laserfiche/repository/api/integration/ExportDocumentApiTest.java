// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForExportDocument;
import com.laserfiche.repository.api.clients.params.ParametersForExportDocumentWithAuditReason;
import com.laserfiche.repository.api.clients.params.ParametersForGetAuditReasons;
import com.laserfiche.repository.api.clients.params.ParametersForImportDocument;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

import java.io.*;
import java.util.function.Consumer;

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
            CreateEntryResult result = repositoryApiClient
                    .getEntriesClient()
                    .importDocument(new ParametersForImportDocument()
                            .setRepoId(repositoryId)
                            .setParentEntryId(1)
                            .setFileName(fileName)
                            .setAutoRename(true)
                            .setInputStream(new FileInputStream(fileToImport))
                            .setContentType("application/pdf")
                            .setRequestBody(new PostEntryWithEdocMetadataRequest()));

            CreateEntryOperations operations = result.getOperations();
            testEntryId = operations
                    .getEntryCreate()
                    .getEntryId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void classCleanUp() throws InterruptedException {
        deleteEntry(testEntryId);
    }

    @Test
    @EnabledIf("doAuditReasonsNotExist")
    void exportDocument_Returns_Exported_File() {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream f = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        break;
                    }
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        client.exportDocument(new ParametersForExportDocument()
                .setRepoId(repositoryId)
                .setEntryId(testEntryId)
                .setInputStreamConsumer(consumer));
        exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    @DisabledIf("doAuditReasonsNotExist")
    void exportDocumentWithAuditReason_Returns_Exported_File() {
        AuditReasons auditReasons =
                auditReasonsClient.getAuditReasons(new ParametersForGetAuditReasons().setRepoId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream f = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        break;
                    }
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        GetEdocWithAuditReasonRequest requestBody = new GetEdocWithAuditReasonRequest();
        requestBody.setAuditReasonId(auditReasons
                .getExportDocument()
                .get(0)
                .getId());
        requestBody.setComment(auditReasons
                .getExportDocument()
                .get(0)
                .getName());
        client.exportDocumentWithAuditReason(new ParametersForExportDocumentWithAuditReason()
                .setRepoId(repositoryId)
                .setEntryId(testEntryId)
                .setInputStreamConsumer(consumer)
                .setRequestBody(requestBody));
        exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    @EnabledIf("doAuditReasonsNotExist")
    void exportDocumentAsStream_Returns_Exported_File() throws IOException {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream f = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        break;
                    }
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        ParametersForExportDocument input = new ParametersForExportDocument()
                .setRepoId(repositoryId)
                .setEntryId(testEntryId)
                .setInputStreamConsumer(consumer);
        InputStream inputStream = client.exportDocumentAsStream(input);
        input
                .getInputStreamConsumer()
                .accept(inputStream);
        inputStream.close();
        exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    @DisabledIf("doAuditReasonsNotExist")
    void exportDocumentWithAuditReasonAsStream_Returns_Exported_File() throws IOException {
        AuditReasons auditReasons =
                auditReasonsClient.getAuditReasons(new ParametersForGetAuditReasons().setRepoId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream f = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length <= 0) {
                        break;
                    }
                    f.write(buffer, 0, length);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        GetEdocWithAuditReasonRequest requestBody = new GetEdocWithAuditReasonRequest();
        requestBody.setAuditReasonId(auditReasons
                .getExportDocument()
                .get(0)
                .getId());
        requestBody.setComment(auditReasons
                .getExportDocument()
                .get(0)
                .getName());
        ParametersForExportDocumentWithAuditReason input = new ParametersForExportDocumentWithAuditReason()
                .setRepoId(repositoryId)
                .setEntryId(testEntryId)
                .setInputStreamConsumer(consumer)
                .setRequestBody(requestBody);
        InputStream inputStream = client.exportDocumentWithAuditReasonAsStream(input);
        input
                .getInputStreamConsumer()
                .accept(inputStream);
        inputStream.close();
        exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    void exportDocument_Returns_Error_For_Invalid_EntryId() {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            assertTrue(false, "Consumer should not have been called.");
        };
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportDocument(new ParametersForExportDocument()
                    .setRepoId(repositoryId)
                    .setEntryId(-1)
                    .setInputStreamConsumer(consumer));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    @Test
    @DisabledIf("doAuditReasonsNotExist")
    void exportDocumentWithAuditReason_Returns_Error_For_Invalid_EntryId() {
        AuditReasons auditReasons =
                auditReasonsClient.getAuditReasons(new ParametersForGetAuditReasons().setRepoId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            assertTrue(false, "Consumer should not have been called.");
        };
        GetEdocWithAuditReasonRequest requestBody = new GetEdocWithAuditReasonRequest();
        requestBody.setAuditReasonId(auditReasons
                .getExportDocument()
                .get(0)
                .getId());
        requestBody.setComment(auditReasons
                .getExportDocument()
                .get(0)
                .getName());
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportDocumentWithAuditReason(new ParametersForExportDocumentWithAuditReason()
                    .setRepoId(repositoryId)
                    .setEntryId(-1)
                    .setInputStreamConsumer(consumer)
                    .setRequestBody(requestBody));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    @Test
    void exportDocumentAsStream_Returns_Error_For_Invalid_EntryId() {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            assertTrue(false, "Consumer should not have been called.");
        };
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportDocumentAsStream(new ParametersForExportDocument()
                    .setRepoId(repositoryId)
                    .setEntryId(-1)
                    .setInputStreamConsumer(consumer));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    @Test
    @DisabledIf("doAuditReasonsNotExist")
    void exportDocumentWithAuditReasonAsStream_Returns_Error_For_Invalid_EntryId() {
        AuditReasons auditReasons =
                auditReasonsClient.getAuditReasons(new ParametersForGetAuditReasons().setRepoId(repositoryId));
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> consumer = inputStream -> {
            assertTrue(false, "Consumer should not have been called.");
        };
        GetEdocWithAuditReasonRequest requestBody = new GetEdocWithAuditReasonRequest();
        requestBody.setAuditReasonId(auditReasons
                .getExportDocument()
                .get(0)
                .getId());
        requestBody.setComment(auditReasons
                .getExportDocument()
                .get(0)
                .getName());
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.exportDocumentWithAuditReasonAsStream(new ParametersForExportDocumentWithAuditReason()
                    .setRepoId(repositoryId)
                    .setEntryId(-1)
                    .setInputStreamConsumer(consumer)
                    .setRequestBody(requestBody));
        });
        Assertions.assertEquals(
                "Specified argument was out of the range of valid values. (Parameter 'entryId')", thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    boolean doAuditReasonsNotExist() {
        try {
            AuditReasons auditReasons = repositoryApiClient
                    .getAuditReasonsClient()
                    .getAuditReasons(
                            new ParametersForGetAuditReasons().setRepoId(repositoryId));
            if (auditReasons
                    .getExportDocument()
                    .size() == 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
