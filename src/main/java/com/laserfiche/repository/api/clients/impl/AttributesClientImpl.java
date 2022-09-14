package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AttributesClientImpl extends ApiClient implements AttributesClient {

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey,
            Boolean everyone) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (everyone != null) {
            queryParameters.put("everyone", everyone);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .routeParam("repoId", repoId)
                .routeParam("attributeKey", attributeKey)
                .queryString(queryParameters)
                .asObjectAsync(Attribute.class)
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
                        throw new RuntimeException("Requested attribute key not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId,
            Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (everyone != null) {
            queryParameters.put("everyone", everyone);
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
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes")
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfListOfAttribute.class)
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
