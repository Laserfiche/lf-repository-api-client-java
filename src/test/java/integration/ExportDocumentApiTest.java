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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
        File exportedFile = new File("exportDocument_temp_file.txt");
        File file = client
                .exportDocument(repoId, createdEntryId, null, exportedFile)
                .join();
        assertNotNull(file);
        assertNotNull(exportedFile);
        assertTrue(file.exists());
        assertTrue(exportedFile.exists());
        assertEquals(file.getAbsolutePath(), exportedFile.getAbsolutePath());
        assertEquals(file.length(), exportedFile.length());
        boolean exportedFileIsDeletedSuccessfully = file.delete();
        assertTrue(exportedFileIsDeletedSuccessfully);
    }

    @Test
    void exportDocument_Returns_Error_For_Invalid_EntryId() {
        File exportedFile = new File("exportDocument_temp_file.txt");
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client
                    .exportDocument(repoId, -createdEntryId, null, exportedFile);
        });
        Assertions.assertEquals("Invalid or bad request.", thrown.getMessage());

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
