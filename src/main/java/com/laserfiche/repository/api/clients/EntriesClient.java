package com.laserfiche.repository.api.clients;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;

public interface EntriesClient {

    /**
     *  - Returns the fields assigned to an entry.
     * - Provide an entry ID, and get a paged listing of all fields assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param formatValue An optional query parameter used to indicate if the field values should be formatted.
     *             The default value is false.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
     *             culture will not be used for formatting.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfFieldValue> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture<ODataValueContextOfIListOfFieldValue> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call <b>getFieldValues</b>, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId The requested repository ID.
     * @param entryId The requested entry ID.
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param formatValue An optional query parameter used to indicate if the field values should be formatted.
     *            The default value is false.
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
     *            culture will not be used for formatting.
     * @param select Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top Limits the number of items returned from a collection.
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<Void> The return value
     * @throws InterruptedException
     * @throws ExecutionException
     */
    CompletableFuture<Void> getFieldValuesForEach(Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException;

    /**
     *  - Update the field values assigned to an entry.
     * - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.
     * - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The entry ID of the entry that will have its fields updated.
     *  @param requestBody
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @return CompletableFuture<ODataValueOfIListOfFieldValue> The return value
     */
    CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId, Map<String, FieldToUpdate> requestBody, String culture);

    /**
     *  - Creates a new document in the specified folder with file (no more than 100 MB).
     * - Optionally sets metadata and electronic document component.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed. With this route, partial success is possible. The response returns multiple operation (entryCreate operation, setEdoc operation, setLinks operation, etc..) objects, which contain information about any errors that may have occurred during the creation. As long as the entryCreate operation succeeds, the entry will be created, even if all other operations fail.
     *
     *  @param repoId The requested repository ID.
     *  @param parentEntryId The entry ID of the folder that the document will be created in.
     *  @param fileName The created document's file name.
     *  @param autoRename An optional query parameter used to indicate if the new document should be automatically
     *             renamed if an entry already exists with the given name in the folder. The default value is false.
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @param file The file that will be uploaded.
     *  @param requestBody null
     *  @return CompletableFuture<CreateEntryResult> The return value
     */
    CompletableFuture<CreateEntryResult> importDocument(String repoId, Integer parentEntryId, String fileName, Boolean autoRename, String culture, File file, PostEntryWithEdocMetadataRequest requestBody);

    /**
     *  - Returns the links assigned to an entry.
     * - Provide an entry ID, and get a paged listing of links assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param prefer An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call <b>getLinkValuesFromEntry</b>, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId The requested repository ID.
     * @param entryId The requested entry ID.
     * @param prefer An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top Limits the number of items returned from a collection.
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<Void> The return value
     * @throws InterruptedException
     * @throws ExecutionException
     */
    CompletableFuture<Void> getLinkValuesFromEntryForEach(Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException;

    /**
     *  - Assign links to an entry.
     * - Provide an entry ID and a list of links to assign to that entry.
     * - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.
     *
     *  @param repoId The request repository ID.
     *  @param entryId The requested entry ID.
     *  @param requestBody null
     *  @return CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> The return value
     */
    CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId, List<PutLinksRequest> requestBody);

