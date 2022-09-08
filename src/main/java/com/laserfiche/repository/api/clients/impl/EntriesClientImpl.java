package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.GenericType;
import kong.unirest.UnirestInstance;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override()
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId,
            String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId,
            FieldToUpdate requestBody, String culture) {
        return httpClient
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

    @Override()
    public CompletableFuture<CreateEntryResult> importDocument(PostEntryWithEdocMetadataRequest requestBody,
            String repoId, Integer parentEntryId, String fileName, Boolean autoRename, String culture) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .routeParam("repoId", repoId)
                .routeParam("parentEntryId", String.valueOf(parentEntryId))
                .routeParam("fileName", fileName)
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .contentType("application/json")
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

    @Override()
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId,
            Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId,
            PutLinksRequest requestBody) {
        return httpClient
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

    @Override()
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId,
            PutTemplateRequest requestBody, String culture) {
        return httpClient
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

    @Override()
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId) {
        return httpClient
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

    @Override()
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId,
            GetEdocWithAuditReasonRequest requestBody, String range) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .header("range", range)
                .contentType("application/json")
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

    @Override()
    public CompletableFuture<File> exportDocument(String repoId, Integer entryId, String range) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId,
            Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture,
            String select, String orderby, Integer top, Integer skip, Boolean count) {
        return httpClient
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

    @Override()
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId,
            PostEntryChildrenRequest requestBody, Boolean autoRename, String culture) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .contentType("application/json")
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

    @Override()
    public CompletableFuture<Map<String, String[]>> getDynamicFieldValues(String repoId, Integer entryId,
            GetDynamicFieldLogicValueRequest requestBody) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .contentType("application/json")
                .body(requestBody)
                .asObjectAsync((new HashMap<String, String[]>()).getClass())
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

    @Override()
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId,
            CopyAsyncRequest requestBody, Boolean autoRename, String culture) {
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .contentType("application/json")
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

    @Override()
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId,
            String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return httpClient
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

    @Override()
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId,
            PutTagRequest requestBody) {
        return httpClient
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

    @Override()
    public CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String select) {
        return httpClient
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

    @Override()
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest requestBody,
            Boolean autoRename, String culture) {
        return httpClient
                .patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam("repoId", repoId)
                .routeParam("entryId", String.valueOf(entryId))
                .queryString("autoRename", autoRename)
                .queryString("culture", culture)
                .contentType("application/json")
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

    @Override()
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId,
            DeleteEntryWithAuditReason requestBody) {
        return httpClient
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
