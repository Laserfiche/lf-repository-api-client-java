package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
            Integer num = entry.join().id;
            repositoryApiClient
                    .getEntriesClient()
                    .deleteEntryInfo(repoId, num, body)
                    .join();
        }
        entry = null;
    }

    @Test
    void setTags_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> tagDefinitionsResponse = repositoryApiClient
                .getTagDefinitionsClient()
                .getTagDefinitions(repoId, null, null, null, null, null, null, null);
        List<WTagInfo> tagDefinitions = tagDefinitionsResponse.join().value;

        assertNotNull(tagDefinitions);
        assertTrue(tagDefinitions.size() > 0);

        String tag = tagDefinitions.get(0).name;
        PutTagRequest request = new PutTagRequest();
        request.tags.add(tag);
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetTags", 1, true);
        Integer num = entry.join().id;

        CompletableFuture<ODataValueOfIListOfWTagInfo> assignTagsResponse = repositoryApiClient
                .getEntriesClient()
                .assignTags(repoId, num, request);
        List<WTagInfo> tags = assignTagsResponse.join().value;

        assertNotNull(tags);
        assertEquals(tags.get(0).name, tag);
    }

    @Test
    void setTemplates_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;
        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().value;

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(repoId, templateDefinition.id, null, null, null, null, null, null,
                            null);
            if (templateDefinitionsFieldsResponse.join().value != null && allFalse(
                    templateDefinitionsFieldsResponse.join().value).get()) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.templateName = template.name;
        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);

        CompletableFuture<Entry> setTemplateResponse = repositoryApiClient
                .getEntriesClient()
                .writeTemplateValueToEntry(repoId, entry.get().id, request, null);

        assertNotNull(setTemplateResponse.join());
        assertEquals(setTemplateResponse.join().templateName, template.name);
    }

    @Test
    void setFields_Success() throws ExecutionException, InterruptedException {
        WFieldInfo field = null;
        String fieldValue = "a";

        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> fieldDefinitionsResponse = repositoryApiClient
                .getFieldDefinitionsClient()
                .getFieldDefinitions(repoId, null, null, null, null, null, null, null);
        List<WFieldInfo> fieldDefinitions = fieldDefinitionsResponse.join().value;
        for (WFieldInfo fieldDefinition : fieldDefinitions) {
            if (fieldDefinition.fieldType.equals(WFieldType.STRING) && (fieldDefinition.constraint.equals(
                    "") || fieldDefinition.constraint == null) && (fieldDefinition.length >= 1)) {
                field = fieldDefinition;
                break;
            }
        }

        assertNotNull(field);

        ValueToUpdate value = new ValueToUpdate();
        value.value = fieldValue;
        value.position = 1;

        FieldToUpdate name = new FieldToUpdate();

        List<ValueToUpdate> values = new ArrayList<>();

        values.add(value);

        name.values = values;

        entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetFields", 1, true);
        Integer num = entry.join().id;

        CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValuesResponse = repositoryApiClient
                .getEntriesClient()
                .assignFieldValues(repoId, num, name, null);
        List<FieldValue> fields = assignFieldValuesResponse.join().value;

        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals(fields.get(0).fieldName, field.name);
    }

    @Test
    void removeTemplateFromEntryReturnEntry_Success() throws ExecutionException, InterruptedException {
        WTemplateInfo template = null;

        CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> templateDefinitionsResponse = client
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(repoId, null, null, null, null, null, null, null, null);
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.join().value;

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> templateDefinitionsFieldsResponse = client
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(repoId, templateDefinition.id, null, null, null, null, null, null,
                            null);
            if (templateDefinitionsFieldsResponse.join().value != null && allFalse(
                    templateDefinitionsFieldsResponse.join().value).get()) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.templateName = template.name;

        entry = createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", 1, true);

        CompletableFuture<Entry> writeTemplateValueToEntryResponse = client
                .getEntriesClient()
                .writeTemplateValueToEntry(repoId, entry.join().id, request, null);

        assertNotNull(writeTemplateValueToEntryResponse);
        assertEquals(writeTemplateValueToEntryResponse.join().templateName, template.name);
    }
}
