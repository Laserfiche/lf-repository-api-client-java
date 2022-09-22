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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TemplateDefinitionsApiTest extends BaseTest {
    TemplateDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void getTemplateDefinitions_ReturnAllTemplates() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(templateInfoList);
    }

    @Test
    void getTemplateDefinitionsFields_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.value.get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.id, null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.value.size(), tempDef.fieldCount);
    }

    @Test
    void getTemplateDefinitionsFieldsById_ReturnTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.value.get(0);

        assertNotNull(templateInfoList);

        WTemplateInfo result = client
                .getTemplateDefinitionById(repoId, tempDef.id, null, null)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.id, tempDef.id);
    }

    @Test
    void getTemplateDefinitionsByTemplateName_TemplateNameQueryParameter_ReturnSingleTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.value.get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfWTemplateInfo result = client
                .getTemplateDefinitions(repoId, tempDef.name, null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
    }

    @Test
    void getTemplateDefinitionsByTemplateName_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo allTemplateDefinitionsFuture = client.getTemplateDefinitions(repoId,null,null,null,null,null,null,null,null).join();
        WTemplateInfo firstTemplateDefinitions = allTemplateDefinitionsFuture.value.get(0);
        assertNotNull(firstTemplateDefinitions);

        ODataValueContextOfIListOfTemplateFieldInfo result = client.getTemplateFieldDefinitionsByTemplateName(repoId, firstTemplateDefinitions.name, null, null, null, null, null, null, null).join();
        List<TemplateFieldInfo> templateFieldDefinitions = result.value;
        assertNotNull(templateFieldDefinitions);
        assertEquals(templateFieldDefinitions.size(),firstTemplateDefinitions.fieldCount);
    }
}
