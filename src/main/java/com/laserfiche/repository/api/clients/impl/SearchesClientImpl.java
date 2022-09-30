package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import kong.unirest.*;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.SearchesClient;

public class SearchesClientImpl extends ApiClient implements SearchesClient {

    public SearchesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<OperationProgress> getSearchStatus(String repoId, String searchToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam(pathParameters)
                .asObjectAsync(OperationProgress.class)
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
                        throw new RuntimeException("Request search token not found.");
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
    public CompletableFuture<ODataValueOfBoolean> cancelOrCloseSearch(String repoId, String searchToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam(pathParameters)
                .asObjectAsync(ODataValueOfBoolean.class)
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
                        throw new RuntimeException("Request search token not found.");
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
    public CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHits(String repoId,
            String searchToken, Integer rowNumber, String prefer, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return doGetSearchContextHits(
                baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits", repoId,
                searchToken, rowNumber, prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfContextHit> doGetSearchContextHits(String url, String repoId,
            String searchToken, Integer rowNumber, String prefer, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken", "rowNumber"},
                new Object[]{repoId, searchToken, rowNumber});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObjectAsync(ODataValueContextOfIListOfContextHit.class)
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
                        throw new RuntimeException("Request search token not found.");
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
    public CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHitsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetSearchContextHits(nextLink, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<Void> getSearchContextHitsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfContextHit>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String searchToken, Integer rowNumber, String prefer, String select,
            String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfContextHit> response = getSearchContextHits(repoId, searchToken,
                rowNumber, prefer, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .get()) {
            String nextLink = response
                    .get()
                    .getOdataNextLink();
            response = getSearchContextHitsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<AcceptedOperation> createSearchOperation(String repoId,
            AdvancedSearchRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Searches")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(AcceptedOperation.class)
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
                        throw new RuntimeException("Operation limit or request limit reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResults(String repoId, String searchToken,
            Boolean groupByEntryType, Boolean refresh, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetSearchResults(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results", repoId,
                searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, select, orderby, top,
                skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntry> doGetSearchResults(String url, String repoId,
            String searchToken, Boolean groupByEntryType, Boolean refresh, String[] fields, Boolean formatFields,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"groupByEntryType", "refresh", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{groupByEntryType, refresh, fields, formatFields, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObjectAsync(ODataValueContextOfIListOfEntry.class)
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
                        throw new RuntimeException("Request search token not found.");
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
    public CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResultsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetSearchResults(nextLink, null, null, null, null, null, null,
                mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getSearchResultsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String searchToken, Boolean groupByEntryType, Boolean refresh,
            String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfEntry> response = getSearchResults(repoId, searchToken,
                groupByEntryType, refresh, fields, formatFields, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .get()) {
            String nextLink = response
                    .get()
                    .getOdataNextLink();
            response = getSearchResultsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
