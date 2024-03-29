// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.TemplateDefinition;
import com.laserfiche.repository.api.clients.impl.model.TemplateDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldDefinition;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateId;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDefinitionsClientTest extends BaseTest {
    private TemplateDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void listTemplateDefinitionsAndGetTemplateDefinitionWork() {
        TemplateDefinitionCollectionResponse collectionResponse =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        assertNotNull(collectionResponse);
        assertFalse(collectionResponse.getValue().isEmpty());

        TemplateDefinition firstTemplateDefinition = collectionResponse.getValue().get(0);

        TemplateDefinition templateDefinition = client.getTemplateDefinition(new ParametersForGetTemplateDefinition()
                .setRepositoryId(repositoryId)
                .setTemplateId(firstTemplateDefinition.getId()));

        assertNotNull(templateDefinition);
        Assertions.assertEquals(templateDefinition.getId(), firstTemplateDefinition.getId());
        Assertions.assertEquals(templateDefinition.getName(), firstTemplateDefinition.getName());
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateIdWorks() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        assertNotNull(templateInfoList);
        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        TemplateFieldDefinitionCollectionResponse result =
                client.listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                        .setRepositoryId(repositoryId)
                        .setTemplateId(tempDef.getId()));

        assertNotNull(result);
        Assertions.assertEquals(tempDef.getFieldCount(), result.getValue().size());
    }

    @Test
    void listTemplateDefinitionsNextLinkWorks() {
        int maxPageSize = 2;
        TemplateDefinitionCollectionResponse collectionResponse =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(collectionResponse);
        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        TemplateDefinitionCollectionResponse nextLinkResponse =
                client.listTemplateDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTemplateDefinitionsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<TemplateDefinitionCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listTemplateDefinitionsForEach(
                callback, maxPageSize, new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateIdNextLinkWorks() {
        int maxPageSize = 2;
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        TemplateFieldDefinitionCollectionResponse result =
                client.listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                        .setRepositoryId(repositoryId)
                        .setTemplateId(tempDef.getId())
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(result);
        Assertions.assertEquals(maxPageSize, result.getValue().size());

        String nextLink = result.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(result.getValue().size() <= maxPageSize);

        TemplateFieldDefinitionCollectionResponse nextLinkResponse =
                client.listTemplateFieldDefinitionsByTemplateIdNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateIdForEachWorks() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listTemplateFieldDefinitionsByTemplateIdForEach(
                callback,
                maxPageSize,
                new ParametersForListTemplateFieldDefinitionsByTemplateId()
                        .setRepositoryId(repositoryId)
                        .setTemplateId(tempDef.getId()));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateNameWorks() {
        TemplateDefinitionCollectionResponse collectionResponse =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        TemplateDefinition firstTemplateDefinitions =
                collectionResponse.getValue().get(0);
        assertNotNull(firstTemplateDefinitions);

        TemplateFieldDefinitionCollectionResponse result = client.listTemplateFieldDefinitionsByTemplateName(
                new ParametersForListTemplateFieldDefinitionsByTemplateName()
                        .setRepositoryId(repositoryId)
                        .setTemplateName(firstTemplateDefinitions.getName()));
        List<TemplateFieldDefinition> templateFieldDefinitions = result.getValue();
        assertNotNull(templateFieldDefinitions);
        assertEquals(firstTemplateDefinitions.getFieldCount(), templateFieldDefinitions.size());
    }
}
