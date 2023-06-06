package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

/** The Laserfiche Repository Searches API client. */
public interface SearchesClient {

    /**
     * - Returns search status.<br>
     * - Provide a token (returned in the create search asynchronous route), and get the search
     * status, progress, and any errors that may have occurred. When the search is completed, the
     * Location header can be inspected as a link to the search results.<br>
     * - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or
     * Canceled.
     *
     * @param parameters An object of type {@link ParametersForGetSearchStatus} which encapsulates
     *     the parameters of {@link #getSearchStatus getSearchStatus} method.
     * @return {@link OperationProgress} The return value
     */
    OperationProgress getSearchStatus(ParametersForGetSearchStatus parameters);

    /**
     * - Cancels a currently running search.<br>
     * - Closes a completed search.
     *
     * @param parameters An object of type {@link ParametersForCancelOrCloseSearch} which
     *     encapsulates the parameters of {@link #cancelOrCloseSearch cancelOrCloseSearch} method.
     * @return {@link ODataValueOfBoolean} The return value
     */
    ODataValueOfBoolean cancelOrCloseSearch(ParametersForCancelOrCloseSearch parameters);

    /**
     * - Returns the context hits associated with a search result entry.<br>
     * - Given a searchToken, and rowNumber associated with a search entry in the listing, return
     * the context hits for that entry.<br>
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top
     * | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetSearchContextHits} which
     *     encapsulates the parameters of {@link #getSearchContextHits getSearchContextHits} method.
     * @return {@link ODataValueContextOfIListOfContextHit} The return value
     */
    ODataValueContextOfIListOfContextHit getSearchContextHits(
            ParametersForGetSearchContextHits parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfContextHit} The return value
     */
    ODataValueContextOfIListOfContextHit getSearchContextHitsNextLink(
            String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link
     * #getSearchContextHits getSearchContextHits}, and apply a function on the response of each
     * iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetSearchContextHits} which
     *     encapsulates the parameters of {@link #getSearchContextHits getSearchContextHits} method.
     */
    void getSearchContextHitsForEach(
            Function<ODataValueContextOfIListOfContextHit, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetSearchContextHits parameters);

    /**
     * - Runs a search operation on the repository.<br>
     * - Optional body parameters: FuzzyType: (default none), which can be used to determine what is
     * considered a match by number of letters or percentage. FuzzyFactor: integer value that
     * determines the degree to which a search will be considered a match (integer value for
     * NumberOfLetters, or int value representing a percentage). The status for search operations
     * must be checked via the Search specific status checking route.
     *
     * @param parameters An object of type {@link ParametersForCreateSearchOperation} which
     *     encapsulates the parameters of {@link #createSearchOperation createSearchOperation}
     *     method.
     * @return {@link AcceptedOperation} The return value
     */
    AcceptedOperation createSearchOperation(ParametersForCreateSearchOperation parameters);

    /**
     * - Returns a search result listing if the search is completed.<br>
     * - Optional query parameter: groupByOrderType (default false). This query parameter decides
     * whether or not results are returned in groups based on their entry type.<br>
     * - Optional query parameter: refresh (default false). If the search listing should be
     * refreshed to show updated values.<br>
     * - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top
     * | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName
     * direction,PropertyName2 direction&quot;. sort order can be either &quot;asc&quot; or
     * &quot;desc&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving
     * the results again.<br>
     * - Optionally returns field values for the entries in the search result listing. Each field
     * name needs to be specified in the request. Maximum limit of 10 field names.<br>
     * - If field values are requested, only the first value is returned if it is a multi value
     * field.<br>
     * - Null or Empty field values should not be used to determine if a field is assigned to the
     * entry.
     *
     * @param parameters An object of type {@link ParametersForGetSearchResults} which encapsulates
     *     the parameters of {@link #getSearchResults getSearchResults} method.
     * @return {@link ODataValueContextOfIListOfEntry} The return value
     */
    ODataValueContextOfIListOfEntry getSearchResults(ParametersForGetSearchResults parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfEntry} The return value
     */
    ODataValueContextOfIListOfEntry getSearchResultsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getSearchResults
     * getSearchResults}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetSearchResults} which encapsulates
     *     the parameters of {@link #getSearchResults getSearchResults} method.
     */
    void getSearchResultsForEach(
            Function<ODataValueContextOfIListOfEntry, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetSearchResults parameters);
}
