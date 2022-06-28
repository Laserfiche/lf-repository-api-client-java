package integration;

import com.laserfiche.repository.api.client.EntriesApi;
import com.laserfiche.repository.api.client.RepositoriesApi;
import com.laserfiche.repository.api.client.model.Entry;
import com.laserfiche.repository.api.client.model.RepositoryInfo;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepositoriesApiTest extends BaseTest {
    @Test
    public void getRepositoryList_Success() throws IOException {
        RepositoriesApi repositoriesClient = repositoryApiClient.getRepositoryClient();
        Call<List<RepositoryInfo>> call = repositoriesClient.getRepositoryList();
        Response<List<RepositoryInfo>> entry = call.execute();

        assertNotNull(entry.body());
    }
}
