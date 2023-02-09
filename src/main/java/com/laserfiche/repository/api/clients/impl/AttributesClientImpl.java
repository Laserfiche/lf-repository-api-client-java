package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeValueByKey;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository Attributes API client.
 */
public class AttributesClientImpl extends ApiClient implements AttributesClient {

    private HttpRequestHandler httpRequestHandler;

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public Attribute getTrusteeAttributeValueByKey(ParametersForGetTrusteeAttributeValueByKey parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean"},
                new String[]{"everyone"}, new Object[]{parameters.isEveryone()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"repoId", "attributeKey"},
                new Object[]{parameters.getRepoId(), parameters.getAttributeKey()});
        return sendRequestParseResponse(httpClient, objectMapper, Attribute.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}", "GET", null, null, null, null,
                queryParameters, pathParameters, new HashMap<String, String>(), false);
    }

    @Override
    public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairs(
            ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        return doGetTrusteeAttributeKeyValuePairs(baseUrl + "/v1/Repositories/{repoId}/Attributes", parameters);
    }

    private ODataValueContextOfListOfAttribute doGetTrusteeAttributeKeyValuePairs(String url,
            ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"boolean", "String", "String", "int", "int", "boolean"},
                new String[]{"everyone", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isEveryone(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueContextOfListOfAttribute.class,
                httpRequestHandler, url, "GET", null, null, null, null, queryParameters, pathParameters,
                headerParametersWithStringTypeValue, false);
    }

    @Override
    public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairsNextLink(String nextLink,
            int maxPageSize) {
        return doGetTrusteeAttributeKeyValuePairs(nextLink,
                new ParametersForGetTrusteeAttributeKeyValuePairs().setPrefer(
                        mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTrusteeAttributeKeyValuePairsForEach(Function<ODataValueContextOfListOfAttribute, Boolean> callback,
            Integer maxPageSize, ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfListOfAttribute response = getTrusteeAttributeKeyValuePairs(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        }
    }
}
