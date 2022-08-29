package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_Success() {
        CompletableFuture<WFieldInfo> future = client.getFieldDefinitionById(repoId, 1, null, null);
        WFieldInfo fieldInfo = future.join();
        assertNotNull(fieldInfo);
    }

    @Test
    void getFieldDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = future.join();

        assertNotNull(fieldInfoList);
    }
}
