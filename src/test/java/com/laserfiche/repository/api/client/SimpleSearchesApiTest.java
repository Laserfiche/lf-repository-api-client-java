package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfEntry;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.SimpleSearchRequest;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for SimpleSearchesApi
 */
public class SimpleSearchesApiTest {

    private SimpleSearchesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(SimpleSearchesApi.class);
    }


    /**
     * 
     *
     * - Runs a \&quot;simple\&quot; search operation on the repository. - Returns a truncated search result listing. - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches. - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     */
    @Test
    public void createSimpleSearchOperationTest() {
        String repoId = null;
        SimpleSearchRequest body = null;
        List<String> fields = null;
        Boolean formatFields = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Boolean $count = null;
        // ODataValueOfIListOfEntry response = api.createSimpleSearchOperation(repoId, body, fields, formatFields, culture, $select, $orderby, $count);

        // TODO: test validations
    }
}
