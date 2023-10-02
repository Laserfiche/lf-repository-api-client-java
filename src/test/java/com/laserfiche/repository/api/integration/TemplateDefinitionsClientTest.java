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
    void getTemplateDefinitions_ReturnAllTemplates() {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        assertNotNull(templateInfoList);
        assertFalse(templateInfoList.getValue().isEmpty());
    }

    @Test
    void getTemplateDefinitionsFields_ReturnTemplateFields() {
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
    void getTemplateDefinitions_NextLink() throws InterruptedException {
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
    void getTemplateDefinitions_ForEach() throws InterruptedException {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TemplateDefinitionCollectionResponse, Boolean> callback = listOfWTemplateInfo -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertNotEquals(0, listOfWTemplateInfo.getValue().size());
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
    void getTemplateDefinitionsFields_NextLink() throws InterruptedException {
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
    void getTemplateDefinitionsFields_ForEach() throws InterruptedException {
        TemplateDefinitionCollectionResponse templateInfoList =
                client.listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));

        TemplateDefinition tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback = fieldInfoList -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertNotEquals(0, fieldInfoList.getValue().size());
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
    void getTemplateDefinitionById_ReturnTemplate() {
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
    void getTemplateDefinitionsByTemplateName_TemplateNameQueryParameter_ReturnSingleTemplate() {
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
    void getTemplateDefinitionsFieldByTemplateName_ReturnTemplateFields() {
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
