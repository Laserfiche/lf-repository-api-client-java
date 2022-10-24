package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AuditReasonsClientImpl extends ApiClient implements AuditReasonsClient {

    public AuditReasonsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/AuditReasons")
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 200) {
                        try {
                            Object body = httpResponse.getBody();
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, AuditReasons.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Object body = httpResponse.getBody();
                            String jsonString = new JSONObject(body).toString();
                            ProblemDetails problemDetails = objectMapper.readValue(jsonString, ProblemDetails.class);
                            Map<String, String> headersMap = getHeadersMap(httpResponse);
                            if (httpResponse.getStatus() == 400)
                                throw new ApiException("Invalid or bad request.", httpResponse.getStatus(),
                                        httpResponse.getStatusText(), headersMap, problemDetails);
                            else if (httpResponse.getStatus() == 401)
                                throw new ApiException("Access token is invalid or expired.", httpResponse.getStatus(),
                                        httpResponse.getStatusText(), headersMap, problemDetails);
                            else if (httpResponse.getStatus() == 403)
                                throw new ApiException("Access denied for the operation.", httpResponse.getStatus(),
                                        httpResponse.getStatusText(), headersMap, problemDetails);
                            else if (httpResponse.getStatus() == 404)
                                throw new ApiException("Not found.", httpResponse.getStatus(),
                                        httpResponse.getStatusText(), headersMap, problemDetails);
                            else if (httpResponse.getStatus() == 429)
                                throw new ApiException("Rate limit is reached.", httpResponse.getStatus(),
                                        httpResponse.getStatusText(), headersMap, problemDetails);
                            else
                                throw new RuntimeException(httpResponse.getStatusText());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                });
    }
}
