package integration;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class TemplateDefinitionsApiTest extends BaseTest {
    TemplateDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTemplateDefinitionClient();
    }

    @Test
    void getTemplateDefinitions_Success() {
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
        String nextLink = templateInfoList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(templateInfoList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> nextLinkResponse = client.getTemplateDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTemplateInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
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
            if (result._atOdataNextLink != null) {
                assertNotEquals(0, result.value.size());
                assertTrue(result.value.size() <= maxPageSize);
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
    void getTemplateDefinitionsFields_Success() {
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
    void getTemplateDefinitionsFields_NextLink() throws InterruptedException {
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

        String nextLink = result._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(result.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> nextLinkResponse = client.getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfTemplateFieldInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getTemplateDefinitionsFields_ForEach() throws InterruptedException {
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

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfTemplateFieldInfo futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
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
    void getTemplateDefinitionsFieldsById_Success() {
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
    void getTemplateDefinitionsByTemplateName_Success() {
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
}
