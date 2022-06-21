package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.WFieldInfo;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for FieldDefinitionsApi
 */
public class FieldDefinitionsApiTest {

    private FieldDefinitionsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(FieldDefinitionsApi.class);
    }


    /**
     * 
     *
     * - Returns a single field definition associated with the specified ID.  - Useful when a route provides a minimal amount of details and more information about the specific field definition is needed. - Allowed OData query options: Select
     */
    @Test
    public void getFieldDefinitionByIdTest() {
        String repoId = null;
        Integer fieldDefinitionId = null;
        String culture = null;
        String $select = null;
        // WFieldInfo response = api.getFieldDefinitionById(repoId, fieldDefinitionId, culture, $select);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns a paged listing of field definitions available in the specified repository. - Useful when trying to find a list of all field definitions available, rather than only those assigned to a specific entry/template. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getFieldDefinitionsTest() {
        String repoId = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfWFieldInfo response = api.getFieldDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }
}
