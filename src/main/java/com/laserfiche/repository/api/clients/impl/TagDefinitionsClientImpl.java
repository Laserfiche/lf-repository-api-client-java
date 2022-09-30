package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;

public class TagDefinitionsClientImpl extends ApiClient implements TagDefinitionsClient {

    public TagDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTagDefinitions(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions", repoId, prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWTagInfo> doGetTagDefinitions(String url, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { culture, select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId" }, new Object[] { repoId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfWTagInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return doGetTagDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getTagDefinitionsForEach(Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> response = getTagDefinitions(repoId, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getOdataNextLink();
            response = getTagDefinitionsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, Integer tagId, String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture", "$select" }, new Object[] { culture, select });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "tagId" }, new Object[] { repoId, tagId });
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions/{tagId}").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(WTagInfo.class).thenApply(httpResponse -> {
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
                throw new RuntimeException("Request tag definition id not found.");
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
}
