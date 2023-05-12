package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Skipped failed test for now")
class RepositoriesApiTest extends BaseTest {
    @Test
    @Disabled("To be updated for payload compression")
    void getRepositoryList_ReturnSuccessful() {
        RepositoriesClient client = repositoryApiClient.getRepositoryClient();
        RepositoryInfo[] repositoryInfoList = client.getRepositoryList();
        boolean foundRepo = false;
        assertNotNull(repositoryInfoList);
        for (RepositoryInfo repositoryInfo : repositoryInfoList) {
            assertNotNull(repositoryInfo.getRepoId());
            if (!authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                assertNotNull(repositoryInfo.getWebclientUrl());
                assertTrue(repositoryInfo
                        .getWebclientUrl()
                        .contains(repositoryInfo.getRepoId()));
            }
            if (repositoryInfo
                    .getRepoId()
                    .equalsIgnoreCase(repositoryId)) {
                foundRepo = true;
            }
        }
        assertTrue(foundRepo);
    }

    @Test
    @DisabledIf("isCloudEnvironment")
    void getSelfHostedRepositoryList_WithNoAuthentication_ReturnSuccessful() {
        RepositoryInfo[] repositoryInfoList = RepositoriesClientImpl.getSelfHostedRepositoryList(baseUrl);
        assertNotNull(repositoryInfoList);
        boolean foundRepo = false;
        for (RepositoryInfo repositoryInfo : repositoryInfoList) {
            assertNotNull(repositoryInfo.getRepoId());
            if (repositoryInfo
                    .getRepoId()
                    .equalsIgnoreCase(repositoryId)) {
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
