package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.LinkDefinitionsClient;

public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    public LinkDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, Integer linkTypeId, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "$select" }, new Object[] { select });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "linkTypeId" }, new Object[] { repoId, linkTypeId });
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(EntryLinkTypeInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkDefinitions(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions", repoId, prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> doGetLinkDefinitions(String url, String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId" }, new Object[] { repoId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfEntryLinkTypeInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return doGetLinkDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getLinkDefinitionsForEach(Function<CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> response = getLinkDefinitions(repoId, prefer, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getAtOdataNextLink();
            response = getLinkDefinitionsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
