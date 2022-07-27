package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

public interface TagDefinitionsApi {
  /**
   * 
   * - Returns a single tag definition. - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed. - Allowed OData query options: Select
   * @param repoId The requested repository ID. (required)
   * @param tagId The requested tag definition ID. (required)
   * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @return CompletableFuture&lt;WTagInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/TagDefinitions/{tagId}")
  CompletableFuture<WTagInfo> getTagDefinitionById(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("tagId") Integer tagId, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select
  );

  /**
   * 
   * - Returns all tag definitions in the repository. - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
   * @param repoId The requested repository ID. (required)
   * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
   * @param $top Limits the number of items returned from a collection. (optional)
   * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
   * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
   * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/TagDefinitions")
  CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );
}
