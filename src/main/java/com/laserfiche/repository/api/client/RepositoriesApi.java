package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.RepositoryInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RepositoriesApi {
  /**
   * 
   * - Get the repository resource list that current user has access to.
   * @return Call&lt;List&lt;RepositoryInfo&gt;&gt;
   */
  @GET("v2-alpha/Repositories")
  Call<List<RepositoryInfo>> getRepositoryList();
    

}
