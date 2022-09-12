package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfEntry> createSimpleSearchOperation(String select, String orderby,
            Boolean count, String repoId, String[] fields, Boolean formatFields, SimpleSearchRequest requestBody,
            String culture) {
        Map<String, Object> queryParameters = new HashMap<>();
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
        if (count != null) {
            queryParameters.put("count", count);
        }
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/SimpleSearches")
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(ODataValueOfIListOfEntry.class)
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
}
