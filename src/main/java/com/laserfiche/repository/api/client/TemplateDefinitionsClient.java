package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.client.model.WTemplateInfo;
import org.mockito.internal.stubbing.answers.Returns;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.concurrent.CompletableFuture;

public class TemplateDefinitionsClient {
    private TemplateDefinitionsApi client;

    protected void setClient(TemplateDefinitionsApi client) {
        this.client = client;
    }

    /**
     *
     * - Returns a single template definition (including field definitions, if relevant). - Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed. - Allowed OData query options: Select
     * @param repoId The requested repository ID. (required)
     * @param templateId The requested template definition ID. (required)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @return CompletableFuture&lt;WTemplateInfo&gt;
     */
    CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, Integer templateId, String culture, String $select) {
        return client.getTemplateDefinitionById(repoId, templateId, culture, $select);
    }

    /**
     *
     * - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned. - Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param templateName An optional query parameter. Can be used to get a single template definition using the template name. (optional)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTemplateInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId, String templateName, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTemplateDefinitions(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Returns the field definitions assigned to a template definition. - Provide a template definition ID, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param templateId The requested template definition ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfTemplateFieldInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId, Integer templateId, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTemplateFieldDefinitions(repoId, templateId, prefer, culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Returns the field definitions assigned to a template definition. - Provide a template definition name, and get a paged listing of the field definitions assigned to that template.  - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param templateName A required query parameter for the requested template name. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfTemplateFieldInfo&gt;
     */
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(String repoId, String templateName, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTemplateFieldDefinitionsByTemplateName(repoId, templateName, prefer, culture, $select, $orderby, $top, $skip, $count);
    }
}
