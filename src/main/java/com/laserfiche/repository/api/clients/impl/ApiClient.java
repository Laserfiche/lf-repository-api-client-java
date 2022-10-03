package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    private ObjectMapper jacksonMapper;

    public ApiClient(String baseUrl, UnirestInstance httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.jacksonMapper = JsonMapper
                .builder()
                .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
    }

    protected String mergeMaxSizeIntoPrefer(Integer maxSize, String prefer) {
        if (maxSize == null)
            return prefer;
        else
            return prefer == null ? String.format("maxpagesize=%d", maxSize) : String.format("%s; maxpagesize=%d",
                    prefer, maxSize);
    }

    protected Map<String, Object> getNonNullParameters(String[] parameterNames, Object[] parameters) {
        if (parameterNames == null || parameters == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (parameterNames.length != parameters.length) {
            throw new IllegalArgumentException("The array for parameter name and value should have the same length.");
        }
        Map<String, Object> paramKeyValuePairs = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] != null) {
                paramKeyValuePairs.put(parameterNames[i],
                        parameters[i] instanceof String ? parameters[i] : String.valueOf(parameters[i]));
            }
        }
        return paramKeyValuePairs;
    }

    protected String toJson(Object object) {
        String json = null;
        try {
            json = jacksonMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return json;
    }
}
