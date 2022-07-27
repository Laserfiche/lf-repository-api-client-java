package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TasksApiTest extends BaseTest {
    TasksClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTasksClient();
        createEntryClient = repositoryApiClient;
    }

    @Test
    void cancelOperation_Success() throws InterruptedException {
        CompletableFuture<Entry> deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);
        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> result = repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, deleteEntry.join().getId(), body);
        String token = result.join().getToken();
        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        boolean isCancelOperationSuccessful = client.cancelOperation(repoId, token).join();
        assertFalse(isCancelOperationSuccessful); // The operation is very fast. So we assert that we cannot cancel it due to it is already completed.
    }

    @Test
    @Disabled("Exception Thrown")
    void getOperationStatus_Success() throws InterruptedException {
        CompletableFuture<Entry> deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus", 1, true);
        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> result = repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, deleteEntry.join().getId(), body);
        String token = result.join().getToken();
        assertNotNull(token);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<OperationProgress> operationProgressResponse = client.getOperationStatusAndProgress(repoId, token);
        assertNotNull(operationProgressResponse);
        Assertions.assertSame(operationProgressResponse.join().getStatus(), OperationStatus.COMPLETED);
        Assertions.assertSame(operationProgressResponse.join().getPercentComplete(), 100);
    }
}
