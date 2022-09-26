package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_Success() {
        WFieldInfo fieldInfo = client
                .getFieldDefinitionById(repoId, 1, null, null)
                .join();

        assertNotNull(fieldInfo);
    }

    @Test
    void getFieldDefinitions_Success() {
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

        String nextLink = fieldInfoList._atOdataNextLink;
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(fieldInfoList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> nextLinkResponse = client.getFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWFieldInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getFieldDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client
                .getFieldDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(fieldInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWFieldInfo result = data.join();
            if (result._atOdataNextLink != null) {
                assertNotEquals(0, result.value.size());
                assertTrue(result.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getFieldDefinitionsForEach(callback, maxPageSize, repoId,null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
