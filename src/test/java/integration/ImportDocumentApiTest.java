package integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.DeleteEntryWithAuditReason;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ImportDocumentApiTest extends BaseTest {
    EntriesClient client;

    int createdEntryId;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createdEntryId = 0;
    }

    @AfterEach
    void deleteEntries() {
        if (createdEntryId != 0) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            client
                    .deleteEntryInfo(repoId, createdEntryId, body)
                    .join();
        }
    }
}
