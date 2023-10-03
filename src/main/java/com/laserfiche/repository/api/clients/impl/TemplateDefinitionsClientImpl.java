package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/**
 * The Laserfiche Repository TemplateDefinitions API client.
 */
public class TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    public TemplateDefinitionsClientImpl(
            String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient, httpRequestHandler);
    }

    @Override
    public TemplateDefinitionCollectionResponse listTemplateDefinitions(
            ParametersForListTemplateDefinitions parameters) {
        return doListTemplateDefinitions(baseUrl + "/v2/Repositories/{repositoryId}/TemplateDefinitions", parameters);
    }

    private TemplateDefinitionCollectionResponse doListTemplateDefinitions(
            String url, ParametersForListTemplateDefinitions parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String", "String", "String", "int", "int", "boolean"},
                new String[] {"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[] {
                    parameters.getTemplateName(),
                    parameters.getCulture(),
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.getTop(),
                    parameters.getSkip(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"repositoryId"}, new Object[] {parameters.getRepositoryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, TemplateDefinitionCollectionResponse> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(responseJson, TemplateDefinitionCollectionResponse.class);
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
    public TemplateDefinitionCollectionResponse listTemplateDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doListTemplateDefinitions(
                nextLink,
                new ParametersForListTemplateDefinitions()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listTemplateDefinitionsForEach(
            Function<TemplateDefinitionCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListTemplateDefinitions parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        TemplateDefinitionCollectionResponse response = listTemplateDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listTemplateDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateName(
            ParametersForListTemplateFieldDefinitionsByTemplateName parameters) {
        return doListTemplateFieldDefinitionsByTemplateName(
                baseUrl + "/v2/Repositories/{repositoryId}/TemplateDefinitions/FieldDefinitions", parameters);
    }

    private TemplateFieldDefinitionCollectionResponse doListTemplateFieldDefinitionsByTemplateName(
            String url, ParametersForListTemplateFieldDefinitionsByTemplateName parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String", "String", "String", "int", "int", "boolean"},
                new String[] {"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[] {
                    parameters.getTemplateName(),
                    parameters.getCulture(),
                    parameters.getSelect(),
                    parameters.getOrderby(),
                    parameters.getTop(),
                    parameters.getSkip(),
                    parameters.isCount()
                });
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"repositoryId"}, new Object[] {parameters.getRepositoryId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, TemplateFieldDefinitionCollectionResponse> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(
                                    responseJson, TemplateFieldDefinitionCollectionResponse.class);
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
    public TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateNameNextLink(
            String nextLink, int maxPageSize) {
        return doListTemplateFieldDefinitionsByTemplateName(
                nextLink,
                new ParametersForListTemplateFieldDefinitionsByTemplateName()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listTemplateFieldDefinitionsByTemplateNameForEach(
            Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListTemplateFieldDefinitionsByTemplateName parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        TemplateFieldDefinitionCollectionResponse response = listTemplateFieldDefinitionsByTemplateName(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listTemplateFieldDefinitionsByTemplateNameNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public TemplateDefinition getTemplateDefinition(ParametersForGetTemplateDefinition parameters) {
        Map<String, Object> queryParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "String"},
                new String[] {"culture", "$select"},
                new Object[] {parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String", "int"},
                new String[] {"repositoryId", "templateId"},
                new Object[] {parameters.getRepositoryId(), parameters.getTemplateId()});
        Function<HttpResponse<Object>, TemplateDefinition> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, TemplateDefinition.class);
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
                baseUrl + "/v2/Repositories/{repositoryId}/TemplateDefinitions/{templateId}",
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

    @Override
    public TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateId(
            ParametersForListTemplateFieldDefinitionsByTemplateId parameters) {
        return doListTemplateFieldDefinitionsByTemplateId(
                baseUrl + "/v2/Repositories/{repositoryId}/TemplateDefinitions/{templateId}/FieldDefinitions",
                parameters);
    }

    private TemplateFieldDefinitionCollectionResponse doListTemplateFieldDefinitionsByTemplateId(
            String url, ParametersForListTemplateFieldDefinitionsByTemplateId parameters) {
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
                new String[] {"String", "int"},
                new String[] {"repositoryId", "templateId"},
                new Object[] {parameters.getRepositoryId(), parameters.getTemplateId()});
        Map<String, Object> headerParameters = ApiClientUtils.getParametersWithNonDefaultValue(
                new String[] {"String"}, new String[] {"prefer"}, new Object[] {parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        Function<HttpResponse<Object>, TemplateFieldDefinitionCollectionResponse> parseResponse =
                (HttpResponse<Object> httpResponse) -> {
                    Object body = httpResponse.getBody();
                    Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String responseJson = new JSONObject(body).toString();
                            return objectMapper.readValue(
                                    responseJson, TemplateFieldDefinitionCollectionResponse.class);
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
    public TemplateFieldDefinitionCollectionResponse listTemplateFieldDefinitionsByTemplateIdNextLink(
            String nextLink, int maxPageSize) {
        return doListTemplateFieldDefinitionsByTemplateId(
                nextLink,
                new ParametersForListTemplateFieldDefinitionsByTemplateId()
                        .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void listTemplateFieldDefinitionsByTemplateIdForEach(
            Function<TemplateFieldDefinitionCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListTemplateFieldDefinitionsByTemplateId parameters) {
        parameters.setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        TemplateFieldDefinitionCollectionResponse response = listTemplateFieldDefinitionsByTemplateId(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = listTemplateFieldDefinitionsByTemplateIdNextLink(nextLink, maxPageSize);
        }
    }
}
