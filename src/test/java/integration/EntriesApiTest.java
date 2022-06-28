package integration;

import com.laserfiche.repository.api.client.EntriesApi;
import com.laserfiche.repository.api.client.model.Entry;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfFieldValue;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntriesApiTest extends BaseTest {

    @Test
    public void getEntry_Success() {
        EntriesApi client = repositoryApiClient.getEntriesClient();
        CompletableFuture<Entry> future = client.getEntry(repoId, 1, null);
        Entry entry = future.join();

        assertNotNull(entry);
    }

    @Test
    public void getFieldValues_Success() {
        EntriesApi client = repositoryApiClient.getEntriesClient();
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = client.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
    }

    @Test
    public void getEntryListing_Success() {
        EntriesApi client = repositoryApiClient.getEntriesClient();
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
    }

    @Test
    public void getEntryListingPaginate_Success() {
        EntriesApi client = repositoryApiClient.getEntriesClient();
        CompletableFuture<ODataValueContextOfIListOfEntry> future = client.getEntryListing(repoId, 1, false, null, false, "maxpagesize=1", null, null, null, null, null, false);
        ODataValueContextOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
        assertNotNull(entryList.getAtOdataNextLink());

        String nextLink = entryList.getAtOdataNextLink();

        CompletableFuture<ODataValueContextOfIListOfEntry> newFuture = client.getEntryListingPaginate(nextLink, "maxpagesize=2");
        ODataValueContextOfIListOfEntry newEntryList = newFuture.join();

        assertNotNull(newEntryList);
    }
}
