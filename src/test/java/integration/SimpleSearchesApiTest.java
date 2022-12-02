package integration;

import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import com.laserfiche.repository.api.clients.params.ParametersForCreateSimpleSearchOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimpleSearchesApiTest extends BaseTest {
    @Test
    void createSimpleSearch_ReturnSearchResults() {
        SimpleSearchesClient client = repositoryApiClient.getSimpleSearchesClient();

        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"search text\", option=\"DFANLT\"})");

        ODataValueOfIListOfEntry entryList = client
                .createSimpleSearchOperation(new ParametersForCreateSimpleSearchOperation().setRepoId(repoId).
        setRequestBody(searchRequest));

        assertNotNull(entryList);
    }
}
