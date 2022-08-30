package integration;

import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TagDefinitionsApiTest extends BaseTest {
    TagDefinitionsClient client;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient.getTagDefinitionsClient();
    }

    @Test
    void getTagDefinitions_Success() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);
    }

    @Test
    void getTagDefinitionById_Success() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagDefinitions(repoId, null, null, null, null, null, null, false)
                .join();

        assertNotNull(tagInfoList);

        WTagInfo tagInfo = client
                .getTagDefinitionById(repoId, tagInfoList.value.get(0).id, null, null)
                .join();

        assertNotNull(tagInfo);
    }
}
