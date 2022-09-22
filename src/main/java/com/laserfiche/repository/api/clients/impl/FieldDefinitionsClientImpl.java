package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import kong.unirest.UnirestInstance;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId,
            String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture", "$select"},
                new Object[]{culture, select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "fieldDefinitionId"},
                new Object[]{repoId, String.valueOf(fieldDefinitionId)});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}")
                .routeParam("repoId", repoId)
                .routeParam("fieldDefinitionId", String.valueOf(fieldDefinitionId))
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetFieldDefinitions(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions", repoId, prefer, culture,
                select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWFieldInfo> doGetFieldDefinitions(String url, String repoId,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{culture, select, orderby, String.valueOf(top), String.valueOf(skip), String.valueOf(
                        count)});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(url)
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink,
            int maxPageSize) {
        return doGetFieldDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null, null);
    }
}
