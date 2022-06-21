package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.AcceptedOperation;
import com.laserfiche.repository.api.client.model.AdvancedSearchRequest;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfContextHit;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.client.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.client.model.OperationProgress;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for SearchesApi
 */
public class SearchesApiTest {

    private SearchesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(SearchesApi.class);
    }


    /**
     * Cancel or close an advanced search.
     *
     * - Cancels a currently running search. - Closes a completed search.
     */
    @Test
    public void cancelOrCloseSearchTest() {
        String repoId = null;
        String searchToken = null;
        // ODataValueOfBoolean response = api.cancelOrCloseSearch(repoId, searchToken);

        // TODO: test validations
    }

    /**
     * Run a search in the specified repository.
     *
     * - Runs a search operation on the repository. - Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage). The status for search operations must be checked via the Search specific status checking route.         
     */
    @Test
    public void createSearchOperationTest() {
        String repoId = null;
        AdvancedSearchRequest body = null;
        // AcceptedOperation response = api.createSearchOperation(repoId, body);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the context hits associated with a search result entry. - Given a searchToken, and rowNumber associated with a search entry in the listing, return the context hits for that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getSearchContextHitsTest() {
        String repoId = null;
        String searchToken = null;
        Integer rowNumber = null;
        String prefer = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfContextHit response = api.getSearchContextHits(repoId, searchToken, rowNumber, prefer, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * Get the search results listing of a search.
     *
     * - Returns a search result listing if the search is completed. - Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type. - Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values. - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. sort order can be either \&quot;asc\&quot; or \&quot;desc\&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving the results again. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     */
    @Test
    public void getSearchResultsTest() {
        String repoId = null;
        String searchToken = null;
        Boolean groupByEntryType = null;
        Boolean refresh = null;
        List<String> fields = null;
        Boolean formatFields = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfEntry response = api.getSearchResults(repoId, searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * Get the status of a search using a token.
     *
     * - Returns search status. - Provide a token (returned in the create search asynchronous route), and get the search status, progress, and any errors that may have occurred. When the search is completed, the Location header can be inspected as a link to the search results. - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or Canceled.
     */
    @Test
    public void getSearchStatusTest() {
        String repoId = null;
        String searchToken = null;
        // OperationProgress response = api.getSearchStatus(repoId, searchToken);

        // TODO: test validations
    }
}
