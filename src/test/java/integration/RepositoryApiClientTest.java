package integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.params.ParametersForGetEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryApiClientTest extends BaseTest {
    @Test
    void invalidCredentials_ThrowsApiExceptionWhenMakingApiCall() {
        RepositoryApiClient invalidClient = null;
        try {
            if (authorizationType.equals(AuthorizationType.CLOUD_ACCESS_KEY)) {
                invalidClient = RepositoryApiClientImpl.createFromAccessKey("fake key", accessKey);
            } else if (authorizationType.equals(AuthorizationType.API_SERVER_USERNAME_PASSWORD)) {
                invalidClient = RepositoryApiClientImpl.createFromUsernamePassword(repositoryId, username,
                        "fake password", baseUrl);
            } else {
                fail(String.format("Test not implemented for AuthorizationType %s.", authorizationType));
            }

            final RepositoryApiClient finalInvalidClient = invalidClient;
            ApiException exception = assertThrows(ApiException.class, () -> finalInvalidClient
                    .getEntriesClient().getEntry(new ParametersForGetEntry()
                            .setRepoId(repositoryId)
                            .setEntryId(1)));

            assertEquals(401, exception.getStatusCode());
            assertEquals(exception.getStatusCode(), exception.getProblemDetails().getStatus());
            assertEquals(exception.getMessage(), exception.getProblemDetails().getTitle());
            assertTrue(exception.getHeaders().size() > 0);
            assertNotNull(exception.getProblemDetails().getOperationId());
            assertNotNull(exception.getProblemDetails().getInstance());
            assertNotNull(exception.getProblemDetails().getType());
        } finally {
            if (invalidClient != null)
                invalidClient.close();
        }
    }
}
