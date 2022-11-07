package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AttributesClientImpl extends ApiClient implements AttributesClient {

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<Attribute> getTrusteeAttributeValueByKey(String repoId, String attributeKey,
            Boolean everyone) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"everyone"}, new Object[]{everyone});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "attributeKey"},
                new Object[]{repoId, attributeKey});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 200) {
                        try {
                            Object body = httpResponse.getBody();
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Attribute.class);
                        } catch (JsonProcessingException | IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        Object body = httpResponse.getBody();
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Requested attribute key not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairs(String repoId,
            Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTrusteeAttributeKeyValuePairs(baseUrl + "/v1/Repositories/{repoId}/Attributes", repoId, everyone,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfListOfAttribute> doGetTrusteeAttributeKeyValuePairs(String url,
            String repoId, Boolean everyone, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"everyone", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{everyone, select, orderby, top, skip, count});
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
                            return objectMapper.readValue(jsonString, ODataValueContextOfListOfAttribute.class);
                        } catch (JsonProcessingException | IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        Object body = httpResponse.getBody();
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(decideErrorMessage(problemDetails, "Not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsNextLink(
            String nextLink, Integer maxPageSize) {
        return doGetTrusteeAttributeKeyValuePairs(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null,
                null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getTrusteeAttributeKeyValuePairsForEach(
            Function<CompletableFuture<ODataValueContextOfListOfAttribute>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, Boolean everyone, String prefer, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfListOfAttribute> response = getTrusteeAttributeKeyValuePairs(repoId,
                everyone, prefer, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
