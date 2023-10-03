package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.impl.model.Tag;
import com.laserfiche.repository.api.clients.params.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import kong.unirest.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;

class EntriesClientTest extends BaseTest {
    private EntriesClient client;

    private static Entry testClassParentFolder;

    private String rootPath = "\\";

    private String nonExistingPath = "\\Non Existing Path";

    private List<Entry> createdEntries = new ArrayList<>();

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
    }

    @BeforeAll
    static void classSetup() {
        String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
        testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
    }

    @AfterEach
    void perTestCleanUp() {
        deleteEntries(createdEntries);
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

        StartTaskResponse copyEntryResponse = client.startCopyEntry(new ParametersForStartCopyEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(copyAsyncRequest));
        String taskId = copyEntryResponse.getTaskId();
        waitUntilTaskEnds(taskId, Duration.ofMillis(100));

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

        String invalidRepoId = String.format("%s-%s", repositoryId, repositoryId);
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.copyEntry(new ParametersForCopyEntry()
                        .setRepositoryId(invalidRepoId)
                        .setEntryId(childFolder.getId())
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
    void listEntriesWorksAndRespectsMaxPageSize() {
        int maxPageSize = 10;
        EntryCollectionResponse entries = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertEquals(maxPageSize, entries.getValue().size());

        for (Entry entry : entries.getValue()) {
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
                    fail("This should not happen.");
            }
        }
    }

    @Test
    void listEntriesNextLinkWorks() {
        int maxPageSize = 10;
        EntryCollectionResponse entryList = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertEquals(maxPageSize, entryList.getValue().size());

        String nextLink = entryList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(entryList.getValue().size() <= maxPageSize);

        EntryCollectionResponse nextLinkResult = client.listEntriesNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listEntriesForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 20;
        int maxPageSize = 5;
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
    void listFieldsWorks() {
        FieldCollectionResponse fieldValueList = client.listFields(
                new ParametersForListFields().setRepositoryId(repositoryId).setEntryId(1));

        assertNotNull(fieldValueList);
        assertFalse(fieldValueList.getValue().isEmpty());
    }

    @Test
    void listLinksWorks() {
        LinkCollectionResponse linkInfoList =
                client.listLinks(new ParametersForListLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1));

        assertNotNull(linkInfoList);
    }

    @Test
    void listFieldsNextLinkWorksAndRespectsMaxPageSize() {
        int maxPageSize = 1;
        FieldCollectionResponse fieldValueList = client.listFields(new ParametersForListFields()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(fieldValueList);

        String nextLink = fieldValueList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(fieldValueList.getValue().size() <= maxPageSize);

        FieldCollectionResponse nextLinkResult = client.listFieldsNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listFieldsForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 5;
        int maxPageSize = 1;
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
                new ParametersForListFields().setRepositoryId(repositoryId).setEntryId(1));
    }

    @Test
    void listLinksNextLinkWorks() {
        int maxPageSize = 1;
        LinkCollectionResponse linkInfoList =
                client.listLinks(new ParametersForListLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(linkInfoList);

        if (linkInfoList.getValue().isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = linkInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(linkInfoList.getValue().size() <= maxPageSize);

        LinkCollectionResponse nextLinkResult =
                client.listLinksNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listLinksForEachWorks() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 5;
        int maxPageSize = 1;
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
                        .setEntryId(1));
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

        waitUntilTaskEnds(deleteEntryResponse.getTaskId(), Duration.ofMillis(100));

        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.getEntry(new ParametersForGetEntry().setRepositoryId(repositoryId)
                        .setEntryId(entryToDelete.getId())));

        assertNotNull(apiException);
        assertEquals(HttpStatus.NOT_FOUND, apiException.getStatusCode());
    }

    @Test
    void listTagsNextLinkWorks() {
        int maxPageSize = 1;
        TagCollectionResponse tagInfoList =
                client.listTags(new ParametersForListTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(tagInfoList);

        if (tagInfoList.getValue().isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = tagInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList.getValue().size() <= maxPageSize);

        TagCollectionResponse nextLinkResult =
                client.listTagsNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void listTagsForEachWorks() {
        int assignedTags = assignTagsToEntry(1);
        assertTrue(assignedTags > 0);
        try {
            AtomicInteger pageCount = new AtomicInteger();
            int maxPages = 5;
            int maxPageSize = 1;
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
                            .setEntryId(1));
        } finally {
            removeTagsFromEntry(1);
        }
    }

    private void removeTagsFromEntry(int entryId) {
        SetTagsRequest requestBody = new SetTagsRequest();
        requestBody.setTags(new ArrayList<>());
        TagCollectionResponse tagCollectionResponse = client.setTags(new ParametersForSetTags()
                .setRepositoryId(repositoryId).setEntryId(entryId)
                .setRequestBody(requestBody));
        assertTrue(tagCollectionResponse.getValue().isEmpty());
    }

    private int assignTagsToEntry(int entryId) {
        TagDefinitionsClient tagDefinitionsClient = repositoryApiClient.getTagDefinitionsClient();
        TagDefinitionCollectionResponse tagDefinitions = tagDefinitionsClient.listTagDefinitions(new ParametersForListTagDefinitions()
                .setRepositoryId(repositoryId).setTop(100));

        List<String> tags = new ArrayList<>();
        for (TagDefinition tagDefinition : tagDefinitions.getValue()) {
            tags.add(tagDefinition.getName());
        }
        SetTagsRequest requestBody = new SetTagsRequest();
        requestBody.setTags(tags);
        TagCollectionResponse tagCollectionResponse = client.setTags(new ParametersForSetTags()
                .setRepositoryId(repositoryId).setEntryId(entryId)
                .setRequestBody(requestBody));
        return tagCollectionResponse.getValue().size();
    }

    @Test
    void listTagsWorks() {
        TagCollectionResponse tagInfoList =
                client.listTags(new ParametersForListTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1));

        assertNotNull(tagInfoList);
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
    void listEntriesWorksAndRespectsSpecifiedFields() {
        String[] fieldNames = {"Sender", "Subject"};
        EntryCollectionResponse entries = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setFields(fieldNames)
                .setPrefer("maxpagesize=5"));
        assertNotNull(entries);
        for (Entry entry : entries.getValue()) {
            int numberOfReturnedFields = (int) entry.getFields().stream()
                    .filter(entryFieldValue -> entryFieldValue.getName().equalsIgnoreCase(fieldNames[0])
                            || entryFieldValue.getName().equalsIgnoreCase(fieldNames[1]))
                    .count();
            assertEquals(fieldNames.length, numberOfReturnedFields);
        }
    }

    @Test
    void setTemplateThrowsExceptionForInvalidTemplate() {
        Entry parentFolder = createEntry(repositoryApiClient, "EntriesTest", 1, true);
        createdEntries.add(parentFolder);

        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName("fake_template");
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.setTemplate(new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(parentFolder.getId())
                        .setRequestBody(request)));
        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertTrue(apiException.getMessage().startsWith("Template not found."), apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }

    @Test
    void setTagsWorks() {
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
                repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetTags", testClassParentFolder.getId(), true);
        Integer num = entry.getId();

        TagCollectionResponse assignTagsResponse = client
                .setTags(new ParametersForSetTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(num)
                        .setRequestBody(request));
        List<Tag> tags = assignTagsResponse.getValue();

        assertNotNull(tags);
        assertEquals(tags.get(0).getName(), tag);
    }

    @Test
    void setTemplateWorks() {
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
                repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetFields", testClassParentFolder.getId(), true);
        Integer entryId = entry.getId();

        FieldCollectionResponse assignFieldValuesResponse = client
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
    void removeTemplateWorks() throws ExecutionException, InterruptedException {
        TemplateDefinition template = null;

        TemplateDefinitionCollectionResponse templateDefinitionsResponse = repositoryApiClient.getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertFalse(templateDefinitions.isEmpty());

        for (TemplateDefinition templateDefinition : templateDefinitions) {
            TemplateFieldDefinitionCollectionResponse templateDefinitionsFieldsResponse =
                    repositoryApiClient.getTemplateDefinitionClient()
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
        Entry sourceEntry = createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest Java SetLinks Source", 1, true);
        createdEntries.add(sourceEntry);
        Entry targetEntry = createEntry(repositoryApiClient, "RepositoryApiClientIntegrationTest .Net SetLinks Target", 1, true);
        createdEntries.add(targetEntry);

        LinkToUpdate link = new LinkToUpdate();
        link.setOtherEntryId(targetEntry.getId());
        link.setLinkDefinitionId(1);
        List<LinkToUpdate> links = new ArrayList<LinkToUpdate>();
        links.add(link);
        SetLinksRequest request = new SetLinksRequest();
        request.setLinks(links);
        LinkCollectionResponse result = client
                .setLinks(new ParametersForSetLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(sourceEntry.getId())
                        .setRequestBody(request));
        List<Link> resultLinks = result.getValue();
        assertNotNull(resultLinks);
        assertEquals(links.size(), resultLinks.size());
        assertEquals(sourceEntry.getId(), resultLinks.get(0).getSourceId());
        assertEquals(targetEntry.getId(), resultLinks.get(0).getTargetId());
    }
}
