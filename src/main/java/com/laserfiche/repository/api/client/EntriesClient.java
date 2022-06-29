package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.client.model.*;
import com.laserfiche.repository.api.client.model.Part;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EntriesClient {
    private EntriesApi client;

    protected void setClient(EntriesApi client) {
        this.client = client;
    }

    /**
     *
     * - Assign links to an entry. - Provide an entry ID and a list of links to assign to that entry. - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.
     * @param repoId The request repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param body  (optional)
     * @return Call&lt;ODataValueOfIListOfWEntryLinkInfo&gt;
     */
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId, List<PutLinksRequest> body) {
        return client.assignEntryLinks(repoId, entryId, body);
    }

    /**
     *
     * - Update field values assigned to an entry. - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.  - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.
     * @param repoId The requested repository ID. (required)
     * @param entryId The entry ID of the entry that will have its fields updated. (required)
     * @param body  (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return Call&lt;ODataValueOfIListOfFieldValue&gt;
     */
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId, Map<String, FieldToUpdate> body, String culture) {
        return client.assignFieldValues(repoId, entryId, body, culture);
    }

    /**
     *
     * - Assign tags to an entry. - Provide an entry ID and a list of tags to assign to that entry. - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param body The tags to add. (optional)
     * @return Call&lt;ODataValueOfIListOfWTagInfo&gt;
     */
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId, PutTagRequest body) {
        return client.assignTags(repoId, entryId, body);
    }

    /**
     *
     * - Copy a new child entry in the designated folder async, and potentially return an operationToken. - Provide the parent folder ID, and copy an entry as a child of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.  - The status of the operation can be checked via the Tasks/{operationToken} route.
     * @param repoId The requested repository ID. (required)
     * @param entryId The folder ID that the entry will be created in. (required)
     * @param body Copy entry request. (optional)
     * @param autoRename An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return Call&lt;AcceptedOperation&gt;
     */
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId, CopyAsyncRequest body, Boolean autoRename, String culture) {
        return client.copyEntryAsync(repoId, entryId, body, autoRename, culture);
    }

    /**
     *
     * - Create/copy a new child entry in the designated folder. - Provide the parent folder ID, and based on the request body, copy or create a folder/shortcut as a child entry of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     * @param repoId The requested repository ID. (required)
     * @param entryId The folder ID that the entry will be created in. (required)
     * @param body The entry to create. (optional)
     * @param autoRename An optional query parameter used to indicate if the new entry should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return Call&lt;Entry&gt;
     */
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId, PostEntryChildrenRequest body, Boolean autoRename, String culture) {
        return client.createOrCopyEntry(repoId, entryId, body, autoRename, culture);
    }

    /**
     *
     * - Remove the currently assigned template from the specified entry. - Provide an entry ID to clear template value on. - If the entry does not have a template assigned, no change will be made.
     * @param repoId The requested repository ID. (required)
     * @param entryId The ID of the entry that will have its template removed. (required)
     * @return Call&lt;Entry&gt;
     */
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId) {
        return client.deleteAssignedTemplate(repoId, entryId);
    }

    /**
     *
     * - Delete the edoc associated with the provided entry ID.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @return Call&lt;ODataValueOfBoolean&gt;
     */
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId) {
        return client.deleteDocument(repoId, entryId);
    }

    /**
     *
     * - Begins a task to delete an entry, and returns an operationToken. - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately. - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param body The submitted audit reason. (optional)
     * @return Call&lt;AcceptedOperation&gt;
     */
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId, DeleteEntryWithAuditReason body) {
        return client.deleteEntryInfo(repoId, entryId, body);
    }

    /**
     *
     * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted. - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: \&quot;1,2,3\&quot;, \&quot;1-3,5\&quot;, \&quot;2-7,10-12.\&quot;
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @param pageRange The pages to be deleted. (optional)
     * @return Call&lt;ODataValueOfBoolean&gt;
     */
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange) {
        return client.deletePages(repoId, entryId, pageRange);
    }

    /**
     *
     * - Get an entry&#x27;s edoc resource in a stream format. - Provide an entry ID, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @param range An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. (optional)
     * @return Call&lt;File&gt;
     */
    public CompletableFuture<File> exportDocument(String repoId, Integer entryId, String range) {
        return client.exportDocument(repoId, entryId, range);
    }

    /**
     *
     * - Get an entry&#x27;s edoc resource in a stream format while including an audit reason. - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @param body  (optional)
     * @param range An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. (optional)
     * @return Call&lt;File&gt;
     */
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId, GetEdocWithAuditReasonRequest body, String range) {
        return client.exportDocumentWithAuditReason(repoId, entryId, body, range);
    }

    /**
     *
     * - Get information about the edoc content of an entry, without downloading the edoc in its entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers. - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested document ID. (required)
     * @return Call&lt;Void&gt;
     */
    public CompletableFuture<Void> getDocumentContentType(String repoId, Integer entryId) {
        return client.getDocumentContentType(repoId, entryId);
    }

    /**
     *
     * - Get dynamic field logic values with the current values of the fields in the template. - Provide an entry ID and field values in the JSON body to get dynamic field logic values.  Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param body  (optional)
     * @return Call&lt;Map&lt;String, List&lt;String&gt;&gt;&gt;
     */
    public CompletableFuture<Map<String, List<String>>> getDynamicFieldValues(String repoId, Integer entryId, GetDynamicFieldLogicValueRequest body) {
        return client.getDynamicFieldValues(repoId, entryId, body);
    }

    /**
     *
     * - Returns a single entry object. - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc. - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param $select Limits the properties returned in the result. (optional)
     * @return Call&lt;Entry&gt;
     */
    public CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String $select) {
        return client.getEntry(repoId, entryId, $select);
    }

    /**
     *
     * - Returns the children entries of a folder in the repository. - Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. Sort order can be either value \&quot;asc\&quot; or \&quot;desc\&quot;. Optional query parameters: groupByOrderType (bool). This query parameter decides if results are returned in groups based on their entry type. Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route. - Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     * @param repoId The requested repository ID. (required)
     * @param entryId The folder ID. (required)
     * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not. (optional)
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each entry.  (optional)
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified. (optional)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise             culture will not be used for formatting. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId, Boolean groupByEntryType, List<String> fields, Boolean formatFields, String prefer, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Returns the children entries of a folder in the repository.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize Maximum number of items returned from the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink, int maxPageSize) {
        return client.getEntryListingPaginate(nextLink, String.format("maxpagezie=%d", maxPageSize));
    }

    /**
     *
     * - Returns the fields assigned to an entry. - Provide an entry ID, and get a paged listing of all fields assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param formatValue An optional query parameter used to indicate if the field values should be formatted.             The default value is false. (optional, default to false)
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.             The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise             culture will not be used for formatting. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfFieldValue&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getFieldValues(repoId, entryId, prefer, formatValue, culture, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Returns the fields assigned to an entry.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize Maximum number of items returned from the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfFieldValue&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink, int maxPageSize) {
        return client.getFieldValuesPaginate(nextLink, String.format("maxpagezie=%d", maxPageSize));
    }

    /**
     *
     * - Get the links assigned to an entry. - Provide an entry ID, and get a paged listing of links assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param prefer An optional odata header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWEntryLinkInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId, Integer entryId, String prefer, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getLinkValuesFromEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Get the links assigned to an entry.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize Maximum number of items returned from the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWEntryLinkInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink, int maxPageSize) {
        return client.getLinkValuesFromEntryPaginate(nextLink, String.format("maxpagezie=%d", maxPageSize));
    }

    /**
     *
     * - Get the tags assigned to an entry. - Provide an entry ID, and get a paged listing of tags assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize. (optional)
     * @param $select Limits the properties returned in the result. (optional)
     * @param $orderby Specifies the order in which items are returned. The maximum number of expressions is 5. (optional)
     * @param $top Limits the number of items returned from a collection. (optional)
     * @param $skip Excludes the specified number of items of the queried collection from the result. (optional)
     * @param $count Indicates whether the total count of items within a collection are returned in the result. (optional)
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId, String prefer, String $select, String $orderby, Integer $top, Integer $skip, Boolean $count) {
        return client.getTagsAssignedToEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);
    }

    /**
     *
     * - Get the tags assigned to an entry.
     * @param nextLink Full next link URL returned by the backend.
     * @param maxPageSize Maximum number of items returned from the backend.
     * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
     */
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink, int maxPageSize) {
        return client.getTagsAssignedToEntryPaginate(nextLink, String.format("maxpagezie=%d", maxPageSize));
    }

    /**
     *
     * - Moves and/or renames an entry. - Move and/or rename an entry by passing in the new parent folder ID or name in the JSON body. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     * @param repoId The requested repository ID. (required)
     * @param entryId The requested entry ID. (required)
     * @param body The request containing the folder ID that the entry will be moved to and the new name
    the entry will be renamed to. (optional)
     * @param autoRename An optional query parameter used to indicate if the entry should be automatically             renamed if another entry already exists with the same name in the folder. The default value is false. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return CompletableFuture&lt;Entry&gt;
     */
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest body, Boolean autoRename, String culture) {
        return client.moveOrRenameDocument(repoId, entryId, body, autoRename, culture);
    }

    /**
     *
     * - Export an entry. - Provide an entry ID, part, format, page range, and audit event and export the entry part as the provided format. - This operation is a &#x27;simple operation&#x27;. For more functionality, refer to the non-simple export route.
     * @param part Specifies which &#x27;part&#x27; of the document to export. Options include: Edoc, Pages and Text. (required)
     * @param repoId The requested repository ID. (required)
     * @param entryId The ID of entry to export. (required)
     * @param body The body of the export request. (optional)
     * @param format Specifies the format to export as. This value is ignored when part&#x3D;Edoc or Text. Options include: TIFF, PNG, PDF and JPEG. The default value is TIFF. (optional)
     * @param pageRange A comma seperated range of pages to include. This value is ignored when part&#x3D;Edoc. Ex: 1,3,4 or 1-3,5-7,9. (optional)
     * @return CompletableFuture&lt;ODataValueOfString&gt;
     */
    public CompletableFuture<ODataValueOfString> simpleExport(Part part, String repoId, Integer entryId, ExportRequestBody body, Format format, String pageRange) {
        return client.simpleExport(part, repoId, entryId, body, format, pageRange);
    }

    /**
     *
     * - Import a new document in the specified folder with file (no more than 100Mb). - Optionally sets metadata. - With this route, partial success is possible. The response returns error message, which contain information about any errors that may have occurred during the import. As long as the documentLink returned, the entry is created, even if other steps fail.
     * @param repoId The requested repository ID. (required)
     * @param parentEntryId The entry ID of the folder that the document will be created in. (required)
     * @param file  (optional)
     * @param request  (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return CompletableFuture&lt;SimpleImportResult&gt;
     */
    public CompletableFuture<SimpleImportResult> simpleImport(String repoId, Integer parentEntryId, RequestBody file, SimpleImportRequest request, String culture) {
        return client.simpleImport(repoId, parentEntryId, file, request, culture);
    }

    /**
     *
     * - Assign a template to an entry. - Provide an entry ID, template name, and a list of template fields to assign to that entry. - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.
     * @param repoId The requested repository ID. (required)
     * @param entryId The ID of entry that will have its template updated. (required)
     * @param body The template and template fields that will be assigned to the entry. (optional)
     * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
     * @return CompletableFuture&lt;Entry&gt;
     */
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId, PutTemplateRequest body, String culture) {
        return client.writeTemplateValueToEntry(repoId, entryId, body, culture);
    }

}
