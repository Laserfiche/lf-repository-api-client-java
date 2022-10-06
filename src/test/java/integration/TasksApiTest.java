package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
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
                true).join();

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body)
                .join();

        String token = result.getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(10);

        Exception thrown = Assertions.assertThrows(CompletionException.class, () -> {
            client
                    .cancelOperation(repoId, token)
                    .join();
        });

        Assertions.assertEquals("java.lang.RuntimeException: Invalid or bad request.", thrown.getMessage());
    }

    @Test
    void cancelOperation_OperationCancelledSuccessfully() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1,
                true).join();

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body)
                .join();

        String token = result.getToken();
        assertNotNull(token);

        boolean cancellationResult = client
                .cancelOperation(repoId, token)
                .join();
        assertTrue(cancellationResult);
    }

    @Test
    void getOperationStatus_ReturnStatus() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus",
                1, true).join();

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        CompletableFuture<AcceptedOperation> result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(repoId, deleteEntry.getId(), body);

        String token = result
                .join()
                .getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<OperationProgress> operationProgressResponse = client.getOperationStatusAndProgress(repoId,
                token);

        assertNotNull(operationProgressResponse);
        Assertions.assertSame(operationProgressResponse
                .join()
                .getStatus(), OperationStatus.COMPLETED);
        Assertions.assertSame(operationProgressResponse
                .join()
                .getPercentComplete(), 100);
    }
}
