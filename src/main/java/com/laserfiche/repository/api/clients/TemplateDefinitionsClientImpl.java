package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.TemplateDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.TemplateDefinitionsApiEx;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.CompletableFuture;

public class TemplateDefinitionsClientImpl extends BaseClient<TemplateDefinitionsApi, TemplateDefinitionsApiEx> implements TemplateDefinitionsClient {
    public TemplateDefinitionsClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(TemplateDefinitionsApi.class, TemplateDefinitionsApiEx.class);
    }

    @Override
    public CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, Integer templateId, String culture, String select) {
        return generatedClient.getTemplateDefinitionById(repoId, templateId, culture, select);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId, String templateName, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getTemplateDefinitions(repoId, templateName, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getTemplateDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getTemplateDefinitionsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWTemplateInfo>> callback, String repoId, String templateName, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = getTemplateDefinitions(repoId, templateName, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTemplateDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId, Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getTemplateFieldDefinitions(repoId, templateId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getTemplateFieldDefinitionsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getTemplateFieldDefinitionsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo>> callback, String repoId, Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> future = getTemplateFieldDefinitions(repoId, templateId, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(String repoId, String templateName, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getTemplateFieldDefinitionsByTemplateName(repoId, templateName, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateNameNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getTemplateFieldDefinitionsByTemplateNamePaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getTemplateFieldDefinitionsByTemplateNameForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo>> callback, String repoId, String templateName, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> future = getTemplateFieldDefinitionsByTemplateName(repoId, templateName, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTemplateFieldDefinitionsByTemplateNameNextLink(nextLink, maxPageSize);
            });
        }
    }
}
