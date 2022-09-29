package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import kong.unirest.UnirestInstance;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;

public class TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    public TemplateDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return doGetTemplateDefinitions(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions", repoId, templateName,
                prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> doGetTemplateDefinitions(String url,
            String repoId, String templateName, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{templateName, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfWTemplateInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request template name not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitionsNextLink(String nextLink,
            int maxPageSize) {
        return doGetTemplateDefinitions(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null, null);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(
            String repoId, String templateName, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        return doGetTemplateFieldDefinitionsByTemplateName(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/Fields", repoId, templateName, prefer, culture,
                select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> doGetTemplateFieldDefinitionsByTemplateName(
            String url, String repoId, String templateName, String prefer, String culture, String select,
            String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"templateName", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{templateName, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId"}, new Object[]{repoId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfTemplateFieldInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request template name not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateNameNextLink(
            String nextLink, int maxPageSize) {
        return doGetTemplateFieldDefinitionsByTemplateName(nextLink, null, null,
                mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId,
            Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        return doGetTemplateFieldDefinitions(
                baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/Fields", repoId, templateId,
                prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> doGetTemplateFieldDefinitions(String url,
            String repoId, Integer templateId, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "templateId"},
                new Object[]{repoId, templateId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfTemplateFieldInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request template id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsNextLink(
            String nextLink, int maxPageSize) {
        return doGetTemplateFieldDefinitions(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null,
                null, null, null, null, null);
    }

    @Override
    public CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, Integer templateId, String culture,
            String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture", "$select"},
                new Object[]{culture, select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "templateId"},
                new Object[]{repoId, templateId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObjectAsync(WTemplateInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request template id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }
}
