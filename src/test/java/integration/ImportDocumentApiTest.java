package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryResult;
import com.laserfiche.repository.api.clients.impl.model.DeleteEntryWithAuditReason;
import com.laserfiche.repository.api.clients.impl.model.PostEntryWithEdocMetadataRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void importDocument_Success() {
        String fileName = "myFile";
        File toUpload = null;
        try {
            toUpload = File.createTempFile(fileName, "txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(toUpload);

        CreateEntryResult result = client.importDocument(repoId, 1, fileName, true, null,
                toUpload, new PostEntryWithEdocMetadataRequest()).join();

        assertNotNull(result);
        assertNotNull(result.documentLink);
        assertNotNull(result.operations);
    }
}
