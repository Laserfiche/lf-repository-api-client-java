package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The Laserfiche Repository Repositories API client.
 */
public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    private HttpRequestHandler httpRequestHandler;

    public RepositoriesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public RepositoryInfo[] getRepositoryList() {
        Function<HttpResponse<Object>, RepositoryInfo[]> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONArray(((ArrayList) body).toArray()).toString();
                    return objectMapper.readValue(responseJson, RepositoryInfo[].class);
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v1/Repositories", "GET",
                null, null, null, null, null, null, new HashMap<String, String>(), false, parseResponse);
    }
}
