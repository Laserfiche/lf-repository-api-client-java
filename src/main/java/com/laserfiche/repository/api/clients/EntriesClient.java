package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;

/** The Laserfiche Repository Entries API client. */
public interface EntriesClient {

    /**
     * - Returns the fields assigned to an entry. - Provide an entry ID, and get a paged listing of
     * all fields assigned to that entry. - Default page size: 100. Allowed OData query options:
     * Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetFieldValues} which encapsulates
     *     the parameters of {@link #getFieldValues getFieldValues} method.
     * @return {@link ODataValueContextOfIListOfFieldValue} The return value
     */
    ODataValueContextOfIListOfFieldValue getFieldValues(ParametersForGetFieldValues parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfFieldValue} The return value
     */
    ODataValueContextOfIListOfFieldValue getFieldValuesNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getFieldValues
     * getFieldValues}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetFieldValues} which encapsulates
     *     the parameters of {@link #getFieldValues getFieldValues} method.
     */
    void getFieldValuesForEach(
            Function<ODataValueContextOfIListOfFieldValue, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetFieldValues parameters);

    /**
     * - Update the field values assigned to an entry. - Provide the new field values to assign to
     * the entry, and remove/reset all previously assigned field values. - This is an overwrite
     * action. The request body must include all desired field values, including any existing field
     * values that should remain assigned to the entry. Field values that are not included in the
     * request will be deleted from the entry. If the field value that is not included is part of a
     * template, it will still be assigned (as required by the template), but its value will be
     * reset.
     *
     * @param parameters An object of type {@link ParametersForAssignFieldValues} which encapsulates
     *     the parameters of {@link #assignFieldValues assignFieldValues} method.
     * @return {@link ODataValueOfIListOfFieldValue} The return value
     */
    ODataValueOfIListOfFieldValue assignFieldValues(ParametersForAssignFieldValues parameters);

    /**
     * - Creates a new document in the specified folder with file (no more than 100 MB). -
     * Optionally sets metadata and electronic document component. - Optional parameter: autoRename
     * (default false). If an entry already exists with the given name, the entry will be
     * automatically renamed. With this route, partial success is possible. The response returns
     * multiple operation (entryCreate operation, setEdoc operation, setLinks operation, etc..)
     * objects, which contain information about any errors that may have occurred during the
     * creation. As long as the entryCreate operation succeeds, the entry will be created, even if
     * all other operations fail.
     *
     * @param parameters An object of type {@link ParametersForImportDocument} which encapsulates
     *     the parameters of {@link #importDocument importDocument} method.
     * @return {@link CreateEntryResult} The return value
     */
    CreateEntryResult importDocument(ParametersForImportDocument parameters);

    /**
     * - Returns the links assigned to an entry. - Provide an entry ID, and get a paged listing of
     * links assigned to that entry. - Default page size: 100. Allowed OData query options: Select |
     * Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetLinkValuesFromEntry} which
     *     encapsulates the parameters of {@link #getLinkValuesFromEntry getLinkValuesFromEntry}
     *     method.
     * @return {@link ODataValueContextOfIListOfWEntryLinkInfo} The return value
     */
    ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntry(
            ParametersForGetLinkValuesFromEntry parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfWEntryLinkInfo} The return value
     */
    ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntryNextLink(
            String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link
     * #getLinkValuesFromEntry getLinkValuesFromEntry}, and apply a function on the response of each
     * iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetLinkValuesFromEntry} which
     *     encapsulates the parameters of {@link #getLinkValuesFromEntry getLinkValuesFromEntry}
     *     method.
     */
    void getLinkValuesFromEntryForEach(
            Function<ODataValueContextOfIListOfWEntryLinkInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetLinkValuesFromEntry parameters);

    /**
     * - Assign links to an entry. - Provide an entry ID and a list of links to assign to that
     * entry. - This is an overwrite action. The request must include all links to assign to the
     * entry, including existing links that should remain assigned to the entry.
     *
     * @param parameters An object of type {@link ParametersForAssignEntryLinks} which encapsulates
     *     the parameters of {@link #assignEntryLinks assignEntryLinks} method.
     * @return {@link ODataValueOfIListOfWEntryLinkInfo} The return value
     */
    ODataValueOfIListOfWEntryLinkInfo assignEntryLinks(ParametersForAssignEntryLinks parameters);

    /**
     * - Assign a template to an entry. - Provide an entry ID, template name, and a list of template
     * fields to assign to that entry. - Only template values will be modified. Any existing
     * independent fields on the entry will not be modified, nor will they be added if included in
     * the request. The only modification to fields will only occur on templated fields. If the
     * previously assigned template includes common template fields as the newly assigned template,
     * the common field values will not be modified.
     *
     * @param parameters An object of type {@link ParametersForWriteTemplateValueToEntry} which
     *     encapsulates the parameters of {@link #writeTemplateValueToEntry
     *     writeTemplateValueToEntry} method.
     * @return {@link Entry} The return value
     */
    Entry writeTemplateValueToEntry(ParametersForWriteTemplateValueToEntry parameters);

    /**
     * - Remove the currently assigned template from the specified entry. - Provide an entry ID to
     * clear template value on. - If the entry does not have a template assigned, no change will be
     * made.
     *
     * @param parameters An object of type {@link ParametersForDeleteAssignedTemplate} which
     *     encapsulates the parameters of {@link #deleteAssignedTemplate deleteAssignedTemplate}
     *     method.
     * @return {@link Entry} The return value
     */
    Entry deleteAssignedTemplate(ParametersForDeleteAssignedTemplate parameters);

    /**
     * - Returns dynamic field logic values with the current values of the fields in the template. -
     * Provide an entry ID and field values in the JSON body to get dynamic field logic values.
     * Independent and non-dynamic fields in the request body will be ignored, and only related
     * dynamic field logic values for the assigned template will be returned.
     *
     * @param parameters An object of type {@link ParametersForGetDynamicFieldValues} which
     *     encapsulates the parameters of {@link #getDynamicFieldValues getDynamicFieldValues}
     *     method.
     * @return Map&lt;String,String[]&gt; The return value
     */
    Map<String, String[]> getDynamicFieldValues(ParametersForGetDynamicFieldValues parameters);

    /**
     * - Returns a single entry object using the entry path. - Optional query parameter:
     * fallbackToClosestAncestor. Use the fallbackToClosestAncestor query parameter to return the
     * closest existing ancestor if the initial entry path is not found.
     *
     * @param parameters An object of type {@link ParametersForGetEntryByPath} which encapsulates
     *     the parameters of {@link #getEntryByPath getEntryByPath} method.
     * @return {@link FindEntryResult} The return value
     */
    FindEntryResult getEntryByPath(ParametersForGetEntryByPath parameters);

    /**
     * - Copy a new child entry in the designated folder async, and potentially return an
     * operationToken. - Provide the parent folder ID, and copy an entry as a child of the
     * designated folder. - Optional parameter: autoRename (default false). If an entry already
     * exists with the given name, the entry will be automatically renamed. - The status of the
     * operation can be checked via the Tasks/{operationToken} route. - Token substitution in the
     * name of the copied entry is not supported.
     *
     * @param parameters An object of type {@link ParametersForCopyEntryAsync} which encapsulates
     *     the parameters of {@link #copyEntryAsync copyEntryAsync} method.
     * @return {@link AcceptedOperation} The return value
     */
    AcceptedOperation copyEntryAsync(ParametersForCopyEntryAsync parameters);

    /**
     * - Returns a single entry object. - Provide an entry ID, and get the entry associated with
     * that ID. Useful when detailed information about the entry is required, such as metadata, path
     * information, etc. - Allowed OData query options: Select. If the entry is a subtype (Folder,
     * Document, or Shortcut), the entry will automatically be converted to include those
     * model-specific properties.
     *
     * @param parameters An object of type {@link ParametersForGetEntry} which encapsulates the
     *     parameters of {@link #getEntry getEntry} method.
     * @return {@link Entry} The return value
     */
    Entry getEntry(ParametersForGetEntry parameters);

    /**
     * - Moves and/or renames an entry. - Move and/or rename an entry by passing in the new parent
     * folder ID or name in the JSON body. - Optional parameter: autoRename (default false). If an
     * entry already exists with the given name, the entry will be automatically renamed.
     *
     * @param parameters An object of type {@link ParametersForMoveOrRenameEntry} which encapsulates
     *     the parameters of {@link #moveOrRenameEntry moveOrRenameEntry} method.
     * @return {@link Entry} The return value
     */
    Entry moveOrRenameEntry(ParametersForMoveOrRenameEntry parameters);

    /**
     * - Begins a task to delete an entry, and returns an operationToken. - Provide an entry ID, and
     * queue a delete task to remove it from the repository (includes nested objects if the entry is
     * a Folder type). The entry will not be deleted immediately. - Optionally include an audit
     * reason ID and comment in the JSON body. This route returns an operationToken, and will run as
     * an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
     *
     * @param parameters An object of type {@link ParametersForDeleteEntryInfo} which encapsulates
     *     the parameters of {@link #deleteEntryInfo deleteEntryInfo} method.
     * @return {@link AcceptedOperation} The return value
     */
    AcceptedOperation deleteEntryInfo(ParametersForDeleteEntryInfo parameters);

    /**
     * - Returns an entry's edoc resource in a stream format while including an audit reason. -
     * Provide an entry ID and audit reason/comment in the request body, and get the edoc resource
     * as part of the response content. - Optional header: Range. Use the Range header (single range
     * with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This
     * route is identical to the GET edoc route, but allows clients to include an audit reason when
     * downloading the edoc.
     *
     * @param parameters An object of type {@link ParametersForExportDocumentWithAuditReason} which
     *     encapsulates the parameters of {@link #exportDocumentWithAuditReason
     *     exportDocumentWithAuditReason} method.
     */
    void exportDocumentWithAuditReason(ParametersForExportDocumentWithAuditReason parameters);

    /**
     * Returns the input stream of an entry's edoc resource while including an audit reason.
     *
     * @param parameters An object of type {@link ParametersForExportDocumentWithAuditReason} which
     *     encapsulates the parameters of {@link #exportDocumentWithAuditReason
     *     exportDocumentWithAuditReason} method.
     * @return {@link InputStream} The return value
     */
    InputStream exportDocumentWithAuditReasonAsStream(
            ParametersForExportDocumentWithAuditReason parameters);

    /**
     * - Returns an entry's edoc resource in a stream format. - Provide an entry ID, and get the
     * edoc resource as part of the response content. - Optional header: Range. Use the Range header
     * (single range with byte unit) to retrieve partial content of the edoc, rather than the entire
     * edoc.
     *
     * @param parameters An object of type {@link ParametersForExportDocument} which encapsulates
     *     the parameters of {@link #exportDocument exportDocument} method.
     */
    void exportDocument(ParametersForExportDocument parameters);

    /**
     * Returns the input stream of an entry's edoc resource.
     *
     * @param parameters An object of type {@link ParametersForExportDocument} which encapsulates
     *     the parameters of {@link #exportDocument exportDocument} method.
     * @return {@link InputStream} The return value
     */
    InputStream exportDocumentAsStream(ParametersForExportDocument parameters);

    /**
     * - Delete the edoc associated with the provided entry ID.
     *
     * @param parameters An object of type {@link ParametersForDeleteDocument} which encapsulates
     *     the parameters of {@link #deleteDocument deleteDocument} method.
     * @return {@link ODataValueOfBoolean} The return value
     */
    ODataValueOfBoolean deleteDocument(ParametersForDeleteDocument parameters);

    /**
     * - Returns information about the edoc content of an entry, without downloading the edoc in its
     * entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the
     * response headers. - This route does not provide a way to download the actual edoc. Instead,
     * it just gives metadata information about the edoc associated with the entry. - If an error
     * occurs, the error message can be found in the X-APIServer-Error HTTP response header.
     *
     * @param parameters An object of type {@link ParametersForGetDocumentContentType} which
     *     encapsulates the parameters of {@link #getDocumentContentType getDocumentContentType}
     *     method.
     * @return Map&lt;String,String&gt; The return value
     */
    Map<String, String> getDocumentContentType(ParametersForGetDocumentContentType parameters);

    /**
     * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all
     * pages will be deleted. - Optional parameter: pageRange (default empty). The value should be a
     * comma-seperated string which contains non-overlapping single values, or page ranges. Ex:
     * &quot;1,2,3&quot;, &quot;1-3,5&quot;, &quot;2-7,10-12.&quot;
     *
     * @param parameters An object of type {@link ParametersForDeletePages} which encapsulates the
     *     parameters of {@link #deletePages deletePages} method.
     * @return {@link ODataValueOfBoolean} The return value
     */
    ODataValueOfBoolean deletePages(ParametersForDeletePages parameters);

    /**
     * - Returns the children entries of a folder in the repository. - Provide an entry ID (must be
     * a folder), and get a paged listing of entries in that folder. Used as a way of navigating
     * through the repository. - Default page size: 150. Allowed OData query options: Select | Count
     * | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow:
     * &quot;PropertyName direction,PropertyName2 direction&quot;. Sort order can be either value
     * &quot;asc&quot; or &quot;desc&quot;. Optional query parameters: groupByOrderType (bool). This
     * query parameter decides if results are returned in groups based on their entry type. Entries
     * returned in the listing are not automatically converted to their subtype (Folder, Shortcut,
     * Document), so clients who want model-specific information should request it via the GET entry
     * by ID route. - Optionally returns field values for the entries in the folder. Each field name
     * needs to be specified in the request. Maximum limit of 10 field names. - If field values are
     * requested, only the first value is returned if it is a multi value field. - Null or Empty
     * field values should not be used to determine if a field is assigned to the entry.
     *
     * @param parameters An object of type {@link ParametersForGetEntryListing} which encapsulates
     *     the parameters of {@link #getEntryListing getEntryListing} method.
     * @return {@link ODataValueContextOfIListOfEntry} The return value
     */
    ODataValueContextOfIListOfEntry getEntryListing(ParametersForGetEntryListing parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfEntry} The return value
     */
    ODataValueContextOfIListOfEntry getEntryListingNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #getEntryListing
     * getEntryListing}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetEntryListing} which encapsulates
     *     the parameters of {@link #getEntryListing getEntryListing} method.
     */
    void getEntryListingForEach(
            Function<ODataValueContextOfIListOfEntry, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetEntryListing parameters);

    /**
     * - Create/copy a new child entry in the designated folder. - Provide the parent folder ID, and
     * based on the request body, copy or create a folder/shortcut as a child entry of the
     * designated folder. - Optional parameter: autoRename (default false). If an entry already
     * exists with the given name, the entry will be automatically renamed.
     *
     * @param parameters An object of type {@link ParametersForCreateOrCopyEntry} which encapsulates
     *     the parameters of {@link #createOrCopyEntry createOrCopyEntry} method.
     * @return {@link Entry} The return value
     */
    Entry createOrCopyEntry(ParametersForCreateOrCopyEntry parameters);

    /**
     * - Returns the tags assigned to an entry. - Provide an entry ID, and get a paged listing of
     * tags assigned to that entry. - Default page size: 100. Allowed OData query options: Select |
     * Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type {@link ParametersForGetTagsAssignedToEntry} which
     *     encapsulates the parameters of {@link #getTagsAssignedToEntry getTagsAssignedToEntry}
     *     method.
     * @return {@link ODataValueContextOfIListOfWTagInfo} The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntry(
            ParametersForGetTagsAssignedToEntry parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link ODataValueContextOfIListOfWTagInfo} The return value
     */
    ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntryNextLink(
            String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link
     * #getTagsAssignedToEntry getTagsAssignedToEntry}, and apply a function on the response of each
     * iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false
     *     to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForGetTagsAssignedToEntry} which
     *     encapsulates the parameters of {@link #getTagsAssignedToEntry getTagsAssignedToEntry}
     *     method.
     */
    void getTagsAssignedToEntryForEach(
            Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize,
            ParametersForGetTagsAssignedToEntry parameters);

    /**
     * - Assign tags to an entry. - Provide an entry ID and a list of tags to assign to that entry.
     * - This is an overwrite action. The request must include all tags to assign to the entry,
     * including existing tags that should remain assigned to the entry.
     *
     * @param parameters An object of type {@link ParametersForAssignTags} which encapsulates the
     *     parameters of {@link #assignTags assignTags} method.
     * @return {@link ODataValueOfIListOfWTagInfo} The return value
     */
    ODataValueOfIListOfWTagInfo assignTags(ParametersForAssignTags parameters);
}
