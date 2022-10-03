package com.laserfiche.repository.api.clients;

import java.util.concurrent.CompletableFuture;

import com.laserfiche.repository.api.clients.impl.model.*;

public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.
     * - Only available in Laserfiche Cloud.
     *
     * @return CompletableFuture<RepositoryInfo [ ]> The return value
     */
    CompletableFuture<RepositoryInfo[]> getRepositoryList();
}
