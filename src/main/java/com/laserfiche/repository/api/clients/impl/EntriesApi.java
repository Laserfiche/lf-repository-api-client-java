package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.*;
import retrofit2.http.*;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface EntriesApi {
  /**
   * 
   * - Assign links to an entry. - Provide an entry ID and a list of links to assign to that entry. - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.
   * @param repoId The request repository ID. (required)
   * @param entryId The requested entry ID. (required)
   * @param body  (optional)
   * @return Call&lt;ODataValueOfIListOfWEntryLinkInfo&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("v1/Repositories/{repoId}/Entries/{entryId}/links")
  CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body List<PutLinksRequest> body
  );

  /**
   * 
   * - Update field values assigned to an entry. - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.  - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.
   * @param repoId The requested repository ID. (required)
   * @param entryId The entry ID of the entry that will have its fields updated. (required)
   * @param body  (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
   * @return Call&lt;ODataValueOfIListOfFieldValue&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("v1/Repositories/{repoId}/Entries/{entryId}/fields")
  CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body Map<String, FieldToUpdate> body, @retrofit2.http.Query("culture") String culture
  );

  /**
   * 
   * - Assign tags to an entry. - Provide an entry ID and a list of tags to assign to that entry. - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested entry ID. (required)
   * @param body The tags to add. (optional)
   * @return Call&lt;ODataValueOfIListOfWTagInfo&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("v1/Repositories/{repoId}/Entries/{entryId}/tags")
  CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body PutTagRequest body
  );

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
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
  CompletableFuture<AcceptedOperation> copyEntryAsync(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body CopyAsyncRequest body, @retrofit2.http.Query("autoRename") Boolean autoRename, @retrofit2.http.Query("culture") String culture
  );

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
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
  CompletableFuture<Entry> createOrCopyEntry(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body PostEntryChildrenRequest body, @retrofit2.http.Query("autoRename") Boolean autoRename, @retrofit2.http.Query("culture") String culture
  );

  /**
   * 
   * - Remove the currently assigned template from the specified entry. - Provide an entry ID to clear template value on. - If the entry does not have a template assigned, no change will be made.
   * @param repoId The requested repository ID. (required)
   * @param entryId The ID of the entry that will have its template removed. (required)
   * @return Call&lt;Entry&gt;
   */
  @DELETE("v1/Repositories/{repoId}/Entries/{entryId}/template")
  CompletableFuture<Entry> deleteAssignedTemplate(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId
  );

  /**
   * 
   * - Delete the edoc associated with the provided entry ID.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested document ID. (required)
   * @return Call&lt;ODataValueOfBoolean&gt;
   */
  @DELETE("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
  CompletableFuture<ODataValueOfBoolean> deleteDocument(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId
  );

  /**
   * 
   * - Begins a task to delete an entry, and returns an operationToken. - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately. - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested entry ID. (required)
   * @param body The submitted audit reason. (optional)
   * @return Call&lt;AcceptedOperation&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @DELETE("v1/Repositories/{repoId}/Entries/{entryId}")
  CompletableFuture<AcceptedOperation> deleteEntryInfo(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body DeleteEntryWithAuditReason body
  );

  /**
   * 
   * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted. - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: \&quot;1,2,3\&quot;, \&quot;1-3,5\&quot;, \&quot;2-7,10-12.\&quot;
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested document ID. (required)
   * @param pageRange The pages to be deleted. (optional)
   * @return Call&lt;ODataValueOfBoolean&gt;
   */
  @DELETE("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages")
  CompletableFuture<ODataValueOfBoolean> deletePages(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Query("pageRange") String pageRange
  );

  /**
   * 
   * - Get an entry&#x27;s edoc resource in a stream format. - Provide an entry ID, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested document ID. (required)
   * @param range An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. (optional)
   * @return Call&lt;File&gt;
   */
  @GET("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
  CompletableFuture<File> exportDocument(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Header("Range") String range
  );

  /**
   * 
   * - Get an entry&#x27;s edoc resource in a stream format while including an audit reason. - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested document ID. (required)
   * @param body  (optional)
   * @param range An optional header used to retrieve partial content of the edoc. Only supports single             range with byte unit. (optional)
   * @return Call&lt;File&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
  CompletableFuture<File> exportDocumentWithAuditReason(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body GetEdocWithAuditReasonRequest body, @retrofit2.http.Header("Range") String range
  );

  /**
   * 
   * - Get information about the edoc content of an entry, without downloading the edoc in its entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers. - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested document ID. (required)
   * @return Call&lt;Void&gt;
   */
  @HEAD("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
  CompletableFuture<Void> getDocumentContentType(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId
  );

  /**
   * 
   * - Get dynamic field logic values with the current values of the fields in the template. - Provide an entry ID and field values in the JSON body to get dynamic field logic values.  Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested entry ID. (required)
   * @param body  (optional)
   * @return Call&lt;Map&lt;String, List&lt;String&gt;&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
  CompletableFuture<Map<String, List<String>>> getDynamicFieldValues(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body GetDynamicFieldLogicValueRequest body
  );

  /**
   * 
   * - Returns a single entry object. - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc. - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.
   * @param repoId The requested repository ID. (required)
   * @param entryId The requested entry ID. (required)
   * @param $select Limits the properties returned in the result. (optional)
   * @return Call&lt;Entry&gt;
   */
  @GET("v1/Repositories/{repoId}/Entries/{entryId}")
  CompletableFuture<Entry> getEntry(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Query("$select") String $select
  );

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
  @GET("v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
  CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Query("groupByEntryType") Boolean groupByEntryType, @retrofit2.http.Query("fields") List<String> fields, @retrofit2.http.Query("formatFields") Boolean formatFields, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   *
   * - Returns the children entries of a folder in the repository.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfEntry&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

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
  @GET("v1/Repositories/{repoId}/Entries/{entryId}/fields")
  CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("formatValue") Boolean formatValue, @retrofit2.http.Query("culture") String culture, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   *
   * - Returns the fields assigned to an entry.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfFieldValue&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

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
  @GET("v1/Repositories/{repoId}/Entries/{entryId}/links")
  CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   *
   * - Get the links assigned to an entry.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfWEntryLinkInfo&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

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
  @GET("v1/Repositories/{repoId}/Entries/{entryId}/tags")
  CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Header("Prefer") String prefer, @retrofit2.http.Query("$select") String $select, @retrofit2.http.Query("$orderby") String $orderby, @retrofit2.http.Query("$top") Integer $top, @retrofit2.http.Query("$skip") Integer $skip, @retrofit2.http.Query("$count") Boolean $count
  );

  /**
   * Creates a new document in a folder.
   * - Creates a new document in the specified folder. - Optionally sets metadata and electronic document component. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed. With this route, partial success is possible. The response returns multiple operation (entryCreate operation, setEdoc operation, setLinks operation, etc..) objects, which contain information about any errors that may have occurred during the creation. As long as the entryCreate operation succeeds, the entry will be created, even if all other operations fail.
   * @param repoId The requested repository ID. (required)
   * @param parentEntryId The entry ID of the folder that the document will be created in. (required)
   * @param fileName The created document&#x27;s file name. (required)
   * @param electronicDocument  (optional)
   * @param request  (optional)
   * @param autoRename An optional query parameter used to indicate if the new document should be automatically             renamed if an entry already exists with the given name in the folder. The default value is false. (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
   * @return Call&lt;CreateEntryResult&gt;
   */
  @retrofit2.http.Multipart
  @POST("v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
  CompletableFuture<CreateEntryResult> importDocument(
          @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("parentEntryId") Integer parentEntryId, @retrofit2.http.Path("fileName") String fileName, @retrofit2.http.Part("electronicDocument\"; filename=\"electronicDocument") RequestBody electronicDocument, @retrofit2.http.Part("request") PostEntryWithEdocMetadataRequest request, @retrofit2.http.Query("autoRename") Boolean autoRename, @retrofit2.http.Query("culture") String culture
  );

  /**
   *
   * - Get the tags assigned to an entry.
   * @param url Full next link URL returned by the backend.
   * @param prefer May contain maxpagesize information.
   * @return CompletableFuture&lt;ODataValueContextOfIListOfWTagInfo&gt;
   */
  @GET
  CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryPaginate(@Url String url, @retrofit2.http.Header("Prefer") String prefer);

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
  @Headers({
    "Content-Type:application/json"
  })
  @PATCH("v1/Repositories/{repoId}/Entries/{entryId}")
  CompletableFuture<Entry> moveOrRenameDocument(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body PatchEntryRequest body, @retrofit2.http.Query("autoRename") Boolean autoRename, @retrofit2.http.Query("culture") String culture
  );

  /**
   * 
   * - Assign a template to an entry. - Provide an entry ID, template name, and a list of template fields to assign to that entry. - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.
   * @param repoId The requested repository ID. (required)
   * @param entryId The ID of entry that will have its template updated. (required)
   * @param body The template and template fields that will be assigned to the entry. (optional)
   * @param culture An optional query parameter used to indicate the locale that should be used.             The value should be a standard language tag. (optional)
   * @return CompletableFuture&lt;Entry&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("v1/Repositories/{repoId}/Entries/{entryId}/template")
  CompletableFuture<Entry> writeTemplateValueToEntry(
    @retrofit2.http.Path("repoId") String repoId, @retrofit2.http.Path("entryId") Integer entryId, @retrofit2.http.Body PutTemplateRequest body, @retrofit2.http.Query("culture") String culture
  );

}
