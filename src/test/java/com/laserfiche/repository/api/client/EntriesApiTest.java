package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.AcceptedOperation;
import com.laserfiche.repository.api.client.model.CopyAsyncRequest;
import com.laserfiche.repository.api.client.model.DeleteEntryWithAuditReason;
import com.laserfiche.repository.api.client.model.Entry;
import com.laserfiche.repository.api.client.model.ExportRequestBody;
import com.laserfiche.repository.api.client.model.FieldToUpdate;
import java.io.File;
import com.laserfiche.repository.api.client.model.Format;
import com.laserfiche.repository.api.client.model.GetDynamicFieldLogicValueRequest;
import com.laserfiche.repository.api.client.model.GetEdocWithAuditReasonRequest;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfEntry;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfFieldValue;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWEntryLinkInfo;
import com.laserfiche.repository.api.client.model.ODataValueContextOfIListOfWTagInfo;
import com.laserfiche.repository.api.client.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfFieldValue;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfWEntryLinkInfo;
import com.laserfiche.repository.api.client.model.ODataValueOfIListOfWTagInfo;
import com.laserfiche.repository.api.client.model.ODataValueOfString;
import com.laserfiche.repository.api.client.model.Part;
import com.laserfiche.repository.api.client.model.PatchEntryRequest;
import com.laserfiche.repository.api.client.model.PostEntryChildrenRequest;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.client.model.PutLinksRequest;
import com.laserfiche.repository.api.client.model.PutTagRequest;
import com.laserfiche.repository.api.client.model.PutTemplateRequest;
import com.laserfiche.repository.api.client.model.SimpleImportRequest;
import com.laserfiche.repository.api.client.model.SimpleImportResult;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for EntriesApi
 */
public class EntriesApiTest {

