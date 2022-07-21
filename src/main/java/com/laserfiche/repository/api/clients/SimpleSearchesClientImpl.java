package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.SimpleSearchesApi;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SimpleSearchesClientImpl extends BaseClient<SimpleSearchesApi, Void> implements SimpleSearchesClient {
    public SimpleSearchesClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(SimpleSearchesApi.class, null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfEntry> createSimpleSearchOperation(String repoId, SimpleSearchRequest body, List<String> fields, Boolean formatFields, String culture, String select, String orderby, Boolean count) {
        return generatedClient.createSimpleSearchOperation(repoId, body, fields, formatFields, culture, select, orderby, count);
    }
}
