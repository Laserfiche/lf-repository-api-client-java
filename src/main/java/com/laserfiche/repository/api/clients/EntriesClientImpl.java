package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.BaseClient;
import com.laserfiche.repository.api.ForEachCallBack;
import com.laserfiche.repository.api.clients.impl.EntriesApi;
import com.laserfiche.repository.api.clients.impl.EntriesApiEx;
import com.laserfiche.repository.api.clients.impl.model.*;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EntriesClientImpl extends BaseClient<EntriesApi, EntriesApiEx> implements EntriesClient {
    public EntriesClientImpl(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        super(clientBuilder, okBuilder);
        super.setupClients(EntriesApi.class, EntriesApiEx.class);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId, List<PutLinksRequest> body) {
        return generatedClient.assignEntryLinks(repoId, entryId, body);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId, Map<String, FieldToUpdate> body, String culture) {
        return generatedClient.assignFieldValues(repoId, entryId, body, culture);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId, PutTagRequest body) {
        return generatedClient.assignTags(repoId, entryId, body);
    }

    @Override
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId, CopyAsyncRequest body, Boolean autoRename, String culture) {
        return generatedClient.copyEntryAsync(repoId, entryId, body, autoRename, culture);
    }

    @Override
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId, PostEntryChildrenRequest body, Boolean autoRename, String culture) {
        return generatedClient.createOrCopyEntry(repoId, entryId, body, autoRename, culture);
    }

    @Override
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId) {
        return generatedClient.deleteAssignedTemplate(repoId, entryId);
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId) {
        return generatedClient.deleteDocument(repoId, entryId);
    }

    @Override
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId, DeleteEntryWithAuditReason body) {
        return generatedClient.deleteEntryInfo(repoId, entryId, body);
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange) {
        return generatedClient.deletePages(repoId, entryId, pageRange);
    }

    @Override
    public CompletableFuture<File> exportDocument(String repoId, Integer entryId, String range) {
        return generatedClient.exportDocument(repoId, entryId, range);
    }

    @Override
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId, GetEdocWithAuditReasonRequest body, String range) {
        return generatedClient.exportDocumentWithAuditReason(repoId, entryId, body, range);
    }

    @Override
    public CompletableFuture<Void> getDocumentContentType(String repoId, Integer entryId) {
        return generatedClient.getDocumentContentType(repoId, entryId);
    }

    @Override
    public CompletableFuture<Map<String, List<String>>> getDynamicFieldValues(String repoId, Integer entryId, GetDynamicFieldLogicValueRequest body) {
        return generatedClient.getDynamicFieldValues(repoId, entryId, body);
    }

    @Override
    public CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String select) {
        return generatedClient.getEntry(repoId, entryId, select);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId, Boolean groupByEntryType, List<String> fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getEntryListingPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getEntryListingForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfEntry>> callback, String repoId, Integer entryId, Boolean groupByEntryType, List<String> fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfEntry> future = getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getEntryListingNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getFieldValues(repoId, entryId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), formatValue, culture, select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getFieldValuesPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getFieldValuesForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfFieldValue>> callback, String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfFieldValue> future = getFieldValues(repoId, entryId, prefer, formatValue, culture, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getFieldValuesNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getLinkValuesFromEntry(repoId, entryId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getLinkValuesFromEntryPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getLinkValuesFromEntryForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>> callback, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> future = getLinkValuesFromEntry(repoId, entryId, prefer, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        return generatedClient.getTagsAssignedToEntry(repoId, entryId, mergeMaxPageSizeIntoPrefer(maxPageSize, prefer), select, orderby, top, skip, count);
    }

    @Override
    public CompletableFuture<CreateEntryResult> importDocument(String repoId, Integer parentEntryId, String fileName, RequestBody electronicDocument, PostEntryWithEdocMetadataRequest request, Boolean autoRename, String culture) {
        return generatedClient.importDocument(repoId, parentEntryId, fileName, electronicDocument, request, autoRename, culture);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink, Integer maxPageSize) {
        return extensionClient.getTagsAssignedToEntryPaginate(nextLink, mergeMaxPageSizeIntoPrefer(maxPageSize, null));
    }

    @Override
    public void getTagsAssignedToEntryForEach(ForEachCallBack<CompletableFuture<ODataValueContextOfIListOfWTagInfo>> callback, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count, Integer maxPageSize) {
        // Initial request
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> future = getTagsAssignedToEntry(repoId, entryId, prefer, select, orderby, top, skip, count, maxPageSize);
        // Subsequent request based on return value of callback
        while (callback.apply(future)) {
            future = future.thenCompose(dataFromLastRequest -> {
                String nextLink = dataFromLastRequest.getAtOdataNextLink();
                if (nextLink == null) {
                    // We are at the end of the data stream
                    return CompletableFuture.completedFuture(null);
                }
                return getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
            });
        }
    }

    @Override
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest body, Boolean autoRename, String culture) {
        return generatedClient.moveOrRenameDocument(repoId, entryId, body, autoRename, culture);
    }

    @Override
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId, PutTemplateRequest body, String culture) {
        return generatedClient.writeTemplateValueToEntry(repoId, entryId, body, culture);
    }

}
