package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntriesApiTest extends BaseTest {
    EntriesClient client;
    RepositoryApiClient createEntryClient;

    String rootPath = "\\";

    String nonExistingPath = "\\Non Existing Path";

    List<Entry> createdEntries = new ArrayList<>();

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @AfterEach
    void perTestCleanUp() throws InterruptedException {
        deleteEntries(createdEntries);
    }

    @Test
    void getEntry_ReturnRootFolder() {
        Entry entry = client.getEntry(
                new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(1));

        assertNotNull(entry);
        assertTrue(entry instanceof Folder); // We know the root folder is of type Folder.
    }

    @Test
    void getEntry_ReturnEntryWhenTypeInfoMissing() {
        Entry entry = client.getEntry(new ParametersForGetEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setSelect("name"));

        assertNotNull(entry);
        assertFalse(entry instanceof Folder
                || entry instanceof Shortcut
                || entry instanceof Document
                || entry instanceof RecordSeries); // When no type information, the data is deserialized to Entry.
    }

    @Test
    void getEntryListing_ReturnEntries() {
        EntryCollectionResponse entries = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer("maxpagesize=5"));

        assertNotNull(entries);

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
            }
        }
    }

    @Test
    void getEntryListing_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        EntryCollectionResponse entryList = client.listEntries(new ParametersForListEntries()
                .setRepositoryId(repositoryId)
                .setEntryId(1)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(entryList);

        String nextLink = entryList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(entryList.getValue().size() <= maxPageSize);

        EntryCollectionResponse nextLinkResult = client.listEntriesNextLink(nextLink, maxPageSize);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getEntryListing_ForEach() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 3;
        Function<EntryCollectionResponse, Boolean> callback = entries -> {
            if (pageCount.incrementAndGet() <= maxPages && entries.getOdataNextLink() != null) {
                assertNotEquals(0, entries.getValue().size());
                assertTrue(entries.getValue().size() <= maxPageSize);
                return true;
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
    void getFieldValues_ReturnFields() {
        FieldCollectionResponse fieldValueList = client.listFields(
                new ParametersForListFields().setRepositoryId(repositoryId).setEntryId(1));

        assertNotNull(fieldValueList);
    }

    @Test
    void getLinkValuesFromEntry_ReturnLinks() {
        LinkCollectionResponse linkInfoList =
                client.listLinks(new ParametersForListLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1));

        assertNotNull(linkInfoList);
    }

    @Test
    void getFieldValues_NextLink() throws InterruptedException {
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
    void getFieldValues_ForEach() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<FieldCollectionResponse, Boolean> callback = fieldValues -> {
            if (pageCount.incrementAndGet() <= maxPages && fieldValues.getOdataNextLink() != null) {
                assertNotEquals(0, fieldValues.getValue().size());
                assertTrue(fieldValues.getValue().size() <= maxPageSize);
                return true;
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
    void getLinkValuesFromEntry_NextLink() throws InterruptedException {
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
    void getLinkValuesFromEntry_ForEach() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<LinkCollectionResponse, Boolean> callback = entryLinkIntoList -> {
            if (pageCount.incrementAndGet() <= maxPages && entryLinkIntoList.getOdataNextLink() != null) {
                assertNotEquals(0, entryLinkIntoList.getValue().size());
                assertTrue(entryLinkIntoList.getValue().size() <= maxPageSize);
                return true;
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
    void deleteEntry_ReturnOperationToken() throws InterruptedException {
        Entry entryToDelete =
                createEntry(createEntryClient, "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true);

        StartTaskResponse deleteEntryResponse = client.startDeleteEntry(new ParametersForStartDeleteEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(entryToDelete.getId())
                .setRequestBody(new StartDeleteEntryRequest()));
        WaitUntilTaskEnds(deleteEntryResponse.getTaskId(), Duration.ofMillis(100));
        String taskId = deleteEntryResponse.getTaskId();
        assertNotNull(taskId);
    }

    @Test
    void getTagsAssignedToEntry_NextLink() throws InterruptedException {
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
    void getTagsAssignedToEntry_ForEach() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 1;
        Function<TagCollectionResponse, Boolean> callback = tagInfoList -> {
            if (pageCount.incrementAndGet() <= maxPages && tagInfoList.getOdataNextLink() != null) {
                assertNotEquals(0, tagInfoList.getValue().size());
                assertTrue(tagInfoList.getValue().size() <= maxPageSize);
                return true;
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
    }

    @Test
    void getTagsAssignedToEntry_ReturnTags() {
        TagCollectionResponse tagInfoList =
                client.listTags(new ParametersForListTags()
                        .setRepositoryId(repositoryId)
                        .setEntryId(1));

        assertNotNull(tagInfoList);
    }

    @Test
    void getDynamicFieldsEntry_ReturnDynamicFields() {
        TemplateDefinitionCollectionResponse templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .listTemplateDefinitions(new ParametersForListTemplateDefinitions().setRepositoryId(repositoryId));
        List<TemplateDefinition> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

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
    void getEntryByFullPath_ReturnRootFolder() {
        GetEntryByPathResponse entry = repositoryApiClient
                .getEntriesClient()
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
    void getEntryByFullPath_ReturnAncestorRootFolder() {
        GetEntryByPathResponse entry = repositoryApiClient
                .getEntriesClient()
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
    void getEntryListing_ProblemDetails_Fields_Are_Valid_When_Exception_Thrown() {
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
    void getEntryListing_WithFields_ReturnEntries() {
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
    void writeTemplateValueToEntry_ReturnsCorrectErrorMessage_For_Invalid_TemplateName() {
        Entry parentFolder = createEntry(createEntryClient, "EntriesTest", 1, true);
        createdEntries.add(parentFolder);

        SetTemplateRequest request = new SetTemplateRequest();
        request.setTemplateName("fake_template");
        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.setTemplate (new ParametersForSetTemplate()
                        .setRepositoryId(repositoryId)
                        .setEntryId(parentFolder.getId())
                        .setRequestBody(request)));
        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertTrue(apiException.getMessage().startsWith("Template not found."), apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }
}
