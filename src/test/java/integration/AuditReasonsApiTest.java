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
        AuditReasonsApi client = repositoryApiClient.getAuditReasonsClient();
        Call<AuditReasons> call = client.getAuditReasons(repoId);
        Response<AuditReasons> response = call.execute();

        assertNotNull(response.body());
    }
}
