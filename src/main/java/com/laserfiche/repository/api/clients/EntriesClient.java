package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

import java.util.Map;
import java.util.function.Function;

/**
 * The Laserfiche Repository Entries API client.
 */
public interface EntriesClient {

    /**
     * - Starts an asynchronous export operation to export an entry.<br>- If successful, it returns a taskId which can be used to check the status of the export operation or download the export result, otherwise, it returns an error.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForStartExportEntry} which encapsulates the parameters of {@link #startExportEntry startExportEntry} method.
     * @return {@link StartTaskResponse} The return value
     */
    StartTaskResponse startExportEntry(ParametersForStartExportEntry parameters);

    /**
     * - Returns the fields assigned to an entry.<br>- Provide an entry ID, and get a paged listing of all fields assigned to that entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListFields} which encapsulates the parameters of {@link #listFields listFields} method.
     * @return {@link FieldCollectionResponse} The return value
     */
    FieldCollectionResponse listFields(ParametersForListFields parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link FieldCollectionResponse} The return value
     */
    FieldCollectionResponse listFieldsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listFields listFields}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListFields} which encapsulates the parameters of {@link #listFields listFields} method.
     */
    void listFieldsForEach(Function<FieldCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListFields parameters);

    /**
     * - Update the field values assigned to an entry.<br>- Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.<br>- This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForSetFields} which encapsulates the parameters of {@link #setFields setFields} method.
     * @return {@link FieldCollectionResponse} The return value
     */
    FieldCollectionResponse setFields(ParametersForSetFields parameters);

    /**
     * - Assign a template to an entry.<br>- Provide an entry ID, template name, and a list of template fields to assign to that entry.<br>- Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForSetTemplate} which encapsulates the parameters of {@link #setTemplate setTemplate} method.
     * @return {@link Entry} The return value
     */
    Entry setTemplate(ParametersForSetTemplate parameters);

    /**
     * - Remove the currently assigned template from the specified entry.<br>- Provide an entry ID to clear template value on.<br>- If the entry does not have a template assigned, no change will be made.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForRemoveTemplate} which encapsulates the parameters of {@link #removeTemplate removeTemplate} method.
     * @return {@link Entry} The return value
     */
    Entry removeTemplate(ParametersForRemoveTemplate parameters);

    /**
     * - Returns a single entry object.<br>- Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc.<br>- If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.<br>- Allowed OData query options: Select.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetEntry} which encapsulates the parameters of {@link #getEntry getEntry} method.
     * @return {@link Entry} The return value
     */
    Entry getEntry(ParametersForGetEntry parameters);

    /**
     * - Update an entry. (Move and/or Rename)<br>- Move an entry to a new folder by setting the ParentId in the request body.<br>- Rename an entry by setting the Name in the request body.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForUpdateEntry} which encapsulates the parameters of {@link #updateEntry updateEntry} method.
     * @return {@link Entry} The return value
     */
    Entry updateEntry(ParametersForUpdateEntry parameters);

    /**
     * - Begins a task to delete an entry, and returns a taskId.<br>- Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately.<br>- Optionally include an audit reason ID and comment in the JSON body. This route returns a taskId, and will run as an asynchronous operation. Check the progress via the Tasks route.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForStartDeleteEntry} which encapsulates the parameters of {@link #startDeleteEntry startDeleteEntry} method.
     * @return {@link StartTaskResponse} The return value
     */
    StartTaskResponse startDeleteEntry(ParametersForStartDeleteEntry parameters);

    /**
     * - Requests Upload URLs to upload a large file in chunks.<br>- Returns an UploadId and an array of URLs to which the file chunks should be written in the same order.<br>- To request a new batch of Upload URLs for the same file, set the value of UploadId to the one returned when the first batch of Upload URLs was requested. For requesting the first batch of Upload URLs, leave UploadId empty or null.<br>- Example: if a file is going to be uploaded in 10 chunks, the 10 Upload URLs can be retrieved by two successive calls to this api, each call requesting 5 Upload URLs. For this, the first call should have StartingPartNumber=1 and NumberOfParts=5, and the second call should have StartingPartNumber=6 and NumberOfParts=5, along with UploadId returned in the first call.<br>- Each Upload URL expires after 15 minutes.<br>- Each file chunk written to an Upload URL should be at least 5 MB and at most 5 GB. There is no minimum size limit for the last chunk.<br>- The value of NumberOfParts must be in the range [1, 100], meaning that in each call to this api, a maximum of 100 Upload URLs can be requested. <br>- The total number of Upload URLs for a single file is 1000, which means (StartingPartNumber + NumberOfParts) should be less than or equal to 1001.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForCreateMultipartUploadUrls} which encapsulates the parameters of {@link #createMultipartUploadUrls createMultipartUploadUrls} method.
     * @return {@link CreateMultipartUploadUrlsResponse} The return value
     */
    CreateMultipartUploadUrlsResponse createMultipartUploadUrls(ParametersForCreateMultipartUploadUrls parameters);

    /**
     * - Import a new document in the specified folder, and optionally assigns metadata.<br>- The import may fail if the file is greater than 100 MB or time out if it takes longer than 60 seconds. These values are subject to change at anytime. Use the long operation asynchronous import if you run into these restrictions.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForImportEntry} which encapsulates the parameters of {@link #importEntry importEntry} method.
     * @return {@link Entry} The return value
     */
    Entry importEntry(ParametersForImportEntry parameters);

    /**
     * - Returns a single entry object using the entry path.<br>- Optional query parameter: fallbackToClosestAncestor. Use the fallbackToClosestAncestor query parameter to return the closest existing ancestor if the initial entry path is not found.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForGetEntryByPath} which encapsulates the parameters of {@link #getEntryByPath getEntryByPath} method.
     * @return {@link GetEntryByPathResponse} The return value
     */
    GetEntryByPathResponse getEntryByPath(ParametersForGetEntryByPath parameters);

    /**
     * - Export an entry.<br>- The export may time out if it takes longer than 60 seconds. This value is subject to change at anytime. Use the long operation asynchronous export if you run into this restriction.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForExportEntry} which encapsulates the parameters of {@link #exportEntry exportEntry} method.
     * @return {@link ExportEntryResponse} The return value
     */
    ExportEntryResponse exportEntry(ParametersForExportEntry parameters);

    /**
     * - Returns the links assigned to an entry.<br>- Provide an entry ID, and get a paged listing of links assigned to that entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListLinks} which encapsulates the parameters of {@link #listLinks listLinks} method.
     * @return {@link LinkCollectionResponse} The return value
     */
    LinkCollectionResponse listLinks(ParametersForListLinks parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link LinkCollectionResponse} The return value
     */
    LinkCollectionResponse listLinksNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listLinks listLinks}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListLinks} which encapsulates the parameters of {@link #listLinks listLinks} method.
     */
    void listLinksForEach(Function<LinkCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListLinks parameters);

    /**
     * - Assign links to an entry.<br>- Provide an entry ID and a list of links to assign to that entry.<br>- This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForSetLinks} which encapsulates the parameters of {@link #setLinks setLinks} method.
     * @return {@link LinkCollectionResponse} The return value
     */
    LinkCollectionResponse setLinks(ParametersForSetLinks parameters);

    /**
     * - Returns the tags assigned to an entry.<br>- Provide an entry ID, and get a paged listing of tags assigned to that entry.<br>- Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListTags} which encapsulates the parameters of {@link #listTags listTags} method.
     * @return {@link TagCollectionResponse} The return value
     */
    TagCollectionResponse listTags(ParametersForListTags parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link TagCollectionResponse} The return value
     */
    TagCollectionResponse listTagsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listTags listTags}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListTags} which encapsulates the parameters of {@link #listTags listTags} method.
     */
    void listTagsForEach(Function<TagCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListTags parameters);

    /**
     * - Assign tags to an entry.<br>- Provide an entry ID and a list of tags to assign to that entry.<br>- This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForSetTags} which encapsulates the parameters of {@link #setTags setTags} method.
     * @return {@link TagCollectionResponse} The return value
     */
    TagCollectionResponse setTags(ParametersForSetTags parameters);

    /**
     * - Copy a new child entry in the designated folder async, and potentially return a taskId.<br>- Provide the parent folder ID, and copy an entry as a child of the designated folder.<br>- The status of the operation can be checked via the Tasks route.<br>- Token substitution in the name of the copied entry is not supported.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForStartCopyEntry} which encapsulates the parameters of {@link #startCopyEntry startCopyEntry} method.
     * @return {@link StartTaskResponse} The return value
     */
    StartTaskResponse startCopyEntry(ParametersForStartCopyEntry parameters);

    /**
     * - Delete the edoc associated with the provided entry ID.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForDeleteElectronicDocument} which encapsulates the parameters of {@link #deleteElectronicDocument deleteElectronicDocument} method.
     * @return {@link Entry} The return value
     */
    Entry deleteElectronicDocument(ParametersForDeleteElectronicDocument parameters);

    /**
     * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted.<br>- Optional parameter: pageRange (default empty). The value should be a comma-separated string which contains non-overlapping single values, or page ranges. Ex: &quot;1,2,3&quot;, &quot;1-3,5&quot;, &quot;2-7,10-12.&quot;<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForDeletePages} which encapsulates the parameters of {@link #deletePages deletePages} method.
     * @return {@link Entry} The return value
     */
    Entry deletePages(ParametersForDeletePages parameters);

    /**
     * - Returns dynamic field logic values with the current values of the fields in the template.<br>- Provide an entry ID and field values in the JSON body to get dynamic field logic values.<br>- Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListDynamicFieldValues} which encapsulates the parameters of {@link #listDynamicFieldValues listDynamicFieldValues} method.
     * @return Map&lt;String,String[]&gt; The return value
     */
    Map<String, String[]> listDynamicFieldValues(ParametersForListDynamicFieldValues parameters);

    /**
     * - Imports a new file in the specified folder. The file should be already written (in chunks) to the upload URLs obtained by calling the Upload api. The maximum file size allowed is 64 GB.<br>- This route does not support partial success.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForStartImportUploadedParts} which encapsulates the parameters of {@link #startImportUploadedParts startImportUploadedParts} method.
     * @return {@link StartTaskResponse} The return value
     */
    StartTaskResponse startImportUploadedParts(ParametersForStartImportUploadedParts parameters);

    /**
     * - Returns the children entries of a folder in the repository.<br>- Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository.<br>- Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route.<br>- Optional query parameters: groupByEntryType (bool). This query parameter decides if results are returned in groups based on their entry type. <br>- Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names. If field values are requested, only the first value is returned if it is a multi value field. The remaining field values can be retrieved via the GET fields route. Null or Empty field values should not be used to determine if a field is assigned to the entry.<br>- Default page size: 150. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName direction,PropertyName2 direction&quot;. Sort order can be either value &quot;asc&quot; or &quot;desc&quot;.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListEntries} which encapsulates the parameters of {@link #listEntries listEntries} method.
     * @return {@link EntryCollectionResponse} The return value
     */
    EntryCollectionResponse listEntries(ParametersForListEntries parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link EntryCollectionResponse} The return value
     */
    EntryCollectionResponse listEntriesNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listEntries listEntries}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListEntries} which encapsulates the parameters of {@link #listEntries listEntries} method.
     */
    void listEntriesForEach(Function<EntryCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListEntries parameters);

    /**
     * - Create a new child entry in the designated folder.<br>- Provide the parent folder ID, and based on the request body, create a folder/shortcut as a child entry of the designated folder.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForCreateEntry} which encapsulates the parameters of {@link #createEntry createEntry} method.
     * @return {@link Entry} The return value
     */
    Entry createEntry(ParametersForCreateEntry parameters);

    /**
     * - Copy a new child entry in the designated folder.<br>- Provide the parent folder ID, and based on the request body, copy a child entry of the designated folder.<br>- Required OAuth scope: repository.Write
     *
     * @param parameters An object of type {@link ParametersForCopyEntry} which encapsulates the parameters of {@link #copyEntry copyEntry} method.
     * @return {@link Entry} The return value
     */
    Entry copyEntry(ParametersForCopyEntry parameters);
}
