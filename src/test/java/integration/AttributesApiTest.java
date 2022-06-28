package integration;

import com.laserfiche.repository.api.client.AttributesApi;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttributesApiTest extends BaseTest {

    @Test
    public void getTrusteeAttributeKeyValuePairs_Success() throws IOException {
        AttributesApi attributesClient = repositoryApiClient.getAttributesClient();
        Call<ODataValueContextOfListOfAttribute> call = attributesClient.getTrusteeAttributeKeyValuePairs(repoId, true, null, null, null, null, null, false);
        Response<ODataValueContextOfListOfAttribute> attributePairs = call.execute();

        assertNotNull(attributePairs.body());
    }
}
