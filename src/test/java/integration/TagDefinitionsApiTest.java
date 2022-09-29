package integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void getTagDefinitions_ReturnAllTags() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getTagDefinitions_NextLink() throws InterruptedException {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(tagInfoList
                .getValue()
                .size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTagDefinitionById_ReturnTag() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        WTagInfo tagInfo = client
                .getTagDefinitionById(repoId, tagInfoList
                        .getValue()
                        .get(0)
                        .getId(), null, null)
                .join();

        assertNotNull(tagInfo);
    }
}
