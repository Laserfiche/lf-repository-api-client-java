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
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;

public class TagDefinitionsClientImpl extends ApiClient implements TagDefinitionsClient {

    public TagDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    /**
     * - Returns all tag definitions in the repository.
     * - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param parameters An object of type ParametersForGetTagDefinitions which encapsulates the parameters of getTagDefinitions method.
     *  @return ODataValueContextOfIListOfWTagInfo The return value
     */
    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitions(ParametersForGetTagDefinitions parameters) {
        return doGetTagDefinitions(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions", parameters);
    }

    private ODataValueContextOfIListOfWTagInfo doGetTagDefinitions(String url, ParametersForGetTagDefinitions parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[] { "String", "String", "String", "int", "int", "boolean" }, new String[] { "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObject(Object.class);
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
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(), (parsingException.isPresent() ? parsingException.get().getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetTagDefinitions(nextLink, new ParametersForGetTagDefinitions().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTagDefinitionsForEach(Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback, Integer maxPageSize, ParametersForGetTagDefinitions parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWTagInfo response = getTagDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTagDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    /**
     * - Returns a single tag definition.
     * - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed.
     * - Allowed OData query options: Select
     *
     *  @param parameters An object of type ParametersForGetTagDefinitionById which encapsulates the parameters of getTagDefinitionById method.
     *  @return WTagInfo The return value
     */
    @Override
    public WTagInfo getTagDefinitionById(ParametersForGetTagDefinitionById parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[] { "String", "String" }, new String[] { "culture", "$select" }, new Object[] { parameters.getCulture(), parameters.getSelect() });
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repoId", "tagId" }, new Object[] { parameters.getRepoId(), parameters.getTagId() });
        HttpResponse<Object> httpResponse = httpClient.get(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions/{tagId}").queryString(queryParameters).routeParam(pathParameters).asObject(Object.class);
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
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(), (parsingException.isPresent() ? parsingException.get().getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request tag definition id not found."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
