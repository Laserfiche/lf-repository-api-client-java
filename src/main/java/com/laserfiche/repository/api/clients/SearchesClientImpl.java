package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.RepositoriesApi;
import com.laserfiche.repository.api.clients.impl.SearchesApi;
import com.laserfiche.repository.api.clients.impl.SearchesApiEx;
import com.laserfiche.repository.api.clients.impl.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SearchesClientImpl extends BaseClient<SearchesApi, SearchesApiEx> implements SearchesClient {
    public SearchesClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(SearchesApi.class, SearchesApiEx.class);
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> cancelOrCloseSearch(String repoId, String searchToken) {
        return generatedClient.cancelOrCloseSearch(repoId, searchToken);
    }

    @Override
    public CompletableFuture<AcceptedOperation> createSearchOperation(String repoId, AdvancedSearchRequest body) {
        return generatedClient.createSearchOperation(repoId, body);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHits(String repoId, String searchToken, Integer rowNumber, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getSearchContextHits(repoId, searchToken, rowNumber, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHitsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getSearchContextHitsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getSearchContextHitsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfContextHit>> callback, String repoId, String searchToken, Integer rowNumber, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfContextHit> future = getSearchContextHits(repoId, searchToken, rowNumber, prefer, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getSearchContextHitsNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResults(String repoId, String searchToken, Boolean groupByEntryType, Boolean refresh, List<String> fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getSearchResults(repoId, searchToken, groupByEntryType, refresh, fields, formatFields, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResultsNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getSearchResultsPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getSearchResultsForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfEntry>> callback, String repoId, String searchToken, Boolean groupByEntryType, Boolean refresh, List<String> fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfEntry> future = getSearchResults(repoId, searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getSearchResultsNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<OperationProgress> getSearchStatus(String repoId, String searchToken) {
        return generatedClient.getSearchStatus(repoId, searchToken);
    }
}
