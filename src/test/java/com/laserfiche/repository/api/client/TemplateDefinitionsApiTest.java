package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.WTemplateInfo;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for TemplateDefinitionsApi
 */
public class TemplateDefinitionsApiTest {

    private TemplateDefinitionsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(TemplateDefinitionsApi.class);
    }


    /**
     * 
     *
     * - Returns a single template definition (including field definitions, if relevant). - Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed. - Allowed OData query options: Select
     */
    @Test
    public void getTemplateDefinitionByIdTest() {
        String repoId = null;
        Integer templateId = null;
        String culture = null;
        String $select = null;
        // WTemplateInfo response = api.getTemplateDefinitionById(repoId, templateId, culture, $select);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned. - Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getTemplateDefinitionsTest() {
        String repoId = null;
        String templateName = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfWTemplateInfo response = api.getTemplateDefinitions(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the field definitions assigned to a template definition. - Provide a template definition ID, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getTemplateFieldDefinitionsTest() {
        String repoId = null;
        Integer templateId = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfTemplateFieldInfo response = api.getTemplateFieldDefinitions(repoId, templateId, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the field definitions assigned to a template definition. - Provide a template definition name, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getTemplateFieldDefinitionsByTemplateNameTest() {
        String repoId = null;
        String templateName = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfTemplateFieldInfo response = api.getTemplateFieldDefinitionsByTemplateName(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }
}
