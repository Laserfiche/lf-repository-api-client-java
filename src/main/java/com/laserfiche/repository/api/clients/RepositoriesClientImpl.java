package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesApi;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RepositoriesClientImpl extends BaseClient<RepositoriesApi, Void> implements RepositoriesClient {
    @Override
    public CompletableFuture<List<RepositoryInfo>> getRepositoryList() {
        return generatedClient.getRepositoryList();
    }
}
