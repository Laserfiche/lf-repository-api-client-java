package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TagDefinitionsClientImpl extends ApiClient implements TagDefinitionsClient {

    public TagDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitions(String repoId, String prefer, String culture,
            String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTagDefinitions(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions", repoId, prefer, culture,
                select, orderby, top, skip, count);
    }

    private ODataValueContextOfIListOfWTagInfo doGetTagDefinitions(String url, String repoId, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
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
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitionsNextLink(String nextLink, Integer maxPageSize) {
        return doGetTagDefinitions(nextLink, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null, null);
    }

    @Override
    public void getTagDefinitionsForEach(Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize, String repoId, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfWTagInfo response = getTagDefinitions(repoId, prefer, culture, select, orderby, top,
                skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTagDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public WTagInfo getTagDefinitionById(String repoId, Integer tagId, String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture", "$select"},
                new Object[]{culture, select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "tagId"},
                new Object[]{repoId, tagId});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions/{tagId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, WTagInfo.class);
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
                throw new ApiException(decideErrorMessage(problemDetails, "Request tag definition id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
