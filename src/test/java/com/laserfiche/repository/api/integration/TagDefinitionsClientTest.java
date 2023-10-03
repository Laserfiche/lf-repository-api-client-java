package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.laserfiche.repository.api.clients.impl.model.TagDefinition;
import com.laserfiche.repository.api.clients.impl.model.TagDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListTagDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void listTagDefinitionsNextLinkWorks() throws InterruptedException {
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
    void listTagDefinitionsForEachWorks() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TagDefinitionCollectionResponse, Boolean> callback = data -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertNotEquals(0, data.getValue().size());
                assertTrue(data.getValue().size() <= maxPageSize);
                return data.getOdataNextLink() != null;
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
