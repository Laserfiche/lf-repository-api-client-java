package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.params.ParametersForExportDocument;
import kong.unirest.*;
import kong.unirest.Headers;
import kong.unirest.json.JSONObject;

import java.io.InputStream;
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

    protected static Map<String, Object> getParametersWithNonDefaultValue(String[] parameterTypes, String[] parameterNames,
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

    protected static ProblemDetails deserializeToProblemDetails(String jsonString, ObjectMapper objectMapper) {
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

//    protected static ProblemDetails deserializeToProblemDetails(String jsonString) {
//        ProblemDetails problemDetails = objectMapper.readValue(jsonString, ProblemDetails.class);
//        if (problemDetails.get("title") != null)
//            problemDetails.setTitle(problemDetails
//                    .get("title")
//                    .toString());
//        if (problemDetails.get("type") != null)
//            problemDetails.setType(problemDetails
//                    .get("type")
//                    .toString());
//        if (problemDetails.get("instance") != null)
//            problemDetails.setInstance(problemDetails
//                    .get("instance")
//                    .toString());
//        if (problemDetails.get("detail") != null)
//            problemDetails.setDetail(problemDetails
//                    .get("detail")
//                    .toString());
//        problemDetails.setStatus(Integer.parseInt(problemDetails
//                .get("status")
//                .toString()));
//        problemDetails.setExtensions((Map<String, Object>) problemDetails.get("extensions"));
//        return problemDetails;
//    }

    protected ProblemDetails deserializeRawResponse(String jsonString) {
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

    protected static String decideErrorMessage(ProblemDetails problemDetails, String genericErrorMessage) {
        return (problemDetails != null && problemDetails.getTitle() != null && problemDetails
                .getTitle()
                .trim()
                .length() > 0) ? problemDetails.getTitle() : genericErrorMessage;
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

    protected static boolean isJsonResponse(HttpResponse<Object> httpResponse){
        List<String> ResponseContentType = httpResponse.getHeaders().get("Content-Type");
        for (int i = 0; i < ResponseContentType.size(); i++){
            if(ResponseContentType.get(i).contains("json")){
                return true;
            }
        }
        return false;
    }

    protected static RuntimeException createApiException(HttpResponse<Object> httpResponse, ProblemDetails problemDetails){
        int statusCode = httpResponse.getStatus();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        if (statusCode == 400)
            return new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                    statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
        else if (statusCode == 401)
            return new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                    statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
        else if (statusCode == 403)
            return new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                    statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
        else if (statusCode == 404)
            return new ApiException(decideErrorMessage(problemDetails, "Not found."), statusCode,
                    httpResponse.getStatusText(), headersMap, problemDetails);
        else if (statusCode == 429)
            return new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                    statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
        else
            return new RuntimeException(httpResponse.getStatusText());
    }

    protected static void createExportApiException(ParametersForExportDocument parameters,
            RawResponse rawResponse, RuntimeException[] exception){
        //ObjectMapper objectMapper;
        if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
            parameters
                    .getInputStreamConsumer()
                    .accept(rawResponse.getContent());
        } else {
            ProblemDetails problemDetails = null;
            Map<String, String> headersMap = getHeadersMap(rawResponse.getHeaders());
            try {
                String jsonString = rawResponse.getContentAsString();
                problemDetails = deserializeToProblemDetails(jsonString, objectMapper);
            } catch (IllegalStateException e) {
                exception[0] = new ApiException(rawResponse.getStatusText(), rawResponse.getStatus(),
                        rawResponse.getContentAsString(), headersMap, null);
            }
            if (rawResponse.getStatus() == 400)
                exception[0] = new ApiException(
                        decideErrorMessage(problemDetails, "Invalid or bad request."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else if (rawResponse.getStatus() == 401)
                exception[0] = new ApiException(
                        decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else if (rawResponse.getStatus() == 403)
                exception[0] = new ApiException(
                        decideErrorMessage(problemDetails, "Access denied for the operation."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else if (rawResponse.getStatus() == 404)
                exception[0] = new ApiException(
                        decideErrorMessage(problemDetails, "Request entry id not found."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else if (rawResponse.getStatus() == 423)
                exception[0] = new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else if (rawResponse.getStatus() == 429)
                exception[0] = new ApiException(
                        decideErrorMessage(problemDetails, "Rate limit is reached."),
                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                        problemDetails);
            else
                exception[0] = new RuntimeException(rawResponse.getStatusText());
        }
    }

    private static String beforeSend(String url, Map<String, String> headerParametersWithStringTypeValue, HttpRequestHandler httpRequestHandler) {
        String requestUrl;
        Request customRequest = new RequestImpl();
        BeforeSendResult beforeSendResult = httpRequestHandler.beforeSend(customRequest);
        String authorizationValue = customRequest.headers().get("Authorization");
        if (authorizationValue != null){
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

    protected static <TResponse> TResponse sendRequestParseResponse(UnirestInstance httpClient,
            ObjectMapper objectMapper, Class<TResponse> deserializedResponseType,
            HttpRequestHandler httpRequestHandler, String url, String requestMethod,
            String fieldName, InputStream edoc, String edocName, Object edocRequestObject, String contentType,
            String queryStringFields, Object requestBody,
            Collection<?> queryStringFieldList,
            Map<String, Object> queryParameters, Map<String, Object> pathParameters,
            Map<String, String> headerParametersWithStringTypeValue) {
        int retryCount = 0;
        int maxRetries = 1;
        boolean shouldRetry = true;
        HttpResponse<Object> httpResponse = null;
        String responseJson = null;
        while (retryCount <= maxRetries && shouldRetry) {
            try {
                String requestUrl = beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
                HttpRequest<?> httpRequest = null;
                HttpRequestWithBody httpRequestWithBody = httpClient.request(requestMethod, requestUrl);
                if(queryParameters != null){
                    httpRequest = httpRequestWithBody.queryString(queryParameters);
                }
                if (pathParameters != null){
                    httpRequest = httpRequestWithBody.routeParam(pathParameters);
                }
                if (headerParametersWithStringTypeValue != null){
                    httpRequest = httpRequestWithBody.headers(headerParametersWithStringTypeValue);
                }
                if (queryStringFields != null && queryStringFieldList != null){
                    httpRequest = httpRequestWithBody.queryString(queryStringFields, queryStringFieldList);
                }
                if (contentType != null){
                    httpRequest = httpRequestWithBody.contentType(contentType);
                }
                if (requestBody != null){
                    httpRequest = httpRequestWithBody.body(requestBody);
                }
                if (fieldName != null){
                    if (edoc != null && edocName != null){
                        httpRequestWithBody.field(fieldName, edoc, edocName);
                    }
                    if (edocRequestObject != null){
                        httpRequestWithBody.field(fieldName, edocRequestObject);
                    }
                }
                httpResponse = httpRequest.asObject(Object.class);

                HttpMethod httpMethod = httpRequest.getHttpMethod();
                int statusCode = httpResponse.getStatus();
                shouldRetry = httpRequestHandler.afterSend(new ResponseImpl((short) statusCode)) || isRetryableStatusCode(statusCode, httpMethod);
                boolean isJsonResponse = isJsonResponse(httpResponse);
                if (isJsonResponse){
                    Object body = httpResponse.getBody();
                    responseJson = new JSONObject(body).toString();
                }
                if (!shouldRetry) {
                    if (statusCode == 200) {
                        try {
                            return objectMapper.readValue(responseJson, deserializedResponseType);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        ProblemDetails problemDetails;
                        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
                        try {
                            problemDetails = deserializeToProblemDetails(responseJson, objectMapper);
                        } catch (IllegalStateException e) {
                            Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                            throw new ApiException(httpResponse.getStatusText(), statusCode,
                                    (parsingException.isPresent() ? parsingException
                                            .get()
                                            .getOriginalBody() : null), headersMap, null);
                        }
                        throw createApiException(httpResponse, problemDetails);
                    }
                }
            } catch (Exception err) {
                if (retryCount >= maxRetries) {
                    throw err;
                }
                shouldRetry = true;
                System.err.println("Retrying fetch due to exception: "+ err);
            } finally {
                retryCount++;
            }
        }
        if (httpResponse == null){
            throw new IllegalStateException("Undefined response, there is a bug");
        }
        if (responseJson != null){
            return objectMapper.readValue(responseJson, deserializedResponseType);
        }
        else{
            throw new RuntimeException("Response does not contain Json");
        }
    }

    protected static void sendExportRequest(UnirestInstance httpClient,
            HttpRequestHandler httpRequestHandler, String url, String contentType,
            String queryStringFields, Object requestBody,
            ParametersForExportDocument parameters,
            Map<String, Object> pathParameters,
            Map<String, String> headerParametersWithStringTypeValue){
        int retryCount = 0;
        int maxRetries = 1;
        boolean shouldRetry = true;
        HttpResponse<Object> httpResponse = null;
        String responseJson = null;
        while (retryCount <= maxRetries && shouldRetry) {
            try {
                String requestUrl = beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
                HttpRequest<?> httpRequest = null;
                HttpRequestWithBody httpRequestWithBody = httpClient.post(requestUrl);
                httpRequest = httpRequestWithBody;
                final RuntimeException[] exception = {null};
                httpRequest.thenConsume(rawResponse -> {
                    createExportApiException(parameters, rawResponse, exception);
                });
                if (exception[0] != null) {
                    throw exception[0];
                }
            } catch (Exception err) {
                if (retryCount >= maxRetries) {
                    throw err;
                }
                shouldRetry = true;
                System.err.println("Retrying fetch due to exception: " + err);
            } finally {
                retryCount++;
            }
        }

    }
}
