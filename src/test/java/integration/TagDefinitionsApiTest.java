package integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
<<<<<<< HEAD
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
=======
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
>>>>>>> 1.x

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
<<<<<<< HEAD
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, null, false)
=======
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
>>>>>>> 1.x
                .join();

        assertNotNull(tagInfoList);

<<<<<<< HEAD
        String nextLink = tagInfoList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(tagInfoList.value.size() <= maxPageSize);
=======
        String nextLink = tagInfoList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(tagInfoList
                .getValue()
                .size() <= maxPageSize);
>>>>>>> 1.x

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> nextLinkResponse = client.getTagDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfIListOfWTagInfo nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
<<<<<<< HEAD
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
=======
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTagDefinitionById_ReturnTag() {
>>>>>>> 1.x
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
