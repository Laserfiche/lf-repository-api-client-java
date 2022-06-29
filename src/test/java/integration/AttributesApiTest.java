package integration;

import com.laserfiche.repository.api.AttributesClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttributesApiTest extends BaseTest {
    AttributesClient client;

    private final String preferMaxPageSize1 = "maxpagesize=1";
    private final int maxPageSize = 1;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    public void getTrusteeAttributeKeyValuePairs_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
    }

    @Test
    public void getTrusteeAttributeKeyValuePairsPaginate_Success() {
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, preferMaxPageSize1, null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
        assertNotNull(attributeList.getAtOdataNextLink());

        String nextLink = attributeList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfListOfAttribute> newFuture = client.getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        ODataValueContextOfListOfAttribute newAttributeList = newFuture.join();

        assertNotNull(newAttributeList);
    }
}
