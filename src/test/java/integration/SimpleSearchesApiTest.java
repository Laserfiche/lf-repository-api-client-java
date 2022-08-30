package integration;

import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimpleSearchesApiTest extends BaseTest {
    @Test
    void createSimpleSearchOperation_Success() {
        SimpleSearchesClient client = repositoryApiClient.getSimpleSearchesClient();

        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.searchCommand = "({LF:Basic ~= \"search text\", option=\"DFANLT\"})";

        ODataValueOfIListOfEntry entryList = client
                .createSimpleSearchOperation(null, null, null, repoId, null, null, searchRequest, null)
                .join();

        assertNotNull(entryList);
    }
}
