package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

/**
 * The Laserfiche Repository Attributes API client.
 */
public interface AttributesClient {

    /**
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the &quot;Everyone&quot; group.<br>- Attribute keys can be used with subsequent calls to get specific attribute values.<br>- Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the &quot;Everyone&quot; group. Note when this is true, the response does not include both the &quot;Everyone&quot; groups attribute and the currently authenticated user, but only the &quot;Everyone&quot; groups.<br>- Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListAttributes} which encapsulates the parameters of {@link #listAttributes listAttributes} method.
     * @return {@link AttributeCollectionResponse} The return value
     */
    AttributeCollectionResponse listAttributes(ParametersForListAttributes parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link AttributeCollectionResponse} The return value
     */
    AttributeCollectionResponse listAttributesNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listAttributes listAttributes}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListAttributes} which encapsulates the parameters of {@link #listAttributes listAttributes} method.
     */
    void listAttributesForEach(
            Function<AttributeCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListAttributes parameters);

    /**
     * - Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within &quot;Everyone&quot; group.<br>- Optional query parameters: everyone (bool, default false). When true, the server only searches for the attribute value with the given key upon the authenticated users attributes. If false, only the authenticated users attributes will be queried.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetAttribute} which encapsulates the parameters of {@link #getAttribute getAttribute} method.
     * @return {@link Attribute} The return value
     */
    Attribute getAttribute(ParametersForGetAttribute parameters);
}
