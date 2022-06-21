package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.client.model.ODataValueOfDateTime;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for ServerSessionApi
 */
public class ServerSessionApiTest {

    private ServerSessionApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(ServerSessionApi.class);
    }


    /**
     * 
     *
     * - Deprecated. This function is a no-op, always returns 200.
     */
    @Test
    public void createServerSessionTest() {
        String repoId = null;
        // ODataValueOfBoolean response = api.createServerSession(repoId);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Invalidates the server session. - Acts as a \&quot;logout\&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.
     */
    @Test
    public void invalidateServerSessionTest() {
        String repoId = null;
        // ODataValueOfBoolean response = api.invalidateServerSession(repoId);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires. - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.
     */
    @Test
    public void refreshServerSessionTest() {
        String repoId = null;
        // ODataValueOfDateTime response = api.refreshServerSession(repoId);

        // TODO: test validations
    }
}
