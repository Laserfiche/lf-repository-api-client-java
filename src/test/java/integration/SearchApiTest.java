package integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
        request.searchCommand = "({LF:Basic ~= \"*\", option=\"DFANLT\"})";
        request.fuzzyFactor = 2;

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request).join();
        searchToken = searchResponse.token;

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultResponse.join();

        assertNotNull(searchResults);
        assertTrue(searchResults.value.size() > 0);

        int rowNum = searchResults.value.get(0).rowNumber;

        ODataValueContextOfIListOfContextHit contextHitResponse = client.getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null).join();

        assertNotNull(contextHitResponse);
    }

    @Test
    void getSearchResults_ReturnSearchResults() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"DFANLT\"})";

        CompletableFuture<AcceptedOperation> searchResponse = client.createSearchOperation(repoId, request);

        searchToken = searchResponse.join().token;

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null);
        assertNotNull(searchResultResponse.join());
    }

    @Test
    void getSearchStatus_ReturnSearchStatus() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"DFANLT\"})";

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request).join();
        searchToken = searchResponse.token;

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        OperationProgress searchStatusResponse = client.getSearchStatus(repoId, searchToken).join();

        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_CloseSearch() {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"DFANLT\"})";

        AcceptedOperation searchOperationResponse = client.createSearchOperation(repoId, request).join();

        searchToken = searchOperationResponse.token;

        assertNotNull(searchToken);

        ODataValueOfBoolean closeSearchResponse = client.cancelOrCloseSearch(repoId, searchToken).join();

        assertTrue(closeSearchResponse.value);
    }

    @Test
    void getSearchResults_NextLink() throws InterruptedException {
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"NLT\"})";

        CompletableFuture<AcceptedOperation> searchResponse = client.createSearchOperation(repoId, request);

        searchToken = searchResponse.join().token;
        assertTrue(searchToken != null && !searchToken.trim().isEmpty());

        TimeUnit.SECONDS.sleep(10);

        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultResponse.join();
        assertNotNull(searchResults);
        if (searchResults.value.isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }

        String nextLink = searchResults._atOdataNextLink;
        assertNotNull(nextLink);
        assertTrue(searchResults.value.size() <= maxPageSize);

        // Paging request
        searchResultResponse = client.getSearchResultsNextLink(nextLink, maxPageSize);
        searchResults = searchResultResponse.join();
        assertNotNull(searchResults);
        assertTrue(searchResults.value.size() <= maxPageSize);
    }

    @Test
    void getSearchResults_ForEach() throws InterruptedException {
        int maxPageSize = 90;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"NLT\"})";

        AcceptedOperation searchOperationResponse = client.createSearchOperation(repoId, request).join();

        searchToken = searchOperationResponse.token;
        assertTrue(searchToken != null && !searchToken.trim().isEmpty());

        TimeUnit.SECONDS.sleep(10);

        Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfEntry futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getSearchResultsForEach(callback, maxPageSize, repoId, searchToken, null, null, null, null, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSearchContextHits_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"*\", option=\"DFANLT\"})";
        request.fuzzyFactor = 2;

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request).join();
        searchToken = searchResponse.token;

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultResponse.join();

        assertNotNull(searchResults);
        assertTrue(searchResults.value.size() > 0);

        int rowNum = searchResults.value.get(0).rowNumber;

        ODataValueContextOfIListOfContextHit contextHitResponse = client.getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null).join();

        assertNotNull(contextHitResponse);

        String nextLink = searchResults._atOdataNextLink;
        assertNotNull(nextLink);
        assertTrue(searchResults.value.size() <= maxPageSize);

        // Paging request
        CompletableFuture<ODataValueContextOfIListOfContextHit> nextLinkResponse = client.getSearchContextHitsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfContextHit nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getSearchContextHits_ForEach() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"*\", option=\"DFANLT\"})";
        request.fuzzyFactor = 2;

        AcceptedOperation searchResponse = client.createSearchOperation(repoId, request).join();
        searchToken = searchResponse.token;

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        CompletableFuture<ODataValueContextOfIListOfEntry> searchResultResponse = client.getSearchResults(repoId, searchToken, null, null, null, null, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, null);
        ODataValueContextOfIListOfEntry searchResults = searchResultResponse.join();

        assertNotNull(searchResults);
        assertTrue(searchResults.value.size() > 0);

        int rowNum = searchResults.value.get(0).rowNumber;

        ODataValueContextOfIListOfContextHit contextHitResponse = client.getSearchContextHits(repoId, searchToken, rowNum, null, null, null, null, null, null).join();

        assertNotNull(contextHitResponse);

        Function<CompletableFuture<ODataValueContextOfIListOfContextHit>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfContextHit futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getSearchContextHitsForEach(callback, maxPageSize, repoId, searchToken,1, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    void createSearchOperations_ReturnToken() {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.searchCommand = "({LF:Basic ~= \"search text\", option=\"DFANLT\"})";

        assertNotNull(searchToken);
    }
}
