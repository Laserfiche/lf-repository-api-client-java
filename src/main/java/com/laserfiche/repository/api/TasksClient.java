package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.TasksApi;
import com.laserfiche.repository.api.client.model.OperationProgress;

import java.util.concurrent.CompletableFuture;

public class TasksClient extends BaseClient<TasksApi> {
    /**
     *
     * - Cancels an operation. - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary. - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     * @param repoId The requested repository ID (required)
     * @param operationToken The operation token (required)
     * @return CompletableFuture&lt;Void&gt;
     */
    public CompletableFuture<Void> cancelOperation(String repoId, String operationToken) {
        return client.cancelOperation(repoId, operationToken);
    }

    /**
     *
     * - Returns the status of an operation. - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant). - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
     * @param repoId The requested repository ID (required)
     * @param operationToken The operation token (required)
     * @return CompletableFuture&lt;OperationProgress&gt;
     */
    public CompletableFuture<OperationProgress> getOperationStatusAndProgress(String repoId, String operationToken) {
        return client.getOperationStatusAndProgress(repoId, operationToken);
    }
}
