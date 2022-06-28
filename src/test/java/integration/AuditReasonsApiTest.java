package integration;

import com.laserfiche.repository.api.client.AuditReasonsApi;
import com.laserfiche.repository.api.client.model.AuditReasons;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuditReasonsApiTest extends BaseTest {
    @Test
    public void getAuditReasons_Success() {
        AuditReasonsApi client = repositoryApiClient.getAuditReasonsClient();
        CompletableFuture<AuditReasons> future = client.getAuditReasons(repoId);
        AuditReasons reasons = future.join();

        assertNotNull(reasons);
    }
}
