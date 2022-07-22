package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RepositoriesClient {
    /**
     *
     * - Get the repository resource list that current user has access to.
     * @return CompletableFuture&lt;List&lt;RepositoryInfo&gt;&gt;
     */
    CompletableFuture<List<RepositoryInfo>> getRepositoryList();
}
