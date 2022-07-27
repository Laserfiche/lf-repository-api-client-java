package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.*;
import retrofit2.Response;
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

    /**
     *
     * - Get information about the edoc content of an entry, without downloading the edoc in its entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers. - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @return Call&lt;Void&gt;
     */
    @HEAD("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
    CompletableFuture<Response<Void>> getDocumentContentType(
            @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId
    );
}
