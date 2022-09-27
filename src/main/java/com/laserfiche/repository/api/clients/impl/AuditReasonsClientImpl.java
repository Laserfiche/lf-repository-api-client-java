package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import kong.unirest.UnirestInstance;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AuditReasonsClientImpl extends ApiClient implements AuditReasonsClient {

    public AuditReasonsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/AuditReasons")
                .routeParam(pathParameters)
                .asObjectAsync(AuditReasons.class)
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
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }
}
