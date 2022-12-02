package com.laserfiche.repository.api.clients;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;

public interface SearchesClient {

    /**
     *  - Returns search status.
     * - Provide a token (returned in the create search asynchronous route), and get the search status, progress, and any errors that may have occurred. When the search is completed, the Location header can be inspected as a link to the search results.
     * - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or Canceled.
     *
     *  @param parameters An object of type ParametersForGetSearchStatus which encapsulates the parameters of getSearchStatus method.
     *  @return OperationProgress The return value
     */
    OperationProgress getSearchStatus(ParametersForGetSearchStatus parameters);

    /**
     *  - Cancels a currently running search.
     * - Closes a completed search.
     *
     *  @param parameters An object of type ParametersForCancelOrCloseSearch which encapsulates the parameters of cancelOrCloseSearch method.
     *  @return ODataValueOfBoolean The return value
     */
    ODataValueOfBoolean cancelOrCloseSearch(ParametersForCancelOrCloseSearch parameters);

    /**
     *  - Returns the context hits associated with a search result entry.
     * - Given a searchToken, and rowNumber associated with a search entry in the listing, return the context hits for that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param parameters An object of type ParametersForGetSearchContextHits which encapsulates the parameters of getSearchContextHits method.
     *  @return ODataValueContextOfIListOfContextHit The return value
     */
    ODataValueContextOfIListOfContextHit getSearchContextHits(ParametersForGetSearchContextHits parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfContextHit The return value
     */
    ODataValueContextOfIListOfContextHit getSearchContextHitsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getSearchContextHits&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     */
    void getSearchContextHitsForEach(Function<ODataValueContextOfIListOfContextHit, Boolean> callback, Integer maxPageSize, ParametersForGetSearchContextHits parameters);

    /**
     *  - Runs a search operation on the repository.
     * - Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage). The status for search operations must be checked via the Search specific status checking route.
     *
     *  @param parameters An object of type ParametersForCreateSearchOperation which encapsulates the parameters of createSearchOperation method.
     *  @return AcceptedOperation The return value
     */
    AcceptedOperation createSearchOperation(ParametersForCreateSearchOperation parameters);

    /**
     *  - Returns a search result listing if the search is completed.
     * - Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type.
     * - Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values.
     * - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName direction,PropertyName2 direction&quot;. sort order can be either &quot;asc&quot; or &quot;desc&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving the results again.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     *  @param parameters An object of type ParametersForGetSearchResults which encapsulates the parameters of getSearchResults method.
     *  @return ODataValueContextOfIListOfEntry The return value
     */
    ODataValueContextOfIListOfEntry getSearchResults(ParametersForGetSearchResults parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfEntry The return value
     */
    ODataValueContextOfIListOfEntry getSearchResultsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getSearchResults&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     */
    void getSearchResultsForEach(Function<ODataValueContextOfIListOfEntry, Boolean> callback, Integer maxPageSize, ParametersForGetSearchResults parameters);
}
