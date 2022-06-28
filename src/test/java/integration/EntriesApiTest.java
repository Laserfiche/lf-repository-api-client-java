package integration;

import com.laserfiche.repository.api.client.EntriesApi;
import com.laserfiche.repository.api.client.model.Entry;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfFieldValue;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        EntriesApi entriesClient = repositoryApiClient.getEntriesClient();
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = entriesClient.getFieldValues(repoId, 1, null, null, null, null, null, null, null, false);
        ODataValueContextOfIListOfFieldValue fieldValueList = future.join();

        assertNotNull(fieldValueList);
    }
}
