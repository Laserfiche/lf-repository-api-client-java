package integration;

import com.laserfiche.repository.api.FieldDefinitionsClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    private final String preferMaxPageSize1 = "maxpagesize=1";
    private final int maxPageSize = 1;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    public void getFieldDefinitionById_Success() {
        CompletableFuture<WFieldInfo> future = client.getFieldDefinitionById(repoId, 261, null, null);
        WFieldInfo fieldInfo = future.join();

        assertNotNull(fieldInfo);
    }

    @Test
    public void getFieldDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = future.join();

        assertNotNull(fieldInfoList);
    }

    @Test
    public void getFieldDefinitionsPaginate_Success() {
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> future = client.getFieldDefinitions(repoId, preferMaxPageSize1, null, null, null, null, null, false);
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = future.join();

        assertNotNull(fieldInfoList);
        assertNotNull(fieldInfoList.getAtOdataNextLink());

        String nextLink = fieldInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> newFuture = client.getFieldDefinitionsNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWFieldInfo newFieldInfoList = newFuture.join();

        assertNotNull(newFieldInfoList);
    }
}
