package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitionsByTemplateName;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository TemplateDefinitions API client.
 */
public class TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    public TemplateDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitions(
            ParametersForGetTemplateDefinitions parameters) {
        return doGetTemplateDefinitions(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions", parameters);
    }

    private ODataValueContextOfIListOfWTemplateInfo doGetTemplateDefinitions(String url,
            ParametersForGetTemplateDefinitions parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getTemplateName(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
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
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWTemplateInfo.class);
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
    public ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitionsNextLink(String nextLink, int maxPageSize) {
        return doGetTemplateDefinitions(nextLink,
                new ParametersForGetTemplateDefinitions().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTemplateDefinitionsForEach(Function<ODataValueContextOfIListOfWTemplateInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetTemplateDefinitions parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWTemplateInfo response = getTemplateDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateName(
            ParametersForGetTemplateFieldDefinitionsByTemplateName parameters) {
        return doGetTemplateFieldDefinitionsByTemplateName(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/Fields", parameters);
    }

    private ODataValueContextOfIListOfTemplateFieldInfo doGetTemplateFieldDefinitionsByTemplateName(String url,
            ParametersForGetTemplateFieldDefinitionsByTemplateName parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getTemplateName(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
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
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfTemplateFieldInfo.class);
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
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateNameNextLink(
            String nextLink, int maxPageSize) {
        return doGetTemplateFieldDefinitionsByTemplateName(nextLink,
                new ParametersForGetTemplateFieldDefinitionsByTemplateName().setPrefer(
                        mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTemplateFieldDefinitionsByTemplateNameForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback, Integer maxPageSize,
            ParametersForGetTemplateFieldDefinitionsByTemplateName parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfTemplateFieldInfo response = getTemplateFieldDefinitionsByTemplateName(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateFieldDefinitionsByTemplateNameNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitions(
            ParametersForGetTemplateFieldDefinitions parameters) {
        return doGetTemplateFieldDefinitions(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/Fields", parameters);
    }

    private ODataValueContextOfIListOfTemplateFieldInfo doGetTemplateFieldDefinitions(String url,
            ParametersForGetTemplateFieldDefinitions parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "String", "int", "int", "boolean"},
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "templateId"}, new Object[]{parameters.getRepoId(), parameters.getTemplateId()});
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
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfTemplateFieldInfo.class);
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
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsNextLink(String nextLink,
            int maxPageSize) {
        return doGetTemplateFieldDefinitions(nextLink,
                new ParametersForGetTemplateFieldDefinitions().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTemplateFieldDefinitionsForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback, Integer maxPageSize,
            ParametersForGetTemplateFieldDefinitions parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfTemplateFieldInfo response = getTemplateFieldDefinitions(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public WTemplateInfo getTemplateDefinitionById(ParametersForGetTemplateDefinitionById parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"culture", "$select"}, new Object[]{parameters.getCulture(), parameters.getSelect()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "templateId"}, new Object[]{parameters.getRepoId(), parameters.getTemplateId()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, WTemplateInfo.class);
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
}
