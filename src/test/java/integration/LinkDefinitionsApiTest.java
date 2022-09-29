package integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
<<<<<<< HEAD
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
=======
>>>>>>> 1.x
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
<<<<<<< HEAD
        assertNotNull(linkDefinitionsResponse.value);
=======

        assertNotNull(linkDefinitionsResponse.getValue());
>>>>>>> 1.x
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
<<<<<<< HEAD
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> linkDefinitionsfuture = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = linkDefinitionsfuture.join();
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult.value.get(0);
        assertNotNull(firstLinkDefinition);
        CompletableFuture<EntryLinkTypeInfo> linkDefinitionFuture = client.getLinkDefinitionById(repoId, firstLinkDefinition.linkTypeId, null);
        EntryLinkTypeInfo linkDefinitions = linkDefinitionFuture.join();
        assertNotNull(linkDefinitions);
        assertEquals(linkDefinitions.linkTypeId, firstLinkDefinition.linkTypeId);
=======
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> linkDefinitionsFuture = client.getLinkDefinitions(repoId, null, null, null, null, null, null);
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = linkDefinitionsFuture.join();
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult.getValue().get(0);
        assertNotNull(firstLinkDefinition);

        CompletableFuture<EntryLinkTypeInfo> linkDefinitionFuture = client.getLinkDefinitionById(repoId, firstLinkDefinition.getLinkTypeId(), null);
        EntryLinkTypeInfo linkDefinitions = linkDefinitionFuture.join();
        assertNotNull(linkDefinitions);

        assertEquals(linkDefinitions.getLinkTypeId(), firstLinkDefinition.getLinkTypeId());
>>>>>>> 1.x
    }
}
