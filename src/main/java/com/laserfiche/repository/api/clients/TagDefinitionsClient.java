package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;

import java.util.concurrent.CompletableFuture;

public interface TagDefinitionsClient {

    /**
     * - Returns all tag definitions in the repository.
     * - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry.
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
     * @return CompletableFuture<ODataValueContextOfIListOfWTagInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * - Returns a single tag definition.
     * - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed.
     * - Allowed OData query options: Select
     *
     * @param repoId  The requested repository ID.
     * @param tagId   The requested tag definition ID.
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag.
     * @param select  Limits the properties returned in the result.
     * @return CompletableFuture<WTagInfo> The return value
     */
    CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, Integer tagId, String culture, String select);
}
