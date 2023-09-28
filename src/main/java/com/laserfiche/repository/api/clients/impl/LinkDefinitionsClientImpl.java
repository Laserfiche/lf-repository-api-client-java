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
import com.laserfiche.repository.api.clients.LinkDefinitionsClient;

/**
 * The Laserfiche Repository LinkDefinitions API client.
 */
public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    public LinkDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public LinkDefinitionCollectionResponse listLinkDefinitions(ParametersForListLinkDefinitions parameters) {
        return doListLinkDefinitions(baseUrl + "/v2/Repositories/{repositoryId}/LinkDefinitions", parameters);
    }

    private LinkDefinitionCollectionResponse doListLinkDefinitions(String url, ParametersForListLinkDefinitions parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "String", "int", "int", "boolean" }, new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repositoryId" }, new Object[] { parameters.getRepositoryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, LinkDefinitionCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, LinkDefinitionCollectionResponse.class);
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
    public LinkDefinitionCollectionResponse listLinkDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doListLinkDefinitions(nextLink, new ParametersForListLinkDefinitions().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listLinkDefinitionsForEach(Function<LinkDefinitionCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListLinkDefinitions parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        LinkDefinitionCollectionResponse response = listLinkDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listLinkDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public LinkDefinition getLinkDefinition(ParametersForGetLinkDefinition parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "$select" }, new Object[] { parameters.getSelect() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "int" }, new String[] { "repositoryId", "linkDefinitionId" }, new Object[] { parameters.getRepositoryId(), parameters.getLinkDefinitionId() });
        Function<HttpResponse<Object>, LinkDefinition> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, LinkDefinition.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/LinkDefinitions/{linkDefinitionId}", "GET", null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}
