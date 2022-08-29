package com.laserfiche.repository.api.clients;

import java.util.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.util.concurrent.CompletableFuture;
import com.laserfiche.repository.api.clients.impl.model.*;

public interface TemplateDefinitionsClient {

    /**
     *  - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned.
     * - Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param templateName An optional query parameter. Can be used to get a single template definition using the template name.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWTemplateInfo> getTemplateDefinitions(String repoId, String templateName, String prefer, String culture, String select, String orderby, int top, int skip, boolean count);

    /**
     *  - Returns the field definitions assigned to a template definition.
     * - Provide a template definition name, and get a paged listing of the field definitions assigned to that template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param templateName A required query parameter for the requested template name.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitionsByTemplateName(String repoId, String templateName, String prefer, String culture, String select, String orderby, int top, int skip, boolean count);

    /**
     *  - Returns the field definitions assigned to a template definition.
     * - Provide a template definition ID, and get a paged listing of the field definitions assigned to that template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param templateId The requested template definition ID.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfTemplateFieldInfo> getTemplateFieldDefinitions(String repoId, int templateId, String prefer, String culture, String select, String orderby, int top, int skip, boolean count);

    /**
     *  - Returns a single template definition (including field definitions, if relevant).
     * - Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed.
     * - Allowed OData query options: Select
     *
     *  @param repoId The requested repository ID.
     *  @param templateId The requested template definition ID.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag.
     *  @param select Limits the properties returned in the result.
     *  @return CompletableFuture<WTemplateInfo> The return value
     */
    CompletableFuture<WTemplateInfo> getTemplateDefinitionById(String repoId, int templateId, String culture, String select);
}
