package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.TasksApi;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;

import java.util.concurrent.CompletableFuture;

public class TasksClientImpl extends BaseClient<TasksApi, Void> implements TasksClient {
    @Override
    public CompletableFuture<Void> cancelOperation(String repoId, String operationToken) {
        return generatedClient.cancelOperation(repoId, operationToken);
    }

    @Override
    public CompletableFuture<OperationProgress> getOperationStatusAndProgress(String repoId, String operationToken) {
        return generatedClient.getOperationStatusAndProgress(repoId, operationToken);
    }
}
