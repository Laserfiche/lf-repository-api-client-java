package integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void getTagDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getTagDefinitionById_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();
        assertNotNull(tagInfoList);
        CompletableFuture<WTagInfo> newFuture = client.getTagDefinitionById(repoId, tagInfoList.getValue().get(0).getId(), null, null);
        WTagInfo tagInfo = newFuture.join();
        assertNotNull(tagInfo);
    }
}
