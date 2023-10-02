package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasonCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForListAuditReasons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditReasonsClientTest extends BaseTest {

    private AuditReasonsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAuditReasonsClient();
    }

    @Test
    void listAuditReasonsWorks() {
        AuditReasonCollectionResponse reasons = client.listAuditReasons(
                new ParametersForListAuditReasons()
                        .setRepositoryId(repositoryId));
        assertNotNull(reasons);
    }
}
