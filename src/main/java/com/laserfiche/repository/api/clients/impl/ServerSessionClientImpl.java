package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.ServerSessionClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfDateTime;
import com.laserfiche.repository.api.clients.params.ParametersForCreateServerSession;
import com.laserfiche.repository.api.clients.params.ParametersForInvalidateServerSession;
import com.laserfiche.repository.api.clients.params.ParametersForRefreshServerSession;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/** The Laserfiche Repository ServerSession API client. */
public class ServerSessionClientImpl extends ApiClient implements ServerSessionClient {

    public ServerSessionClientImpl(
            String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public ODataValueOfBoolean invalidateServerSession(
            ParametersForInvalidateServerSession parameters) {
        Map<String, Object> pathParameters =
                ApiClientUtils.getParametersWithNonDefaultValue(
                        new String[] {"String"},
                        new String[] {"repoId"},
                        new Object[] {parameters.getRepoId()});
        Function<HttpResponse<Object>, ODataValueOfBoolean> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap =
                            ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, ODataValueOfBoolean.class);
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
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Invalidate",
                "POST",
                null,
                null,
                null,
                null,
                null,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }

    @Override
    public ODataValueOfBoolean createServerSession(ParametersForCreateServerSession parameters) {
        Map<String, Object> pathParameters =
                ApiClientUtils.getParametersWithNonDefaultValue(
                        new String[] {"String"},
                        new String[] {"repoId"},
                        new Object[] {parameters.getRepoId()});
        Function<HttpResponse<Object>, ODataValueOfBoolean> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap =
                            ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, ODataValueOfBoolean.class);
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
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Create",
                "POST",
                null,
                null,
                null,
                null,
                null,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }

    @Override
    public ODataValueOfDateTime refreshServerSession(ParametersForRefreshServerSession parameters) {
        Map<String, Object> pathParameters =
                ApiClientUtils.getParametersWithNonDefaultValue(
                        new String[] {"String"},
                        new String[] {"repoId"},
                        new Object[] {parameters.getRepoId()});
        Function<HttpResponse<Object>, ODataValueOfDateTime> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap =
                            ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, ODataValueOfDateTime.class);
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
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Refresh",
                "POST",
                null,
                null,
                null,
                null,
                null,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }
}
