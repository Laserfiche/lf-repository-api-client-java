package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.clients.params.ParametersForCreateSimpleSearchOperation;

public interface SimpleSearchesClient {

    /**
     * - Runs a &quot;simple&quot; search operation on the repository.
     * - Returns a truncated search result listing.
     * - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     * @param parameters An object of type ParametersForCreateSimpleSearchOperation which encapsulates the parameters of createSimpleSearchOperation method.
     * @return ODataValueContextOfIListOfEntry The return value
     */
    ODataValueContextOfIListOfEntry createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation parameters);
}
