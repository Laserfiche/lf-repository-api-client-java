package com.laserfiche.repository.api.clients.impl;

import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    public ApiClient(String baseUrl, UnirestInstance httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    protected String mergeMaxSizeIntoPrefer(int maxSize, String prefer) {
        return prefer == null ? String.format("maxpagesize={%d}", maxSize) : String.format("%s; maxpagesize={%d}", prefer, maxSize);
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
                paramKeyValuePairs.put(parameterNames[i], parameters[i] instanceof String ? parameters[i] : String.valueOf(parameters[i]));
            }
        }
        return paramKeyValuePairs;
    }
}
