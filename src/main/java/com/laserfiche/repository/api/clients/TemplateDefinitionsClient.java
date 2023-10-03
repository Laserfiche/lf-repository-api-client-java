package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.TemplateDefinition;
import com.laserfiche.repository.api.clients.impl.model.TemplateDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateId;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateName;

import java.util.function.Function;

/**
 * The Laserfiche Repository TemplateDefinitions API client.
 */
public interface TemplateDefinitionsClient {

    /**
     * - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned.<br>- Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListTemplateDefinitions} which encapsulates the parameters of {@link #listTemplateDefinitions listTemplateDefinitions} method.
     * @return {@link TemplateDefinitionCollectionResponse} The return value
     */
    TemplateDefinitionCollectionResponse listTemplateDefinitions(ParametersForListTemplateDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateDefinitionCollectionResponse} The return value
     */
    TemplateDefinitionCollectionResponse listTemplateDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateDefinitions listTemplateDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListTemplateDefinitions} which encapsulates the parameters of {@link #listTemplateDefinitions listTemplateDefinitions} method.
     */
    void listTemplateDefinitionsForEach(Function<TemplateDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTemplateDefinitions parameters);

    /**
     * - Returns the field definitions assigned to a template definition.<br>- Provide a template definition name, and get a paged listing of the field definitions assigned to that template. <br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateName} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateName listTemplateFieldDefinitionsByTemplateName} method.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateName(ParametersForListTemplateFieldDefinitionsByTemplateName parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateNameNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateFieldDefinitionsByTemplateName listTemplateFieldDefinitionsByTemplateName}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateName} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateName listTemplateFieldDefinitionsByTemplateName} method.
     */
    void listTemplateFieldDefinitionsByTemplateNameForEach(Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTemplateFieldDefinitionsByTemplateName parameters);

    /**
     * - Returns a single template definition (including field definitions, if relevant).<br>- Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed.<br>- Allowed OData query options: Select<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetTemplateDefinition} which encapsulates the parameters of {@link #getTemplateDefinition getTemplateDefinition} method.
     * @return {@link TemplateDefinition} The return value
     */
    TemplateDefinition getTemplateDefinition(ParametersForGetTemplateDefinition parameters);

    /**
     * - Returns the field definitions assigned to a template definition.<br>- Provide a template definition ID, and get a paged listing of the field definitions assigned to that template. <br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateId} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateId listTemplateFieldDefinitionsByTemplateId} method.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateId(ParametersForListTemplateFieldDefinitionsByTemplateId parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateIdNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateFieldDefinitionsByTemplateId listTemplateFieldDefinitionsByTemplateId}, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters  An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateId} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateId listTemplateFieldDefinitionsByTemplateId} method.
     */
    void listTemplateFieldDefinitionsByTemplateIdForEach(Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTemplateFieldDefinitionsByTemplateId parameters);
}
