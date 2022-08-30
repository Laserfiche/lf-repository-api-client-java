package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true).join();

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, deleteEntry.id, body).join();

        String token = result.token;

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        client.cancelOperation(repoId, token).join(); // TODO: We probably need to manually override this API as by default it returns via HTTP headers.
    }

    @Test
    @Disabled("Exception Thrown")
    void getOperationStatus_Success() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus", 1, true).join();

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        CompletableFuture<AcceptedOperation> result = repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, deleteEntry.id, body);

        String token = result.join().token;

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<OperationProgress> operationProgressResponse = client.getOperationStatusAndProgress(repoId, token);
        
        assertNotNull(operationProgressResponse);
        Assertions.assertSame(operationProgressResponse.join().status, OperationStatus.COMPLETED);
        Assertions.assertSame(operationProgressResponse.join().percentComplete, 100);
    }
}
