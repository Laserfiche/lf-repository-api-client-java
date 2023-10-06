package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.Tag;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import kong.unirest.HttpStatus;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class EntriesClientTest extends BaseTest {
    private EntriesClient client;

    private static Entry testClassParentFolder;

    private final String rootPath = "\\";

    private final String nonExistingPath = "\\Non Existing Path";

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
    }

    @BeforeAll
    static void classSetup() {
        String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
        testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
    }

    @AfterAll
    static void classCleanUp() {
        deleteEntry(testClassParentFolder.getId());
    }

    @Test
    void createEntryCanCreateFolder() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.FOLDER);
        request.setName(newEntryName);
        request.setAutoRename(true);

        Entry createdEntry = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(createdEntry);

        assertEquals(testClassParentFolder.getId(), createdEntry.getParentId());
        assertEquals(createdEntry.getEntryType(), EntryType.FOLDER);
        assertEquals(Folder.class.getName(), createdEntry.getClass().getName());
    }

    @Test
    void createEntryCanCreateShortcut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(repositoryApiClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);

        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        request.setAutoRename(true);

        Entry shortCut = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(shortCut);
        assertEquals(testClassParentFolder.getId(), shortCut.getParentId());
        assertEquals(EntryType.SHORTCUT, shortCut.getEntryType());
        assertEquals(shortCut.getClass().getName(), Shortcut.class.getName());
    }

    @Test
    void startCopyEntryCanCopyFolder() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(repositoryApiClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);
        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        StartCopyEntryRequest copyAsyncRequest = new StartCopyEntryRequest();
        copyAsyncRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyAsyncRequest.setSourceId(targetEntry.getId());
        copyAsyncRequest.setAutoRename(true);

        StartTaskResponse taskResponse = client.startCopyEntry(new ParametersForStartCopyEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyAsyncRequest));
        String taskId = taskResponse.getTaskId();
        waitUntilTaskEnds(taskId);

        TaskCollectionResponse response = repositoryApiClient
                .getTasksClient()
                .listTasks(new ParametersForListTasks()
                        .setRepositoryId(repositoryId)
                        .setTaskIds(taskId));

        assertEquals(1, response.getValue().size());
        assertEquals(response.getValue().get(0).getStatus(), TaskStatus.COMPLETED);
    }

    @Test
    void copyEntryCanCreateShortcut() {
        String newEntryName = "RepositoryApiClientIntegrationTest Java CreateFolder";

        Entry targetEntry = createEntry(repositoryApiClient, newEntryName, testClassParentFolder.getId(), true);
        assertNotNull(targetEntry);
        assertEquals(targetEntry.getParentId(), testClassParentFolder.getId());
        assertEquals(targetEntry.getEntryType(), EntryType.FOLDER);

        newEntryName = "RepositoryApiClientIntegrationTest Java CreateShortcut";
        CreateEntryRequest request = new CreateEntryRequest();
        request.setEntryType(CreateEntryRequestEntryType.SHORTCUT);
        request.setName(newEntryName);
        request.setTargetId(targetEntry.getId());
        request.setAutoRename(true);

        Entry createOrCopyEntryResponse = client.createEntry(new ParametersForCreateEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(request));

        assertNotNull(createOrCopyEntryResponse);
        assertEquals(testClassParentFolder.getId(), createOrCopyEntryResponse.getParentId());
        assertEquals(createOrCopyEntryResponse.getEntryType(), EntryType.SHORTCUT);

        CopyEntryRequest copyRequest = new CopyEntryRequest();
        copyRequest.setName("RepositoryApiClientIntegrationTest Java CopiedEntry");
        copyRequest.setSourceId(createOrCopyEntryResponse.getId());
        copyRequest.setAutoRename(true);
        Entry copyEntryResponse = client.copyEntry(new ParametersForCopyEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyRequest));

        assertEquals(copyEntryResponse.getParentId(), testClassParentFolder.getId());
        assertEquals(EntryType.SHORTCUT, copyEntryResponse.getEntryType());
        assertEquals(copyEntryResponse.getEntryType(), createOrCopyEntryResponse.getEntryType());
    }

    @Test
    void copyEntryDoesNotSupportCopyingFolder() {
        Entry parentFolder = createEntry(
                repositoryApiClient,
                "RepositoryApiClientIntegrationTest Java ParentFolder",
                testClassParentFolder.getId(),
                true);

        Entry childFolder = createEntry(
                repositoryApiClient,
                "RepositoryApiClientIntegrationTest Java ChildFolder",
                testClassParentFolder.getId(),
                true);

        CopyEntryRequest request = new CopyEntryRequest();
        request.setSourceId(parentFolder.getId());
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");
        request.setAutoRename(true);

        ApiException exception = assertThrows(ApiException.class, () -> {
            client.copyEntry(new ParametersForCopyEntry()
                    .setRepositoryId(repositoryId)
                    .setEntryId(childFolder.getId())
                    .setRequestBody(request));
        });

        assertEquals(400, exception.getStatusCode());
        assertEquals(exception.getStatusCode(), exception.getProblemDetails().getStatus());
    }

    @Test
    void copyEntryThrowsExceptionForInvalidRepositoryId() {
        CopyEntryRequest request = new CopyEntryRequest();
        // Since repositoryId is invalid, the entryId values are not used. So, we can use fake entryIds.
        int fakeParentId = -1;
        int fakeChildId = -2;
        request.setSourceId(fakeParentId);
        request.setName("RepositoryApiClientIntegrationTest Java MovedFolder");
        request.setAutoRename(true);

        String invalidRepoId = String.format("%s-%s", repositoryId, repositoryId);
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.copyEntry(new ParametersForCopyEntry()
                        .setRepositoryId(invalidRepoId)
                        .setEntryId(fakeChildId)
                        .setRequestBody(request)));

        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertEquals(
                "Error: Repository with the given Id not found or no connection could be made.",
                apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }

    @Test
    void getEntryCanReturnRootFolder() {
        Entry entry = client.getEntry(
                new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(1));

        assertNotNull(entry);
        assertTrue(entry instanceof Folder); // We know the root folder is of type Folder.
    }

    @Test
    void getEntryReturnsResultAsGenericEntryWhenEntryTypeNotSpecified() {
        Entry entry = client.getEntry(new ParametersForGetEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setSelect("name"));

        assertNotNull(entry);
        // When no type information, the data is deserialized to Entry.
        // Use separate assertions to know which one fails, if assertion fails.
        assertFalse(entry instanceof Folder);
        assertFalse(entry instanceof Shortcut);
        assertFalse(entry instanceof Document);
        assertFalse(entry instanceof RecordSeries);
    }

    @Test
    void listEntriesWorksAndRespectsMaxPageSizeAndRespectsSpecifiedFields() {
        int maxPageSize = 2;
        String[] fieldNames = {"Sender", "Subject"};
        EntryCollectionResponse collectionResponse = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setFields(fieldNames)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertEquals(maxPageSize, collectionResponse.getValue().size());

        for (Entry entry : collectionResponse.getValue()) {
            switch (entry.getEntryType()) {
                case FOLDER:
                    assertTrue(entry instanceof Folder);
                    break;
                case SHORTCUT:
                    assertTrue(entry instanceof Shortcut);
                    break;
                case DOCUMENT:
                    assertTrue(entry instanceof Document);
                    break;
                case RECORD_SERIES:
                    assertTrue(entry instanceof RecordSeries);
                    break;
                default:
                    fail("This should not be reached.");
            }

            // Verify that the passed fieldNames is respected
            int numberOfReturnedFields = (int) entry.getFields().stream()
                    .filter(entryFieldValue -> entryFieldValue.getName().equalsIgnoreCase(fieldNames[0])
                            || entryFieldValue.getName().equalsIgnoreCase(fieldNames[1]))
                    .count();
            assertEquals(fieldNames.length, numberOfReturnedFields);
        }
    }

    @Test
    void listEntriesNextLinkWorks() {
        int maxPageSize = 2;
        EntryCollectionResponse collectionResponse = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertEquals(maxPageSize, collectionResponse.getValue().size());

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        EntryCollectionResponse nextLinkCollectionResponse = client.listEntriesNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listEntriesForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<EntryCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listEntriesForEach(
                callback,
                maxPageSize,
                new ParametersForListEntries().setRepositoryId(repositoryId).setEntryId(1));
        assertTrue(pageCount.get() > 1);
    }

    @Test
    void listFieldsNextLinkWorksAndRespectsMaxPageSize() {
        int maxPageSize = 2;
        FieldCollectionResponse collectionResponse = client.listFields(new ParametersForListFields()
                .setRepositoryId(repositoryId)
                .setEntryId(testFolderEntryId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(collectionResponse);

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        FieldCollectionResponse nextLinkCollectionResponse = client.listFieldsNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listFieldsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<FieldCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listFieldsForEach(
                callback,
                maxPageSize,
                new ParametersForListFields().setRepositoryId(repositoryId).setEntryId(testFolderEntryId));
    }

    @Test
    void listLinksNextLinkWorks() {
        int maxPageSize = 2;
        LinkCollectionResponse collectionResponse =
                client.listLinks(new ParametersForListLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(testFolderEntryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(collectionResponse);

        if (collectionResponse.getValue().isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        LinkCollectionResponse nextLinkCollectionResponse =
                client.listLinksNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listLinksForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<LinkCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listLinksForEach(
                callback,
                maxPageSize,
                new ParametersForListLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(testFolderEntryId));
    }

    @Test
    void startDeleteEntryCanDeleteFolder() {
        Entry entryToDelete =
                createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true);

        StartTaskResponse deleteEntryResponse = client.startDeleteEntry(new ParametersForStartDeleteEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(entryToDelete.getId())
                .setRequestBody(new StartDeleteEntryRequest()));
        String taskId = deleteEntryResponse.getTaskId();
        assertNotNull(taskId);

        waitUntilTaskEnds(deleteEntryResponse.getTaskId());

        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.getEntry(new ParametersForGetEntry().setRepositoryId(repositoryId)
                        .setEntryId(entryToDelete.getId())));

        assertNotNull(apiException);
        assertEquals(HttpStatus.NOT_FOUND, apiException.getStatusCode());
    }

    @Test
    void listTagsNextLinkWorks() {
        int maxPageSize = 2;
        TagCollectionResponse collectionResponse =
                client.listTags(new ParametersForListTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(testFolderEntryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(collectionResponse);
        assertFalse(collectionResponse.getValue().isEmpty());

        String nextLink = collectionResponse.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(collectionResponse.getValue().size() <= maxPageSize);

        TagCollectionResponse nextLinkCollectionResponse =
                client.listTagsNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkCollectionResponse);
        assertTrue(nextLinkCollectionResponse.getValue().size() <= maxPageSize);
    }

    @Test
    void listTagsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 2;
        Function<TagCollectionResponse, Boolean> callback = collectionResponse -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertFalse(collectionResponse.getValue().isEmpty());
                assertTrue(collectionResponse.getValue().size() <= maxPageSize);
                return collectionResponse.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listTagsForEach(
                callback,
                maxPageSize,
                new ParametersForListTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(testFolderEntryId));
    }

    @Test
    void listDynamicFieldValuesWorks() {
        TemplateDefinitionCollectionResponse templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertFalse(templateDefinitions.isEmpty());

        ListDynamicFieldValuesRequest request = new ListDynamicFieldValuesRequest();
        request.setTemplateId(templateDefinitions.get(0).getId());

        Map<String, String[]> dynamicFieldValueResponse =
                client.listDynamicFieldValues(new ParametersForListDynamicFieldValues()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1)
                        .setRequestBody(request));
        assertNotNull(dynamicFieldValueResponse);
    }

    @Test
    void getEntryByPathCanReturnRootFolder() {
        GetEntryByPathResponse entry = client
                .getEntryByPath(new ParametersForGetEntryByPath()
                        .setRepositoryId(repositoryId)
                        .setFullPath(rootPath));

        assertNotNull(entry);
        assertEquals(1, entry.getEntry().getId());
        assertEquals(rootPath, entry.getEntry().getFullPath());
        assertEquals("Folder", entry.getEntry().getEntryType().toString());
        assertNull(entry.getAncestorEntry());
    }

    @Test
    void getEntryByPathReturnsRootFolderForInvalidPath() {
        GetEntryByPathResponse entry = client
                .getEntryByPath(new ParametersForGetEntryByPath()
                        .setRepositoryId(repositoryId)
                        .setFullPath(nonExistingPath)
                        .setFallbackToClosestAncestor(true));

        assertNotNull(entry);
        assertEquals(1, entry.getAncestorEntry().getId());
        assertEquals(rootPath, entry.getAncestorEntry().getFullPath());
        assertEquals("Folder", entry.getAncestorEntry().getEntryType().toString());
        assertNull(entry.getEntry());
    }

    @Test
    void listEntriesReturnProblemDetailsWhenExceptionThrown() {
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            client.listEntries(new ParametersForListEntries()
                    .setRepositoryId(repositoryId)
                    .setEntryId(-1)
                    .setPrefer("maxpagesize=100"));
        });
        assertNotNull(apiException);
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
        assertNotNull(problemDetails.getTitle());
        assertNotNull(problemDetails.getType());
        assertNotNull(problemDetails.getInstance());
        assertNotNull(problemDetails.getStatus());
    }

    @Test
    void setTemplateThrowsExceptionForInvalidTemplate() {
        // Since template is not actually set, it's safe to use root folder for the test.
        int parentFolderId = 1;
        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName("fake_template");
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(parentFolderId)
                        .setRequestBody(request)));
        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertTrue(apiException.getMessage().startsWith("Template not found."), apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }

    @Test
    void setTemplateWorks() {
        TemplateDefinition template = null;
        TemplateDefinitionCollectionResponse collectionResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = collectionResponse.getValue();

        assertNotNull(templateDefinitions);
        assertFalse(templateDefinitions.isEmpty());

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
                repositoryApiClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);

        Entry setTemplateResponse = client
                .setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entry.getId())
                        .setRequestBody(request));

        assertNotNull(setTemplateResponse);
        assertEquals(setTemplateResponse.getTemplateName(), template.getName());
    }

    @Test
    void setFieldsWorks() {
        FieldDefinition field = null;
        String fieldValue = "a";

        // Find a field definition that accepts String and has no constraint.
        FieldDefinitionCollectionResponse collectionResponse = repositoryApiClient
                .getFieldDefinitionsClient()
                .listFieldDefinitions(new ParametersForListFieldDefinitions().setRepositoryId(repositoryId));
        List<FieldDefinition> fieldDefinitions = collectionResponse.getValue();
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
                repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetFields", testClassParentFolder.getId(), true);
        Integer entryId = entry.getId();

        FieldCollectionResponse fieldCollectionResponse = client
                .setFields(new ParametersForSetFields()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entryId)
                        .setRequestBody(request));
        List<Field> fields = fieldCollectionResponse.getValue();

        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals(fields.get(0).getName(), field.getName());
    }

    @Test
    void removeTemplateWorks() {
        TemplateDefinition template = null;

        TemplateDefinitionCollectionResponse collectionResponse = repositoryApiClient.getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = collectionResponse.getValue();

        assertNotNull(templateDefinitions);
        assertFalse(templateDefinitions.isEmpty());

        for (TemplateDefinition templateDefinition : templateDefinitions) {
            TemplateFieldDefinitionCollectionResponse fieldCollectionResponse =
                    repositoryApiClient.getTemplateDefinitionClient()
                            .listTemplateFieldDefinitionsByTemplateId(new ParametersForListTemplateFieldDefinitionsByTemplateId()
                                    .setRepositoryId(repositoryId)
                                    .setTemplateId(templateDefinition.getId()));
            if (fieldCollectionResponse.getValue() != null
                    && noRequiredFieldDefinitionsInTemplate(fieldCollectionResponse.getValue())) {
                template = templateDefinition;
                break;
            }
        }

        assertNotNull(template);

        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName(template.getName());

        Entry entry = createEntry(
                repositoryApiClient, "RepositoryApiClientIntegrationTest Java DeleteTemplate", testClassParentFolder.getId(), true);
        entry = client
                .setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entry.getId())
                        .setRequestBody(request));

        assertEquals(template.getName(), entry.getTemplateName());

        entry = client
                .removeTemplate(new ParametersForRemoveTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(entry.getId()));

        assertEquals(0, entry.getTemplateId());
        assertEquals("", entry.getTemplateName());
    }

    @Test
    void setLinksWorks() {
        Entry sourceEntry = createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetLinks Source", testClassParentFolder.getId(), true);
        Entry targetEntry = createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetLinks Target", testClassParentFolder.getId(), true);

        LinkToUpdate link = new LinkToUpdate();
        link.setOtherEntryId(targetEntry.getId());
        link.setLinkDefinitionId(1);
        List<LinkToUpdate> links = new ArrayList<LinkToUpdate>();
        links.add(link);
        SetLinksRequest request = new SetLinksRequest();
        request.setLinks(links);
        LinkCollectionResponse collectionResponse = client
                .setLinks(new ParametersForSetLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(sourceEntry.getId())
                        .setRequestBody(request));
        List<Link> resultLinks = collectionResponse.getValue();
        assertNotNull(resultLinks);
        assertEquals(links.size(), resultLinks.size());
        assertEquals(sourceEntry.getId(), resultLinks.get(0).getSourceId());
        assertEquals(targetEntry.getId(), resultLinks.get(0).getTargetId());
    }
}
