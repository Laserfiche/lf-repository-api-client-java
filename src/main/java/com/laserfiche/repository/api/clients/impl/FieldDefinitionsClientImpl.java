package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitions;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository FieldDefinitions API client.
 */
public class FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    private HttpRequestHandler httpRequestHandler;

    public FieldDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient,
            HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public WFieldInfo getFieldDefinitionById(ParametersForGetFieldDefinitionById parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"culture", "$select"}, new Object[]{parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "fieldDefinitionId"},
                new Object[]{parameters.getRepoId(), parameters.getFieldDefinitionId()});
        return sendRequestParseResponse(httpClient, objectMapper, WFieldInfo.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}", "GET", null, null, null,
                null, queryParameters, pathParameters, new HashMap<String, String>(), false);
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
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueContextOfIListOfWFieldInfo.class,
                httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters,
                headerParametersWithStringTypeValue, false);
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

