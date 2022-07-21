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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetEntriesApiTest extends BaseTest {
    RepositoryApiClient client = repositoryApiClient;

    RepositoryApiClient createEntryClient = repositoryApiClient;

    CompletableFuture<Entry> entry = null;

    @AfterEach
    void deleteEntry() {
        if (entry != null) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            Integer num = entry.join().getId();
            repositoryApiClient.getEntriesClient().deleteEntryInfo(repoId, num, body);
        }
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
        assertTrue(tags.get(0).getName().equals(tag));
    }

    @Test
    void setTemplates_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        for (int i = 0; i < templateDefinitions.size(); i++) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = repositoryApiClient.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinitions.get(i).getId(), null, null, null, null, null, null, null, null);
            if (templateDefinitionsFieldsResponse.join().getValue() != null && allFalse(templateDefinitionsFieldsResponse.join().getValue()).get()) {
                template = templateDefinitions.get(i);
                break;
            }
        }
        assertNotNull(template);
        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);
        CompletableFuture<Entry> setTemplateResponse = repositoryApiClient.getEntriesClient().writeTemplateValueToEntry(repoId, entry.get().getId(), request, null);
        assertNotNull(setTemplateResponse.join());
        assertTrue(setTemplateResponse.join().getTemplateName().equals(template.getName()));
    }

    @Test
    void setFields_Success() throws ExecutionException, InterruptedException {
        WFieldInfo field = null;
        String fieldValue = "a";
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> fieldDefinitionsResponse = repositoryApiClient.getFieldDefinitionsClient().getFieldDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WFieldInfo> fieldDefinitions = fieldDefinitionsResponse.join().getValue();
        for (int i = 0; i < fieldDefinitions.size(); i++) {
            if (fieldDefinitions.get(i).getFieldType() == WFieldType.STRING && (fieldDefinitions.get(i).getConstraint().equals("") || fieldDefinitions.get(i).getConstraint().equals(null)) && (fieldDefinitions.get(i).getLength() >= 1)) {
                field = fieldDefinitions.get(i);
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
        assertTrue(fields.size() == 1);
        assertTrue(fields.get(0).getFieldName().equals(field.getName()));
    }

    @Test
    void removeTemplateFromEntryReturnEntry_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = client.getTemplateDefinitionClient().getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().getValue();
        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);
        for (int i = 0; i < templateDefinitions.size(); i++) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = client.getTemplateDefinitionClient().getTemplateFieldDefinitions(repoId, templateDefinitions.get(i).getId(), null, null, null, null, null, null, null, null);
            if (templateDefinitionsFieldsResponse.join().getValue() != null && allFalse(templateDefinitionsFieldsResponse.join().getValue()).get()) {
                template = templateDefinitions.get(i);
                break;
            }
        }
        assertNotNull(template);
        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        entry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);
        CompletableFuture<Entry> writeTemplateValueToEntryResponse = client.getEntriesClient().writeTemplateValueToEntry(repoId, entry.join().getId(), request, null);
        assertNotNull(writeTemplateValueToEntryResponse);
        assertTrue(writeTemplateValueToEntryResponse.join().getTemplateName().equals(template.getName()));
    }
}
