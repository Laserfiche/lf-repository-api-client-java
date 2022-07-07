package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.AttributesApi;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;

import java.util.concurrent.CompletableFuture;

public class AttributesClient extends BaseClient<AttributesApi> {
    /**
     * Get the attribute key value pairs associated with the authenticated user.
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the \&quot;Everyone\&quot; group. - Attribute keys can be used with subsequent calls to get specific attribute values. - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the \&quot;Everyone\&quot; group. Note when this is true, the response does not include both the \&quot;Everyone\&quot; groups attribute and the currently authenticated user, but only the \&quot;Everyone\&quot; groups.
     * @param repoId The requested repository ID. (required)
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user. (optional)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     * @return CompletableFuture&lt;ODataValueContextOfListOfAttribute&gt;
     */
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return client.getTrusteeAttributeKeyValuePairs(repoId, everyone, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), select, orderby, top, skip, count);
    }

    /**
     * Get an attribute object by key associated with the authenticated user.
     * - Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within
     *  \&quot;Everyone\&quot; group. - Optional query parameters: everyone (bool, default false). When true, the server
     * only searches for the attribute value with the given key upon the authenticated users attributes. If false, only
     * the authenticated users attributes will be queried.
     * @param nextLink Full URL to get the rest of the collection of data.
     * @param maxPageSize The maximum number of items to retrieve.
     * @return CompletableFuture&lt;Attribute&gt;
     */
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsNextLink(String nextLink, Integer maxPageSize) {
        return client.getTrusteeAttributeKeyValuePairsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    /**
     * Get an attribute object by key associated with the authenticated user.
     * @param callback A lambda that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param repoId The requested repository ID. (required)
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user. (optional)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @param maxPageSize Indicates the maximum number of items to return.
     */
    public void getTrusteeAttributeKeyValuePairsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfListOfAttribute>> callback, String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfListOfAttribute> future = getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
