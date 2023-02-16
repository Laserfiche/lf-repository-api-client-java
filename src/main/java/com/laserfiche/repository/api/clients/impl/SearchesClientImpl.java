package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository Searches API client.
 */
public class SearchesClientImpl extends ApiClient implements SearchesClient {

    private HttpRequestHandler httpRequestHandler;

    public SearchesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public OperationProgress getSearchStatus(ParametersForGetSearchStatus parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"repoId", "searchToken"},
                new Object[]{parameters.getRepoId(), parameters.getSearchToken()});
        Function<HttpResponse<Object>, OperationProgress> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 201 || httpResponse.getStatus() == 202) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, OperationProgress.class);
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return sendRequestWithRetry(httpClient, objectMapper, OperationProgress.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}", "GET", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueOfBoolean cancelOrCloseSearch(ParametersForCancelOrCloseSearch parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"repoId", "searchToken"},
                new Object[]{parameters.getRepoId(), parameters.getSearchToken()});
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return sendRequestWithRetry(httpClient, objectMapper, ODataValueOfBoolean.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}", "DELETE", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfContextHit getSearchContextHits(ParametersForGetSearchContextHits parameters) {
        return doGetSearchContextHits(
                baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits",
                parameters);
    }

    private ODataValueContextOfIListOfContextHit doGetSearchContextHits(String url,
            ParametersForGetSearchContextHits parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int"}, new String[]{"repoId", "searchToken", "rowNumber"},
                new Object[]{parameters.getRepoId(), parameters.getSearchToken(), parameters.getRowNumber()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfContextHit> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfContextHit.class);
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return sendRequestWithRetry(httpClient, objectMapper, ODataValueContextOfIListOfContextHit.class,
                httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters,
                headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfContextHit getSearchContextHitsNextLink(String nextLink, int maxPageSize) {
        return doGetSearchContextHits(nextLink, new ParametersForGetSearchContextHits().setPrefer(
                ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getSearchContextHitsForEach(Function<ODataValueContextOfIListOfContextHit, Boolean> callback,
            Integer maxPageSize, ParametersForGetSearchContextHits parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfContextHit response = getSearchContextHits(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getSearchContextHitsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public AcceptedOperation createSearchOperation(ParametersForCreateSearchOperation parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return sendRequestWithRetry(httpClient, objectMapper, AcceptedOperation.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Searches", "POST", "application/json", parameters.getRequestBody(),
                null, null, null, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfEntry getSearchResults(ParametersForGetSearchResults parameters) {
        return doGetSearchResults(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results", parameters);
    }

    private ODataValueContextOfIListOfEntry doGetSearchResults(String url, ParametersForGetSearchResults parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"boolean", "boolean", "String[]", "boolean", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"groupByEntryType", "refresh", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isGroupByEntryType(), parameters.isRefresh(), parameters.getFields(), parameters.isFormatFields(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"repoId", "searchToken"},
                new Object[]{parameters.getRepoId(), parameters.getSearchToken()});
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
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return sendRequestWithRetry(httpClient, objectMapper, ODataValueContextOfIListOfEntry.class, httpRequestHandler,
                url, "GET", null, null, "fields", (queryParameters.get("fields") != null) ? (queryParameters.get(
                        "fields") instanceof String ? Arrays.asList(
                        queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList(),
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfEntry getSearchResultsNextLink(String nextLink, int maxPageSize) {
        return doGetSearchResults(nextLink, new ParametersForGetSearchResults().setPrefer(
                ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getSearchResultsForEach(Function<ODataValueContextOfIListOfEntry, Boolean> callback,
            Integer maxPageSize, ParametersForGetSearchResults parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfEntry response = getSearchResults(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getSearchResultsNextLink(nextLink, maxPageSize);
        }
    }
}

