package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.Config;
import kong.unirest.HttpRequest;

/**
 * An HTTP interceptor used to modify the Laserfiche Cloud API request.
 */
public class OAuthInterceptor implements RepositoryApiClientInterceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

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
        Request customRequest = new RequestImpl();
        oauthHandler.beforeSend(customRequest);
        request.header("Authorization", customRequest
                .headers()
                .get("Authorization"));
    }

    @Override
    public void close() {
        oauthHandler.close();
    }
}
