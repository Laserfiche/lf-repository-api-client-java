package integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuditReasonsApiTest extends BaseTest {
    @Test
    public void getAuditReasons_Success() {
        AuditReasonsClient client = repositoryApiClient.getAuditReasonsClient();
        CompletableFuture<AuditReasons> future = client.getAuditReasons(repoId);
        AuditReasons reasons = future.join();

        assertNotNull(reasons);
    }
}
