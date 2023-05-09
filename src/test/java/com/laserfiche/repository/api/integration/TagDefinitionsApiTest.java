package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void getTagDefinitions_ReturnAllTags() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(new ParametersForGetTagDefinitions().setRepoId(repositoryId));

        assertNotNull(tagInfoList);
    }

    @Test
    void getTagDefinitions_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(new ParametersForGetTagDefinitions()
                        .setRepoId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWTagInfo nextLinkResponse = client.getTagDefinitionsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTagDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(new ParametersForGetTagDefinitions().setRepoId(repositoryId));

        assertNotNull(tagInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback = data -> {
            if (data.getOdataNextLink() != null) {
                assertNotEquals(0, data
                        .getValue()
                        .size());
                assertTrue(data
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getTagDefinitionsForEach(callback, maxPageSize, new ParametersForGetTagDefinitions().setRepoId(
                repositoryId));
    }

    @Test
    void getTagDefinitionById_ReturnTag() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(new ParametersForGetTagDefinitions().setRepoId(repositoryId));

        assertNotNull(tagInfoList);

        WTagInfo tagInfo = client
                .getTagDefinitionById(new ParametersForGetTagDefinitionById()
                        .setRepoId(repositoryId)
                        .setTagId(tagInfoList
                                .getValue()
                                .get(0)
                                .getId()));

        assertNotNull(tagInfo);
    }
}
