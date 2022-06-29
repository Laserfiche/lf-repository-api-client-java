package integration;

import com.laserfiche.repository.api.client.TagDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsApi client;

    private final String preferMaxPageSize1 = "maxpagesize=1";

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    public void getTagDefinitions_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }

    @Test
    public void getTagDefinitionsPaginate_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagDefinitions(repoId, preferMaxPageSize1, null, null, null, null, null, false);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
        assertNotNull(tagInfoList.getAtOdataNextLink());

        String nextLink = tagInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> newFuture = client.getTagDefinitionsPaginate(nextLink, preferMaxPageSize1);
        ODataValueContextOfIListOfWTagInfo newTagInfoList = newFuture.join();

        assertNotNull(newTagInfoList);
    }
}
