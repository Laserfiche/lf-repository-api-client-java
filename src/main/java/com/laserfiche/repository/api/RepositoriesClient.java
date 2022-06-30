package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.RepositoriesApi;
import com.laserfiche.repository.api.client.model.RepositoryInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RepositoriesClient extends BaseClient<RepositoriesApi> {
    /**
     *
     * - Get the repository resource list that current user has access to.
     * @return CompletableFuture&lt;List&lt;RepositoryInfo&gt;&gt;
     */
    public CompletableFuture<List<RepositoryInfo>> getRepositoryList() {
        return client.getRepositoryList();
    }
}
