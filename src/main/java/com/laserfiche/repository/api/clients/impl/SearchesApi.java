package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.*;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchesApi {
  /**
   * Cancel or close an advanced search.
   * - Cancels a currently running search. - Closes a completed search.
   * @param repoId The requested repository ID. (required)
   * @param searchToken The requested searchToken. (required)
   * @return CompletableFuture&lt;ODataValueOfBoolean&gt;
   */
  @DELETE("v1/Repositories/{repoId}/Searches/{searchToken}")
  CompletableFuture<ODataValueOfBoolean> cancelOrCloseSearch(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("searchToken") String searchToken
  );

  /**
   * Run a search in the specified repository.
   * - Runs a search operation on the repository. - Optional body parameters: FuzzyType: (default none), which can be used to determine what is considered a match by number of letters or percentage. FuzzyFactor: integer value that determines the degree to which a search will be considered a match (integer value for NumberOfLetters, or int value representing a percentage). The status for search operations must be checked via the Search specific status checking route.         
   * @param repoId The requested repository ID. (required)
   * @param body The Laserfiche search command to run, optionally include fuzzy search settings. (optional)
   * @return CompletableFuture&lt;AcceptedOperation&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v1/Repositories/{repoId}/Searches")
  CompletableFuture<AcceptedOperation> createSearchOperation(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Body AdvancedSearchRequest body
  );

  /**
   * 
   * - Returns the context hits associated with a search result entry. - Given a searchToken, and rowNumber associated with a search entry in the listing, return the context hits for that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
   * @param repoId The requested repository ID. (required)
   * @param searchToken The requested searchToken. (required)
   * @param rowNumber The search result listing row number to get context hits for. (required)
   * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
   * @param $top Limits the number of items returned from a collection. (optional)
   * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
   * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
   * @return CompletableFuture&lt;ODataValueContextOfIListOfContextHit&gt;
   */
  @GET("v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits")
  CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHits(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("searchToken") String searchToken, @retrofit2.http.Path("rowNumber") Integer rowNumber, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   * - Returns the context hits associated with a search result entry.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfContextHit&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHitsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

  /**
   * Get the search results listing of a search.
   * - Returns a search result listing if the search is completed. - Optional query parameter: groupByOrderType (default false). This query parameter decides whether or not results are returned in groups based on their entry type. - Optional query parameter: refresh (default false). If the search listing should be refreshed to show updated values. - Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. sort order can be either \&quot;asc\&quot; or \&quot;desc\&quot;. Search results expire after 5 minutes, but can be refreshed by retrieving the results again. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
   * @param repoId The requested repository ID. (required)
   * @param searchToken The requested searchToken. (required)
   * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not. (optional)
   * @param refresh If the search listing should be refreshed to show updated values. (optional)
   * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each search result.  (optional)
   * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified. (optional)
   * @param prefer An optional odata header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
   * @param $top Limits the number of items returned from a collection. (optional)
   * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
   * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
   * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
   */
  @GET("v1/Repositories/{repoId}/Searches/{searchToken}/Results")
  CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResults(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("searchToken") String searchToken, @retrofit2.http.Query("groupByEntryType") Boolean groupByEntryType, @retrofit2.http.Query("refresh") Boolean refresh, @retrofit2.http.Query("fields") List<String> fields, @retrofit2.http.Query("formatFields") Boolean formatFields, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   * Get the search results listing of a search.
   * - Returns a search result listing if the search is completed.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResultsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

  /**
   * Get the status of a search using a token.
   * - Returns search status. - Provide a token (returned in the create search asynchronous route), and get the search status, progress, and any errors that may have occurred. When the search is completed, the Location header can be inspected as a link to the search results. - OperationStatus can be one of the following : NotStarted, InProgress, Completed, Failed, or Canceled.
   * @param repoId The requested repository ID. (required)
   * @param searchToken The requested searchToken. (required)
   * @return CompletableFuture&lt;OperationProgress&gt;
   */
  @GET("v1/Repositories/{repoId}/Searches/{searchToken}")
  CompletableFuture<OperationProgress> getSearchStatus(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("searchToken") String searchToken
  );

}
