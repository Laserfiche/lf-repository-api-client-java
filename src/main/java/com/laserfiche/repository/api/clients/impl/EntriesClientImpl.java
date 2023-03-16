package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import kong.unirest.Header;
import kong.unirest.HttpMethod;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository Entries API client.
 */
public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public ODataValueContextOfIListOfFieldValue getFieldValues(ParametersForGetFieldValues parameters) {
        return doGetFieldValues(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", parameters);
    }

    private ODataValueContextOfIListOfFieldValue doGetFieldValues(String url, ParametersForGetFieldValues parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"formatValue", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isFormatValue(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfFieldValue> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfFieldValue.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null,
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfFieldValue getFieldValuesNextLink(String nextLink, int maxPageSize) {
        return doGetFieldValues(nextLink,
                new ParametersForGetFieldValues().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getFieldValuesForEach(Function<ODataValueContextOfIListOfFieldValue, Boolean> callback,
            Integer maxPageSize, ParametersForGetFieldValues parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfFieldValue response = getFieldValues(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getFieldValuesNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueOfIListOfFieldValue assignFieldValues(ParametersForAssignFieldValues parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"culture"}, new Object[]{parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, ODataValueOfIListOfFieldValue> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueOfIListOfFieldValue.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", "PUT", "application/json",
                parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(),
                false, parseResponse);
    }

    @Override
    public CreateEntryResult importDocument(ParametersForImportDocument parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String"}, new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int", "String"}, new String[]{"repoId", "parentEntryId", "fileName"},
                new Object[]{parameters.getRepoId(), parameters.getParentEntryId(), parameters.getFileName()});
        {
            Map<String, String> headerParametersWithStringTypeValue = new HashMap<String, String>();
            int retryCount = 0;
            int maxRetries = 1;
            boolean shouldRetry = true;
            HttpResponse<Object> httpResponse = null;
            String responseJson = null;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue,
                            httpRequestHandler);
                    httpResponse = httpClient
                            .post(requestUrl)
                            .field("electronicDocument", parameters.getInputStream(), parameters.getFileName())
                            .field("request", toJson(parameters.getRequestBody()))
                            .queryString(queryParameters)
                            .headers(headerParametersWithStringTypeValue)
                            .routeParam(pathParameters)
                            .asObject(Object.class);
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    HttpMethod httpMethod = HttpMethod.POST;
                    int statusCode = httpResponse.getStatus();
                    shouldRetry = httpRequestHandler.afterSend(
                            new ResponseImpl((short) statusCode)) || ApiClientUtils.isRetryableStatusCode(statusCode,
                            httpMethod);
                    if (!shouldRetry) {
                        if (httpResponse.getStatus() == 201) {
                            try {
                                responseJson = new JSONObject(body).toString();
                                return objectMapper.readValue(responseJson, CreateEntryResult.class);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                        } else {
                            ProblemDetails problemDetails;
                            if (httpResponse.getStatus() == 400 || httpResponse.getStatus() == 404 || httpResponse.getStatus() == 409 || httpResponse.getStatus() == 500) {
                                try {
                                    String jsonString = new JSONObject(body).toString();
                                    if (httpResponse
                                            .getHeaders()
                                            .getFirst("Content-Type")
                                            .contains("application/problem+json")) {
                                        problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper,
                                                jsonString);
                                    } else {
                                        problemDetails = ProblemDetails.create(httpResponse.getStatus(), headersMap);
                                        CreateEntryResult result = objectMapper.readValue(jsonString,
                                                CreateEntryResult.class);
                                        if (result != null && result.getOperations() != null) {
                                            problemDetails
                                                    .getExtensions()
                                                    .put(CreateEntryResult.class.getSimpleName(), result);
                                            String summary = ApiClientUtils.getCreateEntryResultSummary(result);
                                            if (summary != null && !summary
                                                    .trim()
                                                    .isEmpty())
                                                problemDetails.setTitle(summary);
                                        }
                                    }
                                } catch (Exception e) {
                                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                                }
                            } else {
                                try {
                                    String jsonString = new JSONObject(body).toString();
                                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                                } catch (Exception e) {
                                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                                }
                            }
                            throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                        }
                    } else {
                        if (retryCount == maxRetries) {
                            String jsonString = new JSONObject(body).toString();
                            ProblemDetails problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper,
                                    jsonString);
                            throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                        }
                    }
                } catch (Exception err) {
                    if (err instanceof ApiException) {
                        if (httpResponse.getStatus() == 400 || httpResponse.getStatus() == 404 || httpResponse.getStatus() == 409 || httpResponse.getStatus() == 500 || retryCount == maxRetries) {
                            throw err;
                        }
                        break;
                    }
                    if (retryCount >= maxRetries) {
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
            if (responseJson != null) {
                return objectMapper.readValue(responseJson, CreateEntryResult.class);
            } else {
                throw new RuntimeException("Response does not contain Json");
            }
        }
    }

    @Override
    public ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntry(
            ParametersForGetLinkValuesFromEntry parameters) {
        return doGetLinkValuesFromEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", parameters);
    }

    private ODataValueContextOfIListOfWEntryLinkInfo doGetLinkValuesFromEntry(String url,
            ParametersForGetLinkValuesFromEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfWEntryLinkInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfWEntryLinkInfo.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null,
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntryNextLink(String nextLink, int maxPageSize) {
        return doGetLinkValuesFromEntry(nextLink, new ParametersForGetLinkValuesFromEntry().setPrefer(
                ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getLinkValuesFromEntryForEach(Function<ODataValueContextOfIListOfWEntryLinkInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetLinkValuesFromEntry parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWEntryLinkInfo response = getLinkValuesFromEntry(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueOfIListOfWEntryLinkInfo assignEntryLinks(ParametersForAssignEntryLinks parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, ODataValueOfIListOfWEntryLinkInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueOfIListOfWEntryLinkInfo.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", "PUT", "application/json",
                parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false,
                parseResponse);
    }

    @Override
    public Entry writeTemplateValueToEntry(ParametersForWriteTemplateValueToEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"culture"}, new Object[]{parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Entry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Entry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template", "PUT", "application/json",
                parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(),
                false, parseResponse);
    }

    @Override
    public Entry deleteAssignedTemplate(ParametersForDeleteAssignedTemplate parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Entry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Entry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template", "DELETE", null, null, null, null,
                null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Map<String, String[]> getDynamicFieldValues(ParametersForGetDynamicFieldValues parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Map<String, String[]>> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, new HashMap<String, String[]>().getClass());
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue", "POST",
                "application/json", parameters.getRequestBody(), null, null, null, pathParameters,
                new HashMap<String, String>(), true, parseResponse);
    }

    @Override
    public FindEntryResult getEntryByPath(ParametersForGetEntryByPath parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "boolean"}, new String[]{"fullPath", "fallbackToClosestAncestor"},
                new Object[]{parameters.getFullPath(), parameters.isFallbackToClosestAncestor()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Function<HttpResponse<Object>, FindEntryResult> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, FindEntryResult.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/ByPath", "GET", null, null, null, null, queryParameters,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public AcceptedOperation copyEntryAsync(ParametersForCopyEntryAsync parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String"}, new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, AcceptedOperation> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 201) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, AcceptedOperation.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync", "POST",
                "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters,
                new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry getEntry(ParametersForGetEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"$select"}, new Object[]{parameters.getSelect()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Entry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Entry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}", "GET", null, null, null, null, queryParameters,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry moveOrRenameEntry(ParametersForMoveOrRenameEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String"}, new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Entry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Entry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}", "PATCH", "application/json",
                parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(),
                false, parseResponse);
    }

    @Override
    public AcceptedOperation deleteEntryInfo(ParametersForDeleteEntryInfo parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, AcceptedOperation> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 201) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, AcceptedOperation.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}", "DELETE", "application/json",
                parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false,
                parseResponse);
    }

    @Override
    public void exportDocumentWithAuditReason(ParametersForExportDocumentWithAuditReason parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            int retryCount = 0;
            int maxRetries = 1;
            final int[] statusCode = {0};
            boolean shouldRetry = true;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue,
                            httpRequestHandler);
                    httpClient
                            .post(requestUrl)
                            .routeParam(pathParameters)
                            .headers(headerParametersWithStringTypeValue)
                            .contentType("application/json")
                            .body(parameters.getRequestBody())
                            .thenConsume(rawResponse -> {
                                statusCode[0] = rawResponse.getStatus();
                                if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                                    parameters
                                            .getInputStreamConsumer()
                                            .accept(rawResponse.getContent());
                                } else {
                                    ProblemDetails problemDetails = null;
                                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(
                                            rawResponse.getHeaders());
                                    try {
                                        String jsonString = rawResponse.getContentAsString();
                                        problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper,
                                                jsonString);
                                    } catch (Exception e) {
                                        exception[0] = ApiException.create(rawResponse.getStatus(), headersMap, null,
                                                e);
                                    }
                                    exception[0] = ApiException.create(rawResponse.getStatus(), headersMap,
                                            problemDetails, null);
                                }
                            });
                    shouldRetry = httpRequestHandler.afterSend(
                            new ResponseImpl((short) statusCode[0])) || ApiClientUtils.isRetryableStatusCode(
                            statusCode[0], HttpMethod.POST);
                    if (!shouldRetry) {
                        if (exception[0] != null) {
                            throw exception[0];
                        }
                    } else {
                        if (retryCount == maxRetries) {
                            throw exception[0];
                        }
                        exception[0] = null;
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
        }
    }

    @Override
    public InputStream exportDocumentWithAuditReasonAsStream(ParametersForExportDocumentWithAuditReason parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            int retryCount = 0;
            int maxRetries = 1;
            InputStream inputStream = null;
            HttpResponse<byte[]> httpResponse;
            boolean shouldRetry = true;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue,
                            httpRequestHandler);
                    httpResponse = httpClient
                            .post(requestUrl)
                            .routeParam(pathParameters)
                            .headers(headerParametersWithStringTypeValue)
                            .contentType("application/json")
                            .body(parameters.getRequestBody())
                            .asBytes();
                    int statusCode = httpResponse.getStatus();
                    shouldRetry = httpRequestHandler.afterSend(
                            new ResponseImpl((short) statusCode)) || ApiClientUtils.isRetryableStatusCode(
                            statusCode, HttpMethod.POST);
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    ProblemDetails problemDetails;
                    if (!shouldRetry) {
                        if (statusCode == 200 || statusCode == 206) {
                            inputStream = new ByteArrayInputStream(httpResponse.getBody());
                            return inputStream;
                        } else {
                            try {
                                String jsonString = new String(httpResponse.getBody());
                                problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                            throw ApiClientUtils.createApiExceptionForExportDocumentAsStream(httpResponse,
                                    problemDetails);
                        }
                    } else {
                        if (retryCount == maxRetries) {
                            try {
                                String jsonString = new String(httpResponse.getBody());
                                problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                            throw ApiClientUtils.createApiExceptionForExportDocumentAsStream(httpResponse,
                                    problemDetails);
                        }
                    }
                } catch (Exception err) {
                    if (err instanceof ApiException || retryCount == maxRetries) {
                        throw err;
                    }
                    shouldRetry = true;
                } finally {
                    retryCount++;
                }
            }
            if (inputStream == null) {
                throw new IllegalStateException("Undefined response, there is a bug");
            }
            return inputStream;
        }
    }

    @Override
    public void exportDocument(ParametersForExportDocument parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            int retryCount = 0;
            int maxRetries = 1;
            final int[] statusCode = {0};
            boolean shouldRetry = true;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue,
                            httpRequestHandler);
                    httpClient
                            .get(requestUrl)
                            .routeParam(pathParameters)
                            .headers(headerParametersWithStringTypeValue)
                            .thenConsume(rawResponse -> {
                                statusCode[0] = rawResponse.getStatus();
                                if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                                    parameters
                                            .getInputStreamConsumer()
                                            .accept(rawResponse.getContent());
                                } else {
                                    ProblemDetails problemDetails = null;
                                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(
                                            rawResponse.getHeaders());
                                    try {
                                        String jsonString = rawResponse.getContentAsString();
                                        problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper,
                                                jsonString);
                                    } catch (Exception e) {
                                        exception[0] = ApiException.create(rawResponse.getStatus(), headersMap, null,
                                                e);
                                    }
                                    exception[0] = ApiException.create(rawResponse.getStatus(), headersMap,
                                            problemDetails, null);
                                }
                            });
                    shouldRetry = httpRequestHandler.afterSend(
                            new ResponseImpl((short) statusCode[0])) || ApiClientUtils.isRetryableStatusCode(
                            statusCode[0], HttpMethod.GET);
                    if (!shouldRetry) {
                        if (exception[0] != null) {
                            throw exception[0];
                        }
                    } else {
                        if (retryCount == maxRetries) {
                            throw exception[0];
                        }
                        exception[0] = null;
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
        }
    }

    @Override
    public InputStream exportDocumentAsStream(ParametersForExportDocument parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            int retryCount = 0;
            int maxRetries = 1;
            InputStream inputStream = null;
            HttpResponse<byte[]> httpResponse;
            boolean shouldRetry = true;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue,
                            httpRequestHandler);
                    httpResponse = httpClient
                            .get(requestUrl)
                            .routeParam(pathParameters)
                            .headers(headerParametersWithStringTypeValue)
                            .asBytes();
                    int statusCode = httpResponse.getStatus();
                    shouldRetry = httpRequestHandler.afterSend(
                            new ResponseImpl((short) statusCode)) || ApiClientUtils.isRetryableStatusCode(
                            statusCode, HttpMethod.GET);
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    ProblemDetails problemDetails;
                    if (!shouldRetry) {
                        if (statusCode == 200 || statusCode == 206) {
                            inputStream = new ByteArrayInputStream(httpResponse.getBody());
                            return inputStream;
                        } else {
                            try {
                                String jsonString = new String(httpResponse.getBody());
                                problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                            throw ApiClientUtils.createApiExceptionForExportDocumentAsStream(httpResponse,
                                    problemDetails);
                        }
                    } else {
                        if (retryCount == maxRetries) {
                            try {
                                String jsonString = new String(httpResponse.getBody());
                                problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                            throw ApiClientUtils.createApiExceptionForExportDocumentAsStream(httpResponse,
                                    problemDetails);
                        }
                    }
                } catch (Exception err) {
                    if (err instanceof ApiException || retryCount == maxRetries) {
                        throw err;
                    }
                    shouldRetry = true;
                } finally {
                    retryCount++;
                }
            }
            if (inputStream == null) {
                throw new IllegalStateException("Undefined response, there is a bug");
            }
            return inputStream;
        }
    }

    @Override
    public ODataValueOfBoolean deleteDocument(ParametersForDeleteDocument parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, ODataValueOfBoolean> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueOfBoolean.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc", "DELETE",
                null, null, null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Map<String, String> getDocumentContentType(ParametersForGetDocumentContentType parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Map<String, String>> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                return httpResponse
                        .getHeaders()
                        .all()
                        .stream()
                        .collect(Collectors.toMap(Header::getName, Header::getValue));
            } else {
                throw ApiException.create(httpResponse.getStatus(), headersMap, null, null);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc", "HEAD",
                null, null, null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueOfBoolean deletePages(ParametersForDeletePages parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"pageRange"}, new Object[]{parameters.getPageRange()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, ODataValueOfBoolean> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueOfBoolean.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages", "DELETE",
                null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false,
                parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfEntry getEntryListing(ParametersForGetEntryListing parameters) {
        return doGetEntryListing(
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children",
                parameters);
    }

    private ODataValueContextOfIListOfEntry doGetEntryListing(String url, ParametersForGetEntryListing parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String[]", "boolean", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"groupByEntryType", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isGroupByEntryType(), parameters.getFields(), parameters.isFormatFields(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfEntry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfEntry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, "fields",
                (queryParameters.get("fields") != null) ? (queryParameters.get(
                        "fields") instanceof String ? Arrays.asList(
                        queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(),
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfEntry getEntryListingNextLink(String nextLink, int maxPageSize) {
        return doGetEntryListing(nextLink,
                new ParametersForGetEntryListing().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getEntryListingForEach(Function<ODataValueContextOfIListOfEntry, Boolean> callback, Integer maxPageSize,
            ParametersForGetEntryListing parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfEntry response = getEntryListing(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getEntryListingNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public Entry createOrCopyEntry(ParametersForCreateOrCopyEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "String"}, new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, Entry> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 201) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Entry.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children", "POST",
                "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters,
                new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntry(ParametersForGetTagsAssignedToEntry parameters) {
        return doGetTagsAssignedToEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", parameters);
    }

    private ODataValueContextOfIListOfWTagInfo doGetTagsAssignedToEntry(String url,
            ParametersForGetTagsAssignedToEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfWTagInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfWTagInfo.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null,
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntryNextLink(String nextLink, int maxPageSize) {
        return doGetTagsAssignedToEntry(nextLink, new ParametersForGetTagsAssignedToEntry().setPrefer(
                ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTagsAssignedToEntryForEach(Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetTagsAssignedToEntry parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWTagInfo response = getTagsAssignedToEntry(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueOfIListOfWTagInfo assignTags(ParametersForAssignTags parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "entryId"},
                new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Function<HttpResponse<Object>, ODataValueOfIListOfWTagInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueOfIListOfWTagInfo.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                throw ApiClientUtils.createApiException(httpResponse, problemDetails);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", "PUT", "application/json",
                parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false,
                parseResponse);
    }
}