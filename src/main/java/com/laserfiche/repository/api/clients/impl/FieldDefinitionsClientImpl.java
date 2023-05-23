package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitions;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository FieldDefinitions API client.
 */
public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient,
            HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public WFieldInfo getFieldDefinitionById(ParametersForGetFieldDefinitionById parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String"}, new String[]{"culture", "$select"},
                new Object[]{parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "int"}, new String[]{"repoId", "fieldDefinitionId"},
                new Object[]{parameters.getRepoId(), parameters.getFieldDefinitionId()});
        Function<HttpResponse<Object>, WFieldInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, WFieldInfo.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}", "GET", null, null, null,
                null, queryParameters, pathParameters, new HashMap<String, String>(), false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWFieldInfo getFieldDefinitions(ParametersForGetFieldDefinitions parameters) {
        return doGetFieldDefinitions(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions", parameters);
    }

    private ODataValueContextOfIListOfWFieldInfo doGetFieldDefinitions(String url,
            ParametersForGetFieldDefinitions parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[]{"String", "String", "String", "int", "int", "boolean"},
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, ODataValueContextOfIListOfWFieldInfo> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, ODataValueContextOfIListOfWFieldInfo.class);
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
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler, url, "GET", null, null, null, null,
                queryParameters, pathParameters, headerParametersWithStringTypeValue, false, parseResponse);
    }

    @Override
    public ODataValueContextOfIListOfWFieldInfo getFieldDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetFieldDefinitions(nextLink, new ParametersForGetFieldDefinitions().setPrefer(
                ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getFieldDefinitionsForEach(Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetFieldDefinitions parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWFieldInfo response = getFieldDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
    }
}
