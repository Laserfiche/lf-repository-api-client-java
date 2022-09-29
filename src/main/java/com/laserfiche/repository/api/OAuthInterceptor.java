package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.BeforeSendResult;
import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.Config;
import kong.unirest.HttpRequest;
import kong.unirest.Interceptor;

import java.util.concurrent.CompletableFuture;

public class OAuthInterceptor implements Interceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

    public OAuthInterceptor(String servicePrincipalKey, AccessKey accessKey) {
        oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        Request customRequest = new RequestImpl();
        CompletableFuture<BeforeSendResult> future = oauthHandler.beforeSendAsync(customRequest);
        future.join(); // We are blocked by the HTTP handler
        request.header("Authorization", customRequest.headers().get("Authorization"));
    }
}
