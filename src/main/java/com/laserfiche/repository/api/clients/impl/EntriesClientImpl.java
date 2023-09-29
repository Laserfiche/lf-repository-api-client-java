package com.laserfiche.repository.api.clients.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.HttpMethod;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.ContentType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;
import com.laserfiche.repository.api.clients.EntriesClient;

/**
 * The Laserfiche Repository Entries API client.
 */
public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public StartTaskResponse startExportEntry(ParametersForStartExportEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "pageRange" }, new Object[] { parameters.getPageRange() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, StartTaskResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, StartTaskResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/ExportAsync", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public FieldCollectionResponse listFields(ParametersForListFields parameters) {
        return doListFields(baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Fields", parameters);
    }

    private FieldCollectionResponse doListFields(String url, ParametersForListFields parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "boolean", "String", "String", "String", "int", "int", "boolean" }, new String[] { "formatFieldValues", "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.isFormatFieldValues(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, FieldCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, FieldCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public FieldCollectionResponse listFieldsNextLink(String nextLink, int maxPageSize) {
        return doListFields(nextLink, new ParametersForListFields().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listFieldsForEach(Function<FieldCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListFields parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        FieldCollectionResponse response = listFields(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listFieldsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public FieldCollectionResponse setFields(ParametersForSetFields parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, FieldCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, FieldCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Fields", "PUT", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry setTemplate(ParametersForSetTemplate parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Template", "PUT", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry removeTemplate(ParametersForRemoveTemplate parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Template", "DELETE", null, null, null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry getEntry(ParametersForGetEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "$select" }, new Object[] { parameters.getSelect() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}", "GET", null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry updateEntry(ParametersForUpdateEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}", "PATCH", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public StartTaskResponse startDeleteEntry(ParametersForStartDeleteEntry parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, StartTaskResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, StartTaskResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}", "DELETE", "application/json", parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public CreateMultipartUploadUrlsResponse createMultipartUploadUrls(ParametersForCreateMultipartUploadUrls parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repositoryId" }, new Object[] { parameters.getRepositoryId() });
        Function<HttpResponse<Object>, CreateMultipartUploadUrlsResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, CreateMultipartUploadUrlsResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/CreateMultipartUploadUrls", "POST", "application/json", parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry importEntry(ParametersForImportEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        {
            Map<String, String> headerParametersWithStringTypeValue = new HashMap<String, String>();
            int retryCount = 0;
            int maxRetries = 1;
            boolean shouldRetry = true;
            HttpResponse<Object> httpResponse = null;
            while (retryCount <= maxRetries && shouldRetry) {
                try {
                    String url = baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/Import";
                    String requestUrl = ApiClientUtils.beforeSend(url, headerParametersWithStringTypeValue, httpRequestHandler);
                    httpResponse = httpClient.post(requestUrl).field("file", parameters.getInputStream(), parameters.getContentType() == null ? ContentType.APPLICATION_OCTET_STREAM : ContentType.create(parameters.getContentType()), parameters.getRequestBody().getName()).field("request", toJson(parameters.getRequestBody())).queryString(queryParameters).headers(headerParametersWithStringTypeValue).routeParam(pathParameters).asObject(Object.class);
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    HttpMethod httpMethod = HttpMethod.POST;
                    int statusCode = httpResponse.getStatus();
                    shouldRetry = httpRequestHandler.afterSend(new ResponseImpl((short) statusCode)) || ApiClientUtils.isRetryableStatusCode(statusCode, httpMethod);
                    if (!shouldRetry) {
                        if (httpResponse.getStatus() == 201) {
                            try {
                                String responseJson = new JSONObject(body).toString();
                                return objectMapper.readValue(responseJson, Entry.class);
                            } catch (Exception e) {
                                throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                            }
                        } else {
                            ProblemDetails problemDetails;
                            if (httpResponse.getStatus() == 403 || httpResponse.getStatus() == 404 || httpResponse.getStatus() == 500 || httpResponse.getStatus() == 400 || httpResponse.getStatus() == 401 || httpResponse.getStatus() == 413 || httpResponse.getStatus() == 429) {
                                try {
                                    String jsonString = new JSONObject(body).toString();
                                    if (httpResponse.getHeaders().getFirst("Content-Type").contains("application/problem+json")) {
                                        problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                                    } else {
                                        problemDetails = ProblemDetails.create(httpResponse.getStatus(), headersMap);
                                        ProblemDetails result = objectMapper.readValue(jsonString, ProblemDetails.class);
                                        if (result != null) {
                                            problemDetails.getExtensions().put(ProblemDetails.class.getSimpleName(), result);
                                            problemDetails.setTitle(result.getTitle());
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
                            ProblemDetails problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
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
            throw new IllegalStateException("Undefined response, there is a bug");
        }
    }

    @Override
    public GetEntryByPathResponse getEntryByPath(ParametersForGetEntryByPath parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "boolean" }, new String[] { "fullPath", "fallbackToClosestAncestor" }, new Object[] { parameters.getFullPath(), parameters.isFallbackToClosestAncestor() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repositoryId" }, new Object[] { parameters.getRepositoryId() });
        Function<HttpResponse<Object>, GetEntryByPathResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, GetEntryByPathResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/ByPath", "GET", null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ExportEntryResponse exportEntry(ParametersForExportEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "pageRange" }, new Object[] { parameters.getPageRange() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, ExportEntryResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ExportEntryResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Export", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public LinkCollectionResponse listLinks(ParametersForListLinks parameters) {
        return doListLinks(baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Links", parameters);
    }

    private LinkCollectionResponse doListLinks(String url, ParametersForListLinks parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "String", "int", "int", "boolean" }, new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, LinkCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, LinkCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public LinkCollectionResponse listLinksNextLink(String nextLink, int maxPageSize) {
        return doListLinks(nextLink, new ParametersForListLinks().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listLinksForEach(Function<LinkCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListLinks parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        LinkCollectionResponse response = listLinks(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listLinksNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public LinkCollectionResponse setLinks(ParametersForSetLinks parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, LinkCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, LinkCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Links", "PUT", "application/json", parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public TagCollectionResponse listTags(ParametersForListTags parameters) {
        return doListTags(baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Tags", parameters);
    }

    private TagCollectionResponse doListTags(String url, ParametersForListTags parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "String", "int", "int", "boolean" }, new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, TagCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, TagCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public TagCollectionResponse listTagsNextLink(String nextLink, int maxPageSize) {
        return doListTags(nextLink, new ParametersForListTags().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listTagsForEach(Function<TagCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTags parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        TagCollectionResponse response = listTags(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listTagsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public TagCollectionResponse setTags(ParametersForSetTags parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, TagCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, TagCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Tags", "PUT", "application/json", parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public StartTaskResponse startCopyEntry(ParametersForStartCopyEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, StartTaskResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, StartTaskResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/CopyAsync", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry deleteElectronicDocument(ParametersForDeleteElectronicDocument parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Document/Edoc", "DELETE", null, null, null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry deletePages(ParametersForDeletePages parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "pageRange" }, new Object[] { parameters.getPageRange() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Document/Pages", "DELETE", null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Map<String, String[]> listDynamicFieldValues(ParametersForListDynamicFieldValues parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Fields/GetDynamicFieldLogicValue", "POST", "application/json", parameters.getRequestBody(), null, null, null, pathParameters, new HashMap<String, String>(), true, parseResponse);
    }

    @Override
    public StartTaskResponse startImportUploadedParts(ParametersForStartImportUploadedParts parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Function<HttpResponse<Object>, StartTaskResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, StartTaskResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/ImportUploadedParts", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public EntryCollectionResponse listEntries(ParametersForListEntries parameters) {
        return doListEntries(baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/Children", parameters);
    }

    private EntryCollectionResponse doListEntries(String url, ParametersForListEntries parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "boolean", "String[]", "boolean", "String", "String", "String", "int", "int", "boolean" }, new String[] { "groupByEntryType", "fields", "formatFieldValues", "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.isGroupByEntryType(), parameters.getFields(), parameters.isFormatFieldValues(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, EntryCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, EntryCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, "fields", (queryParameters.get("fields") != null) ? (queryParameters.get("fields") instanceof String ? Arrays.asList(queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(), queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public EntryCollectionResponse listEntriesNextLink(String nextLink, int maxPageSize) {
        return doListEntries(nextLink, new ParametersForListEntries().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listEntriesForEach(Function<EntryCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListEntries parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        EntryCollectionResponse response = listEntries(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listEntriesNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public Entry createEntry(ParametersForCreateEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/Children", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public Entry copyEntry(ParametersForCopyEntry parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "culture" }, new Object[] { parameters.getCulture() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "entryId" }, new Object[] { parameters.getRepositoryId(), parameters.getEntryId() });
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Entries/{entryId}/Folder/Copy", "POST", "application/json", parameters.getRequestBody(), null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}
