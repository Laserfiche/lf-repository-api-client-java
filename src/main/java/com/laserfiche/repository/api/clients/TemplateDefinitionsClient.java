// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

/**
 * The Laserfiche Repository TemplateDefinitions API client.
 */
public interface TemplateDefinitionsClient {

    /**
     * - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned.<br>- Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetTemplateDefinitions} which encapsulates the parameters of {@link #getTemplateDefinitions getTemplateDefinitions} method.
     * @return {@link ODataValueContextOfIListOfWTemplateInfo} The return value
     */
    ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitions(ParametersForGetTemplateDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfWTemplateInfo} The return value
     */
    ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getTemplateDefinitions getTemplateDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetTemplateDefinitions} which encapsulates the parameters of {@link #getTemplateDefinitions getTemplateDefinitions} method.
     */
    void getTemplateDefinitionsForEach(
            Function<ODataValueContextOfIListOfWTemplateInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTemplateDefinitions parameters);

    /**
     * - Returns the field definitions assigned to a template definition.<br>- Provide a template definition name, and get a paged listing of the field definitions assigned to that template. <br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} which encapsulates the parameters of {@link #getTemplateFieldDefinitionsByTemplateName getTemplateFieldDefinitionsByTemplateName} method.
     * @return {@link ODataValueContextOfIListOfTemplateFieldInfo} The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateName(
            ParametersForGetTemplateFieldDefinitionsByTemplateName parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfTemplateFieldInfo} The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateNameNextLink(
            String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getTemplateFieldDefinitionsByTemplateName getTemplateFieldDefinitionsByTemplateName}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} which encapsulates the parameters of {@link #getTemplateFieldDefinitionsByTemplateName getTemplateFieldDefinitionsByTemplateName} method.
     */
    void getTemplateFieldDefinitionsByTemplateNameForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTemplateFieldDefinitionsByTemplateName parameters);

    /**
     * - Returns the field definitions assigned to a template definition.<br>- Provide a template definition ID, and get a paged listing of the field definitions assigned to that template. <br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetTemplateFieldDefinitions} which encapsulates the parameters of {@link #getTemplateFieldDefinitions getTemplateFieldDefinitions} method.
     * @return {@link ODataValueContextOfIListOfTemplateFieldInfo} The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitions(
            ParametersForGetTemplateFieldDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfTemplateFieldInfo} The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getTemplateFieldDefinitions getTemplateFieldDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetTemplateFieldDefinitions} which encapsulates the parameters of {@link #getTemplateFieldDefinitions getTemplateFieldDefinitions} method.
     */
    void getTemplateFieldDefinitionsForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTemplateFieldDefinitions parameters);

    /**
     * - Returns a single template definition (including field definitions, if relevant).<br>- Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed.<br>- Allowed OData query options: Select
     *
     * @param parameters An object of type {@link ParametersForGetTemplateDefinitionById} which encapsulates the parameters of {@link #getTemplateDefinitionById getTemplateDefinitionById} method.
     * @return {@link WTemplateInfo} The return value
     */
    WTemplateInfo getTemplateDefinitionById(ParametersForGetTemplateDefinitionById parameters);
}
