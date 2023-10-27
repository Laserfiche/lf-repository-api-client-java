// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.RepositoriesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Repository;
import com.laserfiche.repository.api.clients.impl.model.RepositoryCollectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesClientTest extends BaseTest {

    private RepositoriesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getRepositoryClient();
    }

    @Test
    void listRepositoriesWorks() {
        RepositoryCollectionResponse collectionResponse = client.listRepositories();
        boolean foundRepo = false;
        assertNotNull(collectionResponse);
        assertFalse(collectionResponse.getValue().isEmpty());
        for (Repository repository : collectionResponse.getValue()) {
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
    void listRepositoriesForSelfHostedWorksWithNoAuthentication() {
        RepositoryCollectionResponse collectionResponse = RepositoriesClientImpl.listRepositoriesForSelfHosted(baseUrl);
        assertNotNull(collectionResponse);
        boolean foundRepo = false;
        for (Repository repositoryInfo : collectionResponse.getValue()) {
            assertNotNull(repositoryInfo.getId());
            if (repositoryInfo.getId().equalsIgnoreCase(repositoryId)) {
                foundRepo = true;
            }
        }
        assertTrue(foundRepo);
    }

    boolean isCloudEnvironment() {
        AuthorizationType AuthType = AuthorizationType.valueOf(getEnvironmentVariable(AUTHORIZATION_TYPE));
        return AuthType != AuthorizationType.API_SERVER_USERNAME_PASSWORD;
    }
}
