// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.clients.SimpleSearchesClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;
import com.laserfiche.repository.api.clients.params.ParametersForCreateSimpleSearchOperation;
import org.junit.jupiter.api.Test;

class SimpleSearchesApiTest extends BaseTest {
    @Test
    void createSimpleSearch_ReturnSearchResults() {
        SimpleSearchesClient client = repositoryApiClient.getSimpleSearchesClient();

        SimpleSearchRequest searchRequest = new SimpleSearchRequest();
        searchRequest.setSearchCommand("({LF:Basic ~= \"Laserfiche\", option=\"NLT\"})");

        ODataValueOfIListOfEntry entryList =
                client.createSimpleSearchOperation(new ParametersForCreateSimpleSearchOperation()
                        .setRepoId(repositoryId)
                        .setRequestBody(searchRequest));

        assertNotNull(entryList);
    }
}
