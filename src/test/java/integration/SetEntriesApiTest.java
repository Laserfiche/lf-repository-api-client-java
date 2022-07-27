package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class SetEntriesApiTest extends BaseTest {
    RepositoryApiClient client = repositoryApiClient;

    RepositoryApiClient createEntryClient = repositoryApiClient;

    CompletableFuture<Entry> entry = null;

    @AfterEach
    void deleteEntry() {
        if (entry != null) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            Integer num = entry.join().getId();
            repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, num, body).join();
        }
        entry = null;
    }

    @Test
    void setTags_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> tagDefinitionsResponse = repositoryApiClient.getTagDefinitionsClient().getTagDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WTagInfo> tagDefinitions = tagDefinitionsResponse.join().getValue();
        assertNotNull(tagDefinitions);
        assertTrue(tagDefinitions.size() > 0);
        String tag = tagDefinitions.get(0).getName();
        PutTagRequest request = new PutTagRequest();
        request.addTagsItem(tag);
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetTags", 1, true);
        Integer num = entry.join().getId();
        CompletableFuture<ODataValueOfIListOfWTagInfo> assignTagsResponse = repositoryApiClient.getEntriesClient().assignTags(repoId, num, request);
        List<WTagInfo> tags = assignTagsResponse.join().getValue();
        assertNotNull(tags);
        assertEquals(tags.get(0).getName(), tag);
    }

    @Test
    void setTemplates_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinition.getId(), null, null, null, null, null, null, null, null);
            if (templateDefinitionsFieldsResponse.join().getValue() != null && allFalse(templateDefinitionsFieldsResponse.join().getValue()).get()) {
                template = templateDefinition;
                break;
            }
        }
        assertNotNull(template);
        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);
        CompletableFuture<Entry> setTemplateResponse = repositoryApiClient.getEntriesClient().writeTemplateValueToEntry(repoId, entry.get().getId(), request, null);
        assertNotNull(setTemplateResponse.join());
        assertEquals(setTemplateResponse.join().getTemplateName(), template.getName());
    }

    @Test
    void setFields_Success() throws ExecutionException, InterruptedException {
        WFieldInfo field = null;
        String fieldValue = "a";
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> fieldDefinitionsResponse = repositoryApiClient.getFieldDefinitionsClient().getFieldDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WFieldInfo> fieldDefinitions = fieldDefinitionsResponse.join().getValue();
        for (WFieldInfo fieldDefinition : fieldDefinitions) {
            if (fieldDefinition.getFieldType() == WFieldType.STRING && (fieldDefinition.getConstraint().equals("") || fieldDefinition.getConstraint() == null) && (fieldDefinition.getLength() >= 1)) {
                field = fieldDefinition;
                break;
            }
        }
        assertNotNull(field);
        ValueToUpdate value = new ValueToUpdate();
        value.setValue(fieldValue);
        value.setPosition(1);
        FieldToUpdate name = new FieldToUpdate();
        List<ValueToUpdate> values = new ArrayList<>();
        values.add(value);
        name.setValues(values);
        Map<String, FieldToUpdate> requestBody = new HashMap<>();
        requestBody.put(field.getName(), name);
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetFields", 1, true);
        Integer num = entry.join().getId();
        CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValuesResponse = repositoryApiClient.getEntriesClient().assignFieldValues(repoId, num, requestBody, null);
        List<FieldValue> fields = assignFieldValuesResponse.join().getValue();
        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals(fields.get(0).getFieldName(), field.getName());
    }

    @Test
    void removeTemplateFromEntryReturnEntry_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = client.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        for (WTemplateInfo templateDefinition : templateDefinitions) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = client.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinition.getId(), null, null, null, null, null, null, null, null);
            if (templateDefinitionsFieldsResponse.join().getValue() != null && allFalse(templateDefinitionsFieldsResponse.join().getValue()).get()) {
                template = templateDefinition;
                break;
            }
        }
        assertNotNull(template);
        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        entry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);
        CompletableFuture<Entry> writeTemplateValueToEntryResponse = client.getEntriesClient().writeTemplateValueToEntry(repoId, entry.join().getId(), request, null);
        assertNotNull(writeTemplateValueToEntryResponse);
        assertEquals(writeTemplateValueToEntryResponse.join().getTemplateName(), template.getName());
    }
}
