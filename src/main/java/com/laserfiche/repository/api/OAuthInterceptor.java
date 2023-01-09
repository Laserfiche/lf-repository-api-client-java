package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.Config;
import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestSummary;
import kong.unirest.HttpResponse;


/**
 * An HTTP interceptor used to modify the Laserfiche Cloud API request.
 */
public class OAuthInterceptor implements RepositoryApiClientInterceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

    private HttpRequest<?> tempRequest = null;

    private final int maxRetries = 1;

    private int retryCount = 0;

    /**
     * Creates a new OAuthInterceptor.
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey The access key exported from the Laserfiche Developer Console.
     */
    public OAuthInterceptor(String servicePrincipalKey, AccessKey accessKey) {
        oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        retryCount++;
        System.out.println(retryCount);
        Request customRequest = new RequestImpl();
        oauthHandler.beforeSend(customRequest);
        customRequest
                .headers()
                .set("Authorization", "wrong");
        request.header("Authorization", customRequest
                .headers()
                .get("Authorization"));
        tempRequest = request;
//        System.out.println(request.getHeaders());
//        System.out.println(request.getBody());
//        System.out.println(request.getHttpMethod());
//        System.out.println(request.getUrl());
    }

    @Override
    public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
//        System.out.println(response.getStatus());
//        System.out.println(response.getHeaders());
        boolean shouldRetry = oauthHandler.afterSend(new ResponseImpl((short) response.getStatus())) || isRetryable(response, request);
//        config.retryAfter(shouldRetry, 1);
        if (shouldRetry && retryCount <= maxRetries){
            //Request customRequest = new RequestImpl();
            onRequest(tempRequest, config);
            //System.out.println(retryCount);
            //retryCount++;
        }
        if(!shouldRetry || retryCount == 2){
            retryCount = 0;
        }
    }

    @Override
    public void close() {
        oauthHandler.close();
    }

    private boolean isRetryable(HttpResponse<?> response, HttpRequestSummary request) {
        boolean isIdempotent = request.getHttpMethod().toString().equals("POST");
        return (response.getStatus() >= 500 || response.getStatus() == 408) && isIdempotent;
    }
}
