package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTagInfo;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface TagDefinitionsApiEx {
    /**
     * - Returns all tag definitions in the repository.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
