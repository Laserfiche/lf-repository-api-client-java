package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import kong.unirest.HttpResponse;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

/** The Laserfiche Repository Repositories API client. */
public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    public RepositoriesClientImpl(
            String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public RepositoryInfo[] getRepositoryList() {
        Function<HttpResponse<Object>, RepositoryInfo[]> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap =
                            ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson =
                                    new JSONArray(((ArrayList) body).toArray()).toString();
                            return objectMapper.readValue(responseJson, RepositoryInfo[].class);
                        } catch (Exception e) {
                            throw ApiException.create(
                                    httpResponse.getStatus(), headersMap, null, e);
                        }
                    } else {
                        ProblemDetails problemDetails;
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails =
                                    ProblemDetailsDeserializer.deserialize(
                                            objectMapper, jsonString);
                        } catch (Exception e) {
                            throw ApiException.create(
                                    httpResponse.getStatus(), headersMap, null, e);
                        }
                        throw ApiClientUtils.createApiException(httpResponse, problemDetails);
                    }
                };
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                baseUrl + "/v1/Repositories",
                "GET",
                null,
                null,
                null,
                null,
                null,
                null,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }

    /**
     * Returns the repository resource list that current user has access to given the API server
     * base URL. Only available in Laserfiche Self-Hosted.
     *
     * @param url API server base URL e.g., https://{APIServerName}/LFRepositoryAPI
     * @return Get the repository resource list successfully.
     */
    public static RepositoryInfo[] getSelfHostedRepositoryList(String url) {
        Map<String, String> headerKeyValuePairs = new HashMap<>();
        headerKeyValuePairs.put("accept", "application/json");
        HttpResponse<Object> httpResponse = null;
        String responseJson;
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        String baseUrl = url + "/v1/Repositories";
        ObjectMapper objectMapper = new TokenClientObjectMapper();
        try (UnirestInstance httpClient = Unirest.spawnInstance()) {
            httpClient.config().setObjectMapper(objectMapper);
            httpResponse =
                    httpClient.get(baseUrl).headers(headerKeyValuePairs).asObject(Object.class);
            Object body = httpResponse.getBody();
            Map<String, String> headersMap =
                    ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    responseJson = new JSONArray(((ArrayList) body).toArray()).toString();
                    return objectMapper.readValue(responseJson, RepositoryInfo[].class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails =
                            ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        }
    }
}
