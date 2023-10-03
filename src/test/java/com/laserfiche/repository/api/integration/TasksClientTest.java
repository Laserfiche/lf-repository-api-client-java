package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import java.util.concurrent.TimeUnit;

import com.laserfiche.repository.api.clients.params.ParametersForCancelTasks;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;
import com.laserfiche.repository.api.clients.params.ParametersForStartDeleteEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TasksClientTest extends BaseTest {
    private TasksClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTasksClient();
    }

    @Test
    void cancelTasksDoesNotReturnErrorWhenCancellingACompletedTask() {
        Entry deleteEntry =
                createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));
        String taskId = result.getTaskId();
        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        CancelTasksResponse response = client.cancelTasks(new ParametersForCancelTasks()
                    .setRepositoryId(repositoryId)
                    .setTaskIds(taskId));
        assertNotNull(response);
        assertEquals(1, response.getValue().size());
        assertTrue(response.getValue().get(0).isResult());
    }

    @Test
    void cancelTasksCanCancelAnInProgressTask() throws InterruptedException {
        Entry deleteEntry =
                createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java CancelOperation", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));

        String taskId = result.getTaskId();
        assertNotNull(taskId);

        CancelTasksResponse cancellationResult = client.cancelTasks(
                new ParametersForCancelTasks().setRepositoryId(repositoryId).setTaskIds(taskId));
        assertTrue(cancellationResult.getValue().get(0).isResult());

        TimeUnit.SECONDS.sleep(5);
        deleteEntry(deleteEntry.getId());
    }

    @Test
    void listTasksWorks() {
        Entry deleteEntry =
                createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));

        String taskId = result.getTaskId();
        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        TaskCollectionResponse operationProgressResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskId));

        assertNotNull(operationProgressResponse);
        assertEquals(1, operationProgressResponse.getValue().size());
        assertEquals(TaskStatus.COMPLETED, operationProgressResponse.getValue().get(0).getStatus());
        assertEquals(100, operationProgressResponse.getValue().get(0).getPercentComplete());
    }

    @Test
    void listTasksAcceptsMultipleTaskIds() {
        // Create N tasks
        final int TASK_COUNT = 5;
        String[] taskIds = new String[TASK_COUNT];

        for (int i = 0; i < TASK_COUNT; i++) {
            Entry entry =
                    createEntry(repositoryApiClient, String.format("RepositoryApiClientIntegrationTest Java ListTasks_%d", i), 1, true);
            StartTaskResponse startTaskResponse = repositoryApiClient
                    .getEntriesClient()
                    .startDeleteEntry(new ParametersForStartDeleteEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(entry.getId())
                            .setRequestBody(new StartDeleteEntryRequest()));
            assertNotNull(startTaskResponse);
            taskIds[i] = startTaskResponse.getTaskId();
        }

        // Call listTasks API to get the status of the tasks
        TaskCollectionResponse taskCollectionResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskIds));

        assertNotNull(taskCollectionResponse);
        assertEquals(TASK_COUNT, taskCollectionResponse.getValue().size());

        // Verify all the original taskIds are in the returned response
        for (String taskId : taskIds) {
            assertTrue(taskCollectionResponse.getValue().stream().anyMatch(taskProgress -> taskProgress.getId().equals(taskId)));
        }
    }

    @Test
    void listTasksCanBeCalledWithNoTaskIds() {
        // Create N tasks
        final int TASK_COUNT = 5;
        String[] taskIds = new String[TASK_COUNT];

        for (int i = 0; i < TASK_COUNT; i++) {
            Entry entry =
                    createEntry(repositoryApiClient, String.format("RepositoryApiClientIntegrationTest Java ListTasks_%d", i), 1, true);
            StartTaskResponse startTaskResponse = repositoryApiClient
                    .getEntriesClient()
                    .startDeleteEntry(new ParametersForStartDeleteEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(entry.getId())
                            .setRequestBody(new StartDeleteEntryRequest()));
            assertNotNull(startTaskResponse);
            taskIds[i] = startTaskResponse.getTaskId();
        }

        // Call listTasks API to get the status of the tasks
        TaskCollectionResponse taskCollectionResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds());

        assertNotNull(taskCollectionResponse);
        assertTrue(TASK_COUNT <= taskCollectionResponse.getValue().size());

        // Verify all the original taskIds are in the returned response
        for (String taskId : taskIds) {
            assertTrue(taskCollectionResponse.getValue().stream().anyMatch(taskProgress -> taskProgress.getId().equals(taskId)));
        }
    }

    @Test
    void listTasksIgnoresInvalidTaskIds() {
        String invalidTaskId = "ThisIsAnInvalidTaskId";

        TaskCollectionResponse taskCollectionResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(invalidTaskId));

        assertNotNull(taskCollectionResponse);
        assertEquals(0, taskCollectionResponse.getValue().size());
    }

    @Test
    void listTasksIgnoresDuplicateTaskIds() {
        Entry deleteEntry =
                createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java GetOperationStatus", 1, true);

        StartTaskResponse result = repositoryApiClient
                .getEntriesClient()
                .startDeleteEntry(new ParametersForStartDeleteEntry()
                        .setRepositoryId(repositoryId)
                        .setEntryId(deleteEntry.getId())
                        .setRequestBody(new StartDeleteEntryRequest()));

        String taskId = result.getTaskId();
        assertNotNull(taskId);

        TaskCollectionResponse operationProgressResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskId, taskId, taskId, taskId));

        assertEquals(1, operationProgressResponse.getValue().size());
        assertEquals(taskId, operationProgressResponse.getValue().get(0).getId());
    }

}
