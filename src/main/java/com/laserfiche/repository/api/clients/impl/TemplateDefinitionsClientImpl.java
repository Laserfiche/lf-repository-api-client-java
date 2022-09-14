package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.TemplateDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    public TemplateDefinitionsClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (templateName != null) {
            queryParameters.put("templateName", templateName);
        }
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        if (orderby != null) {
            queryParameters.put("orderby", orderby);
        }
        if (top != null) {
            queryParameters.put("top", top);
        }
        if (skip != null) {
            queryParameters.put("skip", skip);
        }
        if (count != null) {
            queryParameters.put("count", count);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions")
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(
            String repoId, String templateName, String prefer, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (templateName != null) {
            queryParameters.put("templateName", templateName);
        }
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        if (orderby != null) {
            queryParameters.put("orderby", orderby);
        }
        if (top != null) {
            queryParameters.put("top", top);
        }
        if (skip != null) {
            queryParameters.put("skip", skip);
        }
        if (count != null) {
            queryParameters.put("count", count);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/Fields")
                .routeParam("repoId", repoId)
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId,
            Integer templateId, String prefer, String culture, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        if (orderby != null) {
            queryParameters.put("orderby", orderby);
        }
        if (top != null) {
            queryParameters.put("top", top);
        }
        if (skip != null) {
            queryParameters.put("skip", skip);
        }
        if (count != null) {
            queryParameters.put("count", count);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/Fields")
                .routeParam("repoId", repoId)
                .routeParam("templateId", String.valueOf(templateId))
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, Integer templateId, String culture,
            String select) {
        Map<String, Object> queryParameters = new HashMap<>();
        if (culture != null) {
            queryParameters.put("culture", culture);
        }
        if (select != null) {
            queryParameters.put("select", select);
        }
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}")
                .routeParam("repoId", repoId)
                .routeParam("templateId", String.valueOf(templateId))
                .queryString(queryParameters)
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
                    return httpResponse.getBody();
                });
    }
}
