package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId,
            String culture, String select) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}")
                .routeParam("repoId", repoId)
                .routeParam("fieldDefinitionId", String.valueOf(fieldDefinitionId))
                .queryString(queryParameters)
                .asObjectAsync(WFieldInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Requested field definition id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        if (orderby != null) {
            queryParameters.put("orderby", orderby);
        }
        if (top != null) {
            queryParameters.put("top", top);
        }
        if (skip != null) {
            queryParameters.put("skip", skip);
        }
        if (count != null) {
            queryParameters.put("count", count);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions")
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfWFieldInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }
}
