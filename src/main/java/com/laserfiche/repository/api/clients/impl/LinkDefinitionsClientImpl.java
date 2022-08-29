package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    @Override
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, int linkTypeId, String select) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}")
                .routeParam("repoId", repoId)
                .routeParam("linkTypeId", String.valueOf(linkTypeId))
                .queryString("select", select)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId,
            String prefer, String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions")
                .routeParam("repoId", repoId)
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
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
                    return httpResponse.getBody();
                });
    }
}
