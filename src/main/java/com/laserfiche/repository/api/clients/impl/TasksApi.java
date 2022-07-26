package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

public interface TasksApi {
  /**
   * 
   * - Returns the status of an operation. - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant). - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
   * @param repoId The requested repository ID (required)
   * @param operationToken The operation token (required)
   * @return CompletableFuture&lt;OperationProgress&gt;
   */
  @GET("v1/Repositories/{repoId}/Tasks/{operationToken}")
  CompletableFuture<OperationProgress> getOperationStatusAndProgress(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("operationToken") String operationToken
  );
}
