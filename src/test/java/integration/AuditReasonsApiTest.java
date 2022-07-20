package integration;

import com.laserfiche.repository.api.clients.AuditReasonsClientImpl;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditReasonsApiTest extends BaseTest {
    @Test
    void getAuditReasons_Success() {
        AuditReasonsClientImpl client = repositoryApiClient.getAuditReasonsClient();
        CompletableFuture<AuditReasons> future = client.getAuditReasons(repoId);
        AuditReasons reasons = future.join();
        assertNotNull(reasons);
    }
}
