// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.EntryCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.SearchContextHitCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.StartTaskResponse;
import com.laserfiche.repository.api.clients.params.ParametersForListSearchContextHits;
import com.laserfiche.repository.api.clients.params.ParametersForListSearchResults;
import com.laserfiche.repository.api.clients.params.ParametersForStartSearchEntry;

import java.util.function.Function;

/**
 * The Laserfiche Repository Searches API client.
 */
public interface SearchesClient {

    /**
     * - Runs a search operation on the repository.<br>- The status for search operations must be checked via the Tasks route.<br>- Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage).<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForStartSearchEntry} which encapsulates the parameters of {@link #startSearchEntry startSearchEntry} method.
     * @return {@link StartTaskResponse} The return value
     */
    StartTaskResponse startSearchEntry(ParametersForStartSearchEntry parameters);

    /**
     * - Returns the context hits associated with a search result entry.<br>- Given a taskId, and rowNumber associated with a search entry in the listing, return the context hits for that entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListSearchContextHits} which encapsulates the parameters of {@link #listSearchContextHits listSearchContextHits} method.
     * @return {@link SearchContextHitCollectionResponse} The return value
     */
    SearchContextHitCollectionResponse listSearchContextHits(ParametersForListSearchContextHits parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link SearchContextHitCollectionResponse} The return value
     */
    SearchContextHitCollectionResponse listSearchContextHitsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listSearchContextHits listSearchContextHits}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListSearchContextHits} which encapsulates the parameters of {@link #listSearchContextHits listSearchContextHits} method.
     */
    void listSearchContextHitsForEach(Function<SearchContextHitCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListSearchContextHits parameters);

    /**
     * - Returns a search result listing if the search is completed.<br>- Search results expire after 5 minutes, but can be refreshed by retrieving the results again.<br>- Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type.<br>- Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values.<br>- Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names. If field values are requested, only the first value is returned if it is a multi value field. The remaining field values can be retrieved via the GET fields route. Null or Empty field values should not be used to determine if a field is assigned to the entry.<br>- Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName direction,PropertyName2 direction&quot;. sort order can be either &quot;asc&quot; or &quot;desc&quot;.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListSearchResults} which encapsulates the parameters of {@link #listSearchResults listSearchResults} method.
     * @return {@link EntryCollectionResponse} The return value
     */
    EntryCollectionResponse listSearchResults(ParametersForListSearchResults parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link EntryCollectionResponse} The return value
     */
    EntryCollectionResponse listSearchResultsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listSearchResults listSearchResults}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListSearchResults} which encapsulates the parameters of {@link #listSearchResults listSearchResults} method.
     */
    void listSearchResultsForEach(Function<EntryCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListSearchResults parameters);
}
