package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.TagDefinition;
import com.laserfiche.repository.api.clients.impl.model.TagDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListTagDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TagDefinitionsClientTest extends BaseTest {
    private TagDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void GetTagDefinitionAndListTagDefinitionsNextLinkWork() {
        int maxPageSize = 2;
        TagDefinitionCollectionResponse collectionResponse = client.listTagDefinitions(new ParametersForListTagDefinitions()
                .setRepositoryId(repositoryId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(collectionResponse);
        assertFalse(collectionResponse.getValue().isEmpty());

        TagDefinition firstTagDefinition = collectionResponse.getValue().get(0);

        TagDefinition tagDefinition = client.getTagDefinition(new ParametersForGetTagDefinition()
                .setRepositoryId(repositoryId)
                .setTagId(firstTagDefinition.getId()));

        assertNotNull(tagDefinition);
        assertEquals(firstTagDefinition.getId(), tagDefinition.getId());
        assertEquals(firstTagDefinition.getName(), tagDefinition.getName());

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        TagDefinitionCollectionResponse nextLinkCollectionResponse = client.listTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTagDefinitionsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<TagDefinitionCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listTagDefinitionsForEach(
                callback, maxPageSize, new ParametersForListTagDefinitions().setRepositoryId(repositoryId));
        assertTrue(pageCount.get() > 1);
    }
}
