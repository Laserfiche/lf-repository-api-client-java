package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.LinkDefinition;
import com.laserfiche.repository.api.clients.impl.model.LinkDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListLinkDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkDefinitionsApiTest extends BaseTest {
    LinkDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getLinkDefinitionsClient();
    }

    @Test
    void getLinkDefinitions_ReturnAllLinks() {
        LinkDefinitionCollectionResponse response =
                client.listLinkDefinitions(new ParametersForListLinkDefinitions().setRepositoryId(repositoryId));

        assertNotNull(response.getValue());
        assertFalse(response.getValue().isEmpty());
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
        LinkDefinitionCollectionResponse allLinkDefinitionsResponse =
                client.listLinkDefinitions(new ParametersForListLinkDefinitions().setRepositoryId(repositoryId));

        LinkDefinition firstLinkDefinition = allLinkDefinitionsResponse.getValue().get(0);
        assertNotNull(firstLinkDefinition);

        LinkDefinition linkDefinition = client.getLinkDefinition(new ParametersForGetLinkDefinition()
                .setRepositoryId(repositoryId)
                .setLinkDefinitionId(firstLinkDefinition.getId()));

        assertNotNull(linkDefinition);
        assertEquals(firstLinkDefinition.getId(), linkDefinition.getId());
    }
}
