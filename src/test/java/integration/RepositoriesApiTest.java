package integration;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepositoriesApiTest extends BaseTest {
    @Test
    void getRepositoryList_Success() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        RepositoryInfo[] repositoryInfoList = client.getRepositoryList().join();

        assertNotNull(repositoryInfoList);
    }
}
