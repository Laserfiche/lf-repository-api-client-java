package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/**
 * The Laserfiche Repository SimpleSearches API client.
 */
public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public ODataValueContextOfIListOfEntry createSimpleSearchOperation(
            ParametersForCreateSimpleSearchOperation parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String[]", "boolean", "String", "String", "String", "boolean"},
                new String[] {"fields", "formatFields", "culture", "$select", "$orderby", "$count"},
                new Object[] {
                    parameters.getFields(),
                    parameters.isFormatFields(),
                    parameters.getCulture(),
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"repoId"}, new Object[] {parameters.getRepoId()});
        Function<HttpResponse<Object>, ODataValueContextOfIListOfEntry> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200
                            || httpResponse.getStatus() == 204
                            || httpResponse.getStatus() == 206) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, ODataValueContextOfIListOfEntry.class);
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/SimpleSearches",
                "POST",
                "application/json",
                parameters.getRequestBody(),
                "fields",
                (queryParameters.get("fields") != null)
                        ? (queryParameters.get("fields") instanceof String
                                ? Arrays.asList(queryParameters.remove("fields"))
                                : (List) queryParameters.remove("fields"))
                        : new ArrayList(),
                queryParameters,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }
}
