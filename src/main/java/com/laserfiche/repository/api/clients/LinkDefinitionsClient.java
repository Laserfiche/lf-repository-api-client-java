package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.LinkDefinition;
import com.laserfiche.repository.api.clients.impl.model.LinkDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListLinkDefinitions;

import java.util.function.Function;

/**
 * The Laserfiche Repository LinkDefinitions API client.
 */
public interface LinkDefinitionsClient {

    /**
     * - Returns the link definitions in the repository.<br>- Provide a repository ID and get a paged listing of link definitions available in the repository. Useful when trying to display all link definitions available, not only links assigned to a specific entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListLinkDefinitions} which encapsulates the parameters of {@link #listLinkDefinitions listLinkDefinitions} method.
     * @return {@link LinkDefinitionCollectionResponse} The return value
     */
    LinkDefinitionCollectionResponse listLinkDefinitions(ParametersForListLinkDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link LinkDefinitionCollectionResponse} The return value
     */
    LinkDefinitionCollectionResponse listLinkDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listLinkDefinitions listLinkDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListLinkDefinitions} which encapsulates the parameters of {@link #listLinkDefinitions listLinkDefinitions} method.
     */
    void listLinkDefinitionsForEach(Function<LinkDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListLinkDefinitions parameters);

    /**
     * - Returns a single link definition associated with the specified ID.<br>- Provide a link definition ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed.<br>- Allowed OData query options: Select<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetLinkDefinition} which encapsulates the parameters of {@link #getLinkDefinition getLinkDefinition} method.
     * @return {@link LinkDefinition} The return value
     */
    LinkDefinition getLinkDefinition(ParametersForGetLinkDefinition parameters);
}
