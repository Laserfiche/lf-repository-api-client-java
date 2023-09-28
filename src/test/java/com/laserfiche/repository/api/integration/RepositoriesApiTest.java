package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.RepositoryCollectionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import com.laserfiche.repository.api.clients.impl.model.Repository;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesApiTest extends BaseTest {
    @Test
    void getRepositoryList_ReturnSuccessful() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        RepositoryCollectionResponse response = client.listRepositories();
        boolean foundRepo = false;
        assertNotNull(response);
        assertFalse(response.getValue().isEmpty());
        for (Repository repository : response.getValue()) {
            assertNotNull(repository.getId());
            if (!authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                assertNotNull(repository.getWebClientUrl());
                assertTrue(repository.getWebClientUrl().contains(repository.getId()));
            }
            if (repository.getId().equalsIgnoreCase(repositoryId)) {
                foundRepo = true;
            }
        }
        assertTrue(foundRepo);
    }

    @Test
    @DisabledIf("isCloudEnvironment")
    void getSelfHostedRepositoryList_WithNoAuthentication_ReturnSuccessful() {
        Repository[] repositoryInfoList = RepositoriesClientImpl.getSelfHostedRepositoryList(baseUrl);
        assertNotNull(repositoryInfoList);
        boolean foundRepo = false;
        for (Repository repositoryInfo : repositoryInfoList) {
            assertNotNull(repositoryInfo.getId());
            if (repositoryInfo.getId().equalsIgnoreCase(repositoryId)) {
                foundRepo = true;
            }
        }
        assertTrue(foundRepo);
    }

    boolean isCloudEnvironment() {
        AuthorizationType AuthType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
        if (AuthType == AuthorizationType.API_SERVER_USERNAME_PASSWORD) {
            return false;
        }
        return true;
    }
}
