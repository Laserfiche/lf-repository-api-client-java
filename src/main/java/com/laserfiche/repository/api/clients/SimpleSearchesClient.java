package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;

import java.util.concurrent.CompletableFuture;

public interface SimpleSearchesClient {

    /**
     * - Runs a "simple" search operation on the repository.
     * - Returns a truncated search result listing.
     * - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     * @param select       Limits the properties returned in the result.
     * @param orderby      Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param count        Indicates whether the total count of items within a collection are returned in the result.
     * @param repoId       The requested repository ID.
     * @param fields       Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @param requestBody  The Laserfiche search command to run.
     * @param culture      An optional query parameter used to indicate the locale that should be used for formatting.
     *                     The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *                     culture will not be used for formatting.
     * @return CompletableFuture<ODataValueOfIListOfEntry> The return value
     */
    CompletableFuture<ODataValueOfIListOfEntry> createSimpleSearchOperation(String select, String orderby,
            boolean count, String repoId, String[] fields, boolean formatFields, SimpleSearchRequest requestBody,
            String culture);
}
