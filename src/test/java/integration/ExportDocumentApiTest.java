package integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class ExportDocumentApiTest extends BaseTest {
    private EntriesClient client;
    private AuditReasonsClient auditReasonsClient;
    private int createdEntryId;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        auditReasonsClient = repositoryApiClient.getAuditReasonsClient();
        createEmptyDocument();
    }

    @AfterEach
    public void deleteEntries() {
        if (createdEntryId != 0) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            client
                    .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                            .setRepoId(repositoryId)
                            .setEntryId(createdEntryId)
                            .setRequestBody(body));
        }
    }


    @Test
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
                .setEntryId(createdEntryId)
                .setInputStreamConsumer(consumer));
        File exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(0, exportedFile.length());
        boolean exportedFileIsDeletedSuccessfully = exportedFile.delete();
        assertTrue(exportedFileIsDeletedSuccessfully);
    }

    @Test
    void exportDocumentWithAuditReason_Returns_Exported_File() {
        AuditReasons auditReasons = auditReasonsClient.getAuditReasons(
                new ParametersForGetAuditReasons().setRepoId(repositoryId));
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
                .setEntryId(createdEntryId)
                .setInputStreamConsumer(consumer)
                .setRequestBody(requestBody));
        File exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(0, exportedFile.length());
        boolean exportedFileIsDeletedSuccessfully = exportedFile.delete();
        assertTrue(exportedFileIsDeletedSuccessfully);
    }

    @Test
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
                .setEntryId(createdEntryId)
                .setInputStreamConsumer(consumer);
        InputStream inputStream = client.exportDocumentAsStream(input);
        input
                .getInputStreamConsumer()
                .accept(inputStream);
        inputStream.close();
        File exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(0, exportedFile.length());
        boolean exportedFileIsDeletedSuccessfully = exportedFile.delete();
        assertTrue(exportedFileIsDeletedSuccessfully);
    }

    @Test
    void exportDocumentWithAuditReasonAsStream_Returns_Exported_File() throws IOException {
        AuditReasons auditReasons = auditReasonsClient.getAuditReasons(
                new ParametersForGetAuditReasons().setRepoId(repositoryId));
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
                .setEntryId(createdEntryId)
                .setInputStreamConsumer(consumer)
                .setRequestBody(requestBody);
        InputStream inputStream = client.exportDocumentWithAuditReasonAsStream(input);
        input
                .getInputStreamConsumer()
                .accept(inputStream);
        inputStream.close();
        File exportedFile = new File(FILE_NAME);
        assertTrue(exportedFile.exists());
        assertEquals(0, exportedFile.length());
        boolean exportedFileIsDeletedSuccessfully = exportedFile.delete();
        assertTrue(exportedFileIsDeletedSuccessfully);
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
                    .setEntryId(-createdEntryId)
                    .setInputStreamConsumer(consumer));
        });
        Assertions.assertEquals("Specified argument was out of the range of valid values. (Parameter 'entryId')",
                thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    @Test
    void exportDocumentWithAuditReason_Returns_Error_For_Invalid_EntryId() {
        AuditReasons auditReasons = auditReasonsClient.getAuditReasons(
                new ParametersForGetAuditReasons().setRepoId(repositoryId));
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
                    .setEntryId(-createdEntryId)
                    .setInputStreamConsumer(consumer)
                    .setRequestBody(requestBody));
        });
        Assertions.assertEquals("Specified argument was out of the range of valid values. (Parameter 'entryId')",
                thrown.getMessage());
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
                    .setEntryId(-createdEntryId)
                    .setInputStreamConsumer(consumer));
        });
        Assertions.assertEquals("Specified argument was out of the range of valid values. (Parameter 'entryId')",
                thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    @Test
    void exportDocumentWithAuditReasonAsStream_Returns_Error_For_Invalid_EntryId() {
        AuditReasons auditReasons = auditReasonsClient.getAuditReasons(
                new ParametersForGetAuditReasons().setRepoId(repositoryId));
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
                    .setEntryId(-createdEntryId)
                    .setInputStreamConsumer(consumer)
                    .setRequestBody(requestBody));
        });
        Assertions.assertEquals("Specified argument was out of the range of valid values. (Parameter 'entryId')",
                thrown.getMessage());
        File exportedFile = new File(FILE_NAME);
        assertNotNull(exportedFile);
        assertFalse(exportedFile.exists());
    }

    private void createEmptyDocument() {
        try {
            String fileName = "JavaClientLibrary_ExportDocumentApiTest";
            File toUpload = File.createTempFile(fileName, "txt");
            CreateEntryResult result = client
                    .importDocument(new ParametersForImportDocument()
                            .setRepoId(repositoryId)
                            .setParentEntryId(1)
                            .setFileName(fileName)
                            .setAutoRename(true)
                            .setInputStream(new FileInputStream(toUpload))
                            .setRequestBody(new PostEntryWithEdocMetadataRequest()));

            CreateEntryOperations operations = result.getOperations();
            createdEntryId = operations
                    .getEntryCreate()
                    .getEntryId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
