package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import kong.unirest.UnirestInstance;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    public LinkDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, Integer linkTypeId,
            String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"$select"}, new Object[]{select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "linkTypeId"},
                new Object[]{repoId, String.valueOf(linkTypeId)});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}")
                .routeParam("repoId", repoId)
                .routeParam("linkTypeId", String.valueOf(linkTypeId))
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(EntryLinkTypeInfo.class)
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
                        throw new RuntimeException("Requested link type definition ID not found");
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId,
            String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkDefinitions(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions", repoId, prefer, select,
                orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> doGetLinkDefinitions(String url,
            String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, String.valueOf(top), String.valueOf(skip), String.valueOf(count)});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(url)
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfEntryLinkTypeInfo.class)
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitionsNextLink(String nextLink,
            int maxPageSize) {
        return doGetLinkDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null);
    }
}
