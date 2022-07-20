package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.TagDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.TagDefinitionsApiEx;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;

import java.util.concurrent.CompletableFuture;

public class TagDefinitionsClientImpl extends BaseClient<TagDefinitionsApi, TagDefinitionsApiEx> implements TagDefinitionsClient {
    @Override
    public CompletableFuture<WTagInfo> getTagDefinitionById(String repoId, Integer tagId, String culture, String select) {
        return generatedClient.getTagDefinitionById(repoId, tagId, culture, select);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getTagDefinitions(repoId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getTagDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getTrusteeAttributeKeyValuePairsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWTagInfo>> callback, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = getTagDefinitions(repoId, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTagDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
