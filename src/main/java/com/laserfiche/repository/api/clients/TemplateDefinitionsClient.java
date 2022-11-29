package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfTemplateFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWTemplateInfo;
import com.laserfiche.repository.api.clients.impl.model.WTemplateInfo;

import java.util.function.Function;

public interface TemplateDefinitionsClient {

    /**
     * - Returns all template definitions (including field definitions) in the repository. If a template name query parameter is given, then a single template definition is returned.
     * - Provide a repository ID, and get a paged listing of template definitions available in the repository. Useful when trying to find a list of all template definitions available, rather than a specific one.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId       The requested repository ID.
     * @param templateName An optional query parameter. Can be used to get a single template definition using the template name.
     * @param prefer       An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture      An optional query parameter used to indicate the locale that should be used for formatting.
     *                     The value should be a standard language tag.
     * @param select       Limits the properties returned in the result.
     * @param orderby      Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top          Limits the number of items returned from a collection.
     * @param skip         Excludes the specified number of items of the queried collection from the result.
     * @param count        Indicates whether the total count of items within a collection are returned in the result.
     * @return ODataValueContextOfIListOfWTemplateInfo The return value
     */
    ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitions(String repoId, String templateName, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfWTemplateInfo The return value
     */
    ODataValueContextOfIListOfWTemplateInfo getTemplateDefinitionsNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getTemplateDefinitions&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback     A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize  Optionally specify the maximum number of items to retrieve.
     * @param repoId       The requested repository ID.
     * @param templateName An optional query parameter. Can be used to get a single template definition using the template name.
     * @param prefer       An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture      An optional query parameter used to indicate the locale that should be used for formatting.
     *                     The value should be a standard language tag.
     * @param select       Limits the properties returned in the result.
     * @param orderby      Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top          Limits the number of items returned from a collection.
     * @param skip         Excludes the specified number of items of the queried collection from the result.
     * @param count        Indicates whether the total count of items within a collection are returned in the result.
     */
    void getTemplateDefinitionsForEach(Function<ODataValueContextOfIListOfWTemplateInfo, Boolean> callback,
            Integer maxPageSize, String repoId, String templateName, String prefer, String culture, String select,
            String orderby, Integer top, Integer skip, Boolean count);

    /**
     * - Returns the field definitions assigned to a template definition.
     * - Provide a template definition name, and get a paged listing of the field definitions assigned to that template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId       The requested repository ID.
     * @param templateName A required query parameter for the requested template name.
     * @param prefer       An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture      An optional query parameter used to indicate the locale that should be used for formatting.
     *                     The value should be a standard language tag.
     * @param select       Limits the properties returned in the result.
     * @param orderby      Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top          Limits the number of items returned from a collection.
     * @param skip         Excludes the specified number of items of the queried collection from the result.
     * @param count        Indicates whether the total count of items within a collection are returned in the result.
     * @return ODataValueContextOfIListOfTemplateFieldInfo The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateName(String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfTemplateFieldInfo The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsByTemplateNameNextLink(String nextLink,
            Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getTemplateFieldDefinitionsByTemplateName&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback     A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize  Optionally specify the maximum number of items to retrieve.
     * @param repoId       The requested repository ID.
     * @param templateName A required query parameter for the requested template name.
     * @param prefer       An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture      An optional query parameter used to indicate the locale that should be used for formatting.
     *                     The value should be a standard language tag.
     * @param select       Limits the properties returned in the result.
     * @param orderby      Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top          Limits the number of items returned from a collection.
     * @param skip         Excludes the specified number of items of the queried collection from the result.
     * @param count        Indicates whether the total count of items within a collection are returned in the result.
     */
    void getTemplateFieldDefinitionsByTemplateNameForEach(
            Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback, Integer maxPageSize, String repoId,
            String templateName, String prefer, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count);

    /**
     * - Returns the field definitions assigned to a template definition.
     * - Provide a template definition ID, and get a paged listing of the field definitions assigned to that template.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param repoId     The requested repository ID.
     * @param templateId The requested template definition ID.
     * @param prefer     An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture    An optional query parameter used to indicate the locale that should be used for formatting.
     *                   The value should be a standard language tag.
     * @param select     Limits the properties returned in the result.
     * @param orderby    Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top        Limits the number of items returned from a collection.
     * @param skip       Excludes the specified number of items of the queried collection from the result.
     * @param count      Indicates whether the total count of items within a collection are returned in the result.
     * @return ODataValueContextOfIListOfTemplateFieldInfo The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitions(String repoId, Integer templateId,
            String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink    A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return ODataValueContextOfIListOfTemplateFieldInfo The return value
     */
    ODataValueContextOfIListOfTemplateFieldInfo getTemplateFieldDefinitionsNextLink(String nextLink,
            Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call &lt;b&gt;getTemplateFieldDefinitions&lt;/b&gt;, and apply a function on the response of each iteration.
     *
     * @param callback    A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId      The requested repository ID.
     * @param templateId  The requested template definition ID.
     * @param prefer      An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture     An optional query parameter used to indicate the locale that should be used for formatting.
     *                    The value should be a standard language tag.
     * @param select      Limits the properties returned in the result.
     * @param orderby     Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top         Limits the number of items returned from a collection.
     * @param skip        Excludes the specified number of items of the queried collection from the result.
     * @param count       Indicates whether the total count of items within a collection are returned in the result.
     */
    void getTemplateFieldDefinitionsForEach(Function<ODataValueContextOfIListOfTemplateFieldInfo, Boolean> callback,
            Integer maxPageSize, String repoId, Integer templateId, String prefer, String culture, String select,
            String orderby, Integer top, Integer skip, Boolean count);

    /**
     * - Returns a single template definition (including field definitions, if relevant).
     * - Provide a template definition ID, and get the single template definition associated with that ID. Useful when a route provides a minimal amount of details, and more information about the specific template is needed.
     * - Allowed OData query options: Select
     *
     * @param repoId     The requested repository ID.
     * @param templateId The requested template definition ID.
     * @param culture    An optional query parameter used to indicate the locale that should be used for formatting.
     *                   The value should be a standard language tag.
     * @param select     Limits the properties returned in the result.
     * @return WTemplateInfo The return value
     */
    WTemplateInfo getTemplateDefinitionById(String repoId, Integer templateId, String culture, String select);
}
