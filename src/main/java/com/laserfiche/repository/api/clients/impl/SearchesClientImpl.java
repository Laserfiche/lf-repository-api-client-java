package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/**
 * The Laserfiche Repository Searches API client.
 */
public class SearchesClientImpl extends ApiClient implements SearchesClient {

    public SearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public StartTaskResponse startSearchEntry(ParametersForStartSearchEntry parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"repositoryId"}, new Object[] {parameters.getRepositoryId()});
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                baseUrl + "/v2/Repositories/{repositoryId}/Searches/SearchAsync",
                "POST",
                "application/json",
                parameters.getRequestBody(),
                null,
                null,
                null,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }

    @Override
    public SearchContextHitCollectionResponse listSearchContextHits(ParametersForListSearchContextHits parameters) {
        return doListSearchContextHits(
                baseUrl + "/v2/Repositories/{repositoryId}/Searches/{taskId}/Results/{rowNumber}/ContextHits",
                parameters);
    }

    private SearchContextHitCollectionResponse doListSearchContextHits(
            String url, ParametersForListSearchContextHits parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String", "int", "int", "boolean"},
                new String[] {"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[] {
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.getTop(),
                    parameters.getSkip(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String", "int"},
                new String[] {"repositoryId", "taskId", "rowNumber"},
                new Object[] {parameters.getRepositoryId(), parameters.getTaskId(), parameters.getRowNumber()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, SearchContextHitCollectionResponse> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, SearchContextHitCollectionResponse.class);
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                url,
                "GET",
                null,
                null,
                null,
                null,
                queryParameters,
                pathParameters,
                headerParametersWithStringTypeValue,
                false,
                parseResponse);
    }

    @Override
    public SearchContextHitCollectionResponse listSearchContextHitsNextLink(String nextLink, int maxPageSize) {
        return doListSearchContextHits(
                nextLink,
                new ParametersForListSearchContextHits()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listSearchContextHitsForEach(
            Function<SearchContextHitCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListSearchContextHits parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        SearchContextHitCollectionResponse response = listSearchContextHits(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listSearchContextHitsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public EntryCollectionResponse listSearchResults(ParametersForListSearchResults parameters) {
        return doListSearchResults(baseUrl + "/v2/Repositories/{repositoryId}/Searches/{taskId}/Results", parameters);
    }

    private EntryCollectionResponse doListSearchResults(String url, ParametersForListSearchResults parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {
                    "boolean", "boolean", "String[]", "boolean", "String", "String", "String", "int", "int", "boolean"
                },
                new String[] {
                    "groupByEntryType",
                    "refresh",
                    "fields",
                    "formatFieldValues",
                    "culture",
                    "$select",
                    "$orderby",
                    "$top",
                    "$skip",
                    "$count"
                },
                new Object[] {
                    parameters.isGroupByEntryType(),
                    parameters.isRefresh(),
                    parameters.getFields(),
                    parameters.isFormatFieldValues(),
                    parameters.getCulture(),
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.getTop(),
                    parameters.getSkip(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String"},
                new String[] {"repositoryId", "taskId"},
                new Object[] {parameters.getRepositoryId(), parameters.getTaskId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                url,
                "GET",
                null,
                null,
                "fields",
                (queryParameters.get("fields") != null)
                        ? (queryParameters.get("fields") instanceof String
                                ? Arrays.asList(queryParameters.remove("fields"))
                                : (List) queryParameters.remove("fields"))
                        : new ArrayList(),
                queryParameters,
                pathParameters,
                headerParametersWithStringTypeValue,
                false,
                parseResponse);
    }

    @Override
    public EntryCollectionResponse listSearchResultsNextLink(String nextLink, int maxPageSize) {
        return doListSearchResults(
                nextLink,
                new ParametersForListSearchResults()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listSearchResultsForEach(
            Function<EntryCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListSearchResults parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        EntryCollectionResponse response = listSearchResults(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listSearchResultsNextLink(nextLink, maxPageSize);
        }
    }
}
