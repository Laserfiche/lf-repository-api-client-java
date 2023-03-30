package integration;

import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepositoriesApiTest extends BaseTest {
    @Test
    void getRepositoryList_ReturnSuccessful() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        RepositoryInfo[] repositoryInfoList = client.getRepositoryList();

        assertNotNull(repositoryInfoList);
    }

    @Test
    @DisabledIf("isCloudEnvironment")
    void getSelfHostedRepositoryList_WithNoAuthentication_ReturnSuccessful() {
        RepositoryInfo[] repositoryInfoList = RepositoriesClientImpl.getSelfHostedRepositoryList(baseUrl);
        assertNotNull(repositoryInfoList);
    }

    boolean isCloudEnvironment() {
        AuthorizationType AuthType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
        if (AuthType == AuthorizationType.API_SERVER_USERNAME_PASSWORD) {
            return false;
        }
        return true;
    }
}
