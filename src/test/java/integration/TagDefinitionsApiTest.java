package integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

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
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        String nextLink = tagInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList.getValue().size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getTagDefinitions_ForEach() throws InterruptedException {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        TimeUnit.SECONDS.sleep(10);

        int maxPageSize = 90;
        Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfIListOfWTagInfo result = data.join();
            if (result.getAtOdataNextLink() != null) {
                assertNotEquals(0, result.getValue().size());
                assertTrue(result.getValue().size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTagDefinitionsForEach(callback, maxPageSize, repoId, null, null, null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
