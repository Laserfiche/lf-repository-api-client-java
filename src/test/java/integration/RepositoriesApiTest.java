package integration;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.RepositoriesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepositoriesApiTest extends BaseTest {
    @Test
    void getRepositoryList_Success() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        CompletableFuture<List<RepositoryInfo>> future = client.getRepositoryList();
        List<RepositoryInfo> repositoryInfoList = future.join();

        assertNotNull(repositoryInfoList);
    }
}
