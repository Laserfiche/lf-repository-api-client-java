package com.laserfiche.repository.api;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.AccessKey;
import kong.unirest.*;


/**
 * An HTTP interceptor used to modify the Laserfiche Cloud API request.
 */
public class OAuthInterceptor implements RepositoryApiClientInterceptor {
    private final OAuthClientCredentialsHandler oauthHandler;

    //public final OAuthClientCredentialsHandler publicOauthHandler;

//    private HttpRequest<?> tempRequest = null;
//
//    private final int maxRetries = 1;
//
//    private int retryCount = 0;

    /**
     * Creates a new OAuthInterceptor.
     *
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey           The access key exported from the Laserfiche Developer Console.
     */
    public OAuthInterceptor(String servicePrincipalKey, AccessKey accessKey) {
        oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
        //publicOauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
    }

    @Override
    public void onRequest(HttpRequest<?> request, Config config) {
//        Request customRequest = new RequestImpl();
//        BeforeSendResult beforeSendResult = oauthHandler.beforeSend(customRequest);
//        String authorizationValue = customRequest.headers().get("Authorization");
//        if (authorizationValue != null){
//            request.header("Authorization", authorizationValue);
//        }
//        String absoluteUrl;
//        if (request.getUrl().startsWith("http")) {
//            absoluteUrl = request.getUrl();
//        } else {
//            String apiBasedAddress = getRepositoryEndpoint(beforeSendResult.getRegionalDomain());
//            absoluteUrl = combineURLs(apiBasedAddress, request.getUrl());
//        }
        //customRequest.headers().set("Authorization", "wrong");
        //tempRequest = request;
    }

    @Override
    public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
//        boolean shouldRetry = oauthHandler.afterSend(new ResponseImpl((short) response.getStatus())) || isRetryable(
//                response, request);
//        if (shouldRetry && retryCount < maxRetries) {
//            retryCount++;
//            tempRequest.getHeaders().clear();
//            tempRequest.asObject(Object.class);
//        }
//        if (!shouldRetry || retryCount == maxRetries) {
//            retryCount = 0;
//        }
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

    private String getRepositoryEndpoint(String regionDomain) {
        if (regionDomain == null)
            throw new IllegalArgumentException("regionDomain is null.");
        return "https://api." + regionDomain + "/repository";
    }

    private String combineURLs(String baseURL, String relativeURL) {
   char end = baseURL.charAt(baseURL.length() - 1);
   char begin = relativeURL.charAt(0);
        String url;

        if ((end != '/' && begin == '/') || (end == '/' && begin != '/')) {
            url = baseURL + relativeURL;
        } else if (begin == '/') {
            url = baseURL + relativeURL.substring(1);
        } else {
            url = baseURL + '/' + relativeURL;
        }
        return url;
    }
}
