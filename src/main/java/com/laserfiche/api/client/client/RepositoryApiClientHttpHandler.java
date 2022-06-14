package com.laserfiche.api.client.client;
import com.laserfiche.api.client.ApiClient;
import com.laserfiche.api.client.ApiException;
import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.api.client.oauth.*;
import com.laserfiche.api.client.model.GetAccessTokenResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RepositoryApiClientHttpHandler extends ApiClient{
    private HttpRequestHandler _httpRequestHandler;
    RepositoryApiClientHttpHandler(HttpRequestHandler httpRequestHandler){
        this._httpRequestHandler = httpRequestHandler;
    }

    public CompletableFuture<Response> httpHandler(String baseUrl) throws ExecutionException, InterruptedException, IOException, ApiException {
        CompletableFuture<GetAccessTokenResponse> future = new CompletableFuture<>();
        final int maxRetries = 1;
        int retryCount = 0;
        boolean shouldRetry = true;
        Response response = null;
        Request request = null;
        OkHttpClient client = new OkHttpClient();
        //TokenApiImpl token = new TokenApiImpl(baseUrl);
        //String spKey ="";//gotta set the spkey
        //AccessKey accessKey = null;//gotta set the accessKey
        //GetAccessTokenResponse accessToken = token.getAccessToken(spKey,accessKey);
        //request.addHeader("Authorization", accessToken.getAccessToken());
        while (retryCount <= maxRetries && shouldRetry){
            final CompletableFuture<BeforeSendResult> beforeSendResult = this._httpRequestHandler.beforeSendAsync(request.newBuilder());
            beforeSendResult.get();
            //set abs url
            //else combine the urls
            String absUrl ="";
            if (baseUrl.startsWith("http")){
                absUrl = baseUrl;
            }
            request = new Request.Builder().url(absUrl).build();
            try{
                //fetch response
                //request.url(absUrl);
                request = new Request.Builder().url(absUrl).build();
                response = client.newCall(request).execute();// java equivalent of fetch since the js fetch is sending a get request
                //set should retry
                shouldRetry = (this._httpRequestHandler.afterSendAsync(response).get() || isRetryable(response));
            }catch(Exception err){
                if(retryCount >= maxRetries){
                    throw err;
                }
                shouldRetry = true;
                System.out.println("Retrying fetch due to exception: " + err);
            }
            finally{
                retryCount++;
            }
            Response finalResponse = response;
            return CompletableFuture.supplyAsync(() -> finalResponse);
        }
        return null;
    }
    private static boolean isRetryable(Response response){
        return (response.code() >= 500 || response.code() == 408);
    }

}