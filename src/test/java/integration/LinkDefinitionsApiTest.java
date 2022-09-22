package integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkDefinitionsApiTest extends BaseTest {
    LinkDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getLinkDefinitionsClient();
    }

    @Test
    void getLinkDefinitions_ReturnAllLinks() {
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> future = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo linkDefinitionsResponse = future.join();
        assertNotNull(linkDefinitionsResponse.value);
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> linkDefinitionsfuture = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = linkDefinitionsfuture.join();
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult.value.get(0);
        assertNotNull(firstLinkDefinition);
        CompletableFuture<EntryLinkTypeInfo> linkDefinitionFuture = client.getLinkDefinitionById(repoId, firstLinkDefinition.linkTypeId, null);
        EntryLinkTypeInfo linkDefinitions = linkDefinitionFuture.join();
        assertNotNull(linkDefinitions);
        assertEquals(linkDefinitions.linkTypeId, firstLinkDefinition.linkTypeId);
    }
}
