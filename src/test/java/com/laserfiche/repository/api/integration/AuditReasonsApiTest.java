package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasonCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForListAuditReasons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditReasonsApiTest extends BaseTest {
    @Test
    void getAuditReasons_ReturnAuditReasons() {
        AuditReasonsClient client = repositoryApiClient.getAuditReasonsClient();
        AuditReasonCollectionResponse reasons = client.listAuditReasons(
                new ParametersForListAuditReasons()
                        .setRepositoryId(repositoryId));
        assertNotNull(reasons);
    }
}
