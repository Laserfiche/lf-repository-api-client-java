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
        FieldDefinitionsApi fieldDefinitionsClient = repositoryApiClient.getFieldDefinitionsClient();
        Call<WFieldInfo> call = fieldDefinitionsClient.getFieldDefinitionById(repoId, 261, null, null);
        Response<WFieldInfo> fieldDefinition = call.execute();

        assertNotNull(fieldDefinition.body());
    }

    @Test
    public void getFieldDefinitions_Success() throws IOException {
        FieldDefinitionsApi fieldDefinitionsClient = repositoryApiClient.getFieldDefinitionsClient();
        Call<ODataValueContextOfIListOfWFieldInfo> call = fieldDefinitionsClient.getFieldDefinitions(repoId, null, null, null, null, null, null, false);
        Response<ODataValueContextOfIListOfWFieldInfo> fieldDefinitions = call.execute();

        assertNotNull(fieldDefinitions.body());
    }
}
