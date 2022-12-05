package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.SearchesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchesClientImpl extends ApiClient implements SearchesClient {

    public SearchesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public OperationProgress getSearchStatus(String repoId, String searchToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 201 || httpResponse.getStatus() == 202) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, OperationProgress.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request search token not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueOfBoolean cancelOrCloseSearch(String repoId, String searchToken) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        HttpResponse<Object> httpResponse = httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}")
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfBoolean.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request search token not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfContextHit getSearchContextHits(String repoId, String searchToken,
            Integer rowNumber, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetSearchContextHits(
                baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results/{rowNumber}/ContextHits", repoId,
                searchToken, rowNumber, prefer, select, orderby, top, skip, count);
    }

    private ODataValueContextOfIListOfContextHit doGetSearchContextHits(String url, String repoId, String searchToken,
            Integer rowNumber, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken", "rowNumber"},
                new Object[]{repoId, searchToken, rowNumber});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfContextHit.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request search token not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfContextHit getSearchContextHitsNextLink(String nextLink, Integer maxPageSize) {
        return doGetSearchContextHits(nextLink, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public void getSearchContextHitsForEach(Function<ODataValueContextOfIListOfContextHit, Boolean> callback,
            Integer maxPageSize, String repoId, String searchToken, Integer rowNumber, String prefer, String select,
            String orderby, Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfContextHit response = getSearchContextHits(repoId, searchToken, rowNumber, prefer,
                select, orderby, top, skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getSearchContextHitsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public AcceptedOperation createSearchOperation(String repoId, AdvancedSearchRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        HttpResponse<Object> httpResponse = httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Searches")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 201) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, AcceptedOperation.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Operation limit or request limit reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfEntry getSearchResults(String repoId, String searchToken, Boolean groupByEntryType,
            Boolean refresh, String[] fields, Boolean formatFields, String prefer, String culture, String select,
            String orderby, Integer top, Integer skip, Boolean count) {
        return doGetSearchResults(baseUrl + "/v1/Repositories/{repoId}/Searches/{searchToken}/Results", repoId,
                searchToken, groupByEntryType, refresh, fields, formatFields, prefer, culture, select, orderby, top,
                skip, count);
    }

    private ODataValueContextOfIListOfEntry doGetSearchResults(String url, String repoId, String searchToken,
            Boolean groupByEntryType, Boolean refresh, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"groupByEntryType", "refresh", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{groupByEntryType, refresh, fields, formatFields, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "searchToken"},
                new Object[]{repoId, searchToken});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString("fields", (queryParameters.get("fields") != null) ? (queryParameters.get(
                        "fields") instanceof String ? Arrays.asList(
                        queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList())
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfEntry.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request search token not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfEntry getSearchResultsNextLink(String nextLink, Integer maxPageSize) {
        return doGetSearchResults(nextLink, null, null, null, null, null, null,
                mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public void getSearchResultsForEach(Function<ODataValueContextOfIListOfEntry, Boolean> callback,
            Integer maxPageSize, String repoId, String searchToken, Boolean groupByEntryType, Boolean refresh,
            String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfEntry response = getSearchResults(repoId, searchToken, groupByEntryType, refresh,
                fields, formatFields, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getSearchResultsNextLink(nextLink, maxPageSize);
        }
    }
}
