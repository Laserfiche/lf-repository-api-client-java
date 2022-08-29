package integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemplateDefinitionsApiTest extends BaseTest {
    TemplateDefinitionsClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void getTemplateDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();
        assertNotNull(templateInfoList);
    }

    @Test
    void getTemplateDefinitionsFields_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();
        WTemplateInfo tempDef = templateInfoList.getValue().get(0);
        assertNotNull(templateInfoList);
        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> newFuture = client.getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfTemplateFieldInfo result = newFuture.join();
        assertNotNull(result);
        Assertions.assertSame(result.getValue().size(), tempDef.getFieldCount());
    }

    @Test
    void getTemplateDefinitionsFieldsById_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();
        WTemplateInfo tempDef = templateInfoList.getValue().get(0);
        assertNotNull(templateInfoList);
        CompletableFuture<WTemplateInfo> newFuture = client.getTemplateDefinitionById(repoId, tempDef.getId(), null, null);
        WTemplateInfo result = newFuture.join();
        assertNotNull(result);
        Assertions.assertSame(result.getId(), tempDef.getId());
    }

    @Test
    void getTemplateDefinitionsByTemplateName_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();
        WTemplateInfo tempDef = templateInfoList.getValue().get(0);
        assertNotNull(templateInfoList);
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> newFuture = client.getTemplateDefinitions(repoId, tempDef.getName(), null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo result = newFuture.join();
        assertNotNull(result);
    }
}
