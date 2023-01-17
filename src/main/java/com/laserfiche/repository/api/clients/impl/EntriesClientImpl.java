package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId,
            String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return doGetFieldValues(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", repoId, entryId, prefer,
                formatValue, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfFieldValue> doGetFieldValues(String url, String repoId,
            Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"formatValue", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{formatValue, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
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
                            return objectMapper.readValue(jsonString, ODataValueContextOfIListOfFieldValue.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
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
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetFieldValues(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<Void> getFieldValuesForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, Integer entryId, String prefer, Boolean formatValue, String culture,
            String select, String orderby, Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfFieldValue> response = getFieldValues(repoId, entryId, prefer,
                formatValue, culture, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getFieldValuesNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId,
            Map<String, FieldToUpdate> requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture"}, new Object[]{culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, ODataValueOfIListOfFieldValue.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Requested entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<CreateEntryResult> importDocument(String repoId, Integer parentEntryId, String fileName,
            Boolean autoRename, String culture, InputStream inputStream, PostEntryWithEdocMetadataRequest requestBody) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "parentEntryId", "fileName"},
                new Object[]{repoId, parentEntryId, fileName});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .field("electronicDocument", inputStream, fileName)
                .field("request", toJson(requestBody))
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    String jsonString = new JSONObject(body).toString();
                    try {
                        //String jsonString = new JSONObject(body).toString();
                        ProblemDetails problemDetails = deserializeToProblemDetails(jsonString);
                        return objectMapper.readValue(jsonString, CreateEntryResult.class);
                    } catch (JsonProcessingException | IllegalStateException e) {
                        try {
                            ProblemDetails problemDetails = deserializeToProblemDetails(jsonString);
                        } catch (JsonProcessingException ex) {
                            throw new RuntimeException(ex);
                        }
                        throw new RuntimeException(e);
                    }
//                    if (httpResponse.getStatus() == 201 || httpResponse.getStatus() == 409) {
//                        try {
//                            String jsonString = new JSONObject(body).toString();
//                            return objectMapper.readValue(jsonString, CreateEntryResult.class);
//                        } catch (JsonProcessingException | IllegalStateException e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                    } else {
//                        ProblemDetails problemDetails;
//                        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
//                        try {
//                            String jsonString = new JSONObject(body).toString();
//                            problemDetails = deserializeToProblemDetails(jsonString);
//                        } catch (JsonProcessingException | IllegalStateException e) {
//                            Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
//                            throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
//                                    (parsingException.isPresent() ? parsingException
//                                            .get()
//                                            .getOriginalBody() : null), headersMap, null);
//                        }
//                        if (httpResponse.getStatus() == 400)
//                            throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else if (httpResponse.getStatus() == 401)
//                            throw new ApiException(
//                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else if (httpResponse.getStatus() == 403)
//                            throw new ApiException(
//                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else if (httpResponse.getStatus() == 404)
//                            throw new ApiException(decideErrorMessage(problemDetails, "Parent entry is not found."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else if (httpResponse.getStatus() == 429)
//                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else if (httpResponse.getStatus() == 500)
//                            throw new ApiException(
//                                    decideErrorMessage(problemDetails, "Document creation is complete failure."),
//                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//                        else
//                            throw new RuntimeException(httpResponse.getStatusText());
//                    }
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId,
            Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkValuesFromEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", repoId, entryId,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> doGetLinkValuesFromEntry(String url,
            String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
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
                            return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWEntryLinkInfo.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
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
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetLinkValuesFromEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<Void> getLinkValuesFromEntryForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> response = getLinkValuesFromEntry(repoId, entryId,
                prefer, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId,
            List<PutLinksRequest> requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, ODataValueOfIListOfWEntryLinkInfo.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId,
            PutTemplateRequest requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture"}, new Object[]{culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Entry.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Entry.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<Map<String, String[]>> getDynamicFieldValues(String repoId, Integer entryId,
            GetDynamicFieldLogicValueRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync((new HashMap<String, String[]>()).getClass())
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, new HashMap<String, String[]>().getClass());
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry not found."),
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
    public CompletableFuture<FindEntryResult> getEntryByPath(String repoId, String fullPath,
            Boolean fallbackToClosestAncestor) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"fullPath", "fallbackToClosestAncestor"},
                new Object[]{fullPath, fallbackToClosestAncestor});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/ByPath")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, FindEntryResult.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Requested entry path not found"),
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
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId,
            CopyAsyncRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Operation limit or request limit reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"$select"}, new Object[]{select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Entry.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Requested entry id not found."),
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
    public CompletableFuture<Entry> moveOrRenameEntry(String repoId, Integer entryId, PatchEntryRequest requestBody,
            Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Entry.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 409)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry name conflicts."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId,
            DeleteEntryWithAuditReason requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Operation limit or request limit reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }

    @Override
    public CompletableFuture<Void> exportDocumentWithAuditReason(String repoId, Integer entryId,
            GetEdocWithAuditReasonRequest requestBody, String range, Consumer<InputStream> inputStreamConsumer) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"range"}, new Object[]{range});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            httpClient
                    .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
                    .routeParam(pathParameters)
                    .headers(headerParametersWithStringTypeValue)
                    .contentType("application/json")
                    .body(requestBody)
                    .thenConsume(rawResponse -> {
                        if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                            inputStreamConsumer.accept(rawResponse.getContent());
                        } else {
                            ProblemDetails problemDetails = null;
                            Map<String, String> headersMap = getHeadersMap(rawResponse.getHeaders());
                            try {
                                String jsonString = rawResponse.getContentAsString();
                                problemDetails = deserializeToProblemDetails(jsonString);
                            } catch (JsonProcessingException | IllegalStateException e) {
                                exception[0] = new ApiException(rawResponse.getStatusText(), rawResponse.getStatus(),
                                        rawResponse.getContentAsString(), headersMap, null);
                            }
                            if (rawResponse.getStatus() == 400)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Invalid or bad request."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 401)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 403)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access denied for the operation."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 404)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Request entry id not found."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 423)
                                exception[0] = new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 429)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Rate limit is reached."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else
                                exception[0] = new RuntimeException(rawResponse.getStatusText());
                        }
                    });
            if (exception[0] != null) {
                throw exception[0];
            } else {
                return CompletableFuture.completedFuture(null);
            }
        }
    }

    @Override
    public CompletableFuture<Void> exportDocument(String repoId, Integer entryId, String range,
            Consumer<InputStream> inputStreamConsumer) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"range"}, new Object[]{range});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            httpClient
                    .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                    .routeParam(pathParameters)
                    .headers(headerParametersWithStringTypeValue)
                    .thenConsume(rawResponse -> {
                        if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                            inputStreamConsumer.accept(rawResponse.getContent());
                        } else {
                            ProblemDetails problemDetails = null;
                            Map<String, String> headersMap = getHeadersMap(rawResponse.getHeaders());
                            try {
                                String jsonString = rawResponse.getContentAsString();
                                problemDetails = deserializeToProblemDetails(jsonString);
                            } catch (JsonProcessingException | IllegalStateException e) {
                                exception[0] = new ApiException(rawResponse.getStatusText(), rawResponse.getStatus(),
                                        rawResponse.getContentAsString(), headersMap, null);
                            }
                            if (rawResponse.getStatus() == 400)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Invalid or bad request."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 401)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 403)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access denied for the operation."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 404)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Request entry id not found."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 423)
                                exception[0] = new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 429)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Rate limit is reached."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else
                                exception[0] = new RuntimeException(rawResponse.getStatusText());
                        }
                    });
            if (exception[0] != null) {
                throw exception[0];
            } else {
                return CompletableFuture.completedFuture(null);
            }
        }
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<Map<String, String>> getDocumentContentType(String repoId, Integer entryId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .head(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
                .asObjectAsync(new HashMap<String, String>().getClass())
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        return httpResponse
                                .getHeaders()
                                .all()
                                .stream()
                                .collect(Collectors.toMap(Header::getName, Header::getValue));
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"pageRange"}, new Object[]{pageRange});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
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
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId,
            Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture,
            String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetEntryListing(
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children", repoId,
                entryId, groupByEntryType, fields, formatFields, prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntry> doGetEntryListing(String url, String repoId,
            Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"groupByEntryType", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{groupByEntryType, fields, formatFields, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient
                .get(url)
                .queryString("fields", (queryParameters.get("fields") != null) ? (queryParameters.get(
                        "fields") instanceof String ? Arrays.asList(
                        queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList())
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
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
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(
                                    decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
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
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetEntryListing(nextLink, null, null, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null),
                null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getEntryListingForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, Integer entryId, Boolean groupByEntryType, String[] fields,
            Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfEntry> response = getEntryListing(repoId, entryId, groupByEntryType,
                fields, formatFields, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getEntryListingNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId,
            PostEntryChildrenRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 201) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, Entry.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 409)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry name conflicts."),
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
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId,
            String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTagsAssignedToEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", repoId, entryId,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWTagInfo> doGetTagsAssignedToEntry(String url, String repoId,
            Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
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
                            return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWTagInfo.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
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
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetTagsAssignedToEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<Void> getTagsAssignedToEntryForEach(
            Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback,
            Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> response = getTagsAssignedToEntry(repoId, entryId, prefer,
                select, orderby, top, skip, count);
        while (response != null && callback
                .apply(response)
                .join()) {
            String nextLink = response
                    .join()
                    .getOdataNextLink();
            response = getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId,
            PutTagRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync(Object.class)
                .thenApply(httpResponse -> {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, ODataValueOfIListOfWTagInfo.class);
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
                            throw new ApiException(decideErrorMessage(problemDetails, "Request id not found."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 423)
                            throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                });
    }
}
