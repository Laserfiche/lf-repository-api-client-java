package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.*;


/**
 * An HTTP interceptor used to modify the Laserfiche Cloud API request.
 */
public class OAuthInterceptor implements RepositoryApiClientInterceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

    private HttpRequest<?> tempRequest = null;

    private UnirestInstance httpClient = null;

    private final int maxRetries = 1;

    private int retryCount = 0;

    /**
     * Creates a new OAuthInterceptor.
     *
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey           The access key exported from the Laserfiche Developer Console.
     */
    public OAuthInterceptor(String servicePrincipalKey, AccessKey accessKey) {
        oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
        Request customRequest = new RequestImpl();
        oauthHandler.beforeSend(customRequest);
        request.header("Authorization", customRequest
                .headers()
                .get("Authorization"));
        tempRequest = request;
    }

    @Override
    public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
        boolean shouldRetry = oauthHandler.afterSend(new ResponseImpl((short) response.getStatus())) || isRetryable(
                response, request);
        if (shouldRetry && retryCount < maxRetries) {
            retryCount++;
            tempRequest.getHeaders().clear();
            tempRequest.asObject(Object.class);
        }
        if (!shouldRetry || retryCount == maxRetries) {
            retryCount = 0;
        }
    }

    @Override
    public void close() {
        oauthHandler.close();
    }

    private boolean isRetryable(HttpResponse<?> response, HttpRequestSummary request) {
        boolean isIdempotent = request
                .getHttpMethod()
                .toString()
                .equals("POST");
        return (response.getStatus() >= 500 || response.getStatus() == 408) && isIdempotent;
    }
}
