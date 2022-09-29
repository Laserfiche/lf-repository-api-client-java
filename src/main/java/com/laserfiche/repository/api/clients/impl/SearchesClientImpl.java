package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.UnirestInstance;

import java.util.Map;
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
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
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
            int maxPageSize) {
        return doGetSearchContextHits(nextLink, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
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
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
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
            int maxPageSize) {
        return doGetSearchResults(nextLink, null, null, null, null, null, null,
                mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }
}
