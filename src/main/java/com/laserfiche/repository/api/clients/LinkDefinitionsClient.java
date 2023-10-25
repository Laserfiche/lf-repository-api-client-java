// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

/**
 * The Laserfiche Repository LinkDefinitions API client.
 */
public interface LinkDefinitionsClient {

    /**
     * - Returns a single link definition associated with the specified ID.<br>- Provide a link type ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed.<br>- Allowed OData query options: Select
     *
     * @param parameters An object of type {@link ParametersForGetLinkDefinitionById} which encapsulates the parameters of {@link #getLinkDefinitionById getLinkDefinitionById} method.
     * @return {@link EntryLinkTypeInfo} The return value
     */
    EntryLinkTypeInfo getLinkDefinitionById(ParametersForGetLinkDefinitionById parameters);

    /**
     * - Returns the link definitions in the repository.<br>- Provide a repository ID and get a paged listing of link definitions available in the repository. Useful when trying to display all link definitions available, not only links assigned to a specific entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetLinkDefinitions} which encapsulates the parameters of {@link #getLinkDefinitions getLinkDefinitions} method.
     * @return {@link ODataValueContextOfIListOfEntryLinkTypeInfo} The return value
     */
    ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitions(ParametersForGetLinkDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfEntryLinkTypeInfo} The return value
     */
    ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getLinkDefinitions getLinkDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetLinkDefinitions} which encapsulates the parameters of {@link #getLinkDefinitions getLinkDefinitions} method.
     */
    void getLinkDefinitionsForEach(
            Function<ODataValueContextOfIListOfEntryLinkTypeInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetLinkDefinitions parameters);
}
