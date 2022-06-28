package integration;

import com.laserfiche.repository.api.client.TemplateDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTemplateInfo;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateDefinitionsApiTest extends BaseTest {
    @Test
    public void getTemplateDefinitions_Success() throws IOException {
        TemplateDefinitionsApi client = repositoryApiClient.getTemplateDefinitionClient();
        Call<ODataValueContextOfIListOfWTemplateInfo> call = client.getTemplateDefinitions(repoId, null, null, null, null, null, null, null, false);
        Response<ODataValueContextOfIListOfWTemplateInfo> response = call.execute();

        assertNotNull(response.body());
    }
}
