package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.EntryCollectionResponse;
import com.laserfiche.repository.api.clients.impl.model.SearchEntryRequest;
import com.laserfiche.repository.api.clients.params.ParametersForSearchEntry;
import kong.unirest.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSearchesClientTest extends BaseTest {

    private SimpleSearchesClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getSimpleSearchesClient();
    }

    @Test
    void searchEntryWorks() {
        SearchEntryRequest searchRequest = new SearchEntryRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        EntryCollectionResponse entryList =
                client.searchEntry(new ParametersForSearchEntry()
                        .setRepositoryId(repositoryId)
                        .setRequestBody(searchRequest));

        assertNotNull(entryList);
        assertFalse(entryList.getValue().isEmpty());
    }

    @Test
    void searchEntryThrowsExceptionForInvalidRequest() {
        SearchEntryRequest searchRequest = new SearchEntryRequest();

        ApiException apiException = Assertions.assertThrows(
                ApiException.class,
                () -> client.searchEntry(new ParametersForSearchEntry()
                        .setRepositoryId(repositoryId)
                        .setRequestBody(searchRequest)));

        assertNotNull(apiException);
        assertEquals(HttpStatus.BAD_REQUEST, apiException.getStatusCode());
        ProblemDetails problemDetails = apiException.getProblemDetails();
        assertNotNull(problemDetails);
    }
}
