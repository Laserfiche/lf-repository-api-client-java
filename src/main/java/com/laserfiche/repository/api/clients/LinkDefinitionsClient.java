package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.clients.impl.LinkDefinitionsApi;
import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;

import java.util.concurrent.CompletableFuture;

public class LinkDefinitionsClient extends BaseClient<LinkDefinitionsApi> {
    /**
     * Returns a single link definition object.
     * - Returns a single field definition associated with the specified ID. - Provide a link type ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param linkTypeId The requested link type ID. (required)
     * @param select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;EntryLinkTypeInfo&gt;
     */
    public CompletableFuture<EntryLinkTypeInfo> getLinkDefinitionById(String repoId, Integer linkTypeId, String select) {
        return client.getLinkDefinitionById(repoId, linkTypeId, select);
    }

    /**
     * Returns the link definitions associated with a repository.
     * - Returns the link definitions in the repository. - Provide a repository ID and get a paged listing of link definitions available in the repository. Useful when trying to display all link definitions available, not only links assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param select Limits the properties returned in the result. (optional)
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param top Limits the number of items returned from a collection. (optional)
     * @param skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntryLinkTypeInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfEntryLinkTypeInfo> getLinkDefinitions(String repoId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return client.getLinkDefinitions(repoId, prefer, select, orderby, top, skip, count);
    }
}