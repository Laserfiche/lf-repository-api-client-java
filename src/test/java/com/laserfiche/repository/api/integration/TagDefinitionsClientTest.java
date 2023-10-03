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
    void listTagDefinitionsWorks() {
        TagDefinitionCollectionResponse tagInfoList =
                client.listTagDefinitions(new ParametersForListTagDefinitions().setRepositoryId(repositoryId));

        assertNotNull(tagInfoList);
        assertFalse(tagInfoList.getValue().isEmpty());
    }

    @Test
    void listTagDefinitionsNextLinkWorks() {
        int maxPageSize = 1;
        TagDefinitionCollectionResponse tagInfoList = client.listTagDefinitions(new ParametersForListTagDefinitions()
                .setRepositoryId(repositoryId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList.getValue().size() <= maxPageSize);

        TagDefinitionCollectionResponse nextLinkResponse = client.listTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTagDefinitionsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
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

    @Test
    void getTagDefinitionWorks() {
        TagDefinitionCollectionResponse tagInfoList =
                client.listTagDefinitions(new ParametersForListTagDefinitions().setRepositoryId(repositoryId));

        assertNotNull(tagInfoList);

        TagDefinition tagInfo = client.getTagDefinition(new ParametersForGetTagDefinition()
                .setRepositoryId(repositoryId)
                .setTagId(tagInfoList.getValue().get(0).getId()));

        assertNotNull(tagInfo);
    }
}
