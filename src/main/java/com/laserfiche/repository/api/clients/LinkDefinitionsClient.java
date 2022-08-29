package com.laserfiche.repository.api.clients;

import java.util.*;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.concurrent.CompletableFuture;

import com.laserfiche.repository.api.clients.impl.model.*;

public interface LinkDefinitionsClient {

    /**
     * - Returns a single link definition associated with the specified ID.
     * - Provide a link type ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed.
     * - Allowed OData query options: Select
     *
     * @param repoId     The requested repository ID.
     * @param linkTypeId The requested link type ID.
     * @param select     Limits the properties returned in the result.
     * @return CompletableFuture<EntryLinkTypeInfo> The return value
     */
    CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, int linkTypeId, String select);

    /**
     * - Returns the link definitions in the repository.
     * - Provide a repository ID and get a paged listing of link definitions available in the repository. Useful when trying to display all link definitions available, not only links assigned to a specific entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId  The requested repository ID.
     * @param prefer  An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select  Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top     Limits the number of items returned from a collection.
     * @param skip    Excludes the specified number of items of the queried collection from the result.
     * @param count   Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId, String prefer,
            String select, String orderby, int top, int skip, boolean count);
}
