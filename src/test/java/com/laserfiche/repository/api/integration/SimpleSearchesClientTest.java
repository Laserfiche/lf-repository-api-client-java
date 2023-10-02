package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.EntryCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.SearchEntryRequest;
import com.laserfiche.repository.api.clients.params.ParametersForSearchEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleSearchesClientTest extends BaseTest {

    private SimpleSearchesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getSimpleSearchesClient();
    }

    @Test
    void createSimpleSearch_ReturnSearchResults() {
        SearchEntryRequest searchRequest = new SearchEntryRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        EntryCollectionResponse entryList =
                client.searchEntry(new ParametersForSearchEntry()
                        .setRepositoryId(repositoryId)
                        .setRequestBody(searchRequest));

        assertNotNull(entryList);
    }
}
