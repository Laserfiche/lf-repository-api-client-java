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
    void PerTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void getTagDefinitions_Success() {
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

        String nextLink = tagInfoList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(tagInfoList.value.size() <= maxPageSize);

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
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
            if (result._atOdataNextLink != null) {
                assertNotEquals(0, result.value.size());
                assertTrue(result.value.size() <= maxPageSize);
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
    void getTagDefinitionById_Success() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        WTagInfo tagInfo = client
                .getTagDefinitionById(repoId, tagInfoList.value.get(0).id, null, null)
                .join();

        assertNotNull(tagInfo);
    }
}
