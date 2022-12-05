package integration;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FieldDefinitionsApiTest extends BaseTest {
    FieldDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_ReturnField() {
        WFieldInfo fieldInfo = client
                .getFieldDefinitionById(repoId, 1, null, null);

        assertNotNull(fieldInfo);
    }

    @Test
    void getFieldDefinitions_ReturnAllFields() {
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client
                .getFieldDefinitions(repoId, null, null, null, null, null, null, false);

        assertNotNull(fieldInfoList);
    }

    @Test
    void getFieldDefinitions_NextLink() throws InterruptedException {
        int maxPageSize = 10;
        ODataValueContextOfIListOfWFieldInfo fieldInfoList = client
                .getFieldDefinitions(repoId, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null,
                        false);
        assertNotNull(fieldInfoList);

        String nextLink = fieldInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(fieldInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWFieldInfo nextLinkResult = client.getFieldDefinitionsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getFieldDefinitions_ForEach() {
        int maxPageSize = 10;
        Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback = fieldInfoList -> {
            if (fieldInfoList.getOdataNextLink() != null) {
                assertNotEquals(0, fieldInfoList
                        .getValue()
                        .size());
                assertTrue(fieldInfoList
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getFieldDefinitionsForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null);
    }
}
