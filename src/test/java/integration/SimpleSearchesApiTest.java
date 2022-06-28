package integration;

import com.laserfiche.repository.api.client.SimpleSearchesApi;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.client.model.SimpleSearchRequest;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleSearchesApiTest extends BaseTest {
    @Test
    public void createSimpleSearchOperation_Success() throws IOException {
        SimpleSearchesApi client = repositoryApiClient.getSimpleSearchesClient();
        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        Call<ODataValueOfIListOfEntry> call = client.createSimpleSearchOperation(repoId, searchRequest, null, null, null, null, null, false);
        Response<ODataValueOfIListOfEntry> response = call.execute();

        assertNotNull(response.body());
    }
}
