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
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;

public interface FieldDefinitionsClient {

    /**
     * - Returns a single field definition associated with the specified ID.
     * - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed.
     * - Allowed OData query options: Select
     *
     *  @param parameters An object of type ParametersForGetFieldDefinitionById which encapsulates the parameters of getFieldDefinitionById method.
     *  @return WFieldInfo The return value
     */
    WFieldInfo getFieldDefinitionById(ParametersForGetFieldDefinitionById parameters);

    /**
     * - Returns a paged listing of field definitions available in the specified repository.
     * - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param parameters An object of type ParametersForGetFieldDefinitions which encapsulates the parameters of getFieldDefinitions method.
     *  @return ODataValueContextOfIListOfWFieldInfo The return value
     */
    ODataValueContextOfIListOfWFieldInfo getFieldDefinitions(ParametersForGetFieldDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfWFieldInfo The return value
     */
    ODataValueContextOfIListOfWFieldInfo getFieldDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getFieldDefinitions&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     */
    void getFieldDefinitionsForEach(Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback, Integer maxPageSize, ParametersForGetFieldDefinitions parameters);
}
