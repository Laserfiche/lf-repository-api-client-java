package integration;

import com.laserfiche.repository.api.client.FieldDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.WFieldInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FieldDefinitionsApiTest extends BaseTest {
    @Test
    public void getFieldDefinitionById_Success() {
        FieldDefinitionsApi client = repositoryApiClient.getFieldDefinitionsClient();
        CompletableFuture<WFieldInfo> future = client.getFieldDefinitionById(repoId, 261, null, null);
        WFieldInfo fieldInfo = future.join();

        assertNotNull(fieldInfo);
    }

    @Test
    public void getFieldDefinitions_Success() {
        FieldDefinitionsApi client = repositoryApiClient.getFieldDefinitionsClient();
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = future.join();

        assertNotNull(fieldInfoList);
    }
}
