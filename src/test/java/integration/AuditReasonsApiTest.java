package integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditReasonsApiTest extends BaseTest {
    @Test
    void getAuditReasons_ReturnAuditReasons() {
        AuditReasonsClient client = repositoryApiClient.getAuditReasonsClient();
        AuditReasons reasons = client.getAuditReasons(repoId);

        assertNotNull(reasons);
    }
}
