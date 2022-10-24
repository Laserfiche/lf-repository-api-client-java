package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, Integer fieldDefinitionId,
            String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture", "$select"},
                new Object[]{culture, select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "fieldDefinitionId"},
                new Object[]{repoId, fieldDefinitionId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 200) {
                        try {
                            Object body = httpResponse.getBody();
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, WFieldInfo.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        Object body = httpResponse.getBody();
                        ProblemDetails problemDetails;
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = objectMapper.readValue(jsonString, ProblemDetails.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                        Map<String, String> headersMap = getHeadersMap(httpResponse);
                        if (httpResponse.getStatus() == 400)
                            throw new ApiException("Invalid or bad request.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 401)
                            throw new ApiException("Access token is invalid or expired.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException("Access denied for the operation.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException("Requested field definition id not found.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException("Rate limit is reached.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetFieldDefinitions(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions", repoId, prefer, culture,
                select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWFieldInfo> doGetFieldDefinitions(String url, String repoId,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 200) {
                        try {
                            Object body = httpResponse.getBody();
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWFieldInfo.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        Object body = httpResponse.getBody();
                        ProblemDetails problemDetails;
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = objectMapper.readValue(jsonString, ProblemDetails.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            return null;
                        }
                        Map<String, String> headersMap = getHeadersMap(httpResponse);
                        if (httpResponse.getStatus() == 400)
                            throw new ApiException("Invalid or bad request.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 401)
                            throw new ApiException("Access token is invalid or expired.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException("Access denied for the operation.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException("Not found.", httpResponse.getStatus(), httpResponse.getStatusText(),
                                    headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException("Rate limit is reached.", httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetFieldDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null, null);
    }

    @Override
    public CompletableFuture<Void> getFieldDefinitionsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfWFieldInfo>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWFieldInfo> response = getFieldDefinitions(repoId, prefer, culture,
                select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
