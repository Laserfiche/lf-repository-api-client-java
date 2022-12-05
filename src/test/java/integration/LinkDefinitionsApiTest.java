package integration;

import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitions;
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
        ODataValueContextOfIListOfEntryLinkTypeInfo linkDefinitionsResponse = client.getLinkDefinitions(
                new ParametersForGetLinkDefinitions().setRepoId(repoId));

        assertNotNull(linkDefinitionsResponse.getValue());
    }

    @Test
    void getLinkDefinitionsById_ReturnLinkDefinition() {
        ODataValueContextOfIListOfEntryLinkTypeInfo allLinkDefinitionsResult = client.getLinkDefinitions(
                new ParametersForGetLinkDefinitions().setRepoId(repoId));
        EntryLinkTypeInfo firstLinkDefinition = allLinkDefinitionsResult
                .getValue()
                .get(0);
        assertNotNull(firstLinkDefinition);

        EntryLinkTypeInfo linkDefinitions = client.getLinkDefinitionById(
                new ParametersForGetLinkDefinitionById()
                        .setRepoId(repoId)
                        .setLinkTypeId(firstLinkDefinition.getLinkTypeId()));

        assertNotNull(linkDefinitions);
        assertEquals(linkDefinitions.getLinkTypeId(), firstLinkDefinition.getLinkTypeId());
    }
}