    /**
     *  - Assign a template to an entry.
     * - Provide an entry ID, template name, and a list of template fields to assign to that entry.
     * - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The ID of entry that will have its template updated.
     *  @param requestBody The template and template fields that will be assigned to the entry.
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @return CompletableFuture<Entry> The return value
     */
    CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId, PutTemplateRequest requestBody, String culture);

    /**
     *  - Remove the currently assigned template from the specified entry.
     * - Provide an entry ID to clear template value on.
     * - If the entry does not have a template assigned, no change will be made.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The ID of the entry that will have its template removed.
     *  @return CompletableFuture<Entry> The return value
     */
    CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId);

    /**
     *  - Returns an entry's edoc resource in a stream format while including an audit reason.
     * - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content.
     * - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested document ID.
     *  @param requestBody null
     *  @param range An optional header used to retrieve partial content of the edoc. Only supports single
     *             range with byte unit.
     *  @return CompletableFuture<File> The return value
     */
    CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId, GetEdocWithAuditReasonRequest requestBody, String range);

    /**
     *  - Returns an entry's edoc resource in a stream format.
     * - Provide an entry ID, and get the edoc resource as part of the response content.
     * - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested document ID.
     *  @param range An optional header used to retrieve partial content of the edoc. Only supports single
     *             range with byte unit.
     *  @return CompletableFuture<File> The return value
     */
    CompletableFuture<File> exportDocument(String repoId, Integer entryId, String range);

    /**
     * - Delete the edoc associated with the provided entry ID.
     *
     * @param repoId The requested repository ID.
     * @param entryId The requested document ID.
     * @return CompletableFuture<ODataValueOfBoolean> The return value
     */
    CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId);

    /**
     *  - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted.
     * - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: "1,2,3", "1-3,5", "2-7,10-12."
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested document ID.
     *  @param pageRange The pages to be deleted.
     *  @return CompletableFuture<ODataValueOfBoolean> The return value
     */
    CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange);

    /**
     *  - Returns the children entries of a folder in the repository.
     * - Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: "PropertyName direction,PropertyName2 direction". Sort order can be either value "asc" or "desc". Optional query parameters: groupByOrderType (bool). This query parameter decides if results are returned in groups based on their entry type. Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route.
     * - Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The folder ID.
     *  @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not.
     *  @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     *  @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *             The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *             culture will not be used for formatting.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfEntry> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture<ODataValueContextOfIListOfEntry> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call <b>getEntryListing</b>, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId The requested repository ID.
     * @param entryId The folder ID.
     * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not.
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *            culture will not be used for formatting.
     * @param select Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top Limits the number of items returned from a collection.
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<Void> The return value
     * @throws InterruptedException
     * @throws ExecutionException
     */
    CompletableFuture<Void> getEntryListingForEach(Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException;

    /**
     *  - Create/copy a new child entry in the designated folder.
     * - Provide the parent folder ID, and based on the request body, copy or create a folder/shortcut as a child entry of the designated folder.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The folder ID that the entry will be created in.
     *  @param requestBody The entry to create.
     *  @param autoRename An optional query parameter used to indicate if the new entry should be automatically
     *             renamed if an entry already exists with the given name in the folder. The default value is false.
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @return CompletableFuture<Entry> The return value
     */
    CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId, PostEntryChildrenRequest requestBody, Boolean autoRename, String culture);

    /**
     *  - Returns dynamic field logic values with the current values of the fields in the template.
     * - Provide an entry ID and field values in the JSON body to get dynamic field logic values.
     *  Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param requestBody null
     *  @return CompletableFuture<Map<String,String[]>> The return value
     */
    CompletableFuture<Map<String, String[]>> getDynamicFieldValues(String repoId, Integer entryId, GetDynamicFieldLogicValueRequest requestBody);

    /**
     *  - Copy a new child entry in the designated folder async, and potentially return an operationToken.
     * - Provide the parent folder ID, and copy an entry as a child of the designated folder.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     * - The status of the operation can be checked via the Tasks/{operationToken} route.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The folder ID that the entry will be created in.
     *  @param requestBody Copy entry request.
     *  @param autoRename An optional query parameter used to indicate if the new entry should be automatically
     *             renamed if an entry already exists with the given name in the folder. The default value is false.
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @return CompletableFuture<AcceptedOperation> The return value
     */
    CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId, CopyAsyncRequest requestBody, Boolean autoRename, String culture);

    /**
     *  - Returns the tags assigned to an entry.
     * - Provide an entry ID, and get a paged listing of tags assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *  @param select Limits the properties returned in the result.
     *  @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     *  @param top Limits the number of items returned from a collection.
     *  @param skip Excludes the specified number of items of the queried collection from the result.
     *  @param count Indicates whether the total count of items within a collection are returned in the result.
     *  @return CompletableFuture<ODataValueContextOfIListOfWTagInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return CompletableFuture<ODataValueContextOfIListOfWTagInfo> The return value
     */
    CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink, Integer maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call <b>getTagsAssignedToEntry</b>, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param repoId The requested repository ID.
     * @param entryId The requested entry ID.
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @param select Limits the properties returned in the result.
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @param top Limits the number of items returned from a collection.
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return CompletableFuture<Void> The return value
     * @throws InterruptedException
     * @throws ExecutionException
     */
    CompletableFuture<Void> getTagsAssignedToEntryForEach(Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException;

    /**
     *  - Assign tags to an entry.
     * - Provide an entry ID and a list of tags to assign to that entry.
     * - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param requestBody The tags to add.
     *  @return CompletableFuture<ODataValueOfIListOfWTagInfo> The return value
     */
    CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId, PutTagRequest requestBody);

    /**
     *  - Returns a single entry object.
     * - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc.
     * - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param select Limits the properties returned in the result.
     *  @return CompletableFuture<Entry> The return value
     */
    CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String select);

    /**
     *  - Moves and/or renames an entry.
     * - Move and/or rename an entry by passing in the new parent folder ID or name in the JSON body.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param requestBody The request containing the folder ID that the entry will be moved to and the new name
     *             the entry will be renamed to.
     *  @param autoRename An optional query parameter used to indicate if the entry should be automatically
     *             renamed if another entry already exists with the same name in the folder. The default value is false.
     *  @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     *  @return CompletableFuture<Entry> The return value
     */
    CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest requestBody, Boolean autoRename, String culture);

    /**
     *  - Begins a task to delete an entry, and returns an operationToken.
     * - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately.
     * - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
     *
     *  @param repoId The requested repository ID.
     *  @param entryId The requested entry ID.
     *  @param requestBody The submitted audit reason.
     *  @return CompletableFuture<AcceptedOperation> The return value
     */
    CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId, DeleteEntryWithAuditReason requestBody);
}
