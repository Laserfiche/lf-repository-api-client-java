package integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        assertNotNull(templateInfoList);
    }

    @Test
    void getTemplateDefinitionsFields_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false);

        assertNotNull(result);
        Assertions.assertSame(result
                .getValue()
                .size(), tempDef.getFieldCount());
    }

    @Test
    void getTemplateDefinitions_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, String.format("maxpagesize=%d", maxPageSize), null, null, null,
                        null, null, false);

        assertNotNull(templateInfoList);
        String nextLink = templateInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(templateInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWTemplateInfo nextLinkResponse = client.getTemplateDefinitionsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        assertNotNull(templateInfoList);

        TimeUnit.SECONDS.sleep(10);

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
        client.getTemplateDefinitionsForEach(callback, maxPageSize, repoId, null, null, null, null, null, null,
                null, null);
    }

    @Test
    void getTemplateDefinitionsFields_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, String.format("maxpagesize=%d", maxPageSize), null, null, null,
                        null, null, false);

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), String.format("maxpagesize=%d", maxPageSize),
                        null, null, null, null, null, false);

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
        TimeUnit.SECONDS.sleep(10);
        assertNotNull(nextLinkResponse);
        assertTrue(nextLinkResponse
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFields_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false);

        assertNotNull(result);
        Assertions.assertSame(result
                .getValue()
                .size(), tempDef.getFieldCount());

        TimeUnit.SECONDS.sleep(10);

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
        client.getTemplateFieldDefinitionsForEach(callback, maxPageSize, repoId, tempDef.getId(), null, null, null,
                null, null, null, false);
    }

    @Test
    void getTemplateDefinitionsFieldsById_ReturnTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        WTemplateInfo result = client
                .getTemplateDefinitionById(repoId, tempDef.getId(), null, null);

        assertNotNull(result);
        Assertions.assertSame(result.getId(), tempDef.getId());
    }

    @Test
    void getTemplateDefinitionsByTemplateName_TemplateNameQueryParameter_ReturnSingleTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);

        WTemplateInfo tempDef = templateInfoList
                .getValue()
                .get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfWTemplateInfo result = client
                .getTemplateDefinitions(repoId, tempDef.getName(), null, null, null, null, null, null, false);

        assertNotNull(result);
    }

    @Test
    void getTemplateDefinitionsByTemplateName_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo allTemplateDefinitionsFuture = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null);
        WTemplateInfo firstTemplateDefinitions = allTemplateDefinitionsFuture
                .getValue()
                .get(0);
        assertNotNull(firstTemplateDefinitions);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitionsByTemplateName(repoId, firstTemplateDefinitions.getName(), null, null, null,
                        null, null, null, null);
        List<TemplateFieldInfo> templateFieldDefinitions = result.getValue();
        assertNotNull(templateFieldDefinitions);
        assertEquals(templateFieldDefinitions.size(), firstTemplateDefinitions.getFieldCount());
    }
}
