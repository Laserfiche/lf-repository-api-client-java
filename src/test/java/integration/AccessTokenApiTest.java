package integration;

import com.laserfiche.repository.api.clients.ServerSessionClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.OffsetDateTime;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessTokenApiTest extends BaseTest {
    ServerSessionClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getServerSessionClient();
    }

    @Test
    void invalidateServerSession_LogoutSuccessful() {
        CompletableFuture<ODataValueOfBoolean> future = client.invalidateServerSession(repoId);
        ODataValueOfBoolean logoutStatusResponse = future.join();
        assertTrue(logoutStatusResponse.value);
    }

    @Test
    void refreshServerSession_RefreshSuccessful() {
        OffsetDateTime currentTime = OffsetDateTime.now();
        CompletableFuture<ODataValueOfDateTime> future = client.refreshServerSession(repoId);
        OffsetDateTime expireTime = future.join().value;
        assertNotNull(expireTime);
        Assertions.assertTrue(currentTime.compareTo(expireTime) < 0);
    }
}
