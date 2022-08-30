package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;

import java.util.concurrent.CompletableFuture;

public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    public RepositoriesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override()
    public CompletableFuture<RepositoryInfo[]> getRepositoryList() {
        return httpClient
                .get(baseUrl + "/v1/Repositories")
                .asObjectAsync(RepositoryInfo[].class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }
}
