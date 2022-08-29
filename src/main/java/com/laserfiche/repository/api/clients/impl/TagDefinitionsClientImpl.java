package com.laserfiche.repository.api.clients.impl;

import java.util.*;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

import com.laserfiche.repository.api.clients.impl.model.*;

public class TagDefinitionsClientImpl extends ApiClient implements TagDefinitionsClient {

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions")
                .routeParam("repoId", repoId)
                .queryString("culture", culture)
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfWTagInfo.class)
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

    @Override
    public CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, int tagId, String culture, String select) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions/{tagId}")
                .routeParam("repoId", repoId)
                .routeParam("tagId", String.valueOf(tagId))
                .queryString("culture", culture)
                .queryString("select", select)
                .asObjectAsync(WTagInfo.class)
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
                        throw new RuntimeException("Request tag definition id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }
}
