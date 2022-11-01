package com.laserfiche.repository.api;

import com.laserfiche.api.client.apiserver.TokenClient;
import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.Config;
import kong.unirest.HttpRequest;
import kong.unirest.Interceptor;

import java.util.concurrent.CompletableFuture;

public class SelfHostedInterceptor implements Interceptor {
    private final UsernamePasswordHandler usernamePasswordHandler;

    public SelfHostedInterceptor(String repositoryId, String username, String password, String baseUrl,
            TokenClient client) {
        usernamePasswordHandler = new UsernamePasswordHandler(repositoryId, username, password, baseUrl, client);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        Request customRequest = new RequestImpl();
        CompletableFuture<BeforeSendResult> future = usernamePasswordHandler.beforeSendAsync(customRequest);
        future.join(); // We are blocked by the HTTP handler
        request.header("Authorization", customRequest
                .headers()
                .get("Authorization"));
    }
}
