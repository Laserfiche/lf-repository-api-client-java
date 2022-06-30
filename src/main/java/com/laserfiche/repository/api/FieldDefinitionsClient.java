package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.FieldDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.client.model.WFieldInfo;

import java.util.concurrent.CompletableFuture;

public class FieldDefinitionsClient extends BaseClient<FieldDefinitionsApi> {
    private FieldDefinitionsApi client;

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
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture, String $select) {
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
     * @param maxPageSize Indicates the maximum number of items to return.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count, Integer maxPageSize) {
        return client.getFieldDefinitions(repoId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     * - Returns a paged listing of field definitions available in the specified repository.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize The maximum number of items returned by the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return client.getFieldDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    /**
     * - Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param callback A lambda that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param repoId The requested repository ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     */
    public void getTrusteeAttributeKeyValuePairsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>> callback, String repoId, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = getFieldDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getFieldDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
