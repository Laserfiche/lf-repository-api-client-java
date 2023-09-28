package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import java.util.concurrent.TimeUnit;

import com.laserfiche.repository.api.clients.params.ParametersForCancelTasks;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;
import com.laserfiche.repository.api.clients.params.ParametersForStartDeleteEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Entry deleteEntry =
                createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));
        String taskId = result.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        CancelTasksResponse response = client.cancelTasks(new ParametersForCancelTasks()
                    .setRepositoryId(repositoryId)
                    .setTaskIds(new String[]{taskId}));
        assertNotNull(response);
        assertEquals(1, response.getValue().size());
        assertTrue(response.getValue().get(0).isResult());
    }

    @Test
    void cancelOperation_OperationCancelledSuccessfully() throws InterruptedException {
        Entry deleteEntry =
                createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));

        String taskId = result.getTaskId();
        assertNotNull(taskId);

        CancelTasksResponse cancellationResult = client.cancelTasks(
                new ParametersForCancelTasks().setRepositoryId(repositoryId).setTaskIds(new String[]{taskId}));
        assertTrue(cancellationResult.getValue().get(0).isResult());

        TimeUnit.SECONDS.sleep(5);
        deleteEntry(deleteEntry.getId());
    }

    @Test
    void getOperationStatus_ReturnStatus() throws InterruptedException {
        Entry deleteEntry =
                createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));

        String taskId = result.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        TaskCollectionResponse operationProgressResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(new String[]{taskId}));

        assertNotNull(operationProgressResponse);
        assertEquals(1, operationProgressResponse.getValue().size());
        assertEquals(TaskStatus.COMPLETED, operationProgressResponse.getValue().get(0).getStatus());
        assertEquals(100, operationProgressResponse.getValue().get(0).getPercentComplete());
    }
}
