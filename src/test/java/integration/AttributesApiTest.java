package integration;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import org.junit.jupiter.api.*;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AttributesApiTest extends BaseTest {
    AttributesClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @AfterEach
    void resetClient_Success() {
        client = null;
    }

    @Test
    void getTrusteeAttributeKeyValuePairs_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false, null);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
    }

    @Test
    void getAttributeValueByKey_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false, null);
        ODataValueContextOfListOfAttribute attributeList = future.join();
        assertNotNull(attributeList);
        CompletableFuture<Attribute> newFuture = client.getTrusteeAttributeValueByKey(repoId, attributeList.getValue().get(0).getKey(), true);
        Attribute attributeObj = newFuture.join();
        assertNotNull(attributeObj);
    }

    @Test
    void getTrusteeAttributeKeyValuePairsNextLink_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
        assertNotNull(attributeList.getAtOdataNextLink());

        String nextLink = attributeList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfListOfAttribute> newFuture = client.getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        ODataValueContextOfListOfAttribute newAttributeList = newFuture.join();

        assertNotNull(newAttributeList);
    }

    @Test
    void getTrusteeAttributeKeyValuePairsForEach_Success() {
        client.getTrusteeAttributeKeyValuePairsForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfListOfAttribute attributeList = future.join();
            if (attributeList != null) {
                assertNotNull(attributeList.getValue());
            }
            return attributeList != null; // Stop asking if there's no data.
        }), repoId, true, null, null, null, null, null, false, maxPageSize);
    }
}
