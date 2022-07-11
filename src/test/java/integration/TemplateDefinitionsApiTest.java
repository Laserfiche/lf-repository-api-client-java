package integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.TemplateDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
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

    @Test
    void getTemplateDefinitionsNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> allTemplateDefinitionsResponse = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, maxPageSize);
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionResult = allTemplateDefinitionsResponse.join();
        assertNotNull(templateDefinitionResult);
        String nextLink = templateDefinitionResult.getAtOdataNextLink();
        assertNotNull(nextLink);
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> result = client.getTemplateDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(result);
        assertTrue(result.join().getValue().size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFieldNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> allTemplateDefinitionsResponse = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        WTemplateInfo firstTemplateDefinitionResponse = allTemplateDefinitionsResponse.join().getValue().get(0);
        assertNotNull(firstTemplateDefinitionResponse);
        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldResponse = client.getTemplateFieldDefinitions(repoId, firstTemplateDefinitionResponse.getId(), null, null, null, null, null, null, null, maxPageSize);
        assertNotNull(templateDefinitionsFieldResponse);
        String nextLink = templateDefinitionsFieldResponse.join().getAtOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(nextLink.length() > 0);
        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> result = client.getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(result);
        assertTrue(result.join().getValue().size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsForEach_Success() {
        client.getTemplateDefinitionsForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWTemplateInfo templateList = future.join();
            if (templateList != null) {
                assertNotNull(templateList.getValue());
            }
            return templateList != null; // Stop asking if there's no data.
        }), repoId, null, null, null, null, null, null, null, null, maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFieldForEach_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> allTemplateDefinitionsResponse = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        WTemplateInfo firstTemplateDefinitionResponse = allTemplateDefinitionsResponse.join().getValue().get(0);
        assertNotNull(firstTemplateDefinitionResponse);
        client.getTemplateFieldDefinitionsForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfTemplateFieldInfo templateFieldList = future.join();
            if (templateFieldList != null) {
                assertNotNull(templateFieldList.getValue());
            }
            return templateFieldList != null;
        }), repoId, firstTemplateDefinitionResponse.getId(), null, null, null, null, null, null, null, maxPageSize);
    }
}
