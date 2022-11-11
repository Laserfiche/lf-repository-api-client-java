package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.ApiException;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryOperations;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryResult;
import com.laserfiche.repository.api.clients.impl.model.DeleteEntryWithAuditReason;
import com.laserfiche.repository.api.clients.impl.model.PostEntryWithEdocMetadataRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class ExportDocumentApiTest extends BaseTest {
    private EntriesClient client;
    private int createdEntryId;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEmptyDocument();
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
    void exportDocument_Returns_Exported_File() {
        final String FILE_NAME = "exportDocument_temp_file.txt";
        Consumer<InputStream> c = inputStream -> {
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

        client
                .exportDocument(repoId, createdEntryId, null, c)
                .join();
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
            client
                    .exportDocument(repoId, -createdEntryId, null, consumer);
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
                    .importDocument(repoId, 1, fileName, true, null,
                            new FileInputStream(toUpload), new PostEntryWithEdocMetadataRequest())
                    .join();

            CreateEntryOperations operations = result.getOperations();
            createdEntryId = operations
                    .getEntryCreate()
                    .getEntryId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
