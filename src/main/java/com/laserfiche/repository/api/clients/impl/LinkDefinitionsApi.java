package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import retrofit2.http.*;

import java.util.concurrent.CompletableFuture;

public interface LinkDefinitionsApi {
  /**
   * Returns a single link definition object.
   * - Returns a single field definition associated with the specified ID. - Provide a link type ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed. - Allowed OData query options: Select
   * @param repoId The requested repository ID. (required)
   * @param linkTypeId The requested link type ID. (required)
   * @param $select Limits the properties returned in the result. (optional)
   * @return CompletableFuture&lt;EntryLinkTypeInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}")
  CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("linkTypeId") Integer linkTypeId, @retrofit2.http.Query("$select") String $select
  );

  /**
   * Returns the link definitions associated with a repository.
   * - Returns the link definitions in the repository. - Provide a repository ID and get a paged listing of link definitions available in the repository. Useful when trying to display all link definitions available, not only links assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
   * @param repoId The requested repository ID. (required)
   * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
   * @param $top Limits the number of items returned from a collection. (optional)
   * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
   * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
   * @return CompletableFuture&lt;ODataValueContextOfIListOfEntryLinkTypeInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/LinkDefinitions")
  CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

}
