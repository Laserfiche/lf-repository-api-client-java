package com.laserfiche.repository.api.clients.impl;

import java.util.*;

import com.laserfiche.repository.api.clients.AttributesClient;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

import com.laserfiche.repository.api.clients.impl.model.*;

public class AttributesClientImpl extends ApiClient implements AttributesClient {

    @Override
    public CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey,
            boolean everyone) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .routeParam("repoId", repoId)
                .routeParam("attributeKey", attributeKey)
                .queryString("everyone", everyone)
                .asObjectAsync(Attribute.class)
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
                        throw new RuntimeException("Requested attribute key not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId,
            boolean everyone, String prefer, String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes")
                .routeParam("repoId", repoId)
                .queryString("everyone", everyone)
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfListOfAttribute.class)
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
                    return httpResponse.getBody();
                });
    }
}