package integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class EntriesApiTest extends BaseTest {
    EntriesClient client;
    RepositoryApiClient createEntryClient;

    String rootPath = "\\";

    String nonExistingPath = "\\Non Existing Path";

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        createEntryClient = repositoryApiClient;
    }

    @Test
    void getEntry_ReturnRootFolder() {
        Entry entry = client.getEntry(new ParametersForGetEntry()
                .setRepoId(repoId)
                .setEntryId(1));

        assertNotNull(entry);
        assertTrue(entry instanceof Folder); // We know the root folder is of type Folder.
    }

    @Test
    void getEntry_ReturnEntryWhenTypeInfoMissing() {
        Entry entry = client.getEntry(new ParametersForGetEntry()
                .setRepoId(repoId)
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
        ODataValueContextOfIListOfEntry entries = client
                .getEntryListing(new ParametersForGetEntryListing()
                        .setRepoId(repoId)
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
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(new ParametersForGetEntryListing()
                        .setRepoId(repoId)
                        .setEntryId(1).
                        setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(entryList);

        String nextLink = entryList.getOdataNextLink();
        assertNotNull(nextLink);
        assertTrue(entryList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfEntry nextLinkResult = client.getEntryListingNextLink(nextLink,
                maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getEntryListing_ForEach() {
        int maxPageSize = 10;
        Function<ODataValueContextOfIListOfEntry, Boolean> callback = entries -> {
            if (entries.getOdataNextLink() != null) {
                assertNotEquals(0, entries
                        .getValue()
                        .size());
                assertTrue(entries
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getEntryListingForEach(callback, maxPageSize,
                new ParametersForGetEntryListing()
                        .setRepoId(repoId)
                        .setEntryId(1));
    }

    @Test
    void getFieldValues_ReturnFields() {
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(new ParametersForGetFieldValues()
                        .setRepoId(repoId)
                        .setEntryId(1));

        assertNotNull(fieldValueList);
    }

    @Test
    void getLinkValuesFromEntry_ReturnLinks() {
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(new ParametersForGetLinkValuesFromEntry()
                        .setRepoId(repoId)
                        .setEntryId(1));

        assertNotNull(linkInfoList);
    }

    @Test
    void getFieldValues_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfFieldValue fieldValueList = client
                .getFieldValues(new ParametersForGetFieldValues()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(fieldValueList);

        String nextLink = fieldValueList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(fieldValueList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfFieldValue nextLinkResult = client.getFieldValuesNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getFieldValues_ForEach() {
        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfFieldValue, Boolean> callback = fieldValues -> {
            if (fieldValues.getOdataNextLink() != null) {
                assertNotEquals(0, fieldValues
                        .getValue()
                        .size());
                assertTrue(fieldValues
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getFieldValuesForEach(callback, maxPageSize, new ParametersForGetFieldValues()
                .setRepoId(repoId)
                .setEntryId(1));
    }


    @Test
    void getLinkValuesFromEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = client
                .getLinkValuesFromEntry(new ParametersForGetLinkValuesFromEntry()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(linkInfoList);

        if (linkInfoList
                .getValue()
                .isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = linkInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(linkInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWEntryLinkInfo nextLinkResult = client.getLinkValuesFromEntryNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getLinkValuesFromEntry_ForEach() {
        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWEntryLinkInfo, Boolean> callback = entryLinkIntoList -> {
            if (entryLinkIntoList.getOdataNextLink() != null) {
                assertNotEquals(0, entryLinkIntoList
                        .getValue()
                        .size());
                assertTrue(entryLinkIntoList
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getLinkValuesFromEntryForEach(callback, maxPageSize, new ParametersForGetLinkValuesFromEntry()
                .setRepoId(repoId)
                .setEntryId(1));
    }

    @Test
    void deleteEntry_ReturnOperationToken() {
        Entry entryToDelete = createEntry(createEntryClient,
                "RepositoryApiClientIntegrationTest Java DeleteFolder", 1, true);

        AcceptedOperation deleteEntryResponse = client.deleteEntryInfo(new ParametersForDeleteEntryInfo()
                .setRepoId(repoId)
                .setEntryId(entryToDelete.getId())
                .setRequestBody(new DeleteEntryWithAuditReason()));

        String token = deleteEntryResponse
                .getToken();

        assertNotNull(token);
    }

    @Test
    void getTagsAssignedToEntry_NextLink() throws InterruptedException {
        int maxPageSize = 1;
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(new ParametersForGetTagsAssignedToEntry()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));

        assertNotNull(tagInfoList);

        if (tagInfoList
                .getValue()
                .isEmpty()) {
            return; // There's no point testing if we don't have any such item.
        }
        String nextLink = tagInfoList.getOdataNextLink();
        assertNotNull(nextLink);

        assertTrue(tagInfoList
                .getValue()
                .size() <= maxPageSize);

        ODataValueContextOfIListOfWTagInfo nextLinkResult = client.getTagsAssignedToEntryNextLink(
                nextLink, maxPageSize);
        assertNotNull(nextLinkResult);

        TimeUnit.SECONDS.sleep(10);

        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult
                .getValue()
                .size() <= maxPageSize);
    }

    @Test
    void getTagsAssignedToEntry_ForEach() {
        int maxPageSize = 90;
        Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback = tagInfoList -> {
            if (tagInfoList.getOdataNextLink() != null) {
                assertNotEquals(0, tagInfoList
                        .getValue()
                        .size());
                assertTrue(tagInfoList
                        .getValue()
                        .size() <= maxPageSize);
                return true;
            } else {
                return false;
            }
        };
        client.getTagsAssignedToEntryForEach(callback, maxPageSize, new ParametersForGetTagsAssignedToEntry()
                .setRepoId(repoId)
                .setEntryId(1));
    }

    @Test
    void getTagsAssignedToEntry_ReturnTags() {
        ODataValueContextOfIListOfWTagInfo tagInfoList = client
                .getTagsAssignedToEntry(new ParametersForGetTagsAssignedToEntry()
                        .setRepoId(repoId)
                        .setEntryId(1));

        assertNotNull(tagInfoList);
    }

    @Test
    void getDynamicFieldsEntry_ReturnDynamicFields() {
        ODataValueContextOfIListOfWTemplateInfo templateDefinitionsResponse = repositoryApiClient
                .getTemplateDefinitionClient()
                .getTemplateDefinitions(new ParametersForGetTemplateDefinitions().setRepoId(repoId));
        List<WTemplateInfo> templateDefinitions = templateDefinitionsResponse.getValue();

        assertNotNull(templateDefinitions);
        assertTrue(templateDefinitions.size() > 0);

        GetDynamicFieldLogicValueRequest request = new GetDynamicFieldLogicValueRequest();
        request.setTemplateId(templateDefinitions
                .get(0)
                .getId());

        Map<String, String[]> dynamicFieldValueResponse = client
                .getDynamicFieldValues(new ParametersForGetDynamicFieldValues()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setRequestBody(request));
        assertNotNull(dynamicFieldValueResponse);
    }

    @Test
    void getEntryByFullPath_ReturnRootFolder() {
        FindEntryResult entry = repositoryApiClient
                .getEntriesClient()
                .getEntryByPath(new ParametersForGetEntryByPath()
                        .setRepoId(repoId)
                        .setFullPath(rootPath));

        assertNotNull(entry);
        assertEquals(1, entry
                .getEntry()
                .getId());
        assertEquals(rootPath, entry
                .getEntry()
                .getFullPath());
        assertEquals("Folder", entry
                .getEntry()
                .getEntryType()
                .toString());
        assertNull(entry.getAncestorEntry());
    }

    @Test
    void getEntryByFullPath_ReturnAncestorRootFolder() {
        FindEntryResult entry = repositoryApiClient
                .getEntriesClient()
                .getEntryByPath(new ParametersForGetEntryByPath()
                        .setRepoId(repoId)
                        .setFullPath(nonExistingPath)
                        .setFallbackToClosestAncestor(true));

        assertNotNull(entry);
        assertEquals(1, entry
                .getAncestorEntry()
                .getId());
        assertEquals(rootPath, entry
                .getAncestorEntry()
                .getFullPath());
        assertEquals("Folder", entry
                .getAncestorEntry()
                .getEntryType()
                .toString());
        assertNull(entry.getEntry());
    }

    @Test
    void getDocumentContentType_ReturnsExpectedHeaders() {
        ODataValueContextOfIListOfEntry entryList = client
                .getEntryListing(new ParametersForGetEntryListing()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setPrefer("maxpagesize=100"));
        assertNotNull(entryList);

        Optional<Entry> optionalEntry = entryList
                .getValue()
                .stream()
                .filter(entry -> entry.getEntryType() == EntryType.DOCUMENT && entry.getId() < 5000)
                .findFirst();
        Entry entry = optionalEntry.get();
        assertNotNull(entry);

        Map<String, String> headers = client
                .getDocumentContentType(new ParametersForGetDocumentContentType()
                        .setRepoId(repoId)
                        .setEntryId(entry.getId()));
        assertNotNull(headers.get("Content-Type"));
        assertNotNull(headers.get("Content-Length"));
    }

    @Test
    void getDocumentContentType_ProblemDetails_Fields_Are_Valid_When_Exception_Thrown() {
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> {
            client
                    .getEntryListing(new ParametersForGetEntryListing()
                            .setRepoId(repoId)
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
    void getEntryListing_WithOneField_ReturnEntries() {
        String[] fieldNames = {"Sender"};
        ODataValueContextOfIListOfEntry entries = client
                .getEntryListing(new ParametersForGetEntryListing()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setFields(fieldNames)
                        .setPrefer("maxpagesize=5"));
        assertNotNull(entries);
        for (Entry entry : entries.getValue()) {
            int numberOfReturnedFields = (int) entry
                    .getFields()
                    .stream()
                    .filter(entryFieldValue -> entryFieldValue
                            .getFieldName()
                            .equalsIgnoreCase(fieldNames[0]) || entryFieldValue
                            .getFieldName()
                            .equalsIgnoreCase(fieldNames[1]))
                    .count();
            assertEquals(fieldNames.length, numberOfReturnedFields);
        }
    }

    @Test
    void getEntryListing_WithFields_ReturnEntries() {
        String[] fieldNames = {"Sender", "Subject"};
        ODataValueContextOfIListOfEntry entries = client
                .getEntryListing(new ParametersForGetEntryListing()
                        .setRepoId(repoId)
                        .setEntryId(1)
                        .setFields(fieldNames)
                        .setPrefer("maxpagesize=5"));
        assertNotNull(entries);
        for (Entry entry : entries.getValue()) {
            int numberOfReturnedFields = (int) entry
                    .getFields()
                    .stream()
                    .filter(entryFieldValue -> entryFieldValue
                            .getFieldName()
                            .equalsIgnoreCase(fieldNames[0]) || entryFieldValue
                            .getFieldName()
                            .equalsIgnoreCase(fieldNames[1]))
                    .count();
            assertEquals(fieldNames.length, numberOfReturnedFields);
        }
    }

    @Test
    void getDocumentContentType_Returns_Valid_Error_Message_ForInvalidRepoId() {
        String invalidRepoId = String.format("%s-%s", repoId, repoId);
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> client
                .getDocumentContentType(new ParametersForGetDocumentContentType()
                        .setRepoId(invalidRepoId)
                        .setEntryId(1)));
        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertEquals("Not Found", apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNull(problemDetails);
    }

    @Test
    void writeTemplateValueToEntry_ReturnsCorrectErrorMessage_For_Invalid_TemplateName() {
        PutTemplateRequest request = new PutTemplateRequest();
        request.setTemplateName("fake_template");
        ApiException apiException = Assertions.assertThrows(ApiException.class, () -> client
                .writeTemplateValueToEntry(new ParametersForWriteTemplateValueToEntry()
                        .setRepoId(repoId)
                        .setEntryId(3)
                        .setRequestBody(request)));
        assertNotNull(apiException);
        assertEquals(404, apiException.getStatusCode());
        assertTrue(apiException
                .getMessage()
                .startsWith("Template not found."), apiException.getMessage());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }
}
