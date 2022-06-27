package integration;

import com.laserfiche.repository.api.client.EntriesApi;
import com.laserfiche.repository.api.client.model.Entry;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntryApiTest extends BaseTest {

    @Test
    public void getEntry_Success() throws IOException {
        EntriesApi entriesClient = repositoryApiClient.getEntriesClient();
        Call<Entry> call = entriesClient.getEntry(repoId, 1, null);
        Response<Entry> entry = call.execute();

        assertNotNull(entry.body());
    }
}
