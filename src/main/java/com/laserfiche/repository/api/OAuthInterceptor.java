package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.BeforeSendResult;
import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.model.AccessKey;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class OAuthInterceptor implements Interceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

    public OAuthInterceptor(String servicePrincipalKey, AccessKey accessKey) {
        oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request originalRequest = chain.request();
        Request customRequest = new RequestImpl();
        CompletableFuture<BeforeSendResult> future = oauthHandler.beforeSendAsync(customRequest);
        future.join(); // We are blocked by the HTTP handler
        okhttp3.Request newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", customRequest.headers().get("Authorization"))
                .build();
        return chain.proceed(newRequest);
    }
}
