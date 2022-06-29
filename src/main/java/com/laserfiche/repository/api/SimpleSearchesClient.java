package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.SimpleSearchesApi;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.client.model.SimpleSearchRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SimpleSearchesClient {
    private SimpleSearchesApi client;

    protected void setClient(SimpleSearchesApi client) {
        this.client = client;
    }

    /**
     *
     * - Runs a \&quot;simple\&quot; search operation on the repository. - Returns a truncated search result listing. - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     * @param repoId The requested repository ID. (required)
     * @param body The Laserfiche search command to run. (optional)
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each search result.  (optional)
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueOfIListOfEntry&gt;
     */
    public CompletableFuture<ODataValueOfIListOfEntry> createSimpleSearchOperation(String repoId, SimpleSearchRequest body, List<String> fields, Boolean formatFields, String culture, String $select, String $orderby, Boolean $count) {
        return client.createSimpleSearchOperation(repoId, body, fields, formatFields, culture, $select, $orderby, $count);
    }
}
