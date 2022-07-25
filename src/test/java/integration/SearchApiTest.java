package integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchApiTest extends BaseTest {
    SearchesClient client;
    private String searchToken = "";
    private final int maxPageSize = 1;

    private final int maxPageSizeForEach = 20;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getSearchesClient();
    }

    @AfterEach
    void cancelCloseSearch_Success() {
        if (searchToken != null) {
            client.cancelOrCloseSearch(repoId, searchToken);
        }
    }

    @Test
    void getSearchContextHits_Success() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);
        CompletableFuture<AcceptedOperation> searchResponse = client.createSearchOperation(repoId, request);
        searchToken = searchResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultResponse.join();
        assertNotNull(searchResults);
        assertTrue(searchResults.getValue().size() > 0);
        int rowNum = searchResults.getValue().get(0).getRowNumber();
        CompletableFuture<ODataValueContextOfIListOfContextHit> contextHitResponse = client.getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null, null);
        assertNotNull(contextHitResponse.join());
    }

    @Test
    void getSearchResults_Success() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchResponse = client.createSearchOperation(repoId, request);
        String searchToken = searchResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null, null);
        assertNotNull(searchResultResponse.join());
    }

    @Test
    void getSearchResultsForEach_Success() throws InterruptedException {
        AdvancedSearchRequest searchRequest = new AdvancedSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchOperationResponse = client.createSearchOperation(repoId, searchRequest);
        searchToken = searchOperationResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        client.getSearchResultsForEach((
                future -> {
                    assertNotNull(future);
                    ODataValueContextOfIListOfEntry searchList = future.join();
                    if (searchList != null) {
                        assertNotNull(searchList.getValue());
                    }
                    return searchList != null; // Stop asking if there's no data.
                }
        ), repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null, maxPageSizeForEach);
    }

    @Test
    @Disabled("Weird Exception Thrown")
    void getSearchContextHitsForEach_Success() throws InterruptedException {
        AdvancedSearchRequest searchRequest = new AdvancedSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchOperationResponse = client.createSearchOperation(repoId, searchRequest);
        searchToken = searchOperationResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultsResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultsResponse.join();
        assertNotNull(searchResults);
        assertTrue(searchResults.getValue().size() > 0);
        int rowNum = searchResults.getValue().get(0).getRowNumber();
        client.getSearchContextHitsForEach((
                future -> {
                    assertNotNull(future);
                    ODataValueContextOfIListOfContextHit searchList = future.join();
                    if (searchList != null) {
                        assertNotNull(searchList.getValue());
                    }
                    return searchList != null; // Stop asking if there's no data.
                }
        ), repoId, searchToken, rowNum, null, null, null, null, null, null, maxPageSizeForEach);

    }

    @Test
    @Disabled("Weird Exception Thrown")
    void getSearchStatus_Success() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchResponse = client.createSearchOperation(repoId, request);
        String searchToken = searchResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<OperationProgress> searchStatusResponse = client.getSearchStatus(repoId, searchToken);
        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_Success() {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchOperationResponse = client.createSearchOperation(repoId, request);
        String searchToken = searchOperationResponse.join().getToken();
        assertNotNull(searchToken);
        CompletableFuture<ODataValueOfBoolean> closeSearchResponse = client.cancelOrCloseSearch(repoId, searchToken);
        assertTrue(closeSearchResponse.join().isValue());
    }

    @Test
    void getSearchResultNextLink_Success() throws InterruptedException {
        AdvancedSearchRequest searchRequest = new AdvancedSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<AcceptedOperation> searchOperationResponse = client.createSearchOperation(repoId, searchRequest);
        String searchToken = searchOperationResponse.join().getToken();
        assertNotNull(searchToken);
        TimeUnit.SECONDS.sleep(5);
        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultsResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null, maxPageSize);
        assertNotNull(searchResultsResponse);
        String nextLink = searchResultsResponse.join().getAtOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(searchResultsResponse.join().getValue().size() <= maxPageSize);
        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultsNextLinkResponse = client.getSearchResultsNextLink(nextLink, maxPageSize);
        assertNotNull(searchResultsNextLinkResponse);
        assertTrue(searchResultsNextLinkResponse.join().getValue().size() <= maxPageSize);
    }
}
