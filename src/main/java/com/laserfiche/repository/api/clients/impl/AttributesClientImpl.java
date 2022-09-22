package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import kong.unirest.UnirestInstance;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AttributesClientImpl extends ApiClient implements AttributesClient {

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey,
            Boolean everyone) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"everyone"},
                new Object[]{String.valueOf(everyone)});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "attributeKey"},
                new Object[]{repoId, attributeKey});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .routeParam("repoId", repoId)
                .routeParam("attributeKey", attributeKey)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId,
            Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTrusteeAttributeKeyValuePairs(baseUrl + "/v1/Repositories/{repoId}/Attributes", repoId, everyone,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfListOfAttribute> doGetTrusteeAttributeKeyValuePairs(String url,
            String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"everyone", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{String.valueOf(everyone), select, orderby, String.valueOf(top), String.valueOf(
                        skip), String.valueOf(count)});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(url)
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsNextLink(
            String nextLink, int maxPageSize) {
        return doGetTrusteeAttributeKeyValuePairs(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null,
                null, null, null, null);
    }
}
