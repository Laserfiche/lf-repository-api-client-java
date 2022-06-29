package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;

import java.util.concurrent.CompletableFuture;

public class AttributesClient {
    private AttributesApi client;

    protected void setClient(AttributesApi client) {
        this.client = client;
    }

    /**
     * Get the attribute key value pairs associated with the authenticated user.
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the \&quot;Everyone\&quot; group. - Attribute keys can be used with subsequent calls to get specific attribute values. - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the \&quot;Everyone\&quot; group. Note when this is true, the response does not include both the \&quot;Everyone\&quot; groups attribute and the currently authenticated user, but only the \&quot;Everyone\&quot; groups.
     * @param repoId The requested repository ID. (required)
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user. (optional)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfListOfAttribute&gt;
     */
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId, Boolean everyone, String prefer, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, $select, $orderby, $top, $skip, $count);
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
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsNextLink(String nextLink, int maxPageSize) {
        return client.getTrusteeAttributeKeyValuePairsPaginate(nextLink, String.format("maxpagesize={%d}", maxPageSize));
    }
}
