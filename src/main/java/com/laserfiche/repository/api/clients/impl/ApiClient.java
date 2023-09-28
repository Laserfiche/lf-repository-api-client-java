package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.stream.Collectors;
import kong.unirest.Header;
import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.ObjectMapper;
import kong.unirest.UnirestInstance;
import org.threeten.bp.OffsetDateTime;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ProblemDetails;

/**
 * The base API client.
 */
public abstract class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    protected ObjectMapper objectMapper;

    protected HttpRequestHandler httpRequestHandler;

    public ApiClient(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
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
