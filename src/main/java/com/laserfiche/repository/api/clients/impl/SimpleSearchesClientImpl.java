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
import com.laserfiche.repository.api.clients.SimpleSearchesClient;

/**
 * The Laserfiche Repository SimpleSearches API client.
 */
public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public EntryCollectionResponse searchEntry(ParametersForSearchEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String[]", "boolean", "String", "String", "String", "boolean" }, new String[] { "fields", "formatFieldValues", "culture", "$select", "$orderby", "$count" }, new Object[] { parameters.getFields(), parameters.isFormatFieldValues(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repositoryId" }, new Object[] { parameters.getRepositoryId() });
        Function<HttpResponse<Object>, EntryCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 206) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, EntryCollectionResponse.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/SimpleSearches", "POST", "application/json", parameters.getRequestBody(), "fields", (queryParameters.get("fields") != null) ? (queryParameters.get("fields") instanceof String ? Arrays.asList(queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(), queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}
