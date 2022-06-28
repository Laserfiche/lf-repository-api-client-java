package integration;

import com.laserfiche.repository.api.client.TemplateDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTemplateInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateDefinitionsApiTest extends BaseTest {
    @Test
    public void getTemplateDefinitions_Success() {
        TemplateDefinitionsApi client = repositoryApiClient.getTemplateDefinitionClient();
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();

        assertNotNull(templateInfoList);
    }
}
