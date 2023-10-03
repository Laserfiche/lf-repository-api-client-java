package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.params.ParametersForGetEntry;
import org.junit.jupiter.api.Test;

class RepositoryApiClientTest extends BaseTest {
    @Test
    void callingApiWithInvalidCredentialsThrowsException() {
        RepositoryApiClient invalidClient = null;
        try {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                invalidClient = RepositoryApiClientImpl.createFromAccessKey("fake key", accessKey);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                invalidClient = RepositoryApiClientImpl.createFromUsernamePassword(
                        repositoryId, username, "fake password", baseUrl);
            } else {
                fail(String.format("Test not implemented for AuthorizationType %s.", authorizationType));
            }

            final RepositoryApiClient finalInvalidClient = invalidClient;
            ApiException exception = assertThrows(ApiException.class, () -> finalInvalidClient
                    .getEntriesClient()
                    .getEntry(
                            new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(1)));

            assertEquals(401, exception.getStatusCode());
            assertEquals(
                    exception.getStatusCode(), exception.getProblemDetails().getStatus());
            assertEquals(exception.getMessage(), exception.getProblemDetails().getTitle());
            assertTrue(exception.getHeaders().size() > 0);
            assertNotNull(exception.getProblemDetails().getOperationId());
            assertNotNull(exception.getProblemDetails().getInstance());
            assertNotNull(exception.getProblemDetails().getType());
        } finally {
            if (invalidClient != null) invalidClient.close();
        }
    }

    @Test
    void repositoryApiClient_WithScope_AllowedApiCalled() {
        RepositoryApiClient client = null;
        String scope = "repository.Read";
        try {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                client = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey, scope);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                return;
            } else {
                fail(String.format("Test not implemented for AuthorizationType %s.", authorizationType));
            }

            int entryId = 1;
            assertNotNull(client);
            Entry entry = client.getEntriesClient()
                    .getEntry(
                            new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(entryId));
            assertEquals(entryId, entry.getId());
        } finally {
            if (client != null) client.close();
        }
    }

    @Test
    void repositoryApiClient_WithScope_NotAllowedApiCalled() {
        RepositoryApiClient client = null;
        String scope = "repository.Write";
        try {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                client = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey, scope);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                return;
            } else {
                fail(String.format("Test not implemented for AuthorizationType %s.", authorizationType));
            }

            int entryId = 1;
            final RepositoryApiClient finalClient = client;
            ApiException exception = assertThrows(ApiException.class, () -> finalClient
                    .getEntriesClient()
                    .getEntry(
                            new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(entryId)));

            assertEquals(403, exception.getStatusCode());
            assertEquals(
                    exception.getStatusCode(), exception.getProblemDetails().getStatus());
        } finally {
            if (client != null) client.close();
        }
    }
}
