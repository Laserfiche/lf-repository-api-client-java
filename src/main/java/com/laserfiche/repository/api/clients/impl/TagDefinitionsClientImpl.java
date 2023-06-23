package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.TagDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.clients.impl.model.WTagInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitions;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/**
 * The Laserfiche Repository TagDefinitions API client.
 */
public class TagDefinitionsClientImpl extends ApiClient implements TagDefinitionsClient {

    public TagDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitions(ParametersForGetTagDefinitions parameters) {
        return doGetTagDefinitions(baseUrl + "/v1/Repositories/{repoId}/TagDefinitions", parameters);
    }

    private ODataValueContextOfIListOfWTagInfo doGetTagDefinitions(
            String url, ParametersForGetTagDefinitions parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String", "String", "int", "int", "boolean"},
                new String[] {"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[] {
                    parameters.getCulture(),
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.getTop(),
                    parameters.getSkip(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"repoId"}, new Object[] {parameters.getRepoId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfWTagInfo> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, ODataValueContextOfIListOfWTagInfo.class);
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                url,
                "GET",
                null,
                null,
                null,
                null,
                queryParameters,
                pathParameters,
                headerParametersWithStringTypeValue,
                false,
                parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetTagDefinitions(
                nextLink,
                new ParametersForGetTagDefinitions()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTagDefinitionsForEach(
            Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTagDefinitions parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWTagInfo response = getTagDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTagDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public WTagInfo getTagDefinitionById(ParametersForGetTagDefinitionById parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String"},
                new String[] {"culture", "$select"},
                new Object[] {parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "int"},
                new String[] {"repoId", "tagId"},
                new Object[] {parameters.getRepoId(), parameters.getTagId()});
        Function<HttpResponse<Object>, WTagInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, WTagInfo.class);
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
        return ApiClientUtils.sendRequestWithRetry(
                httpClient,
                httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/TagDefinitions/{tagId}",
                "GET",
                null,
                null,
                null,
                null,
                queryParameters,
                pathParameters,
                new HashMap<String, String>(),
                false,
                parseResponse);
    }
}
