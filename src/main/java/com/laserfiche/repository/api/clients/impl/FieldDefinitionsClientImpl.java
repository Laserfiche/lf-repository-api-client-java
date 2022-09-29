package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.FieldDefinitionsClient;

public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture", "$select" }, new Object[] { culture, select });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "fieldDefinitionId" }, new Object[] { repoId, fieldDefinitionId });
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(WFieldInfo.class).thenApply(httpResponse -> {
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
            if (httpResponse.getStatus() >= 299) {
                throw new RuntimeException(httpResponse.getStatusText());
            }
            return httpResponse.getBody();
        });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetFieldDefinitions(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions", repoId, prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWFieldInfo> doGetFieldDefinitions(String url, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { culture, select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId" }, new Object[] { repoId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfWFieldInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return doGetFieldDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getFieldDefinitionsForEach(Function<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> response = getFieldDefinitions(repoId, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get()._atOdataNextLink;
            response = getFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
