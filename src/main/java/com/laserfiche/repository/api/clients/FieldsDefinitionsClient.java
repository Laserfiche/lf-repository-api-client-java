package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;

import java.util.concurrent.CompletableFuture;

public interface FieldsDefinitionsClient {
    /**
     *
     * - Returns a single field definition associated with the specified ID.  - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param fieldDefinitionId The requested field definition ID. (required)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;WFieldInfo&gt;
     */
    CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture, String select);

    /**
     *
     * - Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize);

    /**
     * - Returns a paged listing of field definitions available in the specified repository.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize The maximum number of items returned by the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink, Integer maxPageSize);

    /**
     * - Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param callback A lambda that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param repoId The requested repository ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     */
    void getFieldDefinitionsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>> callback, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize);
}
