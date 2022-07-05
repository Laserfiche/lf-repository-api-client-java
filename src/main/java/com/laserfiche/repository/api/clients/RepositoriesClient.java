package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesApi;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;

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
