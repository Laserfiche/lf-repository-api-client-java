package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

/** The Laserfiche Repository ServerSession API client. */
public interface ServerSessionClient {

    /**
     * - Deprecated.<br>
     * - Invalidates the server session.<br>
     * - Acts as a &quot;logout&quot; operation, and invalidates the session associated with the
     * provided access token. This method should be used when the client wants to clean up the
     * current session.<br>
     * - Only available in Laserfiche Cloud.
     *
     * @param parameters An object of type {@link ParametersForInvalidateServerSession} which
     *     encapsulates the parameters of {@link #invalidateServerSession invalidateServerSession}
     *     method.
     * @return {@link ODataValueOfBoolean} The return value
     */
    ODataValueOfBoolean invalidateServerSession(ParametersForInvalidateServerSession parameters);

    /**
     * - Deprecated. This function is a no-op, always returns 200.<br>
     * - Only available in Laserfiche Cloud.
     *
     * @param parameters An object of type {@link ParametersForCreateServerSession} which
     *     encapsulates the parameters of {@link #createServerSession createServerSession} method.
     * @return {@link ODataValueOfBoolean} The return value
     */
    ODataValueOfBoolean createServerSession(ParametersForCreateServerSession parameters);

    /**
     * - Deprecated.<br>
     * - Refreshes the session associated with the access token. This is only necessary if you want
     * to keep the same session alive, otherwise a new session will be automatically created when
     * the session expires.<br>
     * - When a client application wants to keep a session alive that has been idle for an hour,
     * this route can be used to refresh the expiration timer associated with the access token.<br>
     * - Only available in Laserfiche Cloud.
     *
     * @param parameters An object of type {@link ParametersForRefreshServerSession} which
     *     encapsulates the parameters of {@link #refreshServerSession refreshServerSession} method.
     * @return {@link ODataValueOfDateTime} The return value
     */
    ODataValueOfDateTime refreshServerSession(ParametersForRefreshServerSession parameters);
}
