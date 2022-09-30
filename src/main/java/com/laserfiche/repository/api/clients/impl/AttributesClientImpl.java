package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.AttributesClient;

public class AttributesClientImpl extends ApiClient implements AttributesClient {

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey, Boolean everyone) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "everyone" }, new Object[] { everyone });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "attributeKey" }, new Object[] { repoId, attributeKey });
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(Attribute.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTrusteeAttributeKeyValuePairs(baseUrl + "/v1/Repositories/{repoId}/Attributes", repoId, everyone, prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfListOfAttribute> doGetTrusteeAttributeKeyValuePairs(String url, String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "everyone", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { everyone, select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId" }, new Object[] { repoId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfListOfAttribute.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsNextLink(String nextLink, Integer maxPageSize) {
        return doGetTrusteeAttributeKeyValuePairs(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getTrusteeAttributeKeyValuePairsForEach(Function<CompletableFuture<ODataValueContextOfListOfAttribute>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfListOfAttribute> response = getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getOdataNextLink();
            response = getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
