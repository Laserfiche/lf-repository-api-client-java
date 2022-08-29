package com.laserfiche.repository.api.clients.impl;

import java.util.*;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.concurrent.CompletableFuture;
import com.laserfiche.repository.api.clients.impl.model.*;

public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    @Override
    public CompletableFuture<RepositoryInfo[]> getRepositoryList() {
        return Unirest.get(baseUrl + "/v1/Repositories").asObjectAsync(RepositoryInfo[].class).thenApply(httpResponse -> {
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
