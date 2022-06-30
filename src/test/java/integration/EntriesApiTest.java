package integration;

import com.laserfiche.repository.api.EntriesClient;
import com.laserfiche.repository.api.client.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntriesApiTest extends BaseTest {
    EntriesClient client;

    private final int maxPageSize = 1;

    @BeforeEach
    public void PerTestSetup() {
        client = repositoryApiClient.getEntriesClient();
    }

    @Test
    public void getEntry_Success() {
        CompletableFuture<Entry> future = client.getEntry(repoId, 1, null);
        Entry entry = future.join();

        assertNotNull(entry);
    }

    @Test
    public void getEntryListing_Success() {
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
    }

    @Test
    public void getEntryListingNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
        assertNotNull(entryList.getAtOdataNextLink());

        String nextLink = entryList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfEntry> newFuture = client.getEntryListingNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfEntry newEntryList = newFuture.join();

        assertNotNull(newEntryList);
    }

    @Test
    public void getEntryListingForEach_Success() {
        client.getEntryListingForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfEntry entryList = future.join();
            if (entryList != null) {
                assertNotNull(entryList.getValue());
            }
            return entryList != null; // Stop asking if there's no data.
        }), repoId, 1, false, null, false, null, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    public void getFieldValues_Success() {
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
    }

    @Test
    public void getFieldValuesNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
        assertNotNull(fieldValueList.getAtOdataNextLink());

        String nextLink = fieldValueList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfFieldValue> newFuture = client.getFieldValuesNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfFieldValue newFieldValueList = newFuture.join();

        assertNotNull(newFieldValueList);
    }

    @Test
    public void getFieldValuesForEach_Success() {
        client.getFieldValuesForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfFieldValue attributeList = future.join();
            if (attributeList != null) {
                assertNotNull(attributeList.getValue());
            }
            return attributeList != null; // Stop asking if there's no data.
        }), repoId, 1, null, null, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    public void getLinkValuesFromEntry_Success() {
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> future = client.getLinkValuesFromEntry(repoId, 28370, null, null, null, null, null, false, null);
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = future.join();

        assertNotNull(linkInfoList);
    }

    @Test
    public void getLinkValuesFromEntryNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> future = client.getLinkValuesFromEntry(repoId, 28370, null, null, null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWEntryLinkInfo linkInfoList = future.join();

        assertNotNull(linkInfoList);
        assertNotNull(linkInfoList.getAtOdataNextLink());

        String nextLink = linkInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> newFuture = client.getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWEntryLinkInfo newLinkInfoList = newFuture.join();

        assertNotNull(newLinkInfoList);
    }

    @Test
    public void getLinkValuesFromEntryForEach_Success() {
        client.getLinkValuesFromEntryForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWEntryLinkInfo attributeList = future.join();
            if (attributeList != null) {
                assertNotNull(attributeList.getValue());
            }
            return attributeList != null; // Stop asking if there's no data.
        }), repoId, 28370, null, null, null, null, null, false, maxPageSize);
    }

    @Test
    public void getTagsAssignedToEntry_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagsAssignedToEntry(repoId, 1, null, null,null, null, null, false, null);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
    }

    @Test
    public void getTagsAssignedToEntryNextLink_Success() {
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = client.getTagsAssignedToEntry(repoId, 28370, null, null,null, null, null, false, maxPageSize);
        ODataValueContextOfIListOfWTagInfo tagInfoList = future.join();

        assertNotNull(tagInfoList);
        assertNotNull(tagInfoList.getAtOdataNextLink());

        String nextLink = tagInfoList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfWTagInfo> newFuture = client.getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        ODataValueContextOfIListOfWTagInfo newTagInfoList = newFuture.join();

        assertNotNull(newTagInfoList);
    }

    @Test
    public void getTagsAssignedToEntryForEach_Success() {
        client.getTagsAssignedToEntryForEach((future -> {
            assertNotNull(future);
            ODataValueContextOfIListOfWTagInfo attributeList = future.join();
            if (attributeList != null) {
                assertNotNull(attributeList.getValue());
            }
            return attributeList != null; // Stop asking if there's no data.
        }), repoId, 28370, null, null,null, null, null, false, maxPageSize);
    }
}
