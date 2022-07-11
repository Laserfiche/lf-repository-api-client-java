package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.TagDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;

import java.util.concurrent.CompletableFuture;

public class TagDefinitionsClient extends BaseClient<TagDefinitionsApi> {
    /**
     *
     * - Returns a single tag definition. - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param tagId The requested tag definition ID. (required)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;WTagInfo&gt;
     */
    public CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, Integer tagId, String culture, String select) {
        return client.getTagDefinitionById(repoId, tagId, culture, select);
    }

    /**
     *
     * - Returns all tag definitions in the repository. - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return client.getTagDefinitions(repoId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    /**
     * - Returns all tag definitions in the repository.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize Maximum number of items returned by the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return client.getTagDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    /**
     *
     * - Returns all tag definitions in the repository. - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
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
    public void getTrusteeAttributeKeyValuePairsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWTagInfo>> callback, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = getTagDefinitions(repoId, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTagDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
