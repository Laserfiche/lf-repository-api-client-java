package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.SearchesClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchApiTest extends BaseTest {
    SearchesClient searchesClient;
    TasksClient tasksClient;

    private String taskId = "";

    @BeforeEach
    void perTestSetup() {
        searchesClient = repositoryApiClient.getSearchesClient();
        tasksClient = repositoryApiClient.getTasksClient();
    }

    @AfterEach
    void cancelCloseSearch() {
        if (taskId != null) {
            CancelTasksResponse result = tasksClient.cancelTasks(new ParametersForCancelTasks()
                    .setRepositoryId(repositoryId)
                    .setTaskIds(new String[]{taskId}));
        }
    }

    @Test
    void getSearchContextHits_ReturnContextHits() throws InterruptedException {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand(
                "{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        SearchContextHitCollectionResponse contextHitResponse =
                searchesClient.listSearchContextHits(new ParametersForListSearchContextHits()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setRowNumber(1));
        assertNotNull(contextHitResponse);
    }

    @Test
    void getSearchResults_ReturnSearchResults() throws InterruptedException {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResultResponse = searchesClient.listSearchResults(
                new ParametersForListSearchResults().setRepositoryId(repositoryId).setTaskId(taskId));
        assertNotNull(searchResultResponse);
    }

    @Test
    void getSearchStatus_ReturnSearchStatus() throws InterruptedException {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        TaskCollectionResponse searchStatusResponse = tasksClient.listTasks(
                new ParametersForListTasks().setRepositoryId(repositoryId).setTaskIds(new String[] {taskId}));

        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_CloseSearch() throws InterruptedException {
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        StartTaskResponse searchOperationResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchOperationResponse.getTaskId();

        assertNotNull(taskId);

        CancelTasksResponse closeSearchResponse = tasksClient.cancelTasks(
                new ParametersForCancelTasks().setRepositoryId(repositoryId).setTaskIds(new String[]{taskId}));

        assertTrue(closeSearchResponse.getValue().get(0).isResult());
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    void getSearchResults_NextLink() throws InterruptedException {
        int maxPageSize = 2;

        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"test\", option=\"NLT\"})");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();
        assertTrue(taskId != null && !taskId.trim().isEmpty());

        WaitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResults1 = searchesClient.listSearchResults(new ParametersForListSearchResults()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId));
        System.out.println(searchResults1.getValue().size());
                EntryCollectionResponse searchResults = searchesClient.listSearchResults(new ParametersForListSearchResults()
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
        searchResults = searchesClient.listSearchResultsNextLink(nextLink, maxPageSize);
        assertNotNull(searchResults);
        assertTrue(searchResults.getValue().size() <= maxPageSize);
    }

    @Test
    void getSearchResults_ForEach() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 3;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand("({LF:Basic ~= \"test\", option=\"NLT\"})");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));

        taskId = searchResponse.getTaskId();
        assertTrue(taskId != null && !taskId.trim().isEmpty());

        WaitUntilTaskEnds(taskId);

        Function<EntryCollectionResponse, Boolean> callback = data -> {
            if (pageCount.incrementAndGet() <= maxPages && data.getOdataNextLink() != null) {
                assertNotEquals(0, data.getValue().size());
                assertTrue(data.getValue().size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        searchesClient.listSearchResultsForEach(
                callback,
                maxPageSize,
                new ParametersForListSearchResults().setRepositoryId(repositoryId).setTaskId(taskId));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void getSearchContextHits_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand(
                "{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();

        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResults = searchesClient.listSearchResults(new ParametersForListSearchResults()
                .setRepositoryId(repositoryId)
                .setTaskId(taskId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        assertTrue(searchResults.getValue().size() > 0);

        int rowNum = searchResults.getValue().get(0).getRowNumber();

        SearchContextHitCollectionResponse contextHitResponse =
                searchesClient.listSearchContextHits(new ParametersForListSearchContextHits()
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
                searchesClient.listSearchContextHitsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void getSearchContextHits_ForEach() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        StartSearchEntryRequest request = new StartSearchEntryRequest();
        request.setSearchCommand(
                "{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        StartTaskResponse searchResponse = searchesClient.startSearchEntry(
                new ParametersForStartSearchEntry().setRepositoryId(repositoryId).setRequestBody(request));
        taskId = searchResponse.getTaskId();
        assertNotNull(taskId);

        WaitUntilTaskEnds(taskId);

        EntryCollectionResponse searchResultResponse =
                searchesClient.listSearchResults(new ParametersForListSearchResults()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResultResponse);
        assertTrue(searchResultResponse.getValue().size() > 0);

        int rowNum = searchResultResponse.getValue().get(0).getRowNumber();

        Function<SearchContextHitCollectionResponse, Boolean> callback = data -> {
            if (pageCount.incrementAndGet() <= maxPages && data.getOdataNextLink() != null) {
                assertNotEquals(0, data.getValue().size());
                assertTrue(data.getValue().size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        searchesClient.listSearchContextHitsForEach(
                callback,
                maxPageSize,
                new ParametersForListSearchContextHits()
                        .setRepositoryId(repositoryId)
                        .setTaskId(taskId)
                        .setRowNumber(rowNum));
        assertTrue(pageCount.get() > 1);
    }
}
