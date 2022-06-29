package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.AuditReasonsApi;
import com.laserfiche.repository.api.client.model.AuditReasons;

import java.util.concurrent.CompletableFuture;

public class AuditReasonsClient {
    private AuditReasonsApi client;

    protected void setClient(AuditReasonsApi client) {
        this.client = client;
    }

    /**
     * Get the audit reasons associated with the authenticated user.
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     * @param repoId The requested repository ID. (required)
     * @return CompletableFuture&lt;AuditReasons&gt;
     */
    public CompletableFuture<AuditReasons> getAuditReasons(String repoId) {
        return client.getAuditReasons(repoId);
    }
}
