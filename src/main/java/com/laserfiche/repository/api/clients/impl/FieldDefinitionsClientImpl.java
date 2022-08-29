package com.laserfiche.repository.api.clients;

import java.util.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.concurrent.CompletableFuture;
import com.laserfiche.repository.api.clients.impl.model.*;

public interface FieldDefinitionsClientImpl extends ApiClient implements FieldDefinitionsClient {

    CompletableFuture<WFieldInfo> getFieldDefinitionById(String repoId, int fieldDefinitionId, String culture, String select) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions/{fieldDefinitionId}").routeParam("repoId", repoId).routeParam("fieldDefinitionId", fieldDefinitionId).queryString("culture", culture).queryString("select", select).asObjectAsync(WFieldInfo.class).thenApply(httpResponse -> {
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
                throw new RuntimeException("Requested field definition id not found.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }

    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitions(String repoId, String prefer, String culture, String select, String orderby, int top, int skip, boolean count) {
        return Unirest.get(baseUrl + "/v1/Repositories/{repoId}/FieldDefinitions").routeParam("repoId", repoId).queryString("culture", culture).queryString("select", select).queryString("orderby", orderby).queryString("top", top).queryString("skip", skip).queryString("count", count).header("prefer", prefer).asObjectAsync(ODataValueContextOfIListOfWFieldInfo.class).thenApply(httpResponse -> {
            if (httpResponse.getStatus() == "400") {
                throw new RuntimeException("Invalid or bad request.");
            }
            if (httpResponse.getStatus() == "401") {
                throw new RuntimeException("Access token is invalid or expired.");
            }
            if (httpResponse.getStatus() == "403") {
                throw new RuntimeException("Access denied for the operation.");
            }
            if (httpResponse.getStatus() == "429") {
                throw new RuntimeException("Rate limit is reached.");
            }
            return httpResponse.getBody();
        });
    }
}
