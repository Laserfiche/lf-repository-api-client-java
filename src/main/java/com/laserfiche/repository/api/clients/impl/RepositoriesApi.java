package com.laserfiche.repository.api.clients.impl;

import retrofit2.http.*;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RepositoriesApi {
  /**
   * 
   * - Get the repository resource list that current user has access to.
   * @return CompletableFuture&lt;List&lt;RepositoryInfo&gt;&gt;
   */
  @GET("v2-alpha/Repositories")
  CompletableFuture<List<RepositoryInfo>> getRepositoryList();
}
