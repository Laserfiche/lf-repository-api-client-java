package integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkDefinitionsApiTest extends BaseTest {
    LinkDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getLinkDefinitionsClient();
    }

    @Test
    void getLinkDefinitions_ReturnAllLinks() {
        ODataValueContextOfIListOfEntryLinkTypeInfo linkDefinitionsResponse = client.getLinkDefinitions(repoId, null,
                null, null, null, null, null);

        assertNotNull(linkDefinitionsResponse.getValue());
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = client.getLinkDefinitions(
                repoId, null, null, null, null, null, null);
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult
                .getValue()
                .get(0);
        assertNotNull(firstLinkDefinition);

        EntryLinkTypeInfo linkDefinitions = client.getLinkDefinitionById(repoId,
                firstLinkDefinition.getLinkTypeId(), null);

        assertNotNull(linkDefinitions);
        assertEquals(linkDefinitions.getLinkTypeId(), firstLinkDefinition.getLinkTypeId());
    }
}
