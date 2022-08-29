package com.laserfiche.repository.api.clients;

import java.util.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.concurrent.CompletableFuture;
import com.laserfiche.repository.api.clients.impl.model.*;

public interface TemplateDefinitionsClientImpl extends ApiClient implements TemplateDefinitionsClient {

    CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId, String templateName, String prefer, String culture, String select, String orderby, int top, int skip, boolean count) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions").routeParam("repoId", repoId).queryString("templateName", templateName).queryString("culture", culture).queryString("select", select).queryString("orderby", orderby).queryString("top", top).queryString("skip", skip).queryString("count", count).header("prefer", prefer).asObjectAsync(ODataValueContextOfIListOfWTemplateInfo.class).thenApply(httpResponse -> {
            if (httpResponse.getStatus() == "400") {
                throw new RuntimeException("Invalid or bad request.");
            }
            if (httpResponse.getStatus() == "401") {
                throw new RuntimeException("Access token is invalid or expired.");
            }
            if (httpResponse.getStatus() == "403") {
                throw new RuntimeException("Access denied for the operation.");
            }
            if (httpResponse.getStatus() == "404") {
                throw new RuntimeException("Request template name not found.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }

    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(String repoId, String templateName, String prefer, String culture, String select, String orderby, int top, int skip, boolean count) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/Fields").routeParam("repoId", repoId).queryString("templateName", templateName).queryString("culture", culture).queryString("select", select).queryString("orderby", orderby).queryString("top", top).queryString("skip", skip).queryString("count", count).header("prefer", prefer).asObjectAsync(ODataValueContextOfIListOfTemplateFieldInfo.class).thenApply(httpResponse -> {
            if (httpResponse.getStatus() == "400") {
                throw new RuntimeException("Invalid or bad request.");
            }
            if (httpResponse.getStatus() == "401") {
                throw new RuntimeException("Access token is invalid or expired.");
            }
            if (httpResponse.getStatus() == "403") {
                throw new RuntimeException("Access denied for the operation.");
            }
            if (httpResponse.getStatus() == "404") {
                throw new RuntimeException("Request template name not found.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }

    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId, int templateId, String prefer, String culture, String select, String orderby, int top, int skip, boolean count) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}/Fields").routeParam("repoId", repoId).routeParam("templateId", templateId).queryString("culture", culture).queryString("select", select).queryString("orderby", orderby).queryString("top", top).queryString("skip", skip).queryString("count", count).header("prefer", prefer).asObjectAsync(ODataValueContextOfIListOfTemplateFieldInfo.class).thenApply(httpResponse -> {
            if (httpResponse.getStatus() == "400") {
                throw new RuntimeException("Invalid or bad request.");
            }
            if (httpResponse.getStatus() == "401") {
                throw new RuntimeException("Access token is invalid or expired.");
            }
            if (httpResponse.getStatus() == "403") {
                throw new RuntimeException("Access denied for the operation.");
            }
            if (httpResponse.getStatus() == "404") {
                throw new RuntimeException("Request template id not found.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }

    CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, int templateId, String culture, String select) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/TemplateDefinitions/{templateId}").routeParam("repoId", repoId).routeParam("templateId", templateId).queryString("culture", culture).queryString("select", select).asObjectAsync(WTemplateInfo.class).thenApply(httpResponse -> {
            if (httpResponse.getStatus() == "400") {
                throw new RuntimeException("Invalid or bad request.");
            }
            if (httpResponse.getStatus() == "401") {
                throw new RuntimeException("Access token is invalid or expired.");
            }
            if (httpResponse.getStatus() == "403") {
                throw new RuntimeException("Access denied for the operation.");
            }
            if (httpResponse.getStatus() == "404") {
                throw new RuntimeException("Request template id not found.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }
}
