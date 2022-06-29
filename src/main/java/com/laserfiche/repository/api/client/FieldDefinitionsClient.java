package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.client.model.WFieldInfo;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

public class FieldDefinitionsClient {
    private  FieldDefinitionsApi client;

    protected void setClient(FieldDefinitionsApi client) {
        this.client = client;
    }

    /**
     *
     * - Returns a single field definition associated with the specified ID.  - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param fieldDefinitionId The requested field definition ID. (required)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;WFieldInfo&gt;
     */
    CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture, String $select) {
        return client.getFieldDefinitionById(repoId, fieldDefinitionId, culture, $select);
    }

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
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getFieldDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     * - Returns a paged listing of field definitions available in the specified repository.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize The maximum number of items returned by the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink, int maxPageSize) {
        return client.getFieldDefinitionsPaginate(nextLink, String.format("maxpagesize={%d}", maxPageSize));
    }
}
