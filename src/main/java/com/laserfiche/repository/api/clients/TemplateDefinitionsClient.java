package com.laserfiche.repository.api.clients;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.HttpMethod;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.ContentType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;

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
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateDefinitionCollectionResponse} The return value
     */
    TemplateDefinitionCollectionResponse listTemplateDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateDefinitions listTemplateDefinitions}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListTemplateDefinitions} which encapsulates the parameters of {@link #listTemplateDefinitions listTemplateDefinitions} method.
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
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateNameNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateFieldDefinitionsByTemplateName listTemplateFieldDefinitionsByTemplateName}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateName} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateName listTemplateFieldDefinitionsByTemplateName} method.
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
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TemplateFieldDefinitionCollectionResponse} The return value
     */
    TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateIdNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTemplateFieldDefinitionsByTemplateId listTemplateFieldDefinitionsByTemplateId}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListTemplateFieldDefinitionsByTemplateId} which encapsulates the parameters of {@link #listTemplateFieldDefinitionsByTemplateId listTemplateFieldDefinitionsByTemplateId} method.
     */
    void listTemplateFieldDefinitionsByTemplateIdForEach(Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTemplateFieldDefinitionsByTemplateId parameters);
}
