package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesApi;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RepositoriesClientImpl extends BaseClient<RepositoriesApi, Void> implements RepositoriesClient {
    public RepositoriesClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(RepositoriesApi.class, null);
    }

    @Override
    public CompletableFuture<List<RepositoryInfo>> getRepositoryList() {
        return generatedClient.getRepositoryList();
    }
}
