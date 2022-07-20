package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.AuditReasonsApi;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;

import java.util.concurrent.CompletableFuture;

public class AuditReasonsClientImpl extends BaseClient<AuditReasonsApi, Void> implements AuditReasonsClient {
    @Override
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        return generatedClient.getAuditReasons(repoId);
    }
}
