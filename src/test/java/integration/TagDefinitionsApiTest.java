package integration;

import com.laserfiche.repository.api.TagDefinitionsClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    public void getTagDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }

    @Test
    public void getTagDefinitionsNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
        assertNotNull(tagInfoList.getAtOdataNextLink());

        String nextLink = tagInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> newFuture = client.getTagDefinitionsNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWTagInfo newTagInfoList = newFuture.join();

        assertNotNull(newTagInfoList);
    }
}
