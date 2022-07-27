package com.laserfiche.repository.api.clients.impl;

import retrofit2.Response;
import retrofit2.http.DELETE;

import java.util.concurrent.CompletableFuture;

public interface TasksApiEx {
    /**
     *
     * - Cancels an operation. - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary. - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     * @param repoId The requested repository ID (required)
     * @param operationToken The operation token (required)
     * @return CompletableFuture&lt;Response&lt;Void&gt;&gt;
     */
    @DELETE("v1/Repositories/{repoId}/Tasks/{operationToken}")
    CompletableFuture<Response<Void>> cancelOperation(
            @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("operationToken") String operationToken
    );
}
