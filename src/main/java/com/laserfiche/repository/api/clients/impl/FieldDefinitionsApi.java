package com.laserfiche.repository.api.clients.impl;

import retrofit2.http.*;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import java.util.concurrent.CompletableFuture;

public interface FieldDefinitionsApi {
  /**
   * 
   * - Returns a single field definition associated with the specified ID.  - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed. - Allowed OData query options: Select
   * @param repoId The requested repository ID. (required)
   * @param fieldDefinitionId The requested field definition ID. (required)
   * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @return CompletableFuture&lt;WFieldInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}")
  CompletableFuture<WFieldInfo> getFieldDefinitionById(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("fieldDefinitionId") Integer fieldDefinitionId, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select
  );

  /**
   * 
   * - Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
   * @param repoId The requested repository ID. (required)
   * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
   * @param $select Limits the properties returned in the result. (optional)
   * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
   * @param $top Limits the number of items returned from a collection. (optional)
   * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
   * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
   * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
   */
  @GET("v1/Repositories/{repoId}/FieldDefinitions")
  CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   *
   * - Returns a paged listing of field definitions available in the specified repository.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

}
