package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeValueByKey;
import kong.unirest.*;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
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
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        int statusCode = httpResponse.getStatus();
        if (statusCode == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Attribute.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString, objectMapper);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), statusCode,
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (statusCode == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
            else if (statusCode == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
            else if (statusCode == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
            else if (statusCode == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Requested attribute key not found."),
                        statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
            else if (statusCode == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        statusCode, httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
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
                httpRequestHandler, url, "GET",
                queryParameters, pathParameters,
                headerParametersWithStringTypeValue);
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
