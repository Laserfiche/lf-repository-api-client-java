package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.ServerSessionClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfDateTime;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class ServerSessionClientImpl extends ApiClient implements ServerSessionClient {

    public ServerSessionClientImpl(String baseUrl) {
        super(baseUrl);
    }

    @Override()
    public CompletableFuture<ODataValueOfBoolean> invalidateServerSession(String repoId) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Invalidate")
                .routeParam("repoId", repoId)
                .asObjectAsync(ODataValueOfBoolean.class)
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

    @Override()
    public CompletableFuture<ODataValueOfBoolean> createServerSession(String repoId) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Create")
                .routeParam("repoId", repoId)
                .asObjectAsync(ODataValueOfBoolean.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override()
    public CompletableFuture<ODataValueOfDateTime> refreshServerSession(String repoId) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Refresh")
                .routeParam("repoId", repoId)
                .asObjectAsync(ODataValueOfDateTime.class)
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
