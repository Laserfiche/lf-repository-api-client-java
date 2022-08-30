package com.laserfiche.repository.api.clients.impl;

import kong.unirest.UnirestInstance;

public class ApiClient {
    protected String baseUrl;
    protected UnirestInstance httpClient;

    public ApiClient(String baseUrl, UnirestInstance httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }
}
