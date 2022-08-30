package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_Success() {
        WFieldInfo fieldInfo = client.getFieldDefinitionById(repoId, 1, null, null).join();

        assertNotNull(fieldInfo);
    }

    @Test
    void getFieldDefinitions_Success() {
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false).join();

        assertNotNull(fieldInfoList);
    }
}
