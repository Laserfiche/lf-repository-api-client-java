// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.AttributeCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForListAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AttributesClientTest extends BaseTest {
    private AttributesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    void listAttributesAndGetAttributeWork() {
        int maxPageSize = 2;
        AttributeCollectionResponse collectionResponse =
                client.listAttributes(new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize))
                        .setEveryone(true));

        assertNotNull(collectionResponse);
        assertNotNull(collectionResponse.getValue());
        assertFalse(collectionResponse.getValue().isEmpty());

        Attribute firstAttribute = collectionResponse.getValue().get(0);

        // Test getAttribute
        Attribute attribute = client.getAttribute(new ParametersForGetAttribute()
                .setRepositoryId(repositoryId)
                .setAttributeKey(firstAttribute.getKey())
                .setEveryone(true));
        assertNotNull(attribute);
        assertEquals(firstAttribute.getKey(), attribute.getKey());
        assertEquals(firstAttribute.getValue(), attribute.getValue());
    }

    @Test
    void listAttributesNextLinkWorks() {
        int maxPageSize = 2;
        AttributeCollectionResponse collectionResponse =
                client.listAttributes(new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setEveryone(true)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(collectionResponse);
        assertNotNull(collectionResponse.getValue());
        assertFalse(collectionResponse.getValue().isEmpty());

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        AttributeCollectionResponse nextLinkResult =
                client.listAttributesNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listAttributesForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<AttributeCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listAttributesForEach(
                callback,
                maxPageSize,
                new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setEveryone(true));
        assertTrue(pageCount.get() > 1);
    }
}
