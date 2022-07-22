package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.*;
import okhttp3.RequestBody;
import retrofit2.http.*;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EntriesApiEx {
    /**
     *
     * - Returns the children entries of a folder in the repository.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     *
     * - Returns the fields assigned to an entry.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfFieldValue&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     *
     * - Get the links assigned to an entry.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWEntryLinkInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

    /**
     *
     * - Get the tags assigned to an entry.
     * @param url Full next link URL returned by the backend.
     * @param prefer May contain maxpagesize information.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    @GET
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);
}
