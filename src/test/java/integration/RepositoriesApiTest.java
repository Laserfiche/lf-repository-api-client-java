package integration;

import com.laserfiche.repository.api.RepositoriesClient;
import com.laserfiche.repository.api.client.model.RepositoryInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepositoriesApiTest extends BaseTest {
    @Test
    public void getRepositoryList_Success() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        CompletableFuture<List<RepositoryInfo>> future = client.getRepositoryList();
        List<RepositoryInfo> repositoryInfoList = future.join();

        assertNotNull(repositoryInfoList);
    }
}
