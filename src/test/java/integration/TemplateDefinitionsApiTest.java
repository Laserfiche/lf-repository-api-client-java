package integration;

import com.laserfiche.repository.api.TemplateDefinitionsClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTemplateInfo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateDefinitionsApiTest extends BaseTest {
    @Test
    public void getTemplateDefinitions_Success() {
        TemplateDefinitionsClient client = repositoryApiClient.getTemplateDefinitionClient();
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> future = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTemplateInfo templateInfoList = future.join();

        assertNotNull(templateInfoList);
    }
}
