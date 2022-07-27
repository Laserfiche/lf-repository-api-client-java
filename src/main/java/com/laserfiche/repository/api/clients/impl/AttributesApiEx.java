package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public interface AttributesApiEx {
    /**
     * Get the attribute key value pairs associated with the authenticated user.
     * - Returns the attribute key value pairs associated with the authenticated user.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfListOfAttribute&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfListOfAttribute> getTrusteeAttributeKeyValuePairsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
