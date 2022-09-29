package integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

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
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> future = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo linkDefinitionsResponse = future.join();

        assertNotNull(linkDefinitionsResponse.getValue());
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> linkDefinitionsFuture = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = linkDefinitionsFuture.join();
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult.getValue().get(0);
        assertNotNull(firstLinkDefinition);

        CompletableFuture<EntryLinkTypeInfo> linkDefinitionFuture = client.getLinkDefinitionById(repoId, firstLinkDefinition.getLinkTypeId(), null);
        EntryLinkTypeInfo linkDefinitions = linkDefinitionFuture.join();
        assertNotNull(linkDefinitions);

        assertEquals(linkDefinitions.getLinkTypeId(), firstLinkDefinition.getLinkTypeId());
    }
}
