package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TasksClientImpl extends ApiClient implements TasksClient {

    public TasksClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<OperationProgress> getOperationStatusAndProgress(String repoId, String operationToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "operationToken"},
                new Object[]{repoId, operationToken});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}")
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 201 || httpResponse.getStatus() == 202) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, OperationProgress.class);
                        } catch (JsonProcessingException | IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        ProblemDetails problemDetails;
                        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = deserializeToProblemDetails(jsonString);
                        } catch (JsonProcessingException | IllegalStateException e) {
                            Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                            throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                                    (parsingException.isPresent() ? parsingException
                                            .get()
                                            .getOriginalBody() : null), headersMap, null);
                        }
                        if (httpResponse.getStatus() == 400)
                            throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 401)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Request operationToken not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<Boolean> cancelOperation(String repoId, String operationToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "operationToken"},
                new Object[]{repoId, operationToken});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}")
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 204) {
                        return true;
                    } else {
                        ProblemDetails problemDetails;
                        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = deserializeToProblemDetails(jsonString);
                        } catch (JsonProcessingException | IllegalStateException e) {
                            Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                            throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                                    (parsingException.isPresent() ? parsingException
                                            .get()
                                            .getOriginalBody() : null), headersMap, null);
                        }
                        if (httpResponse.getStatus() == 400)
                            throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 401)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Request operationToken not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }
}
