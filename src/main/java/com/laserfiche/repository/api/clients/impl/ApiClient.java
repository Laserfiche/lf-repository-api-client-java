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

    protected static String mergeMaxSizeIntoPrefer(int maxSize, String prefer) {
        if (maxSize == 0)
            return prefer;
        else
            return prefer == null ? String.format("maxpagesize=%d", maxSize) : String.format("%s; maxpagesize=%d",
                    prefer, maxSize);
    }

    protected static Map<String, Object> getParametersWithNonDefaultValue(String[] parameterTypes,
            String[] parameterNames,
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

    private static boolean hasDefaultValue(String type, Object value) {
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

    protected static Map<String, String> getHeadersMap(Headers headers) {
        return headers
                .all()
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
    }

    protected static boolean isRetryableStatusCode(int statusCode, HttpMethod requestMethod) {
        boolean isIdempotent = !requestMethod
                .toString()
                .equals("POST");
        return (statusCode >= 500 || statusCode == 408) && isIdempotent;
    }

    protected static String getRepositoryEndpoint(String regionDomain) {
        if (regionDomain == null)
            throw new IllegalArgumentException("regionDomain is null.");
        return "https://api." + regionDomain + "/repository";
    }

    protected static String combineURLs(String baseURL, String relativeURL) {
        char end = baseURL.charAt(baseURL.length() - 1);
        char begin = relativeURL.charAt(0);
        String url;

        if ((end != '/' && begin == '/') || (end == '/' && begin != '/')) {
            url = baseURL + relativeURL;
        } else if (begin == '/') {
            url = baseURL + relativeURL.substring(1);
        } else {
            url = baseURL + '/' + relativeURL;
        }
        return url;
    }

    protected static boolean isJsonResponse(HttpResponse<Object> httpResponse) {
        List<String> ResponseContentType = httpResponse
                .getHeaders()
                .get("Content-Type");
        for (int i = 0; i < ResponseContentType.size(); i++) {
            if (ResponseContentType
                    .get(i)
                    .contains("json")) {
                return true;
            }
        }
        return false;
    }

    protected static void createApiException(HttpResponse<Object> httpResponse, ProblemDetails problemDetails) {
        int statusCode = httpResponse.getStatus();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        throw ApiException.create(statusCode, headersMap, problemDetails, null);
    }

    protected static String beforeSend(String url, Map<String, String> headerParametersWithStringTypeValue,
            HttpRequestHandler httpRequestHandler) {
        String requestUrl;
        Request customRequest = new RequestImpl();
        BeforeSendResult beforeSendResult = httpRequestHandler.beforeSend(customRequest);
        String authorizationValue = customRequest
                .headers()
                .get("Authorization");
        if (authorizationValue != null) {
            headerParametersWithStringTypeValue.put("Authorization", authorizationValue);
        }
        if (url.startsWith("http")) {
            requestUrl = url;
        } else {
            String apiBasedAddress = getRepositoryEndpoint(beforeSendResult.getRegionalDomain());
            requestUrl = combineURLs(apiBasedAddress, url);
        }
        return requestUrl;
    }

    private static boolean shouldThrowException(String requestMethod, String queryStringFields,
            Collection<?> queryStringFieldList, String url, ApiException err) {
        if (requestMethod.equals(
                "HEAD")) {
            return true;
        }
        if (queryStringFields != null && queryStringFieldList != null) {
            return true;
        }
        if (requestMethod.equals(
                "PATCH")) {
            return true;
        }
        if (requestMethod.equals("PUT") && url.contains("template")) {
            return true;
        }
        if (url.contains(
                "Task") && err
                .getProblemDetails()
                .getTitle()
                .contains("Error")) {
            return true;
        }
        if (err.getProblemDetails().getTitle().equals("Authentication failed (reason: Invalid service principal key. (6-45)).")){
            return true;
        }
        return false;
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
                String requestUrl = beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
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
                Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
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
                        new ResponseImpl((short) statusCode)) || isRetryableStatusCode(statusCode, httpMethod);
                boolean isJsonResponse = isJsonResponse(httpResponse);
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
                        createApiException(httpResponse, problemDetails);
                    }
                }
            } catch (Exception err) {
                if (err
                        .getClass()
                        .getName()
                        .equals("com.laserfiche.api.client.model.ApiException")) {
                    if (shouldThrowException(requestMethod, queryStringFields, queryStringFieldList,url,(ApiException) err)){
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
