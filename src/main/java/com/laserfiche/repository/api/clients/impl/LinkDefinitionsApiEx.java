package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface LinkDefinitionsApiEx {
    /**
     *
     * - Returns the link definitions associated with a repository.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntryLinkTypeInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitionsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
