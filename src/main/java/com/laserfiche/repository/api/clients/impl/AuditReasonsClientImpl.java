package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.OAuthInterceptor;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import kong.unirest.Interceptor;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class AuditReasonsClientImpl extends ApiClient implements AuditReasonsClient {

    public AuditReasonsClientImpl(String baseUrl, Interceptor interceptor) {
        super(baseUrl, interceptor);
    }

    @Override()
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/AuditReasons")
                .routeParam("repoId", repoId)
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
                    return httpResponse.getBody();
                });
    }
}
