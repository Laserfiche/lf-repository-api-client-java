package integration;

import com.laserfiche.repository.api.SimpleSearchesClient;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.client.model.SimpleSearchRequest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleSearchesApiTest extends BaseTest {
    @Test
    public void createSimpleSearchOperation_Success() {
        SimpleSearchesClient client = repositoryApiClient.getSimpleSearchesClient();
        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<ODataValueOfIListOfEntry> future = client.createSimpleSearchOperation(repoId, searchRequest, null, null, null, null, null, false);
        ODataValueOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
    }
}
