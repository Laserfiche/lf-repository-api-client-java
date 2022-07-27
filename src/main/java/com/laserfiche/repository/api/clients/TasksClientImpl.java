package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.TasksApi;
import com.laserfiche.repository.api.clients.impl.TasksApiEx;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;

import java.util.concurrent.CompletableFuture;

public class TasksClientImpl extends BaseClient<TasksApi, TasksApiEx> implements TasksClient {
    public TasksClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(TasksApi.class, TasksApiEx.class);
    }

    @Override
    public CompletableFuture<Boolean> cancelOperation(String repoId, String operationToken) {
        return extensionClient.cancelOperation(repoId, operationToken).thenApply((response) -> response.code() == 204);
    }

    @Override
    public CompletableFuture<OperationProgress> getOperationStatusAndProgress(String repoId, String operationToken) {
        return generatedClient.getOperationStatusAndProgress(repoId, operationToken);
    }
}
