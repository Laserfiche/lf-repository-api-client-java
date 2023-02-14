package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import kong.unirest.Headers;
import kong.unirest.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The base API client.
 */
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

    protected String toJson(Object object) {
        String json = null;
        try {
            json = objectMapper.writeValue(object);
        } catch (RuntimeException e) {
            System.err.println(e);
        }
        return json;
    }

    protected static <TResponse> TResponse sendRequestParseResponse(
            UnirestInstance httpClient,
            ObjectMapper objectMapper, Class<TResponse> deserializedResponseType,
            HttpRequestHandler httpRequestHandler, String url, String requestMethod,
            String contentType,
            Object requestBody,
            String queryStringFields,
            Collection<?> queryStringFieldList,
            Map<String, Object> queryParameters, Map<String, Object> pathParameters,
            Map<String, String> headerParametersWithStringTypeValue,
            boolean isDynamicFieldValues) {
        int retryCount = 0;
        int maxRetries = 1;
        boolean shouldRetry = true;
        HttpResponse<Object> httpResponse = null;
        String responseJson = null;
        while (retryCount <= maxRetries && shouldRetry) {
            try {
                String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
                final HttpRequestWithBody httpRequestWithBody = httpClient.request(requestMethod, requestUrl);
                HttpRequest<?> httpRequest = httpRequestWithBody;
                if (queryParameters != null) {
                    httpRequestWithBody.queryString(queryParameters);
                }
                if (pathParameters != null) {
                    httpRequestWithBody.routeParam(pathParameters);
                }
                if (headerParametersWithStringTypeValue != null) {
                    httpRequestWithBody.headers(headerParametersWithStringTypeValue);
                }
                if (queryStringFields != null && queryStringFieldList != null) {
                    httpRequestWithBody.queryString(queryStringFields, queryStringFieldList);
                }
                if (contentType != null) {
                    httpRequestWithBody.contentType(contentType);
                }
                if (requestBody != null) {
                    if (isDynamicFieldValues || requestMethod.equals("HEAD")) {
                        httpResponse = httpRequestWithBody
                                .body(requestBody)
                                .asObject(new HashMap<String, String[]>().getClass());
                    } else {
                        httpResponse = httpRequestWithBody
                                .body(requestBody)
                                .asObject(Object.class);
                    }
                } else {
                    if (isDynamicFieldValues || requestMethod.equals("HEAD")) {
                        httpResponse = httpRequest.asObject(new HashMap<String, String[]>().getClass());
                    } else {
                        httpResponse = httpRequest.asObject(Object.class);
                    }
                }

                HttpMethod httpMethod = httpRequest.getHttpMethod();
                Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                if (requestMethod.equals("HEAD")) {
                    if (httpResponse.getStatus() == 200) {
                        return (TResponse) httpResponse
                                .getHeaders()
                                .all()
                                .stream()
                                .collect(Collectors.toMap(Header::getName, Header::getValue));
                    } else {
                        throw ApiException.create(httpResponse.getStatus(), headersMap, null, null);
                    }
                }
                int statusCode = httpResponse.getStatus();
                shouldRetry = httpRequestHandler.afterSend(
                        new ResponseImpl((short) statusCode)) || ApiClientUtils.isRetryableStatusCode(statusCode, httpMethod);
                boolean isJsonResponse = ApiClientUtils.isJsonResponse(httpResponse);
                if (isJsonResponse) {
                    Object body = httpResponse.getBody();
                    if (body
                            .getClass()
                            .toString()
                            .contains("Array")) {
                        responseJson = new JSONArray(((ArrayList) body).toArray()).toString();
                    } else {
                        responseJson = new JSONObject(body).toString();
                    }
                }
                if (!shouldRetry) {
                    Object body = httpResponse.getBody();
                    if (statusCode == 200) {
                        try {
                            return objectMapper.readValue(responseJson, deserializedResponseType);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        ProblemDetails problemDetails;
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                        } catch (Exception e) {
                            throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                        }
                        ApiClientUtils.createApiException(httpResponse, problemDetails);
                    }
                }
            } catch (Exception err) {
                if (err
                        .getClass()
                        .getName()
                        .equals("com.laserfiche.api.client.model.ApiException")) {
                    if (ApiClientUtils.shouldThrowException(requestMethod, queryStringFields, queryStringFieldList, url,
                            (ApiException) err)) {
                        throw err;
                    } else {
                        System.err.println(err);
                    }
                    break;
                }
                if (retryCount >= maxRetries) {
                    throw err;
                }
                shouldRetry = true;
                System.err.println("Retrying fetch due to exception: " + err);
            } finally {
                retryCount++;
            }
        }
        if (httpResponse == null) {
            throw new IllegalStateException("Undefined response, there is a bug");
        }
        if (responseJson != null) {
            return objectMapper.readValue(responseJson, deserializedResponseType);
        } else {
            throw new RuntimeException("Response does not contain Json");
        }
    }
}
