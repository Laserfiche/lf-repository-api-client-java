package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SearchesClientTest extends BaseTest {
    private SearchesClient client;
    private TasksClient tasksClient;

    private String taskId = "";

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getSearchesClient();
        tasksClient = repositoryApiClient.getTasksClient();
    }

    @AfterEach
    void cancelCloseSearch() {
        if (taskId != null) {
            tasksClient.cancelTasks(new ParametersForCancelTasks()
                    .setRepositoryId(repositoryId)
                    .setTaskIds(taskId));
        }
    }

    @Test
    void listSearchResultsAndListSearchContextHitsWork() {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand(
                "{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        EntryCollectionResponse collectionResponse = client.listSearchResults(
                new ParametersForListSearchResults().setRepositoryId(repositoryId).setTaskId(taskId));
        assertNotNull(collectionResponse);

        SearchContextHitCollectionResponse contextHitCollectionResponse =
                client.listSearchContextHits(new ParametersForListSearchContextHits()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setRowNumber(1));
        assertNotNull(contextHitCollectionResponse);
    }

    @Test
    void listTasksCanReturnSearchStatusAndCanCloseSearch() throws InterruptedException {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        TaskCollectionResponse collectionResponse = tasksClient.listTasks(
                new ParametersForListTasks().setRepositoryId(repositoryId).setTaskIds(taskId));

        assertNotNull(collectionResponse);
        assertFalse(collectionResponse.getValue().isEmpty());
        assertEquals(taskId, collectionResponse.getValue().get(0).getId());

        CancelTasksResponse cancelTasksResponse = tasksClient.cancelTasks(
                new ParametersForCancelTasks().setRepositoryId(repositoryId).setTaskIds(taskId));
        assertEquals(taskId, cancelTasksResponse.getValue().get(0).getId());
        assertTrue(cancelTasksResponse.getValue().get(0).isResult());
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    void listSearchResultsNextLinkWorks() {
        int maxPageSize = 2;

        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Java Client Library\", option=\"DFANLT\"})");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();
        assertTrue(taskId != null && !taskId.trim().isEmpty());

        waitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResults = client.listSearchResults(new ParametersForListSearchResults()
                .setRepositoryId(repositoryId)
                .setTaskId(taskId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        if (searchResults.getValue().isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }

        String nextLink = searchResults.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(searchResults.getValue().size() <= maxPageSize);

        // Paging request
        searchResults = client.listSearchResultsNextLink(nextLink, maxPageSize);
        assertNotNull(searchResults);
        assertTrue(searchResults.getValue().size() <= maxPageSize);
    }

    @Test
    void listSearchResultsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Java Client Library\", option=\"DFANLT\"})");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();
        assertTrue(taskId != null && !taskId.trim().isEmpty());

        waitUntilTaskEnds(taskId);

        Function<EntryCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listSearchResultsForEach(
                callback,
                maxPageSize,
                new ParametersForListSearchResults().setRepositoryId(repositoryId).setTaskId(taskId));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void listSearchContextHitsNextLinkWorks() {
        int maxPageSize = 2;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Java Client Library\", option=\"DFANLT\"})");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResults = client.listSearchResults(new ParametersForListSearchResults()
                .setRepositoryId(repositoryId)
                .setTaskId(taskId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        assertFalse(searchResults.getValue().isEmpty());

        int rowNum = searchResults.getValue().get(0).getRowNumber();

        SearchContextHitCollectionResponse contextHitResponse =
                client.listSearchContextHits(new ParametersForListSearchContextHits()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setRowNumber(rowNum)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(contextHitResponse);

        String nextLink = contextHitResponse.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(contextHitResponse.getValue().size() <= maxPageSize);

        // Paging request
        SearchContextHitCollectionResponse nextLinkResponse =
                client.listSearchContextHitsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listSearchContextHitsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Java Client Library\", option=\"DFANLT\"})");

        StartTaskResponse searchResponse = client.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();
        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResultResponse =
                client.listSearchResults(new ParametersForListSearchResults()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResultResponse);
        assertFalse(searchResultResponse.getValue().isEmpty());

        int rowNum = searchResultResponse.getValue().get(0).getRowNumber();

        Function<SearchContextHitCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listSearchContextHitsForEach(
                callback,
                maxPageSize,
                new ParametersForListSearchContextHits()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setRowNumber(rowNum));
        assertTrue(pageCount.get() > 1);
    }
}
