package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SearchApiTest extends BaseTest {
    SearchesClient client;
    private String searchToken = "";

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getSearchesClient();
    }

    @AfterEach
    void cancelCloseSearch() {
        if (searchToken != null) {
            ODataValueOfBoolean result = client.cancelOrCloseSearch(new ParametersForCancelOrCloseSearch()
                    .setRepoId(repositoryId)
                    .setSearchToken(searchToken));
            System.out.println(result.isValue());
        }
    }

    @Test
    void getSearchContextHits_ReturnContextHits() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        AcceptedOperation searchResponse = client
                .createSearchOperation(new ParametersForCreateSearchOperation()
                        .setRepoId(repositoryId)
                        .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        WaitUntilSearchEnds(searchResponse);

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(new ParametersForGetSearchContextHits()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setRowNumber(1));
        assertNotNull(contextHitResponse);
    }

    @Test
    void getSearchResults_ReturnSearchResults() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation()
                .setRepoId(repositoryId)
                .setRequestBody(request));

        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        WaitUntilSearchEnds(searchResponse);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(
                new ParametersForGetSearchResults()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken));
        assertNotNull(searchResultResponse);
    }

    @Test
    void getSearchStatus_ReturnSearchStatus() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation()
                .setRepoId(repositoryId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        WaitUntilSearchEnds(searchResponse);

        OperationProgress searchStatusResponse = client.getSearchStatus(new ParametersForGetSearchStatus()
                .setRepoId(repositoryId)
                .setSearchToken(searchToken));

        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_CloseSearch() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        AcceptedOperation searchOperationResponse = client
                .createSearchOperation(new ParametersForCreateSearchOperation()
                        .setRepoId(repositoryId)
                        .setRequestBody(request));

        searchToken = searchOperationResponse.getToken();

        assertNotNull(searchToken);

        ODataValueOfBoolean closeSearchResponse = client
                .cancelOrCloseSearch(new ParametersForCancelOrCloseSearch()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken));

        assertTrue(closeSearchResponse.isValue());
        TimeUnit.SECONDS.sleep(5);
    }


    @Test
    void getSearchResults_NextLink() throws InterruptedException {
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation()
                .setRepoId(repositoryId)
                .setRequestBody(request));

        searchToken = searchResponse.getToken();
        assertTrue(searchToken != null && !searchToken
                .trim()
                .isEmpty());

        WaitUntilSearchEnds(searchResponse);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(
                new ParametersForGetSearchResults()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        if (searchResults
                .getValue()
                .isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }

        String nextLink = searchResults.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(searchResults
                .getValue()
                .size() <= maxPageSize);

        // Paging request
        searchResults = client.getSearchResultsNextLink(nextLink, maxPageSize);
        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getSearchResults_ForEach() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 3;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(
                new ParametersForCreateSearchOperation()
                        .setRepoId(repositoryId)
                        .setRequestBody(request));

        searchToken = searchResponse.getToken();
        assertTrue(searchToken != null && !searchToken
                .trim()
                .isEmpty());

        WaitUntilSearchEnds(searchResponse);

        Function<ODataValueContextOfIListOfEntry, Boolean> callback = data -> {
            if (pageCount.incrementAndGet() <= maxPages && data.getOdataNextLink() != null) {
                assertNotEquals(0, data
                        .getValue()
                        .size());
                assertTrue(data
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getSearchResultsForEach(callback, maxPageSize, new ParametersForGetSearchResults()
                .setRepoId(repositoryId)
                .setSearchToken(searchToken));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void getSearchContextHits_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation()
                .setRepoId(repositoryId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        WaitUntilSearchEnds(searchResponse);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(
                new ParametersForGetSearchResults()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() > 0);

        int rowNum = searchResults
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(new ParametersForGetSearchContextHits()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setRowNumber(rowNum)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(contextHitResponse);

        String nextLink = contextHitResponse.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(contextHitResponse
                .getValue()
                .size() <= maxPageSize);

        // Paging request
        ODataValueContextOfIListOfContextHit nextLinkResponse = client.getSearchContextHitsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getSearchContextHits_ForEach() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("{LF:Basic ~= \"Laserfiche\", option=\"DLT\"} & {LF:name=\"Laserfiche Cloud Overview\", Type=\"DFS\"}");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation()
                .setRepoId(repositoryId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();
        assertNotNull(searchToken);

        WaitUntilSearchEnds(searchResponse);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(
                new ParametersForGetSearchResults()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResultResponse);
        assertTrue(searchResultResponse
                .getValue()
                .size() > 0);

        int rowNum = searchResultResponse
                .getValue()
                .get(0)
                .getRowNumber();

        Function<ODataValueContextOfIListOfContextHit, Boolean> callback = data -> {
            if (pageCount.incrementAndGet() <= maxPages && data.getOdataNextLink() != null) {
                assertNotEquals(0, data
                        .getValue()
                        .size());
                assertTrue(data
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getSearchContextHitsForEach(callback, maxPageSize,
                new ParametersForGetSearchContextHits()
                        .setRepoId(repositoryId)
                        .setSearchToken(searchToken)
                        .setRowNumber(rowNum));
        assertTrue(pageCount.get() > 1);
    }
}
