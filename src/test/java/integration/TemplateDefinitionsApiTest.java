package integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.TemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.List;

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
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(templateInfoList);
    }

    @Test
    void getTemplateDefinitions_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(templateInfoList);
        String nextLink = templateInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(templateInfoList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> nextLinkResponse = client.getTemplateDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTemplateInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        assertNotNull(templateInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWTemplateInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWTemplateInfo result = data.join();
            if (result.getAtOdataNextLink() != null) {
                assertNotEquals(0, result.getValue().size());
                assertTrue(result.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTemplateDefinitionsForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTemplateDefinitionsFields_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.getValue().size(), tempDef.getFieldCount());
    }

    @Test
    void getTemplateDefinitionsFields_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.getValue().size(), tempDef.getFieldCount());

        String nextLink = result.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(result.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> nextLinkResponse = client.getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfTemplateFieldInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFields_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfTemplateFieldInfo result = client
                .getTemplateFieldDefinitions(repoId, tempDef.getId(), null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.getValue().size(), tempDef.getFieldCount());

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfTemplateFieldInfo futureResult = data.join();
            if (futureResult.getAtOdataNextLink() != null) {
                assertNotEquals(0, futureResult.getValue().size());
                assertTrue(futureResult.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTemplateFieldDefinitionsForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTemplateDefinitionsFieldsById_ReturnTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        WTemplateInfo result = client
                .getTemplateDefinitionById(repoId, tempDef.getId(), null, null)
                .join();

        assertNotNull(result);
        Assertions.assertSame(result.getId(), tempDef.getId());
    }

    @Test
    void getTemplateDefinitionsByTemplateName_TemplateNameQueryParameter_ReturnSingleTemplate() {
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = client
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false)
                .join();

        WTemplateInfo tempDef = templateInfoList.getValue().get(0);

        assertNotNull(templateInfoList);

        ODataValueContextOfIListOfWTemplateInfo result = client
                .getTemplateDefinitions(repoId, tempDef.getName(), null, null, null, null, null, null, false)
                .join();

        assertNotNull(result);
    }

    @Test
    void getTemplateDefinitionsByTemplateName_ReturnTemplateFields() {
        ODataValueContextOfIListOfWTemplateInfo allTemplateDefinitionsFuture = client.getTemplateDefinitions(repoId,null,null,null,null,null,null,null,null).join();
        WTemplateInfo firstTemplateDefinitions = allTemplateDefinitionsFuture.getValue().get(0);
        assertNotNull(firstTemplateDefinitions);

        ODataValueContextOfIListOfTemplateFieldInfo result = client.getTemplateFieldDefinitionsByTemplateName(repoId, firstTemplateDefinitions.getName(), null, null, null, null, null, null, null).join();
        List<TemplateFieldInfo> templateFieldDefinitions = result.getValue();
        assertNotNull(templateFieldDefinitions);
        assertEquals(templateFieldDefinitions.size(),firstTemplateDefinitions.getFieldCount());
    }
}
