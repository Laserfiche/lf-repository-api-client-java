package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.WTagInfo;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for TagDefinitionsApi
 */
public class TagDefinitionsApiTest {

    private TagDefinitionsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(TagDefinitionsApi.class);
    }


    /**
     * 
     *
     * - Returns a single tag definition. - Provide a tag definition ID, and get the single tag definition associated with that ID. Useful when another route provides a minimal amount of details, and more information about the specific tag is needed. - Allowed OData query options: Select
     */
    @Test
    public void getTagDefinitionByIdTest() {
        String repoId = null;
        Integer tagId = null;
        String culture = null;
        String $select = null;
        // WTagInfo response = api.getTagDefinitionById(repoId, tagId, culture, $select);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns all tag definitions in the repository. - Provide a repository ID and get a paged listing of tag definitions available in the repository. Useful when trying to display all tag definitions available, not only tags assigned to a specific entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getTagDefinitionsTest() {
        String repoId = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfWTagInfo response = api.getTagDefinitions(repoId, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }
}
