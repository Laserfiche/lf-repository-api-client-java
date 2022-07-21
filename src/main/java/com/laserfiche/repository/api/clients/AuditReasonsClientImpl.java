package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.AuditReasonsApi;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.CompletableFuture;

public class AuditReasonsClientImpl extends BaseClient<AuditReasonsApi, Void> implements AuditReasonsClient {
    public AuditReasonsClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(AuditReasonsApi.class, null);
    }

    @Override
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        return generatedClient.getAuditReasons(repoId);
    }
}
