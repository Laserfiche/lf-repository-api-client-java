package com.laserfiche.repository.api;

import com.laserfiche.api.client.apiserver.TokenClient;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.laserfiche.api.client.httphandlers.UsernamePasswordHandler;
import kong.unirest.Config;
import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestSummary;
import kong.unirest.HttpResponse;

/**
 * An HTTP interceptor used to modify the Laserfiche Self-Hosted API request.
 */
public class SelfHostedInterceptor implements RepositoryApiClientInterceptor {
    private final UsernamePasswordHandler usernamePasswordHandler;

    private HttpRequest<?> tempRequest = null;

    private final int maxRetries = 1;

    private int retryCount = 0;

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
        retryCount++;
        System.out.println(retryCount);
        Request customRequest = new RequestImpl();
        usernamePasswordHandler.beforeSend(customRequest);
//        customRequest
//                .headers()
//                .set("Authorization", "wrong");
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
        boolean shouldRetry = usernamePasswordHandler.afterSend(new ResponseImpl((short) response.getStatus())) || isRetryable(response, request);
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
        usernamePasswordHandler.close();
    }

    private boolean isRetryable(HttpResponse<?> response, HttpRequestSummary request) {
        boolean isIdempotent = request.getHttpMethod().toString().equals("POST");
        return (response.getStatus() >= 500 || response.getStatus() == 408) && isIdempotent;
    }
}
