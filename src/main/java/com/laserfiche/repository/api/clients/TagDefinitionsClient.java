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

public interface TagDefinitionsClient {

    /**
     * - Returns all tag definitions in the repository.
     * - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param parameters An object of type ParametersForGetTagDefinitions which encapsulates the parameters of getTagDefinitions method.
     *  @return ODataValueContextOfIListOfWTagInfo The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagDefinitions(ParametersForGetTagDefinitions parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfWTagInfo The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagDefinitionsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getTagDefinitions&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     */
    void getTagDefinitionsForEach(Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback, Integer maxPageSize, ParametersForGetTagDefinitions parameters);

    /**
     * - Returns a single tag definition.
     * - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed.
     * - Allowed OData query options: Select
     *
     *  @param parameters An object of type ParametersForGetTagDefinitionById which encapsulates the parameters of getTagDefinitionById method.
     *  @return WTagInfo The return value
     */
    WTagInfo getTagDefinitionById(ParametersForGetTagDefinitionById parameters);
}
