package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.EntryCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForSearchEntry;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.*;
import java.util.function.Function;

/**
 * The Laserfiche Repository SimpleSearches API client.
 */
public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public EntryCollectionResponse searchEntry(ParametersForSearchEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String[]", "boolean", "String", "String", "String", "boolean"}, new String[]{"fields", "formatFieldValues", "culture", "$select", "$orderby", "$count"}, new Object[]{parameters.getFields(), parameters.isFormatFieldValues(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"}, new String[]{"repositoryId"}, new Object[]{parameters.getRepositoryId()});
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/SimpleSearches", "POST", "application/json", parameters.getRequestBody(), "fields", (queryParameters.get("fields") != null) ? (queryParameters.get("fields") instanceof String ? Collections.singletonList(queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(), queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}
