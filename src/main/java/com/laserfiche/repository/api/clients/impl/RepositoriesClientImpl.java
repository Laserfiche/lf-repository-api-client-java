package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.RepositoriesClient;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import kong.unirest.UnirestInstance;

import java.util.HashMap;

/**
 * The Laserfiche Repository Repositories API client.
 */
public class RepositoriesClientImpl extends ApiClient implements RepositoriesClient {

    private HttpRequestHandler httpRequestHandler;

    public RepositoriesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public RepositoryInfo[] getRepositoryList() {
        return sendRequestParseResponse(httpClient, objectMapper, RepositoryInfo[].class, httpRequestHandler,
                baseUrl + "/v1/Repositories", "GET", null, null, null, null, null, null, new HashMap<String, String>(),
                false);
    }
}
