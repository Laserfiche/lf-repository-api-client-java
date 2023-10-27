// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasonCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForListAuditReasons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditReasonsClientTest extends BaseTest {
    private AuditReasonsClient client;
    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAuditReasonsClient();
    }

    @Test
    void listAuditReasonsWorks() {
        int maxPageSize = 2;
        AuditReasonCollectionResponse collectionResponse = client.listAuditReasons(
                new ParametersForListAuditReasons()
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize))
                        .setRepositoryId(repositoryId));
        assertNotNull(collectionResponse);
        assertNotNull(collectionResponse.getValue());
    }
}
