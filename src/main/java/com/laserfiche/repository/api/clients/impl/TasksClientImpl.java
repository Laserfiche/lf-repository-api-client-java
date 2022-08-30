package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class TasksClientImpl extends ApiClient implements TasksClient {

    public TasksClientImpl(String baseUrl) {
        super(baseUrl);
    }

    @Override()
    public CompletableFuture<OperationProgress> getOperationStatusAndProgress(String repoId, String operationToken) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}")
                .routeParam("repoId", repoId)
                .routeParam("operationToken", operationToken)
                .asObjectAsync(OperationProgress.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request operationToken not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override()
    public CompletableFuture<Void> cancelOperation(String repoId, String operationToken) {
        return Unirest
                .delete(baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}")
                .routeParam("repoId", repoId)
                .routeParam("operationToken", operationToken)
                .asObjectAsync(Void.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request operationToken not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }
}
