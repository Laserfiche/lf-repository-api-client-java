package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForCancelTasks;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;
import com.laserfiche.repository.api.clients.params.ParametersForStartDeleteEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

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
    void listTasksWorksAndAcceptsMultipleTaskIdsAndCanBeCalledWithNoTaskIdsAndIgnoresDuplicateTaskIds() {
        // Create N tasks
        final int TASK_COUNT = 2;
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

        // Verify it can be called with multiple taskIds
        TaskCollectionResponse collectionResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskIds));

        assertNotNull(collectionResponse);
        assertEquals(TASK_COUNT, collectionResponse.getValue().size());

        // Verify all the original taskIds are in the returned response
        for (String taskId : taskIds) {
            assertTrue(collectionResponse.getValue().stream().anyMatch(taskProgress -> taskProgress.getId().equals(taskId)));
        }

        // Verify it can be called with no taskIds
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

        // Verify it ignores duplicate taskIds
        String firstTaskId = taskIds[0];
        TaskCollectionResponse operationProgressResponse =
                client.listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(firstTaskId, firstTaskId, firstTaskId, firstTaskId));

        assertEquals(1, operationProgressResponse.getValue().size());
        assertEquals(firstTaskId, operationProgressResponse.getValue().get(0).getId());
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
}
