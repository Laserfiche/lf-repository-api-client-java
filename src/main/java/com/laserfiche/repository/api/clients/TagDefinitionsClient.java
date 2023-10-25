// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.TagDefinition;
import com.laserfiche.repository.api.clients.impl.model.TagDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListTagDefinitions;

import java.util.function.Function;

/**
 * The Laserfiche Repository TagDefinitions API client.
 */
public interface TagDefinitionsClient {

    /**
     * - Returns a single tag definition.<br>- Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed.<br>- Allowed OData query options: Select<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetTagDefinition} which encapsulates the parameters of {@link #getTagDefinition getTagDefinition} method.
     * @return {@link TagDefinition} The return value
     */
    TagDefinition getTagDefinition(ParametersForGetTagDefinition parameters);

    /**
     * - Returns all tag definitions in the repository.<br>- Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListTagDefinitions} which encapsulates the parameters of {@link #listTagDefinitions listTagDefinitions} method.
     * @return {@link TagDefinitionCollectionResponse} The return value
     */
    TagDefinitionCollectionResponse listTagDefinitions(ParametersForListTagDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TagDefinitionCollectionResponse} The return value
     */
    TagDefinitionCollectionResponse listTagDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTagDefinitions listTagDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListTagDefinitions} which encapsulates the parameters of {@link #listTagDefinitions listTagDefinitions} method.
     */
    void listTagDefinitionsForEach(Function<TagDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTagDefinitions parameters);
}
