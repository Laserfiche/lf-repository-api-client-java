package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.model.ProblemDetails;
import kong.unirest.Header;
import kong.unirest.Headers;
import kong.unirest.ObjectMapper;
import kong.unirest.UnirestInstance;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    protected ObjectMapper objectMapper;

    public ApiClient(String baseUrl, UnirestInstance httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.objectMapper = httpClient
                .config()
                .getObjectMapper();
    }

    protected String mergeMaxSizeIntoPrefer(int maxSize, String prefer) {
        if (maxSize == 0)
            return prefer;
        else
            return prefer == null ? String.format("maxpagesize=%d", maxSize) : String.format("%s; maxpagesize=%d",
                    prefer, maxSize);
    }

    protected Map<String, Object> getParametersWithNonDefaultValue(String[] parameterTypes, String[] parameterNames,
            Object[] parameterValues) {
        if (parameterTypes == null || parameterNames == null || parameterValues == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (parameterTypes.length != parameterNames.length || parameterNames.length != parameterValues.length) {
            throw new IllegalArgumentException(
                    "The arrays for parameter types/names/values should have the same length.");
        }
        Map<String, Object> paramKeyValuePairs = new HashMap<>();
        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null && !hasDefaultValue(parameterTypes[i], parameterValues[i])) {
                List<Object> values = new ArrayList<>();
                if (parameterValues[i] instanceof Object[]) {
                    Object[] objects = (Object[]) parameterValues[i];
                    Collections.addAll(values, objects);
                } else {
                    values.add(parameterValues[i]);
                }
                if (values.size() == 1) {
                    paramKeyValuePairs.put(parameterNames[i], values.get(0));
                } else {
                    paramKeyValuePairs.put(parameterNames[i], values);
                }
            }
        }
        return paramKeyValuePairs;
    }

    private boolean hasDefaultValue(String type, Object value) {
        switch (type) {
            case "int":
                return value
                        .toString()
                        .equals("0");
            case "boolean":
                return value
                        .toString()
                        .equals("false");
        }
        return false;
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

    protected Map<String, String> getHeadersMap(Headers headers) {
        return headers
                .all()
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
    }

    protected ProblemDetails deserializeToProblemDetails(String jsonString) {
        ProblemDetails problemDetails = objectMapper.readValue(jsonString, ProblemDetails.class);
        if (problemDetails.get("title") != null)
            problemDetails.setTitle(problemDetails
                    .get("title")
                    .toString());
        if (problemDetails.get("type") != null)
            problemDetails.setType(problemDetails
                    .get("type")
                    .toString());
        if (problemDetails.get("instance") != null)
            problemDetails.setInstance(problemDetails
                    .get("instance")
                    .toString());
        if (problemDetails.get("detail") != null)
            problemDetails.setDetail(problemDetails
                    .get("detail")
                    .toString());
        problemDetails.setStatus(Integer.parseInt(problemDetails
                .get("status")
                .toString()));
        problemDetails.setExtensions((Map<String, Object>) problemDetails.get("extensions"));
        return problemDetails;
    }

    protected String decideErrorMessage(ProblemDetails problemDetails, String genericErrorMessage) {
        return (problemDetails != null && problemDetails.getTitle() != null && problemDetails
                .getTitle()
                .trim()
                .length() > 0) ? problemDetails.getTitle() : genericErrorMessage;
    }
}
