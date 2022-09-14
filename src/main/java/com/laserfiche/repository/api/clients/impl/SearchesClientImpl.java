package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SearchesClientImpl extends ApiClient implements SearchesClient {

    public SearchesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<OperationProgress> getSearchStatus(String repoId, String searchToken) {
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam("repoId", repoId)
                .routeParam("searchToken", searchToken)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> cancelOrCloseSearch(String repoId, String searchToken) {
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam("repoId", repoId)
                .routeParam("searchToken", searchToken)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHits(String repoId,
            String searchToken, Integer rowNumber, String prefer, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
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
                .get(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits")
                .routeParam("repoId", repoId)
                .routeParam("searchToken", searchToken)
                .routeParam("rowNumber", String.valueOf(rowNumber))
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<AcceptedOperation> createSearchOperation(String repoId,
            AdvancedSearchRequest requestBody) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Searches")
                .routeParam("repoId", repoId)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResults(String repoId, String searchToken,
            Boolean groupByEntryType, Boolean refresh, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (groupByEntryType != null) {
            queryParameters.put("groupByEntryType", groupByEntryType);
        }
        if (refresh != null) {
            queryParameters.put("refresh", refresh);
        }
        if (fields != null) {
            queryParameters.put("fields", fields);
        }
        if (formatFields != null) {
            queryParameters.put("formatFields", formatFields);
        }
        if (culture != null) {
            queryParameters.put("culture", culture);
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
                .get(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results")
                .routeParam("repoId", repoId)
                .routeParam("searchToken", searchToken)
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }
}
