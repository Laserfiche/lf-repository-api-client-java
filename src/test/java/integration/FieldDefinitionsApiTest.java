package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_ReturnField() {
        WFieldInfo fieldInfo = client
                .getFieldDefinitionById(repoId, 1, null, null)
                .join();

        assertNotNull(fieldInfo);
    }

    @Test
    void getFieldDefinitions_ReturnAllFields() {
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client
                .getFieldDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldInfoList);
    }

    @Test
    void getFieldDefinitions_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client
                .getFieldDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldInfoList);

        String nextLink = fieldInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(fieldInfoList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> nextLinkResponse = client.getFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWFieldInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }
}
