package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;

import java.util.concurrent.CompletableFuture;

public interface AttributesClient {

    /**
     * - Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within "Everyone" group.
     * - Optional query parameters: everyone (bool, default false). When true, the server only searches for the attribute value with the given key upon the authenticated users attributes. If false, only the authenticated users attributes will be queried.
     *
     * @param repoId       The requested repository ID.
     * @param attributeKey The requested attribute key.
     * @param everyone     Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     * @return CompletableFuture<Attribute> The return value
     */
    CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey, Boolean everyone);

    /**
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the "Everyone" group.
     * - Attribute keys can be used with subsequent calls to get specific attribute values.
     * - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the "Everyone" group. Note when this is true, the response does not include both the "Everyone" groups attribute and the currently authenticated user, but only the "Everyone" groups.
     *
     * @param repoId   The requested repository ID.
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     * @param prefer   An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select   Limits the properties returned in the result.
     * @param orderby  Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top      Limits the number of items returned from a collection.
     * @param skip     Excludes the specified number of items of the queried collection from the result.
     * @param count    Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<ODataValueContextOfListOfAttribute> The return value
     */
    CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId,
            Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count);
}
