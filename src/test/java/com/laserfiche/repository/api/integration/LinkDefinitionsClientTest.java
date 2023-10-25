// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.LinkDefinition;
import com.laserfiche.repository.api.clients.impl.model.LinkDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListLinkDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkDefinitionsClientTest extends BaseTest {
    private LinkDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getLinkDefinitionsClient();
    }

    @Test
    void listLinkDefinitionsAndGetLinkDefinitionWork() {
        LinkDefinitionCollectionResponse collectionResponse =
                client.listLinkDefinitions(new ParametersForListLinkDefinitions().setRepositoryId(repositoryId));

        assertNotNull(collectionResponse.getValue());
        assertFalse(collectionResponse.getValue().isEmpty());

        LinkDefinition firstLinkDefinition = collectionResponse.getValue().get(0);

        // Verify that getLinkDefinition works
        LinkDefinition linkDefinition = client.getLinkDefinition(new ParametersForGetLinkDefinition()
                .setRepositoryId(repositoryId)
                .setLinkDefinitionId(firstLinkDefinition.getId()));

        assertNotNull(linkDefinition);
        assertEquals(firstLinkDefinition.getId(), linkDefinition.getId());
        assertEquals(firstLinkDefinition.getSourceLabel(), linkDefinition.getSourceLabel());
        assertEquals(firstLinkDefinition.getTargetLabel(), linkDefinition.getTargetLabel());
    }
}
