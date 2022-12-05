package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    public TemplateDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitions(String repoId, String templateName,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTemplateDefinitions(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions", repoId, templateName,
                prefer, culture, select, orderby, top, skip, count);
    }

    private ODataValueContextOfIListOfWTemplateInfo doGetTemplateDefinitions(String url, String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{templateName, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
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
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWTemplateInfo.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request template name not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitionsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetTemplateDefinitions(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null, null);
    }

    @Override
    public void getTemplateDefinitionsForEach(Function<ODataValueContextOfIListOfWTemplateInfo, Boolean> callback,
            Integer maxPageSize, String repoId, String templateName, String prefer, String culture, String select,
            String orderby, Integer top, Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfWTemplateInfo response = getTemplateDefinitions(repoId, templateName, prefer, culture,
                select, orderby, top, skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateName(String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return doGetTemplateFieldDefinitionsByTemplateName(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/Fields", repoId, templateName, prefer, culture,
                select, orderby, top, skip, count);
    }

    private ODataValueContextOfIListOfTemplateFieldInfo doGetTemplateFieldDefinitionsByTemplateName(String url,
            String repoId, String templateName, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{templateName, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
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
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfTemplateFieldInfo.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request template name not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateNameNextLink(
            String nextLink, Integer maxPageSize) {
        return doGetTemplateFieldDefinitionsByTemplateName(nextLink, null, null,
                mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public void getTemplateFieldDefinitionsByTemplateNameForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback, Integer maxPageSize, String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfTemplateFieldInfo response = getTemplateFieldDefinitionsByTemplateName(repoId,
                templateName, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateFieldDefinitionsByTemplateNameNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitions(String repoId, Integer templateId,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTemplateFieldDefinitions(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/Fields", repoId, templateId,
                prefer, culture, select, orderby, top, skip, count);
    }

    private ODataValueContextOfIListOfTemplateFieldInfo doGetTemplateFieldDefinitions(String url, String repoId,
            Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "templateId"},
                new Object[]{repoId, templateId});
        Map<String, Object> headerParameters = getNonNullParameters(new String[]{"prefer"}, new Object[]{prefer});
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
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfTemplateFieldInfo.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request template id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsNextLink(String nextLink,
            Integer maxPageSize) {
        return doGetTemplateFieldDefinitions(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null,
                null, null, null, null, null);
    }

    @Override
    public void getTemplateFieldDefinitionsForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback, Integer maxPageSize, String repoId,
            Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        ODataValueContextOfIListOfTemplateFieldInfo response = getTemplateFieldDefinitions(repoId, templateId, prefer,
                culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTemplateFieldDefinitionsNextLink(nextLink, maxPageSize);
        }
    }

    @Override
    public WTemplateInfo getTemplateDefinitionById(String repoId, Integer templateId, String culture, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture", "$select"},
                new Object[]{culture, select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "templateId"},
                new Object[]{repoId, templateId});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, WTemplateInfo.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request template id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
