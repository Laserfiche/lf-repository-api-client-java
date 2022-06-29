package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.client.model.WTagInfo;

import java.util.concurrent.CompletableFuture;

public class TagDefinitionsClient {
    private TagDefinitionsApi client;

    protected void setClient(TagDefinitionsApi client) {
        this.client = client;
    }

    /**
     *
     * - Returns a single tag definition. - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param tagId The requested tag definition ID. (required)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;WTagInfo&gt;
     */
    CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, Integer tagId, String culture, String $select) {
        return client.getTagDefinitionById(repoId, tagId, culture, $select);
    }

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
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTagDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);
    }
}
