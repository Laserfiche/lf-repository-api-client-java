package integration;

import com.laserfiche.repository.api.client.TagDefinitionsApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TagDefinitionsApiTest extends BaseTest {
    @Test
    public void getTagDefinitions_Success() throws IOException {
        TagDefinitionsApi client = repositoryApiClient.getTagDefinitionsClient();
        Call<ODataValueContextOfIListOfWTagInfo> call = client.getTagDefinitions(repoId, null, null, null, null, null, null, false);
        Response<ODataValueContextOfIListOfWTagInfo> response = call.execute();

        assertNotNull(response.body());
    }
}
