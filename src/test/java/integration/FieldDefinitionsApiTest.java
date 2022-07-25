package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.*;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    private final int maxPageSize = 1;

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

    @Test
    void getFieldDefinitionsNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = future.join();

        assertNotNull(fieldInfoList);
        assertNotNull(fieldInfoList.getAtOdataNextLink());

        String nextLink = fieldInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> newFuture = client.getFieldDefinitionsNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWFieldInfo newFieldInfoList = newFuture.join();

        assertNotNull(newFieldInfoList);
    }

    @Test
    void getFieldDefinitionsForEach_Success() {
        client.getFieldDefinitionsForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWFieldInfo definitionList = future.join();
            if (definitionList != null) {
                assertNotNull(definitionList.getValue());
            }
            return definitionList != null; // Stop asking if there's no data.
        }), repoId, null, null, null, null, null, null, false, maxPageSize);
    }
}
