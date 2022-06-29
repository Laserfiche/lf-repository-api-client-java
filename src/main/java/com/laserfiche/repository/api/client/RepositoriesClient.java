package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.RepositoryInfo;
import retrofit2.http.GET;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RepositoriesClient {
    private RepositoriesApi client;

    protected void setClient(RepositoriesApi client) {
        this.client = client;
    }

    /**
     *
     * - Get the repository resource list that current user has access to.
     * @return CompletableFuture&lt;List&lt;RepositoryInfo&gt;&gt;
     */
    public CompletableFuture<List<RepositoryInfo>> getRepositoryList() {
        return client.getRepositoryList();
    }
}
