package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.APIServerException;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryOperations;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryResult;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import kong.unirest.Headers;

/**
 * Internal helper class containing utility functions for the RepositoryApiClient.
 */
public class ApiClientUtils {
    private ApiClientUtils() {
        throw new IllegalStateException("Utility class with all static methods are not meant to be instantiated.");
    }

    /**
     * Returns a human-readable summary of the {@link CreateEntryResult}.
     *
     * @param createEntryResult The createEntryResult.
     * @return A human-readable summary of the {@link CreateEntryResult}.
     */
    public static String getCreateEntryResultSummary(CreateEntryResult createEntryResult) {
        Collection<String> messages = new ArrayList<>();
        if (createEntryResult != null && createEntryResult.getOperations() != null) {
            CreateEntryOperations operations = createEntryResult.getOperations();
            if (operations.getEntryCreate() != null) {
                Integer entryId = operations.getEntryCreate().getEntryId();
                if (entryId != null && entryId > 0) {
                    messages.add(String.format("EntryId=%s.", entryId));
                }
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getEntryCreate().getExceptions()));
            }
            if (operations.getSetEdoc() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getSetEdoc().getExceptions()));
            if (operations.getSetTemplate() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getSetTemplate().getExceptions()));
            if (operations.getSetFields() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getSetFields().getExceptions()));
            if (operations.getSetTags() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getSetTags().getExceptions()));
            if (operations.getSetLinks() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(
                        operations.getSetLinks().getExceptions()));
        }
        return messages.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim();
    }

    private static String getErrorMessagesFromAPIServerExceptions(Collection<APIServerException> errors) {
        if (errors == null || errors.isEmpty()) return null;
        return errors.stream()
                .filter(Objects::nonNull)
                .map(APIServerException::getMessage)
                .collect(Collectors.joining(" "))
                .trim();
    }

    protected static ApiException createApiException(HttpResponse<?> httpResponse, ProblemDetails problemDetails) {
        int statusCode = httpResponse.getStatus();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        return ApiException.create(statusCode, headersMap, problemDetails, null);
    }

    /**
     * Sets the authorization bearer token and returns a Request Url.
     *
     * @param url                                 The url.
     * @param headerParametersWithStringTypeValue The header parameters with string type value.
     * @param httpRequestHandler                  The HTTP request handler.
     * @return Sets the authorization bearer token and returns a Request Url.
     */
    public static String beforeSend(
            String url,
            Map<String, String> headerParametersWithStringTypeValue,
            HttpRequestHandler httpRequestHandler) {
        String requestUrl;
        Request customRequest = new RequestImpl();
        BeforeSendResult beforeSendResult = httpRequestHandler.beforeSend(customRequest);
        String authorizationValue = customRequest.headers().get("Authorization");
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

    private static String getRepositoryEndpoint(String regionDomain) {
        if (regionDomain == null) throw new IllegalArgumentException("regionDomain is null.");
        return "https://api." + regionDomain + "/repository";
    }

    private static String combineURLs(String baseURL, String relativeURL) {
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

    /**
     * Returns a Header Map containing the header name and value.
     *
     * @param headers The headers.
     * @return Returns a Header Map containing the header name and value.
     */
    public static Map<String, String> getHeadersMap(Headers headers) {
        return headers.all().stream()
                .collect(Collectors.toMap(
                        Header::getName,
                        Header::getValue,
                        (value1, value2) -> String.format("%s, %s", value1, value2)));
    }

    /**
     * Returns a boolean value that determines whether or not to re-send the same API request.
     *
     * @param statusCode    The status code.
     * @param requestMethod The HTTP request method.
     * @return Returns a boolean value that determines whether or not to re-send the same API request.
     */
    public static boolean isRetryableStatusCode(int statusCode, HttpMethod requestMethod) {
        boolean isIdempotent = !requestMethod.toString().equals("POST");
        return (statusCode >= 500 || statusCode == 408) && isIdempotent;
    }

    /**
     * Returns a combined header string contain the prefer and maxSize String.
     *
     * @param prefer  The prefer header.
     * @param maxSize The max size header number.
     * @return Returns a Header Map containing the header name and value.
     */
    public static String mergeMaxSizeIntoPrefer(int maxSize, String prefer) {
        if (maxSize == 0) return prefer;
        else
            return prefer == null
                    ? String.format("maxpagesize=%d", maxSize)
                    : String.format("%s; maxpagesize=%d", prefer, maxSize);
    }

    protected static Map<String, Object> getParametersWithNonDefaultValue(
            String[] parameterTypes, String[] parameterNames, Object[] parameterValues) {
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
                return value.toString().equals("0");
            case "boolean":
                return value.toString().equals("false");
        }
        return false;
    }

    public static <TResponse> TResponse sendRequestWithRetry(
            UnirestInstance httpClient,
            HttpRequestHandler httpRequestHandler,
            String url,
            String requestMethod,
            String contentType,
            Object requestBody,
            String queryStringFields,
            Collection<?> queryStringFieldList,
            Map<String, Object> queryParameters,
            Map<String, Object> pathParameters,
            Map<String, String> headerParametersWithStringTypeValue,
            boolean isDynamicFieldValues,
            Function<HttpResponse<Object>, TResponse> parseResponse) {
        int retryCount = 0;
        int maxRetries = 1;
        boolean shouldRetry = true;
        HttpResponse<Object> httpResponse = null;
        while (retryCount <= maxRetries && shouldRetry) {
            try {
                String requestUrl =
                        ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
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
                        httpResponse = httpRequestWithBody.body(requestBody).asObject(Object.class);
                    }
                } else {
                    if (isDynamicFieldValues || requestMethod.equals("HEAD")) {
                        httpResponse = httpRequest.asObject(new HashMap<String, String[]>().getClass());
                    } else {
                        httpResponse = httpRequest.asObject(Object.class);
                    }
                }
                HttpMethod httpMethod = httpRequest.getHttpMethod();
                int statusCode = httpResponse.getStatus();
                shouldRetry = httpRequestHandler.afterSend(new ResponseImpl((short) statusCode))
                        || ApiClientUtils.isRetryableStatusCode(statusCode, httpMethod);
                if (!shouldRetry || requestMethod.equals("HEAD")) {
                    return parseResponse.apply(httpResponse);
                }
            } catch (Exception err) {
                if (err instanceof ApiException || retryCount >= maxRetries) {
                    throw err;
                }
                shouldRetry = true;
            } finally {
                retryCount++;
            }
        }
        if (httpResponse == null) {
            throw new IllegalStateException("Undefined response, there is a bug");
        }
        return parseResponse.apply(httpResponse);
    }
}
