package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface TemplateDefinitionsApiEx {
    /**
     * - Returns all template definitions (including field definitions) in the repository.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTemplateInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     *
     * - Returns the field definitions assigned to a template definition.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfTemplateFieldInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     * - Returns the field definitions assigned to a template definition.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfTemplateFieldInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateNamePaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
