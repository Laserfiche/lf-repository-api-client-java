package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import com.laserfiche.repository.api.clients.params.ParametersForCancelOperation;
import com.laserfiche.repository.api.clients.params.ParametersForGetOperationStatusAndProgress;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The Laserfiche Repository Tasks API client.
 */
public class TasksClientImpl extends ApiClient implements TasksClient {

    public TasksClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public OperationProgress getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"repoId", "operationToken"},
                new Object[]{parameters.getRepoId(), parameters.getOperationToken()});
        Function<HttpResponse<Object>, OperationProgress> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 201 || httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, OperationProgress.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}", "GET", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public boolean cancelOperation(ParametersForCancelOperation parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"repoId", "operationToken"},
                new Object[]{parameters.getRepoId(), parameters.getOperationToken()});
        Function<HttpResponse<Object>, Boolean> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 204) {
                return true;
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}", "DELETE", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}