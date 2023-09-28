package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SetEntriesApiTest extends BaseTest {
    static Entry testClassParentFolder;

    RepositoryApiClient client = repositoryApiClient;

    @BeforeAll
    static void classSetup() {
        String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
        testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
    }

    @AfterAll
    static void classCleanUp() throws InterruptedException {
        deleteEntry(testClassParentFolder.getId());
    }

    @Test
    void setTags_ReturnTags() {
        TagDefinitionCollectionResponse tagDefinitionsResponse = repositoryApiClient
                .getTagDefinitionsClient()
                .listTagDefinitions(new ParametersForListTagDefinitions().setRepositoryId(repositoryId));
        List<TagDefinition> tagDefinitions = tagDefinitionsResponse.getValue();

        assertNotNull(tagDefinitions);
        assertTrue(tagDefinitions.size() > 0);

        String tag = tagDefinitions.get(0).getName();
        SetTagsRequest request = new SetTagsRequest();
        request.setTags(new ArrayList<>());
        request.getTags().add(tag);
        Entry entry = createEntry(
                client, "RepositoryApiClientIntegrationTest Java SetTags", testClassParentFolder.getId(), true);
        Integer num = entry.getId();

        TagCollectionResponse assignTagsResponse = repositoryApiClient
                .getEntriesClient()
                .setTags(new ParametersForSetTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(num)
                        .setRequestBody(request));
        List<Tag> tags = assignTagsResponse.getValue();

        assertNotNull(tags);
        assertEquals(tags.get(0).getName(), tag);
    }

    @Test
    void setTemplates_ReturnTemplates() throws ExecutionException, InterruptedException {
        TemplateDefinition template = null;
        TemplateDefinitionCollectionResponse templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (TemplateDefinition templateDefinition : templateDefinitions) {
            TemplateFieldDefinitionCollectionResponse templateDefinitionsFieldsResponse = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                            .setRepositoryId(repositoryId)
                            .setTemplateId(templateDefinition.getId()));
            if (templateDefinitionsFieldsResponse.getValue() != null
                    && noRequiredFieldDefinitionsInTemplate(templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName(template.getName());
        Entry entry = createEntry(
                client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);

        Entry setTemplateResponse = repositoryApiClient
                .getEntriesClient()
                .setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entry.getId())
                        .setRequestBody(request));

        assertNotNull(setTemplateResponse);
        assertEquals(setTemplateResponse.getTemplateName(), template.getName());
    }

    @Test
    void setFields_ReturnFields() {
        FieldDefinition field = null;
        String fieldValue = "a";

        // Find a field definition that accepts String and has no constraint.
        FieldDefinitionCollectionResponse fieldDefinitionsResponse = repositoryApiClient
                .getFieldDefinitionsClient()
                .listFieldDefinitions(new ParametersForListFieldDefinitions().setRepositoryId(repositoryId));
        List<FieldDefinition> fieldDefinitions = fieldDefinitionsResponse.getValue();
        for (FieldDefinition fieldDefinition : fieldDefinitions) {
            if (fieldDefinition.getFieldType().equals(FieldType.STRING)
                    && nullOrEmpty(fieldDefinition.getConstraint())
                    && fieldDefinition.getLength() != null
                    && fieldDefinition.getLength() >= 1) {
                field = fieldDefinition;
                break;
            }
        }

        assertNotNull(field);

        // Create an entry and set a field using the definition we found earlier.
        FieldToUpdate fieldToUpdate = new FieldToUpdate();
        fieldToUpdate.setName(field.getName());
        List<String> values = new ArrayList<>();
        values.add(fieldValue);
        fieldToUpdate.setValues(values);
        List<FieldToUpdate> fieldsToUpdate = new ArrayList<>();
        fieldsToUpdate.add(fieldToUpdate);
        SetFieldsRequest request = new SetFieldsRequest();
        request.setFields(fieldsToUpdate);
        Entry entry = createEntry(
                client, "RepositoryApiClientIntegrationTest Java SetFields", testClassParentFolder.getId(), true);
        Integer entryId = entry.getId();

        FieldCollectionResponse assignFieldValuesResponse = repositoryApiClient
                .getEntriesClient()
                .setFields(new ParametersForSetFields()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entryId)
                        .setRequestBody(request));
        List<Field> fields = assignFieldValuesResponse.getValue();

        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals(fields.get(0).getName(), field.getName());
    }

    @Test
    void removeTemplateFromEntry_ReturnEntry() throws ExecutionException, InterruptedException {
        TemplateDefinition template = null;

        TemplateDefinitionCollectionResponse templateDefinitionsResponse = client.getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertFalse(templateDefinitions.isEmpty());

        for (TemplateDefinition templateDefinition : templateDefinitions) {
            TemplateFieldDefinitionCollectionResponse templateDefinitionsFieldsResponse =
                    client.getTemplateDefinitionClient()
                            .listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                                    .setRepositoryId(repositoryId)
                                    .setTemplateId(templateDefinition.getId()));
            if (templateDefinitionsFieldsResponse.getValue() != null
                    && noRequiredFieldDefinitionsInTemplate(templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName(template.getName());

        Entry entry = createEntry(
                client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);

        Entry writeTemplateValueToEntryResponse = client.getEntriesClient()
                .setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entry.getId())
                        .setRequestBody(request));

        assertNotNull(writeTemplateValueToEntryResponse);
        assertEquals(writeTemplateValueToEntryResponse.getTemplateName(), template.getName());
    }
}
