package integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
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
            client.cancelOrCloseSearch(repoId, searchToken);
        }
    }

    @Test
    void getSearchContextHits_ReturnContextHits() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);

        AcceptedOperation searchResponse = client
                .createSearchOperation(repoId, request);
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(repoId,
                searchToken, null, null, null, null, null, null, null, null, null, null, null);

        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() > 0);

        int rowNum = searchResults
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null);

        assertNotNull(contextHitResponse);
    }

    @Test
    void getSearchResults_ReturnSearchResults() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request);

        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(repoId,
                searchToken, null, null, null, null, null, null, null, null, null, null, null);
        assertNotNull(searchResultResponse);
    }

    @Test
    void getSearchStatus_ReturnSearchStatus() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request);
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        OperationProgress searchStatusResponse = client.getSearchStatus(repoId, searchToken);

        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_CloseSearch() {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchOperationResponse = client
                .createSearchOperation(repoId, request);

        searchToken = searchOperationResponse.getToken();

        assertNotNull(searchToken);

        ODataValueOfBoolean closeSearchResponse = client
                .cancelOrCloseSearch(repoId, searchToken);

        assertTrue(closeSearchResponse.isValue());
    }


    @Test
    void getSearchResults_NextLink() throws InterruptedException {
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request);

        searchToken = searchResponse.getToken();
        assertTrue(searchToken != null && !searchToken
                .trim()
                .isEmpty());

        TimeUnit.SECONDS.sleep(10);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(repoId,
                searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null,
                null, null, null);

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
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"NLT\"})");

        AcceptedOperation searchOperationResponse = client.createSearchOperation(repoId, request);

        searchToken = searchOperationResponse.getToken();
        assertTrue(searchToken != null && !searchToken
                .trim()
                .isEmpty());

        TimeUnit.SECONDS.sleep(10);

        Function<ODataValueContextOfIListOfEntry, Boolean> callback = data -> {
            if (data.getOdataNextLink() != null) {
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
        client.getSearchResultsForEach(callback, maxPageSize, repoId, searchToken, null, null, null, null, null,
                null, null, null, null, null, null);
    }

    @Test
    void getSearchContextHits_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request);
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(repoId,
                searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null,
                null, null, null);

        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() > 0);

        int rowNum = searchResults
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null);

        assertNotNull(contextHitResponse);

        String nextLink = searchResults.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(searchResults
                .getValue()
                .size() <= maxPageSize);

        // Paging request
        ODataValueContextOfIListOfContextHit nextLinkResponse = client.getSearchContextHitsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getSearchContextHits_ForEach() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request);
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(repoId,
                searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null,
                null, null, null);

        assertNotNull(searchResultResponse);
        assertTrue(searchResultResponse
                .getValue()
                .size() > 0);

        int rowNum = searchResultResponse
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null);

        assertNotNull(contextHitResponse);

        Function<ODataValueContextOfIListOfContextHit, Boolean> callback = data -> {
            if (data.getOdataNextLink() != null) {
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
        client.getSearchContextHitsForEach(callback, maxPageSize, repoId, searchToken, 1, null, null, null, null,
                null, null);
    }
}
