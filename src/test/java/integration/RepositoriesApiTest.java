package integration;

import com.laserfiche.repository.api.client.RepositoriesApi;
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
        RepositoriesApi client = repositoryApiClient.getRepositoryClient();
        Call<List<RepositoryInfo>> call = client.getRepositoryList();
        Response<List<RepositoryInfo>> response = call.execute();

        assertNotNull(response.body());
    }
}
