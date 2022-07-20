package integration;

import com.laserfiche.repository.api.clients.SimpleSearchesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimpleSearchesApiTest extends BaseTest {
    @Test
    void createSimpleSearchOperation_Success() {
        SimpleSearchesClientImpl client = repositoryApiClient.getSimpleSearchesClient();
        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");
        CompletableFuture<ODataValueOfIListOfEntry> future = client.createSimpleSearchOperation(repoId, searchRequest, null, null, null, null, null, false);
        ODataValueOfIListOfEntry entryList = future.join();

        assertNotNull(entryList);
    }
}
