package integration;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
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
            client.cancelOrCloseSearch(new ParametersForCancelOrCloseSearch().setRepoId(repoId).setSearchToken(searchToken));
        }
    }

    @Test
    void getSearchContextHits_ReturnContextHits() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);

        AcceptedOperation searchResponse = client
                .createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId).setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(new ParametersForGetSearchResults().setRepoId(repoId)
                        .setSearchToken(searchToken));

        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() > 0);

        int rowNum = searchResults
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(new ParametersForGetSearchContextHits().setRepoId(repoId)
                        .setSearchToken(searchToken).setRowNumber(rowNum));

        assertNotNull(contextHitResponse);
    }

    @Test
    void getSearchResults_ReturnSearchResults() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));

        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(
                new ParametersForGetSearchResults().setRepoId(repoId).setSearchToken(searchToken));
        assertNotNull(searchResultResponse);
    }

    @Test
    void getSearchStatus_ReturnSearchStatus() throws InterruptedException {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        OperationProgress searchStatusResponse = client.getSearchStatus(new ParametersForGetSearchStatus().setRepoId(repoId).setSearchToken(searchToken));

        assertNotNull(searchStatusResponse);
    }

    @Test
    void closeSearchOperations_CloseSearch() {
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        AcceptedOperation searchOperationResponse = client
                .createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId).setRequestBody(request));

        searchToken = searchOperationResponse.getToken();

        assertNotNull(searchToken);

        ODataValueOfBoolean closeSearchResponse = client
                .cancelOrCloseSearch(new ParametersForCancelOrCloseSearch().setRepoId(repoId).setSearchToken(searchToken));

        assertTrue(closeSearchResponse.isValue());
    }


    @Test
    void getSearchResults_NextLink() throws InterruptedException {
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"NLT\"})");

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));

        searchToken = searchResponse.getToken();
        assertTrue(searchToken != null && !searchToken
                .trim()
                .isEmpty());

        TimeUnit.SECONDS.sleep(10);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(
                new ParametersForGetSearchResults().setRepoId(repoId).setSearchToken(searchToken)
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
        int maxPageSize = 1;

        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"search text\", option=\"NLT\"})");

        AcceptedOperation searchOperationResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));

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
        client.getSearchResultsForEach(callback, maxPageSize, new ParametersForGetSearchResults().setRepoId(repoId)
                        .setSearchToken(searchToken));
    }

    @Test
    void getSearchContextHits_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        AdvancedSearchRequest request = new AdvancedSearchRequest();
        request.setSearchCommand("({LF:Basic ~= \"*\", option=\"DFANLT\"})");
        request.setFuzzyFactor(2);

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResults = client.getSearchResults(
                new ParametersForGetSearchResults().setRepoId(repoId)
                        .setSearchToken(searchToken).setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResults);
        assertTrue(searchResults
                .getValue()
                .size() > 0);

        int rowNum = searchResults
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(new ParametersForGetSearchContextHits().setRepoId(repoId)
                        .setSearchToken(searchToken).setRowNumber(rowNum));

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

        AcceptedOperation searchResponse = client.createSearchOperation(new ParametersForCreateSearchOperation().setRepoId(repoId)
                .setRequestBody(request));
        searchToken = searchResponse.getToken();

        assertNotNull(searchToken);

        TimeUnit.SECONDS.sleep(5);

        ODataValueContextOfIListOfEntry searchResultResponse = client.getSearchResults(
                new ParametersForGetSearchResults().setRepoId(repoId)
                        .setSearchToken(searchToken).setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(searchResultResponse);
        assertTrue(searchResultResponse
                .getValue()
                .size() > 0);

        int rowNum = searchResultResponse
                .getValue()
                .get(0)
                .getRowNumber();

        ODataValueContextOfIListOfContextHit contextHitResponse = client
                .getSearchContextHits(new ParametersForGetSearchContextHits().setRepoId(repoId)
                        .setSearchToken(searchToken).setRowNumber(rowNum));

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
        client.getSearchContextHitsForEach(callback, maxPageSize,
                new ParametersForGetSearchContextHits().setRepoId(repoId).setSearchToken(searchToken).setRowNumber(1));
    }
}
