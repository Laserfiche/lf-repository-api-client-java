package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.Unirest;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, int entryId,
            String prefer, boolean formatValue, String culture, String select, String orderby, int top, int skip,
            boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("formatValue", formatValue)
                .queryString("culture", culture)
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfFieldValue.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, int entryId,
            FieldToUpdate requestBody, String culture) {
        return Unirest
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(ODataValueOfIListOfFieldValue.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Requested entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<CreateEntryResult> importDocument(PostEntryWithEdocMetadataRequest requestBody,
            String repoId, int parentEntryId, String fileName, boolean autoRename, String culture) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .routeParam("repoId", repoId)
                .routeParam("parentEntryId", String.valueOf(parentEntryId))
                .routeParam("fileName", fileName)
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(CreateEntryResult.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Parent entry is not found.");
                    }
                    if (httpResponse.getStatus() == 409) {
                        throw new RuntimeException("Document creation is partial success.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() == 500) {
                        throw new RuntimeException("Document creation is complete failure.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId,
            int entryId, String prefer, String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfWEntryLinkInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, int entryId,
            PutLinksRequest requestBody) {
        return Unirest
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .body(requestBody)
                .asObjectAsync(ODataValueOfIListOfWEntryLinkInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, int entryId,
            PutTemplateRequest requestBody, String culture) {
        return Unirest
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(Entry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, int entryId) {
        return Unirest
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .asObjectAsync(Entry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, int entryId,
            GetEdocWithAuditReasonRequest requestBody, String range) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .header("range", range)
                .body(requestBody)
                .asObjectAsync(File.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<File> exportDocument(String repoId, int entryId, String range) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .header("range", range)
                .asObjectAsync(File.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, int entryId) {
        return Unirest
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .asObjectAsync(ODataValueOfBoolean.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, int entryId, String pageRange) {
        return Unirest
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("pageRange", pageRange)
                .asObjectAsync(ODataValueOfBoolean.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, int entryId,
            boolean groupByEntryType, String[] fields, boolean formatFields, String prefer, String culture,
            String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("groupByEntryType", groupByEntryType)
                .queryString("fields", fields)
                .queryString("formatFields", formatFields)
                .queryString("culture", culture)
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfEntry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, int entryId, PostEntryChildrenRequest requestBody,
            boolean autoRename, String culture) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(Entry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 409) {
                        throw new RuntimeException("Entry name conflicts.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<String[]> getDynamicFieldValues(String repoId, int entryId,
            GetDynamicFieldLogicValueRequest requestBody) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .body(requestBody)
                .asObjectAsync(String[].class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, int entryId, CopyAsyncRequest requestBody,
            boolean autoRename, String culture) {
        return Unirest
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(AcceptedOperation.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Operation limit or request limit reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, int entryId,
            String prefer, String select, String orderby, int top, int skip, boolean count) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("select", select)
                .queryString("orderby", orderby)
                .queryString("top", top)
                .queryString("skip", skip)
                .queryString("count", count)
                .header("prefer", prefer)
                .asObjectAsync(ODataValueContextOfIListOfWTagInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, int entryId,
            PutTagRequest requestBody) {
        return Unirest
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .body(requestBody)
                .asObjectAsync(ODataValueOfIListOfWTagInfo.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request id not found.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> getEntry(String repoId, int entryId, String select) {
        return Unirest
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("select", select)
                .asObjectAsync(Entry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Requested entry id not found.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, int entryId, PatchEntryRequest requestBody,
            boolean autoRename, String culture) {
        return Unirest
                .patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .body(requestBody)
                .asObjectAsync(Entry.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 404) {
                        throw new RuntimeException("Request entry id not found.");
                    }
                    if (httpResponse.getStatus() == 409) {
                        throw new RuntimeException("Entry name conflicts.");
                    }
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, int entryId,
            DeleteEntryWithAuditReason requestBody) {
        return Unirest
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .body(requestBody)
                .asObjectAsync(AcceptedOperation.class)
                .thenApply(httpResponse -> {
                    if (httpResponse.getStatus() == 400) {
                        throw new RuntimeException("Invalid or bad request.");
                    }
                    if (httpResponse.getStatus() == 401) {
                        throw new RuntimeException("Access token is invalid or expired.");
                    }
                    if (httpResponse.getStatus() == 403) {
                        throw new RuntimeException("Access denied for the operation.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Operation limit or request limit reached.");
                    }
                    return httpResponse.getBody();
                });
    }
}
