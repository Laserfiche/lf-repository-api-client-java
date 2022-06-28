package integration;

import com.laserfiche.repository.api.client.TagDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TagDefinitionsApiTest extends BaseTest {
    @Test
    public void getTagDefinitions_Success() {
        TagDefinitionsApi client = repositoryApiClient.getTagDefinitionsClient();
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }
}
