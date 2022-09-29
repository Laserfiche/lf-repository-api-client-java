package integration;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
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

class AttributesApiTest extends BaseTest {
    AttributesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    void getTrusteeAttributeKeyValuePairs_ReturnAttributes() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId,
                true, null, null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
    }

    @Test
    void getAttributeValueByKey_ReturnAttribute() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId,
                true, null, null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();
        assertNotNull(attributeList);

        CompletableFuture<Attribute> newFuture = client.getTrusteeAttributeValueByKey(repoId,
                attributeList.getValue().get(0).getKey(), true);
        Attribute attributeObj = newFuture.join();
        assertNotNull(attributeObj);
    }

    @Test
    void getAttributeValueByKey_NextLink() throws InterruptedException {
<<<<<<< HEAD
        int maxPageSize = 1;
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId,
                true, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, false);
=======
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId,
                true, null, null, null, null, null, false);
>>>>>>> 1.x
        ODataValueContextOfListOfAttribute attributeList = future.join();
        assertNotNull(attributeList);

        CompletableFuture<Attribute> newFuture = client.getTrusteeAttributeValueByKey(repoId,
<<<<<<< HEAD
                attributeList.value.get(0).key, true);
        Attribute attributeObj = newFuture.join();
        assertNotNull(attributeObj);

        String nextLink = attributeList._atOdataNextLink;
        assertNotNull(nextLink);

        assertTrue(attributeList.value.size() <= maxPageSize);
=======
                attributeList.getValue().get(0).getKey(), true);
        Attribute attributeObj = newFuture.join();
        assertNotNull(attributeObj);

        String nextLink = attributeList.getAtOdataNextLink();
        assertNotNull(nextLink);
        int maxPageSize = 1;
        assertTrue(attributeList.getValue().size() <= maxPageSize);
>>>>>>> 1.x

        CompletableFuture<ODataValueContextOfListOfAttribute> nextLinkResponse = client.getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResponse);
        TimeUnit.SECONDS.sleep(10);
        ODataValueContextOfListOfAttribute nextLinkResult = nextLinkResponse.join();
        assertNotNull(nextLinkResult);
<<<<<<< HEAD
        assertTrue(nextLinkResult.value.size() <= maxPageSize);
    }

    @Test
    void getAttributeValueByKey_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId,
                true, String.format("maxpagesize=%d", maxPageSize), null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();
        assertNotNull(attributeList);

        CompletableFuture<Attribute> newFuture = client.getTrusteeAttributeValueByKey(repoId,
                attributeList.value.get(0).key, true);
        Attribute attributeObj = newFuture.join();
        assertNotNull(attributeObj);

        TimeUnit.SECONDS.sleep(10);
        Function<CompletableFuture<ODataValueContextOfListOfAttribute>, CompletableFuture<Boolean>> callback = data -> {
            ODataValueContextOfListOfAttribute futureResult = data.join();
            if (futureResult._atOdataNextLink != null) {
                assertNotEquals(0, futureResult.value.size());
                assertTrue(futureResult.value.size() <= maxPageSize);
                return CompletableFuture.completedFuture(true);
            } else {
                return CompletableFuture.completedFuture(false);
            }
        };
        try {
            client.getTrusteeAttributeKeyValuePairsForEach(callback, maxPageSize, repoId, true, null,null, null, null, null, null);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
=======
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
>>>>>>> 1.x
    }
}
