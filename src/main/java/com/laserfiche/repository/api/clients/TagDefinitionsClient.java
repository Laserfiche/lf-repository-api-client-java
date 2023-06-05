package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitions;
import java.util.function.Function;

/** The Laserfiche Repository TagDefinitions API client. */
public interface TagDefinitionsClient {

    /**
     * - Returns all tag definitions in the repository. - Provide a repository ID and get a paged
     * listing of tag definitions available in the repository. Useful when trying to display all tag
     * definitions available, not only tags assigned to a specific entry. - Default page size: 100.
     * Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetTagDefinitions} which encapsulates
     *     the parameters of {@link #getTagDefinitions getTagDefinitions} method.
     * @return {@link ODataValueContextOfIListOfWTagInfo} The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagDefinitions(ParametersForGetTagDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfWTagInfo} The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link
     * #getTagDefinitions getTagDefinitions}, and apply a function on the response of each
     * iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetTagDefinitions} which encapsulates
     *     the parameters of {@link #getTagDefinitions getTagDefinitions} method.
     */
    void getTagDefinitionsForEach(
            Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTagDefinitions parameters);

    /**
     * - Returns a single tag definition. - Provide a tag definition ID, and get the single tag
     * definition associated with that ID. Useful when another route provides a minimal amount of
     * details, and more information about the specific tag is needed. - Allowed OData query
     * options: Select
     *
     * @param parameters An object of type {@link ParametersForGetTagDefinitionById} which
     *     encapsulates the parameters of {@link #getTagDefinitionById getTagDefinitionById} method.
     * @return {@link WTagInfo} The return value
     */
    WTagInfo getTagDefinitionById(ParametersForGetTagDefinitionById parameters);
}
