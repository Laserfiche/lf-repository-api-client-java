package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import com.laserfiche.repository.api.client.model.OperationProgress;
import com.laserfiche.repository.api.client.model.ProblemDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TasksApi {
  /**
   * 
   * - Cancels an operation. - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary. - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
   * @param repoId The requested repository ID (required)
   * @param operationToken The operation token (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("v2-alpha/Repositories/{repoId}/Tasks/{operationToken}")
  Call<Void> cancelOperation(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("operationToken") String operationToken
  );

  /**
   * 
   * - Returns the status of an operation. - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant). - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
   * @param repoId The requested repository ID (required)
   * @param operationToken The operation token (required)
   * @return Call&lt;OperationProgress&gt;
   */
  @GET("v2-alpha/Repositories/{repoId}/Tasks/{operationToken}")
  Call<OperationProgress> getOperationStatusAndProgress(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("operationToken") String operationToken
  );

}