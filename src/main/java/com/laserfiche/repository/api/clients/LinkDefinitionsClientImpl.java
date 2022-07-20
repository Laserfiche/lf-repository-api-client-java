package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.LinkDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.LinkDefinitionsApiEx;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;

import java.util.concurrent.CompletableFuture;

public class LinkDefinitionsClientImpl extends BaseClient<LinkDefinitionsApi, LinkDefinitionsApiEx> implements LinkDefinitionsClient {
    @Override
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, Integer linkTypeId, String select) {
        return generatedClient.getLinkDefinitionById(repoId, linkTypeId, select);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getLinkDefinitions(repoId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getLinkDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getLinkDefinitionsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo>> callback, String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> future = getLinkDefinitions(repoId, prefer, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getLinkDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
