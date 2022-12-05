package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class SetEntriesApiTest extends BaseTest {
    RepositoryApiClient client = repositoryApiClient;

    RepositoryApiClient createEntryClient = repositoryApiClient;

    Entry entry = null;

    @AfterEach
    void deleteEntry() {
        if (entry != null) {
            DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
            Integer num = entry
                    .getId();
            repositoryApiClient
                    .getEntriesClient()
                    .deleteEntryInfo(repoId, num, body);
        }
        entry = null;
    }

    @Test
    void setTags_ReturnTags() {
        ODataValueContextOfIListOfWTagInfo tagDefinitionsResponse = repositoryApiClient
                .getTagDefinitionsClient()
                .getTagDefinitions(repoId, null, null, null, null, null, null, null);
        List<WTagInfo> tagDefinitions = tagDefinitionsResponse.getValue();

        assertNotNull(tagDefinitions);
        assertTrue(tagDefinitions.size() > 0);

        String tag = tagDefinitions
                .get(0)
                .getName();
        PutTagRequest request = new PutTagRequest();
        request.setTags(new ArrayList<>());
        request
                .getTags()
                .add(tag);
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetTags", 1, true);
        Integer num = entry.getId();

        ODataValueOfIListOfWTagInfo assignTagsResponse = repositoryApiClient
                .getEntriesClient()
                .assignTags(repoId, num, request);
        List<WTagInfo> tags = assignTagsResponse.getValue();

        assertNotNull(tags);
        assertEquals(tags
                .get(0)
                .getName(), tag);
    }

    @Test
    void setTemplates_ReturnTemplates() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionsFieldsResponse = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(repoId, templateDefinition.getId(), null, null, null, null, null, null,
                            null);
            if (templateDefinitionsFieldsResponse
                    .getValue() != null && allFalse(templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);

        Entry setTemplateResponse = repositoryApiClient
                .getEntriesClient()
                .writeTemplateValueToEntry(repoId, entry
                        .getId(), request, null);

        assertNotNull(setTemplateResponse);
        assertEquals(setTemplateResponse
                .getTemplateName(), template.getName());
    }

    @Test
    void setFields_ReturnFields() {
        WFieldInfo field = null;
        String fieldValue = "a";

        // Find a field definition that accepts String and has constraint.
        ODataValueContextOfIListOfWFieldInfo fieldDefinitionsResponse = repositoryApiClient
                .getFieldDefinitionsClient()
                .getFieldDefinitions(repoId, null, null, null, null, null, null, null);
        List<WFieldInfo> fieldDefinitions = fieldDefinitionsResponse.getValue();
        for (WFieldInfo fieldDefinition : fieldDefinitions) {
            if (fieldDefinition
                    .getFieldType()
                    .equals(WFieldType.STRING) &&
                    nullOrEmpty(fieldDefinition.getConstraint()) &&
                    fieldDefinition.getLength() != null &&
                    fieldDefinition.getLength() >= 1) {
                field = fieldDefinition;
                break;
            }
        }

        assertNotNull(field);

        // Create an entry and set a field using the definition we found earlier.

        Map<String, FieldToUpdate> requestBody = new HashMap<>();
        FieldToUpdate fieldToUpdate = new FieldToUpdate();
        requestBody.put(field.getName(), fieldToUpdate);

        fieldToUpdate.setValues(new ArrayList<>());
        ValueToUpdate valueToUpdate = new ValueToUpdate();
        fieldToUpdate
                .getValues()
                .add(valueToUpdate);

        valueToUpdate.setPosition(1);
        valueToUpdate.setValue(fieldValue);

        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetFields", 1, true);
        Integer entryId = entry
                .getId();

        ODataValueOfIListOfFieldValue assignFieldValuesResponse = repositoryApiClient
                .getEntriesClient()
                .assignFieldValues(repoId, entryId, requestBody, null);
        List<FieldValue> fields = assignFieldValuesResponse
                .getValue();

        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals(fields
                .get(0)
                .getFieldName(), field.getName());
    }

    @Test
    void removeTemplateFromEntry_ReturnEntry() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;

        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = client
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionsFieldsResponse = client
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(repoId, templateDefinition.getId(), null, null, null, null, null, null,
                            null);
            if (templateDefinitionsFieldsResponse
                    .getValue() != null && allFalse(
                    templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());

        entry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);

        Entry writeTemplateValueToEntryResponse = client
                .getEntriesClient()
                .writeTemplateValueToEntry(repoId, entry
                        .getId(), request, null);

        assertNotNull(writeTemplateValueToEntryResponse);
        assertEquals(writeTemplateValueToEntryResponse
                .getTemplateName(), template.getName());
    }
}
