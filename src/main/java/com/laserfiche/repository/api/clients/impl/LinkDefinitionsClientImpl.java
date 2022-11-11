package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    public LinkDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, Integer linkTypeId,
            String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"$select"}, new Object[]{select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "linkTypeId"},
                new Object[]{repoId, linkTypeId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, EntryLinkTypeInfo.class);
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Requested link type definition ID not found"),
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId,
            String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkDefinitions(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions", repoId, prefer, select,
                orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> doGetLinkDefinitions(String url,
            String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
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
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString,
                                    ODataValueContextOfIListOfEntryLinkTypeInfo.class);
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
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitionsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetLinkDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null);
    }

    @Override
    public CompletableFuture<Void> getLinkDefinitionsForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> response = getLinkDefinitions(repoId, prefer,
                select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getLinkDefinitionsNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }
}
