package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfDateTime;

public interface ServerSessionClient {

    /**
     * - Deprecated.
     * - Invalidates the server session.
     * - Acts as a &quot;logout&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.
     * - Only available in Laserfiche Cloud.
     *
     * @param repoId The requested repository ID.
     * @return ODataValueOfBoolean The return value
     */
    ODataValueOfBoolean invalidateServerSession(String repoId);

    /**
     * - Deprecated. This function is a no-op, always returns 200.
     * - Only available in Laserfiche Cloud.
     *
     * @param repoId The requested repository ID.
     * @return ODataValueOfBoolean The return value
     */
    ODataValueOfBoolean createServerSession(String repoId);

    /**
     * - Deprecated.
     * - Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires.
     * - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.
     * - Only available in Laserfiche Cloud.
     *
     * @param repoId The requested repository ID.
     * @return ODataValueOfDateTime The return value
     */
    ODataValueOfDateTime refreshServerSession(String repoId);
}