    private EntriesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(EntriesApi.class);
    }


    /**
     * 
     *
     * - Assign links to an entry. - Provide an entry ID and a list of links to assign to that entry. - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.
     */
    @Test
    public void assignEntryLinksTest() {
        String repoId = null;
        Integer entryId = null;
        List<PutLinksRequest> body = null;
        // ODataValueOfIListOfWEntryLinkInfo response = api.assignEntryLinks(repoId, entryId, body);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Update field values assigned to an entry. - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.  - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.
     */
    @Test
    public void assignFieldValuesTest() {
        String repoId = null;
        Integer entryId = null;
        Map<String, FieldToUpdate> body = null;
        String culture = null;
        // ODataValueOfIListOfFieldValue response = api.assignFieldValues(repoId, entryId, body, culture);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Assign tags to an entry. - Provide an entry ID and a list of tags to assign to that entry. - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.
     */
    @Test
    public void assignTagsTest() {
        String repoId = null;
        Integer entryId = null;
        PutTagRequest body = null;
        // ODataValueOfIListOfWTagInfo response = api.assignTags(repoId, entryId, body);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Copy a new child entry in the designated folder async, and potentially return an operationToken. - Provide the parent folder ID, and copy an entry as a child of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.  - The status of the operation can be checked via the Tasks/{operationToken} route.
     */
    @Test
    public void copyEntryAsyncTest() {
        String repoId = null;
        Integer entryId = null;
        CopyAsyncRequest body = null;
        Boolean autoRename = null;
        String culture = null;
        // AcceptedOperation response = api.copyEntryAsync(repoId, entryId, body, autoRename, culture);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Create/copy a new child entry in the designated folder. - Provide the parent folder ID, and based on the request body, copy or create a folder/shortcut as a child entry of the designated folder. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     */
    @Test
    public void createOrCopyEntryTest() {
        String repoId = null;
        Integer entryId = null;
        PostEntryChildrenRequest body = null;
        Boolean autoRename = null;
        String culture = null;
        // Entry response = api.createOrCopyEntry(repoId, entryId, body, autoRename, culture);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Remove the currently assigned template from the specified entry. - Provide an entry ID to clear template value on. - If the entry does not have a template assigned, no change will be made.
     */
    @Test
    public void deleteAssignedTemplateTest() {
        String repoId = null;
        Integer entryId = null;
        // Entry response = api.deleteAssignedTemplate(repoId, entryId);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Delete the edoc associated with the provided entry ID.
     */
    @Test
    public void deleteDocumentTest() {
        String repoId = null;
        Integer entryId = null;
        // ODataValueOfBoolean response = api.deleteDocument(repoId, entryId);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Begins a task to delete an entry, and returns an operationToken. - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately. - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
     */
    @Test
    public void deleteEntryInfoTest() {
        String repoId = null;
        Integer entryId = null;
        DeleteEntryWithAuditReason body = null;
        // AcceptedOperation response = api.deleteEntryInfo(repoId, entryId, body);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted. - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: \&quot;1,2,3\&quot;, \&quot;1-3,5\&quot;, \&quot;2-7,10-12.\&quot;
     */
    @Test
    public void deletePagesTest() {
        String repoId = null;
        Integer entryId = null;
        String pageRange = null;
        // ODataValueOfBoolean response = api.deletePages(repoId, entryId, pageRange);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get an entry&#x27;s edoc resource in a stream format. - Provide an entry ID, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.
     */
    @Test
    public void exportDocumentTest() {
        String repoId = null;
        Integer entryId = null;
        String range = null;
        // File response = api.exportDocument(repoId, entryId, range);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get an entry&#x27;s edoc resource in a stream format while including an audit reason. - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content. - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.
     */
    @Test
    public void exportDocumentWithAuditReasonTest() {
        String repoId = null;
        Integer entryId = null;
        GetEdocWithAuditReasonRequest body = null;
        String range = null;
        // File response = api.exportDocumentWithAuditReason(repoId, entryId, body, range);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get information about the edoc content of an entry, without downloading the edoc in its entirety. - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers. - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.
     */
    @Test
    public void getDocumentContentTypeTest() {
        String repoId = null;
        Integer entryId = null;
        // Void response = api.getDocumentContentType(repoId, entryId);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get dynamic field logic values with the current values of the fields in the template. - Provide an entry ID and field values in the JSON body to get dynamic field logic values.  Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.
     */
    @Test
    public void getDynamicFieldValuesTest() {
        String repoId = null;
        Integer entryId = null;
        GetDynamicFieldLogicValueRequest body = null;
        // Map<String, List<String>> response = api.getDynamicFieldValues(repoId, entryId, body);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns a single entry object. - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc. - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.
     */
    @Test
    public void getEntryTest() {
        String repoId = null;
        Integer entryId = null;
        String $select = null;
        // Entry response = api.getEntry(repoId, entryId, $select);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the children entries of a folder in the repository. - Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: \&quot;PropertyName direction,PropertyName2 direction\&quot;. Sort order can be either value \&quot;asc\&quot; or \&quot;desc\&quot;. Optional query parameters: groupByOrderType (bool). This query parameter decides if results are returned in groups based on their entry type. Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route. - Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names. - If field values are requested, only the first value is returned if it is a multi value field. - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     */
    @Test
    public void getEntryListingTest() {
        String repoId = null;
        Integer entryId = null;
        Boolean groupByEntryType = null;
        List<String> fields = null;
        Boolean formatFields = null;
        String prefer = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfEntry response = api.getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the fields assigned to an entry. - Provide an entry ID, and get a paged listing of all fields assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getFieldValuesTest() {
        String repoId = null;
        Integer entryId = null;
        String prefer = null;
        Boolean formatValue = null;
        String culture = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfFieldValue response = api.getFieldValues(repoId, entryId, prefer, formatValue, culture, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get the links assigned to an entry. - Provide an entry ID, and get a paged listing of links assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getLinkValuesFromEntryTest() {
        String repoId = null;
        Integer entryId = null;
        String prefer = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfWEntryLinkInfo response = api.getLinkValuesFromEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Get the tags assigned to an entry. - Provide an entry ID, and get a paged listing of tags assigned to that entry. - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     */
    @Test
    public void getTagsAssignedToEntryTest() {
        String repoId = null;
        Integer entryId = null;
        String prefer = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfIListOfWTagInfo response = api.getTagsAssignedToEntry(repoId, entryId, prefer, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Moves and/or renames an entry. - Move and/or rename an entry by passing in the new parent folder ID or name in the JSON body. - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     */
    @Test
    public void moveOrRenameDocumentTest() {
        String repoId = null;
        Integer entryId = null;
        PatchEntryRequest body = null;
        Boolean autoRename = null;
        String culture = null;
        // Entry response = api.moveOrRenameDocument(repoId, entryId, body, autoRename, culture);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Export an entry. - Provide an entry ID, part, format, page range, and audit event and export the entry part as the provided format. - This operation is a &#x27;simple operation&#x27;. For more functionality, refer to the non-simple export route.
     */
    @Test
    public void simpleExportTest() {
        Part part = null;
        String repoId = null;
        Integer entryId = null;
        ExportRequestBody body = null;
        Format format = null;
        String pageRange = null;
        // ODataValueOfString response = api.simpleExport(part, repoId, entryId, body, format, pageRange);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Import a new document in the specified folder with file (no more than 100Mb). - Optionally sets metadata. - With this route, partial success is possible. The response returns error message, which contain information about any errors that may have occurred during the import. As long as the documentLink returned, the entry is created, even if other steps fail.
     */
    @Test
    public void simpleImportTest() {
        String repoId = null;
        Integer parentEntryId = null;
        File file = null;
        SimpleImportRequest request = null;
        String culture = null;
        // SimpleImportResult response = api.simpleImport(repoId, parentEntryId, file, request, culture);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Assign a template to an entry. - Provide an entry ID, template name, and a list of template fields to assign to that entry. - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.
     */
    @Test
    public void writeTemplateValueToEntryTest() {
        String repoId = null;
        Integer entryId = null;
        PutTemplateRequest body = null;
        String culture = null;
        // Entry response = api.writeTemplateValueToEntry(repoId, entryId, body, culture);

        // TODO: test validations
    }
}
