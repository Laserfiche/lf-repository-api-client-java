package com.laserfiche.repository.api.client;

import retrofit2.http.*;
import com.laserfiche.repository.api.client.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.client.model.ODataValueOfDateTime;
import java.util.concurrent.CompletableFuture;

public interface ServerSessionApi {
  /**
   * 
   * - Deprecated. This function is a no-op, always returns 200.
   * @param repoId The requested repository ID. (required)
   * @return Call&lt;ODataValueOfBoolean&gt;
   */
  @POST("v2-alpha/Repositories/{repoId}/ServerSession/Create")
  CompletableFuture<ODataValueOfBoolean> createServerSession(
    @retrofit2.http.Path("repoId") String repoId
  );

  /**
   * 
   * - Invalidates the server session. - Acts as a \&quot;logout\&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.
   * @param repoId The requested repository ID. (required)
   * @return Call&lt;ODataValueOfBoolean&gt;
   */
  @POST("v2-alpha/Repositories/{repoId}/ServerSession/Invalidate")
  CompletableFuture<ODataValueOfBoolean> invalidateServerSession(
    @retrofit2.http.Path("repoId") String repoId
  );

  /**
   * 
   * - Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires. - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.
   * @param repoId The requested repository ID. (required)
   * @return Call&lt;ODataValueOfDateTime&gt;
   */
  @POST("v2-alpha/Repositories/{repoId}/ServerSession/Refresh")
  CompletableFuture<ODataValueOfDateTime> refreshServerSession(
    @retrofit2.http.Path("repoId") String repoId
  );

}
