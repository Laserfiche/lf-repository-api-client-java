package integration;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeValueByKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AttributesApiTest extends BaseTest {
    AttributesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getAttributesClient();
    }

    @Test
    void getTrusteeAttributeKeyValuePairs_ReturnAttributes() {
        ODataValueContextOfListOfAttribute attributeList = client.getTrusteeAttributeKeyValuePairs(
                new ParametersForGetTrusteeAttributeKeyValuePairs()
                        .setRepoId(repositoryId)
                        .setEveryone(true));

        assertNotNull(attributeList);
        assertNotNull(attributeList.getValue());
    }

    @Test
    void getAttributeValueByKey_ReturnAttribute() {
        ODataValueContextOfListOfAttribute attributeList = client.getTrusteeAttributeKeyValuePairs(
                new ParametersForGetTrusteeAttributeKeyValuePairs()
                        .setRepoId(repositoryId)
                        .setEveryone(true));
        assertNotNull(attributeList);

        Attribute attribute = client.getTrusteeAttributeValueByKey(
                new ParametersForGetTrusteeAttributeValueByKey()
                        .setRepoId(repositoryId)
                        .setAttributeKey(attributeList
                                .getValue()
                                .get(0)
                                .getKey())
                        .setEveryone(true));
        assertNotNull(attribute);
    }

    @Test
    void getAttributeValueByKey_NextLink() throws InterruptedException {
        int maxPageSize = 5;
        ODataValueContextOfListOfAttribute attributeList = client.getTrusteeAttributeKeyValuePairs(
                new ParametersForGetTrusteeAttributeKeyValuePairs()
                        .setRepoId(repositoryId)
                        .setEveryone(true)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(attributeList);

        Attribute attribute = client.getTrusteeAttributeValueByKey(
                new ParametersForGetTrusteeAttributeValueByKey()
                        .setRepoId(repositoryId)
                        .setAttributeKey(attributeList
                                .getValue()
                                .get(0)
                                .getKey())
                        .setEveryone(true));
        assertNotNull(attribute);

        String nextLink = attributeList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(attributeList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfListOfAttribute nextLinkResult = client.getTrusteeAttributeKeyValuePairsNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getAttributeValueByKey_ForEach() throws InterruptedException {
        int maxPageSize = 90;
        ODataValueContextOfListOfAttribute attributeList = client.getTrusteeAttributeKeyValuePairs(
                new ParametersForGetTrusteeAttributeKeyValuePairs()
                        .setRepoId(repositoryId)
                        .setEveryone(true)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(attributeList);

        Attribute attribute = client.getTrusteeAttributeValueByKey(
                new ParametersForGetTrusteeAttributeValueByKey()
                        .setRepoId(repositoryId)
                        .setAttributeKey(attributeList
                                .getValue()
                                .get(0)
                                .getKey())
                        .setEveryone(true));
        assertNotNull(attribute);

        TimeUnit.SECONDS.sleep(10);

        Function<ODataValueContextOfListOfAttribute, Boolean> callback = attributes -> {
            if (attributes.getOdataNextLink() != null) {
                assertNotEquals(0, attributes
                        .getValue()
                        .size());
                assertTrue(attributes
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getTrusteeAttributeKeyValuePairsForEach(callback, maxPageSize,
                new ParametersForGetTrusteeAttributeKeyValuePairs()
                        .setRepoId(repositoryId)
                        .setEveryone(true));
    }
}
