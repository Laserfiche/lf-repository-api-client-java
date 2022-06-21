package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.RepositoryInfo;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for RepositoriesApi
 */
public class RepositoriesApiTest {

    private RepositoriesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(RepositoriesApi.class);
    }


    /**
     * 
     *
     * - Get the repository resource list that current user has access to.
     */
    @Test
    public void getRepositoryListTest() {
        // List<RepositoryInfo> response = api.getRepositoryList();

        // TODO: test validations
    }
}
