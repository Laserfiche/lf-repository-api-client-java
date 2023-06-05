package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import kong.unirest.ObjectMapper;
import kong.unirest.UnirestInstance;

/** The base API client. */
public abstract class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    protected ObjectMapper objectMapper;

    protected HttpRequestHandler httpRequestHandler;

    public ApiClient(
            String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.objectMapper = httpClient.config().getObjectMapper();
        this.httpRequestHandler = httpRequestHandler;
    }

    protected String toJson(Object object) {
        String json = null;
        try {
            json = objectMapper.writeValue(object);
        } catch (RuntimeException e) {
            System.err.println(e);
        }
        return json;
    }
}
