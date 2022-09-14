package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;

import java.util.concurrent.CompletableFuture;

public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.
     * - Only available in Laserfiche Cloud.
     *
     * @return CompletableFuture<RepositoryInfo [ ]> The return value
     */
    CompletableFuture<RepositoryInfo[]> getRepositoryList();
}
