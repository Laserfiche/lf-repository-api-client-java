package integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForCancelOperation;
import com.laserfiche.repository.api.clients.params.ParametersForDeleteEntryInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetOperationStatusAndProgress;
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
                .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                        .setRepoId(repoId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(body));

        String token = result.getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(10);

        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            client.cancelOperation(new ParametersForCancelOperation()
                    .setRepoId(repoId)
                    .setOperationToken(token));
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
                .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                        .setRepoId(repoId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(body));

        String token = result.getToken();
        assertNotNull(token);

        boolean cancellationResult = client.cancelOperation(new ParametersForCancelOperation()
                .setRepoId(repoId)
                .setOperationToken(token));
        assertTrue(cancellationResult);
    }

    @Test
    void getOperationStatus_ReturnStatus() throws InterruptedException {
        Entry deleteEntry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus",
                1, true);

        DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();

        AcceptedOperation result = repositoryApiClient
                .getEntriesClient()
                .deleteEntryInfo(new ParametersForDeleteEntryInfo()
                        .setRepoId(repoId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(body));

        String token = result.getToken();

        assertNotNull(token);

        TimeUnit.SECONDS.sleep(5);

        OperationProgress operationProgressResponse = client.getOperationStatusAndProgress(
                new ParametersForGetOperationStatusAndProgress()
                        .setRepoId(repoId)
                        .setOperationToken(token));

        assertNotNull(operationProgressResponse);
        Assertions.assertSame(operationProgressResponse.getStatus(), OperationStatus.COMPLETED);
        Assertions.assertSame(operationProgressResponse.getPercentComplete(), 100);
    }
}
