package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface FieldDefinitionsApiEx {
    /**
     *
     * - Returns a paged listing of field definitions available in the specified repository.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWFieldInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfWFieldInfo> getFieldDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
