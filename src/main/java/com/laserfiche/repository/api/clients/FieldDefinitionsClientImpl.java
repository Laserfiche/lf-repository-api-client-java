package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.EntriesApi;
import com.laserfiche.repository.api.clients.impl.EntriesApiEx;
import com.laserfiche.repository.api.clients.impl.FieldDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.FieldDefinitionsApiEx;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.CompletableFuture;

public class FieldDefinitionsClientImpl extends BaseClient<FieldDefinitionsApi, FieldDefinitionsApiEx> implements FieldDefinitionsClient {
    public FieldDefinitionsClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(FieldDefinitionsApi.class, FieldDefinitionsApiEx.class);
    }

    @Override
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId, String culture, String select) {
        return generatedClient.getFieldDefinitionById(repoId, fieldDefinitionId, culture, select);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getFieldDefinitions(repoId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getFieldDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getFieldDefinitionsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>> callback, String repoId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = getFieldDefinitions(repoId, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getFieldDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }
}
