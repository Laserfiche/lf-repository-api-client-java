package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.ApiException;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksApiTest extends BaseTest {
    TasksClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTasksClient();
        createEntryClient = repositoryApiClient;
    }

    @Test
    void cancelOperation_OperationEndedBeforeCancel() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1,
                true);

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body);

        String token = result.getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(10);

        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.cancelOperation(repoId, token);
        });

        Assertions.assertEquals(
                String.format("%s: Cannot cancel ended operation.", Error.class.getSimpleName()),
                thrown.getMessage());
    }

    @Test
    void cancelOperation_OperationCancelledSuccessfully() {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1,
                true);

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body);

        String token = result.getToken();
        assertNotNull(token);

        boolean cancellationResult = client.cancelOperation(repoId, token);
        assertTrue(cancellationResult);
    }

    @Test
    void getOperationStatus_ReturnStatus() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus",
                1, true);

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body);

        String token = result.getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        OperationProgress operationProgressResponse = client.getOperationStatusAndProgress(repoId, token);

        assertNotNull(operationProgressResponse);
        Assertions.assertSame(operationProgressResponse.getStatus(), OperationStatus.COMPLETED);
        Assertions.assertSame(operationProgressResponse.getPercentComplete(), 100);
    }
}
