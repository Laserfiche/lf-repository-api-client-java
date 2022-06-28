package integration;

import com.laserfiche.repository.api.client.FieldDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.WFieldInfo;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FieldDefinitionsApiTest extends BaseTest {
    @Test
    public void getFieldDefinitionById_Success() throws IOException {
        FieldDefinitionsApi client = repositoryApiClient.getFieldDefinitionsClient();
        Call<WFieldInfo> call = client.getFieldDefinitionById(repoId, 261, null, null);
        Response<WFieldInfo> response = call.execute();

        assertNotNull(response.body());
    }

    @Test
    public void getFieldDefinitions_Success() throws IOException {
        FieldDefinitionsApi client = repositoryApiClient.getFieldDefinitionsClient();
        Call<ODataValueContextOfIListOfWFieldInfo> call = client.getFieldDefinitions(repoId, null, null, null, null, null, null, false);
        Response<ODataValueContextOfIListOfWFieldInfo> response = call.execute();

        assertNotNull(response.body());
    }
}
