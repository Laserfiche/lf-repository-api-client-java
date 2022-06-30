package integration;

import com.laserfiche.repository.api.AttributesClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttributesApiTest extends BaseTest {
    AttributesClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    public void getTrusteeAttributeKeyValuePairs_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false, null);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
    }

    @Test
    public void getTrusteeAttributeKeyValuePairsNextLink_Success() {
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
    public void getTrusteeAttributeKeyValuePairsForEach_Success() {
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
