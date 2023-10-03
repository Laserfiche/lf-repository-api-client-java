package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.laserfiche.repository.api.clients.impl.model.AttributeCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForListAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttributesClientTest extends BaseTest {
    private AttributesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    void listAttributesWorks() {
        AttributeCollectionResponse attributeList =
                client.listAttributes(new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setEveryone(true));

        assertNotNull(attributeList);
        assertNotNull(attributeList.getValue());
    }

    @Test
    void getAttributeWorks() {
        AttributeCollectionResponse attributeList =
                client.listAttributes(new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setEveryone(true));
        assertNotNull(attributeList);

        Attribute attribute = client.getAttribute(new ParametersForGetAttribute()
                .setRepositoryId(repositoryId)
                .setAttributeKey(attributeList.getValue().get(0).getKey())
                .setEveryone(true));
        assertNotNull(attribute);
    }

    @Test
    void listAttributesNextLinkWorks() throws InterruptedException {
        int maxPageSize = 3;
        AttributeCollectionResponse attributeList =
                client.listAttributes(new ParametersForListAttributes()
                        .setRepositoryId(repositoryId)
                        .setEveryone(true)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(attributeList);

        String nextLink = attributeList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(attributeList.getValue().size() <= maxPageSize);

        AttributeCollectionResponse nextLinkResult =
                client.listAttributesNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listAttributesForEachWorks() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 3;
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
