package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TasksApiTest extends BaseTest {
    TasksClient client;
    RepositoryApiClient createEntryClient;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTasksClient();
        createEntryClient = repositoryApiClient;
    }

    @AfterEach
    void resetClient_Success() {
        client = null;
    }

    @Test
    void cancelOperation_Success() throws InterruptedException {
        CompletableFuture<Entry> deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);
        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
        CompletableFuture<AcceptedOperation> result = repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, deleteEntry.join().getId(), body);
        String token = result.join().getToken();
        assertNotNull(token);
        TimeUnit.SECONDS.sleep(5);
        client.cancelOperation(repoId, token);
    }

    @Test
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
