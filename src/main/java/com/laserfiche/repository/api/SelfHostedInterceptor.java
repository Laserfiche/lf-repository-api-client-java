package com.laserfiche.repository.api;

import com.laserfiche.api.client.apiserver.TokenClient;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.httphandlers.UsernamePasswordHandler;
import com.laserfiche.repository.api.clients.RepositoryApiClientInterceptor;
import kong.unirest.Config;
import kong.unirest.HttpRequest;

/**
 * An HTTP interceptor used to modify the Laserfiche Self-Hosted API request.
 */
public class SelfHostedInterceptor implements RepositoryApiClientInterceptor {
    private final UsernamePasswordHandler usernamePasswordHandler;

    /**
     * Creates a new SelfHostedInterceptor.
     * @param repositoryId Repository ID.
     * @param username     The username used with "password" grant type.
     * @param password     The password used with "password" grant type.
     * @param baseUrl      APIServer Base Url e.g. https://{APIServerName}/LFRepositoryAPI.
     * @param client       OPTIONAL
     */
    public SelfHostedInterceptor(String repositoryId, String username, String password, String baseUrl,
            TokenClient client) {
        usernamePasswordHandler = new UsernamePasswordHandler(repositoryId, username, password, baseUrl, client);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        Request customRequest = new RequestImpl();
        usernamePasswordHandler.beforeSend(customRequest);
        request.header("Authorization", customRequest
                .headers()
                .get("Authorization"));
    }

    @Override
    public void close() {
        usernamePasswordHandler.close();
    }
}
