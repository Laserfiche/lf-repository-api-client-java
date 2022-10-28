package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface SearchesClient {

    /**
     * - Returns search status.
     * - Provide a token (returned in the create search asynchronous route), and get the search status, progress, and any errors that may have occurred. When the search is completed, the Location header can be inspected as a link to the search results.
     * - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or Canceled.
     *
     * @param repoId      The requested repository ID.
     * @param searchToken The requested searchToken.
     * @return CompletableFuture&lt;OperationProgress&gt; The return value
     */
    CompletableFuture<OperationProgress> getSearchStatus(String repoId, String searchToken);

    /**
     * - Cancels a currently running search.
     * - Closes a completed search.
     *
     * @param repoId      The requested repository ID.
     * @param searchToken The requested searchToken.
     * @return CompletableFuture&lt;ODataValueOfBoolean&gt; The return value
     */
    CompletableFuture<ODataValueOfBoolean> cancelOrCloseSearch(String repoId, String searchToken);

    /**
     * - Returns the context hits associated with a search result entry.
     * - Given a searchToken, and rowNumber associated with a search entry in the listing, return the context hits for that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId      The requested repository ID.
     * @param searchToken The requested searchToken.
     * @param rowNumber   The search result listing row number to get context hits for.
     * @param prefer      An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select      Limits the properties returned in the result.
     * @param orderby     Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top         Limits the number of items returned from a collection.
     * @param skip        Excludes the specified number of items of the queried collection from the result.
     * @param count       Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfContextHit&gt; The return value
     */
    CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHits(String repoId, String searchToken,
            Integer rowNumber, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfContextHit&gt; The return value
     */
    CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHitsNextLink(String nextLink,
            Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getSearchContextHits&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId      The requested repository ID.
     * @param searchToken The requested searchToken.
     * @param rowNumber   The search result listing row number to get context hits for.
     * @param prefer      An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select      Limits the properties returned in the result.
     * @param orderby     Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top         Limits the number of items returned from a collection.
     * @param skip        Excludes the specified number of items of the queried collection from the result.
     * @param count       Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture&lt;Void&gt; The return value
     */
    CompletableFuture<Void> getSearchContextHitsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfContextHit>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String searchToken, Integer rowNumber, String prefer, String select,
            String orderby, Integer top, Integer skip, Boolean count);

    /**
     * - Runs a search operation on the repository.
     * - Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage). The status for search operations must be checked via the Search specific status checking route.
     *
     * @param repoId      The requested repository ID.
     * @param requestBody The Laserfiche search command to run, optionally include fuzzy search settings.
     * @return CompletableFuture&lt;AcceptedOperation&gt; The return value
     */
    CompletableFuture<AcceptedOperation> createSearchOperation(String repoId, AdvancedSearchRequest requestBody);

    /**
     * - Returns a search result listing if the search is completed.
     * - Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type.
     * - Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values.
     * - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName direction,PropertyName2 direction&quot;. sort order can be either &quot;asc&quot; or &quot;desc&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving the results again.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     * @param repoId           The requested repository ID.
     * @param searchToken      The requested searchToken.
     * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not.
     * @param refresh          If the search listing should be refreshed to show updated values.
     * @param fields           Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     * @param formatFields     Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @param prefer           An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture          An optional query parameter used to indicate the locale that should be used for formatting.
     *                         The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *                         culture will not be used for formatting.
     * @param select           Limits the properties returned in the result.
     * @param orderby          Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top              Limits the number of items returned from a collection.
     * @param skip             Excludes the specified number of items of the queried collection from the result.
     * @param count            Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt; The return value
     */
    CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResults(String repoId, String searchToken,
            Boolean groupByEntryType, Boolean refresh, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt; The return value
     */
    CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResultsNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getSearchResults&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback         A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize      Optionally specify the maximum number of items to retrieve.
     * @param repoId           The requested repository ID.
     * @param searchToken      The requested searchToken.
     * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not.
     * @param refresh          If the search listing should be refreshed to show updated values.
     * @param fields           Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     * @param formatFields     Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @param prefer           An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture          An optional query parameter used to indicate the locale that should be used for formatting.
     *                         The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *                         culture will not be used for formatting.
     * @param select           Limits the properties returned in the result.
     * @param orderby          Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top              Limits the number of items returned from a collection.
     * @param skip             Excludes the specified number of items of the queried collection from the result.
     * @param count            Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture&lt;Void&gt; The return value
     */
    CompletableFuture<Void> getSearchResultsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String searchToken, Boolean groupByEntryType, Boolean refresh,
            String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count);
}
