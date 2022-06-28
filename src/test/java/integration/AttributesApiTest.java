package integration;

import com.laserfiche.repository.api.client.AttributesApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttributesApiTest extends BaseTest {

    @Test
    public void getTrusteeAttributeKeyValuePairs_Success() {
        AttributesApi client = repositoryApiClient.getAttributesClient();
        CompletableFuture<ODataValueContextOfListOfAttribute> future = client.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false);
        ODataValueContextOfListOfAttribute attributeList = future.join();

        assertNotNull(attributeList);
    }
}
