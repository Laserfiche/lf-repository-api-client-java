package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitionsByTemplateName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDefinitionsApiTest extends BaseTest {
    TemplateDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void getTemplateDefinitions_ReturnAllTemplates() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        assertNotNull(templateInfoList);
    }

    @Test
    void getTemplateDefinitionsFields_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(
                        new ParametersForGetTemplateFieldDefinitions()
                                .setRepoId(repositoryId)
                                .setTemplateId(tempDef.getId()));

        assertNotNull(result);
        Assertions.assertSame(result
                .getValue()
                .size(), tempDef.getFieldCount());
    }

    @Test
    void getTemplateDefinitions_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(
                        new ParametersForGetTemplateDefinitions()
                                .setRepoId(repositoryId)
                                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(templateInfoList);
        String nextLink = templateInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(templateInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWTemplateInfo nextLinkResponse = client.getTemplateDefinitionsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        assertNotNull(templateInfoList);

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWTemplateInfo, Boolean> callback = listOfWTemplateInfo -> {
            if (listOfWTemplateInfo.getOdataNextLink() != null) {
                assertNotEquals(0, listOfWTemplateInfo
                        .getValue()
                        .size());
                assertTrue(listOfWTemplateInfo
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getTemplateDefinitionsForEach(callback, maxPageSize,
                new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
    }

    @Test
    void getTemplateDefinitionsFields_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(
                        new ParametersForGetTemplateDefinitions()
                                .setRepoId(repositoryId)
                                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(
                        new ParametersForGetTemplateFieldDefinitions()
                                .setRepoId(repositoryId)
                                .setTemplateId(tempDef.getId())
                                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(result);
        Assertions.assertSame(maxPageSize, result
                .getValue()
                .size());

        String nextLink = result.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(result
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfTemplateFieldInfo nextLinkResponse = client.getTemplateFieldDefinitionsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFields_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(
                        new ParametersForGetTemplateFieldDefinitions()
                                .setRepoId(repositoryId)
                                .setTemplateId(tempDef.getId()));

        assertNotNull(result);
        Assertions.assertSame(result
                .getValue()
                .size(), tempDef.getFieldCount());

        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback = fieldInfoList -> {
            if (fieldInfoList.getOdataNextLink() != null) {
                assertNotEquals(0, fieldInfoList
                        .getValue()
                        .size());
                assertTrue(fieldInfoList
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getTemplateFieldDefinitionsForEach(callback, maxPageSize,
                new ParametersForGetTemplateFieldDefinitions()
                        .setRepoId(repositoryId)
                        .setTemplateId(tempDef.getId()));
    }

    @Test
    void getTemplateDefinitionsFieldsById_ReturnTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        WTemplateInfo result = client
                .getTemplateDefinitionById(new ParametersForGetTemplateDefinitionById()
                        .setRepoId(repositoryId)
                        .setTemplateId(tempDef.getId()));

        assertNotNull(result);
        Assertions.assertSame(result.getId(), tempDef.getId());
    }

    @Test
    void getTemplateDefinitionsByTemplateName_TemplateNameQueryParameter_ReturnSingleTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfWTemplateInfo result = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions()
                        .setRepoId(repositoryId)
                        .setTemplateName(tempDef.getName()));

        assertNotNull(result);
    }

    @Test
    void getTemplateDefinitionsByTemplateName_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo allTemplateDefinitionsFuture = client
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
        WTemplateInfo firstTemplateDefinitions = allTemplateDefinitionsFuture
                .getValue()
                .get(0);
        assertNotNull(firstTemplateDefinitions);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitionsByTemplateName(
                        new ParametersForGetTemplateFieldDefinitionsByTemplateName()
                                .setRepoId(repositoryId)
                                .setTemplateName(firstTemplateDefinitions.getName()));
        List<TemplateFieldInfo> templateFieldDefinitions = result.getValue();
        assertNotNull(templateFieldDefinitions);
        assertEquals(templateFieldDefinitions.size(), firstTemplateDefinitions.getFieldCount());
    }
}
