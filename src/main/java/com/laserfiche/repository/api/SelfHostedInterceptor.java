package com.laserfiche.repository.api;

import com.laserfiche.api.client.apiserver.TokenClient;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.httphandlers.UsernamePasswordHandler;
import kong.unirest.Config;
import kong.unirest.HttpRequest;

public class SelfHostedInterceptor implements RepositoryApiClientInterceptor {
    private final UsernamePasswordHandler usernamePasswordHandler;

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
