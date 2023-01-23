package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
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

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository FieldDefinitions API client.
 */
public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public WFieldInfo getFieldDefinitionById(ParametersForGetFieldDefinitionById parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"culture", "$select"}, new Object[]{parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "fieldDefinitionId"},
                new Object[]{parameters.getRepoId(), parameters.getFieldDefinitionId()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, WFieldInfo.class);
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
            if (httpResponse.getStatus() == 400)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 401)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 403)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 404)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 429)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
        }
    }

    @Override
    public ODataValueContextOfIListOfWFieldInfo getFieldDefinitions(ParametersForGetFieldDefinitions parameters) {
        return doGetFieldDefinitions(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions", parameters);
    }

    private ODataValueContextOfIListOfWFieldInfo doGetFieldDefinitions(String url,
            ParametersForGetFieldDefinitions parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "String", "int", "int", "boolean"},
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
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
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWFieldInfo.class);
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
            if (httpResponse.getStatus() == 400)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 401)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 403)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 404)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else if (httpResponse.getStatus() == 429)
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            else
                throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
        }
    }

    @Override
    public ODataValueContextOfIListOfWFieldInfo getFieldDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetFieldDefinitions(nextLink,
                new ParametersForGetFieldDefinitions().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getFieldDefinitionsForEach(Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetFieldDefinitions parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWFieldInfo response = getFieldDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
    }
}
