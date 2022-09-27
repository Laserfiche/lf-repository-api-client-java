package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;

import java.util.concurrent.CompletableFuture;

public interface FieldDefinitionsClient {

    /**
     * - Returns a single field definition associated with the specified ID.
     * - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed.
     * - Allowed OData query options: Select
     *
     * @param repoId            The requested repository ID.
     * @param fieldDefinitionId The requested field definition ID.
     * @param culture           An optional query parameter used to indicate the locale that should be used for formatting.
     *                          The value should be a standard language tag.
     * @param select            Limits the properties returned in the result.
     * @return CompletableFuture<WFieldInfo> The return value
     */
    CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture,
            String select);

    /**
     * - Returns a paged listing of field definitions available in the specified repository.
     * - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId  The requested repository ID.
     * @param prefer  An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag.
     * @param select  Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top     Limits the number of items returned from a collection.
     * @param skip    Excludes the specified number of items of the queried collection from the result.
     * @param count   Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<ODataValueContextOfIListOfWFieldInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink,
            int maxPageSize);
}
