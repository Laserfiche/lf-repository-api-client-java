package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.EntryLinkTypeInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntryLinkTypeInfo;

import java.util.function.Function;

public interface LinkDefinitionsClient {

    /**
     * - Returns a single link definition associated with the specified ID.
     * - Provide a link type ID and get the associated link definition. Useful when a route provides a minimal amount of details and more information about the specific link definition is needed.
     * - Allowed OData query options: Select
     *
     * @param repoId     The requested repository ID.
     * @param linkTypeId The requested link type ID.
     * @param select     Limits the properties returned in the result.
     * @return EntryLinkTypeInfo The return value
     */
    EntryLinkTypeInfo getLinkDefinitionById(String repoId, Integer linkTypeId, String select);

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
     * @return ODataValueContextOfIListOfEntryLinkTypeInfo The return value
     */
    ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitions(String repoId, String prefer, String select,
            String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfEntryLinkTypeInfo The return value
     */
    ODataValueContextOfIListOfEntryLinkTypeInfo getLinkDefinitionsNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getLinkDefinitions&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId      The requested repository ID.
     * @param prefer      An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select      Limits the properties returned in the result.
     * @param orderby     Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top         Limits the number of items returned from a collection.
     * @param skip        Excludes the specified number of items of the queried collection from the result.
     * @param count       Indicates whether the total count of items within a collection are returned in the result.
     */
    void getLinkDefinitionsForEach(Function<ODataValueContextOfIListOfEntryLinkTypeInfo, Boolean> callback,
            Integer maxPageSize, String repoId, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count);
}
