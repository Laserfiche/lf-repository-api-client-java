package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.OAuthInterceptor;
import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import kong.unirest.Interceptor;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    public RepositoriesClientImpl(String baseUrl, Interceptor interceptor) {
        super(baseUrl, interceptor);
    }

    @Override()
    public CompletableFuture<RepositoryInfo[]> getRepositoryList() {
        return Unirest
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
