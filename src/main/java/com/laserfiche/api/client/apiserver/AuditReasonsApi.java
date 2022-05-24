/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>51c16645afa5983c3eb4a849158d6f1e355d2bb0_.20220512.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.api.client.apiserver;

import com.laserfiche.api.client.ApiCallback;
import com.laserfiche.api.client.ApiClient;
import com.laserfiche.api.client.ApiException;
import com.laserfiche.api.client.ApiResponse;
import com.laserfiche.api.client.Configuration;
import com.laserfiche.api.client.Pair;
import com.laserfiche.api.client.ProgressRequestBody;
import com.laserfiche.api.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.laserfiche.api.client.model.AuditReasons;
import com.laserfiche.api.client.model.ProblemDetails;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditReasonsApi {
    private ApiClient apiClient;

    public AuditReasonsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuditReasonsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getAuditReasons
     * @param repoId The requested repository ID. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAuditReasonsCall(String repoId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v1/Repositories/{repoId}/AuditReasons"
            .replaceAll("\\{" + "repoId" + "\\}", apiClient.escapeString(repoId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "Authorization", "OAuth2 Authorization Code Flow", "OAuth2 Client Credentials Flow" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAuditReasonsValidateBeforeCall(String repoId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'repoId' is set
        if (repoId == null) {
            throw new ApiException("Missing the required parameter 'repoId' when calling getAuditReasons(Async)");
        }
        
        com.squareup.okhttp.Call call = getAuditReasonsCall(repoId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get the audit reasons associated with the authenticated user.
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     * @param repoId The requested repository ID. (required)
     * @return AuditReasons
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public AuditReasons getAuditReasons(String repoId) throws ApiException {
        ApiResponse<AuditReasons> resp = getAuditReasonsWithHttpInfo(repoId);
        return resp.getData();
    }

    /**
     * Get the audit reasons associated with the authenticated user.
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     * @param repoId The requested repository ID. (required)
     * @return ApiResponse&lt;AuditReasons&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<AuditReasons> getAuditReasonsWithHttpInfo(String repoId) throws ApiException {
        com.squareup.okhttp.Call call = getAuditReasonsValidateBeforeCall(repoId, null, null);
        Type localVarReturnType = new TypeToken<AuditReasons>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get the audit reasons associated with the authenticated user. (asynchronously)
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     * @param repoId The requested repository ID. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAuditReasonsAsync(String repoId, final ApiCallback<AuditReasons> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getAuditReasonsValidateBeforeCall(repoId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<AuditReasons>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
