package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class SetEntriesApiTest extends BaseTest {
    TagDefinitionsClient client;

    CompletableFuture<Entry> entry = null;

    @AfterEach
    void deleteEntry(){
        if(entry != null){
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            Integer num = entry.join().getId();
            repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId,num,body);
        }
    }

    @Test
    void setFields_Success(){
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> tagDefinitionsResponse = repositoryApiClient.getTagDefinitionsClient().getTagDefinitions(repoId,null,null,null,null,null,null,null,null);
        List<WTagInfo> tagDefinitions = tagDefinitionsResponse.join().getValue();
        assertNotNull(tagDefinitions);
        assertTrue(tagDefinitions.size() > 0);
        String tag = tagDefinitions.get(0).getName();
        PutTagRequest request = new PutTagRequest();
        request.addTagsItem(tag);
        RepositoryApiClient client2 = repositoryApiClient;
        entry = createEntry(client2,"RepositoryApiClientIntegrationTest Java SetTags",1,true);
        Integer num = entry.join().getId();
        CompletableFuture<ODataValueOfIListOfWTagInfo> assignTagsResponse = repositoryApiClient.getEntriesClient().assignTags(repoId,num,request);
        List<WTagInfo> tags = assignTagsResponse.join().getValue();
        assertNotNull(tags);
        assertTrue(Objects.equals(tag, tags.get(0).getName()));
    }

    @Test
    @Disabled("Not done yet")
    void setTemplates_Success(){
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId,null,null,null,null,null,null,null,null,null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        for(int i = 0; i < templateDefinitions.size();i++){
            repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId,templateDefinitions.get(i).getId(),null,null,null,null,null,null,null,null);
        }
    }
}
