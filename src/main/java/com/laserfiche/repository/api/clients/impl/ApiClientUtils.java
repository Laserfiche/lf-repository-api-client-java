package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.BeforeSendResult;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.httphandlers.Request;
import com.laserfiche.api.client.httphandlers.RequestImpl;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.APIServerException;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryOperations;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryResult;
import kong.unirest.Header;
import kong.unirest.Headers;
import kong.unirest.HttpMethod;
import kong.unirest.HttpResponse;

import java.util.*;
import java.util.stream.Collectors;

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
                Integer entryId = operations
                        .getEntryCreate()
                        .getEntryId();
                if (entryId != null && entryId > 0) {
                    messages.add(String.format("EntryId=%s.", entryId));
                }
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getEntryCreate()
                        .getExceptions()));
            }
            if (operations.getSetEdoc() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetEdoc()
                        .getExceptions()));
            if (operations.getSetTemplate() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetTemplate()
                        .getExceptions()));
            if (operations.getSetFields() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetFields()
                        .getExceptions()));
            if (operations.getSetTags() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetTags()
                        .getExceptions()));
            if (operations.getSetLinks() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetLinks()
                        .getExceptions()));
        }
        return messages
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim();
    }

    private static String getErrorMessagesFromAPIServerExceptions(Collection<APIServerException> errors) {
        if (errors == null || errors.isEmpty())
            return null;
        return errors
                .stream()
                .filter(Objects::nonNull)
                .map(APIServerException::getMessage)
                .collect(Collectors.joining(" "))
                .trim();
    }

    protected static void createApiException(HttpResponse<Object> httpResponse, ProblemDetails problemDetails) {
        int statusCode = httpResponse.getStatus();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        throw ApiException.create(statusCode, headersMap, problemDetails, null);
    }

    public static String beforeSend(String url, Map<String, String> headerParametersWithStringTypeValue,
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

    private static String getRepositoryEndpoint(String regionDomain) {
        if (regionDomain == null)
            throw new IllegalArgumentException("regionDomain is null.");
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

    public static Map<String, String> getHeadersMap(Headers headers) {
        return headers
                .all()
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
    }

    public static boolean isRetryableStatusCode(int statusCode, HttpMethod requestMethod) {
        boolean isIdempotent = !requestMethod
                .toString()
                .equals("POST");
        return (statusCode >= 500 || statusCode == 408) && isIdempotent;
    }

    public static String mergeMaxSizeIntoPrefer(int maxSize, String prefer) {
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

    protected static boolean shouldThrowException(String requestMethod, String queryStringFields,
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
        if (err
                .getProblemDetails()
                .getTitle()
                .equals("Authentication failed (reason: Invalid service principal key. (6-45)).")) {
            return true;
        }
        if (err
                .getProblemDetails()
                .getTitle()
                .equals("The user account name or password is incorrect. [9010]")) {
            return true;
        }
        return false;
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
}
