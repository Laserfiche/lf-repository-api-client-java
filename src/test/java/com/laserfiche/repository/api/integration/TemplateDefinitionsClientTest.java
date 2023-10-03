package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

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

class TemplateDefinitionsClientTest extends BaseTest {
    private TemplateDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void listTemplateDefinitionsWorks() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        assertNotNull(templateInfoList);
        assertFalse(templateInfoList.getValue().isEmpty());
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
        Assertions.assertSame(tempDef.getFieldCount(), result.getValue().size());
    }

    @Test
    void listTemplateDefinitionsNextLinkWorks() throws InterruptedException {
        int maxPageSize = 1;
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(templateInfoList);
        String nextLink = templateInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(templateInfoList.getValue().size() <= maxPageSize);

        TemplateDefinitionCollectionResponse nextLinkResponse =
                client.listTemplateDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTemplateDefinitionsForEachWorks() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TemplateDefinitionCollectionResponse, Boolean> callback = listOfWTemplateInfo -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(listOfWTemplateInfo.getValue().isEmpty());
                assertTrue(listOfWTemplateInfo.getValue().size() <= maxPageSize);
                return listOfWTemplateInfo.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listTemplateDefinitionsForEach(
                callback, maxPageSize, new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateIdNextLinkWorks() throws InterruptedException {
        int maxPageSize = 1;
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
        Assertions.assertSame(maxPageSize, result.getValue().size());

        String nextLink = result.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(result.getValue().size() <= maxPageSize);

        TemplateFieldDefinitionCollectionResponse nextLinkResponse =
                client.listTemplateFieldDefinitionsByTemplateIdNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateIdForEachWorks() throws InterruptedException {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback = fieldInfoList -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(fieldInfoList.getValue().isEmpty());
                assertTrue(fieldInfoList.getValue().size() <= maxPageSize);
                return fieldInfoList.getOdataNextLink() != null;
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
    void getTemplateDefinitionWorks() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        TemplateDefinition result = client.getTemplateDefinition(new ParametersForGetTemplateDefinition()
                .setRepositoryId(repositoryId)
                .setTemplateId(tempDef.getId()));

        assertNotNull(result);
        Assertions.assertSame(result.getId(), tempDef.getId());
    }

    @Test
    void listTemplateDefinitionsCanBeUsedToRetrieveASingleTemplate() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        TemplateDefinitionCollectionResponse result =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions()
                        .setRepositoryId(repositoryId)
                        .setTemplateName(tempDef.getName()));

        assertNotNull(result);
        assertEquals(1, result.getValue().size());
        assertEquals(tempDef.getName(), result.getValue().get(0).getName());
    }

    @Test
    void listTemplateFieldDefinitionsByTemplateNameWorks() {
        TemplateDefinitionCollectionResponse allTemplateDefinitionsFuture =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        TemplateDefinition firstTemplateDefinitions =
                allTemplateDefinitionsFuture.getValue().get(0);
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
