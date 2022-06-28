package com.laserfiche.repository.api.client;

import retrofit2.http.*;
import com.laserfiche.repository.api.client.model.AuditReasons;
import java.util.concurrent.CompletableFuture;

public interface AuditReasonsApi {
  /**
   * Get the audit reasons associated with the authenticated user.
   * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
   * @param repoId The requested repository ID. (required)
   * @return Call&lt;AuditReasons&gt;
   */
  @GET("v2-alpha/Repositories/{repoId}/AuditReasons")
  CompletableFuture<AuditReasons> getAuditReasons(
    @retrofit2.http.Path("repoId") String repoId
  );

}
