package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.*;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchesApiEx {
    /**
     * - Returns the context hits associated with a search result entry.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfContextHit&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfContextHit> getSearchContextHitsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     * Get the search results listing of a search.
     * - Returns a search result listing if the search is completed.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfEntry> getSearchResultsPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
