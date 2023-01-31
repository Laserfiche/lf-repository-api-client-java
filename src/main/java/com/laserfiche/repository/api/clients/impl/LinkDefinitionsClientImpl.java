package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.LinkDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitions;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository LinkDefinitions API client.
 */
public class LinkDefinitionsClientImpl extends ApiClient implements LinkDefinitionsClient {

    public LinkDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public EntryLinkTypeInfo getLinkDefinitionById(ParametersForGetLinkDefinitionById parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"$select"}, new Object[]{parameters.getSelect()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "linkTypeId"}, new Object[]{parameters.getRepoId(), parameters.getLinkTypeId()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions/{linkTypeId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, EntryLinkTypeInfo.class);
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
    public ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitions(ParametersForGetLinkDefinitions parameters) {
        return doGetLinkDefinitions(baseUrl + "/v1/Repositories/{repoId}/LinkDefinitions", parameters);
    }

    private ODataValueContextOfIListOfEntryLinkTypeInfo doGetLinkDefinitions(String url,
            ParametersForGetLinkDefinitions parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
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
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfEntryLinkTypeInfo.class);
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
    public ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetLinkDefinitions(nextLink,
                new ParametersForGetLinkDefinitions().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getLinkDefinitionsForEach(Function<ODataValueContextOfIListOfEntryLinkTypeInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetLinkDefinitions parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfEntryLinkTypeInfo response = getLinkDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getLinkDefinitionsNextLink(nextLink, maxPageSize);
        }
    }
}
