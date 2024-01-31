// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.FieldDefinition;
import com.laserfiche.repository.api.clients.impl.model.FieldDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListFieldDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FieldDefinitionsClientTest extends BaseTest {
    private FieldDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionWorks() {
        FieldDefinition definition = client.getFieldDefinition(new ParametersForGetFieldDefinition()
                .setRepositoryId(repositoryId)
                .setFieldId(1));

        assertNotNull(definition);
        assertEquals(1, definition.getId());
    }

    @Test
    void listFieldDefinitionsNextLinkWorks() {
        int maxPageSize = 2;
        FieldDefinitionCollectionResponse collectionResponse =
                client.listFieldDefinitions(new ParametersForListFieldDefinitions()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(collectionResponse);
        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        FieldDefinitionCollectionResponse nextLinkCollectionResponse = client.listFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listFieldDefinitionsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<FieldDefinitionCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listFieldDefinitionsForEach(
                callback, maxPageSize, new ParametersForListFieldDefinitions().setRepositoryId(repositoryId));
        assertTrue(pageCount.get() > 1);
    }
}