package integration;

import com.laserfiche.repository.api.client.AuditReasonsApi;
import com.laserfiche.repository.api.client.model.AuditReasons;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuditReasonsApiTest extends BaseTest {
    @Test
    public void getAuditReasons_Success() throws IOException {
        AuditReasonsApi auditReasonsClient = repositoryApiClient.getAuditReasonsClient();
        Call<AuditReasons> call = auditReasonsClient.getAuditReasons(repoId);
        Response<AuditReasons> auditReason = call.execute();

        assertNotNull(auditReason.body());
    }
}
