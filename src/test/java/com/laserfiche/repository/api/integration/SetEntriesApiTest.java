package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

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
        ODataValueContextOfIListOfWTagInfo tagDefinitionsResponse = repositoryApiClient
                .getTagDefinitionsClient()
                .getTagDefinitions(new ParametersForGetTagDefinitions().setRepoId(repositoryId));
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
        Entry entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetTags", testClassParentFolder.getId(), true);
        Integer num = entry.getId();

        ODataValueOfIListOfWTagInfo assignTagsResponse = repositoryApiClient
                .getEntriesClient()
                .assignTags(new ParametersForAssignTags()
                        .setRepoId(repositoryId)
                        .setEntryId(num)
                        .setRequestBody(request));
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
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionsFieldsResponse = repositoryApiClient
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(new ParametersForGetTemplateFieldDefinitions()
                            .setRepoId(repositoryId)
                            .setTemplateId(templateDefinition.getId()));
            if (templateDefinitionsFieldsResponse
                    .getValue() != null && noRequiredFieldDefinitionsInTemplate(templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());
        Entry entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);

        Entry setTemplateResponse = repositoryApiClient
                .getEntriesClient()
                .writeTemplateValueToEntry(new ParametersForWriteTemplateValueToEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(entry
                                .getId())
                        .setRequestBody(request));

        assertNotNull(setTemplateResponse);
        assertEquals(setTemplateResponse
                .getTemplateName(), template.getName());
    }

    @Test
    void setFields_ReturnFields() {
        WFieldInfo field = null;
        String fieldValue = "a";

        // Find a field definition that accepts String and has no constraint.
        ODataValueContextOfIListOfWFieldInfo fieldDefinitionsResponse = repositoryApiClient
                .getFieldDefinitionsClient()
                .getFieldDefinitions(new ParametersForGetFieldDefinitions().setRepoId(repositoryId));
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

        Entry entry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetFields", testClassParentFolder.getId(), true);
        Integer entryId = entry
                .getId();

        ODataValueOfIListOfFieldValue assignFieldValuesResponse = repositoryApiClient
                .getEntriesClient()
                .assignFieldValues(new ParametersForAssignFieldValues()
                        .setRepoId(repositoryId)
                        .setEntryId(entryId)
                        .setRequestBody(requestBody));
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
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repositoryId));
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        for (WTemplateInfo templateDefinition : templateDefinitions) {
            ODataValueContextOfIListOfTemplateFieldInfo templateDefinitionsFieldsResponse = client
                    .getTemplateDefinitionClient()
                    .getTemplateFieldDefinitions(new ParametersForGetTemplateFieldDefinitions()
                            .setRepoId(repositoryId)
                            .setTemplateId(templateDefinition.getId()));
            if (templateDefinitionsFieldsResponse
                    .getValue() != null && noRequiredFieldDefinitionsInTemplate(
                    templateDefinitionsFieldsResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName(template.getName());

        Entry entry = createEntry(client, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);

        Entry writeTemplateValueToEntryResponse = client
                .getEntriesClient()
                .writeTemplateValueToEntry(new ParametersForWriteTemplateValueToEntry()
                        .setRepoId(repositoryId)
                        .setEntryId(entry
                                .getId())
                        .setRequestBody(request));

        assertNotNull(writeTemplateValueToEntryResponse);
        assertEquals(writeTemplateValueToEntryResponse
                .getTemplateName(), template.getName());
    }
}
