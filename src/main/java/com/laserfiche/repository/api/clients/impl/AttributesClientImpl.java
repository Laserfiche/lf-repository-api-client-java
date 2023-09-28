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
import com.laserfiche.repository.api.clients.AttributesClient;

/**
 * The Laserfiche Repository Attributes API client.
 */
public class AttributesClientImpl extends ApiClient implements AttributesClient {

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public AttributeCollectionResponse listAttributes(ParametersForListAttributes parameters) {
        return doListAttributes(baseUrl + "/v2/Repositories/{repositoryId}/Attributes", parameters);
    }

    private AttributeCollectionResponse doListAttributes(String url, ParametersForListAttributes parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "boolean", "String", "String", "int", "int", "boolean" }, new String[] { "everyone", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { parameters.isEveryone(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repositoryId" }, new Object[] { parameters.getRepositoryId() });
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "prefer" }, new Object[] { parameters.getPrefer() });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, AttributeCollectionResponse> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, AttributeCollectionResponse.class);
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
    public AttributeCollectionResponse listAttributesNextLink(String nextLink, int maxPageSize) {
        return doListAttributes(nextLink, new ParametersForListAttributes().setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listAttributesForEach(Function<AttributeCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListAttributes parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        AttributeCollectionResponse response = listAttributes(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listAttributesNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public Attribute getAttribute(ParametersForGetAttribute parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "boolean" }, new String[] { "everyone" }, new Object[] { parameters.isEveryone() });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[] { "String", "String" }, new String[] { "repositoryId", "attributeKey" }, new Object[] { parameters.getRepositoryId(), parameters.getAttributeKey() });
        Function<HttpResponse<Object>, Attribute> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, Attribute.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, baseUrl + "/v2/Repositories/{repositoryId}/Attributes/{attributeKey}", "GET", null, null, null, null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }
}
