package com.laserfiche.repository.api.clients.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
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
import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;
import com.laserfiche.repository.api.clients.SimpleSearchesClient;

/**
 * The Laserfiche Repository SimpleSearches API client.
 */
public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    private HttpRequestHandler httpRequestHandler;

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public ODataValueContextOfIListOfEntry createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String[]", "boolean", "String", "String", "String", "boolean" }, new String[] { "fields", "formatFields", "culture", "$select", "$orderby", "$count" }, new Object[] { parameters.getFields(), parameters.isFormatFields(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueContextOfIListOfEntry.class, httpRequestHandler, baseUrl + "/v1/Repositories/{repoId}/SimpleSearches", "POST", "application/json", parameters.getRequestBody(), "fields", (queryParameters.get("fields") != null) ? (queryParameters.get("fields") instanceof String ? Arrays.asList(queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(), queryParameters, pathParameters, new HashMap<String, String>(), false);
    }
}
