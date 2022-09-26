package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import kong.unirest.UnirestInstance;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId,
            String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top,
            Integer skip, Boolean count) {
        return doGetFieldValues(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", repoId, entryId, prefer,
                formatValue, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfFieldValue> doGetFieldValues(String url, String repoId,
            Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby,
            Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"formatValue", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{formatValue, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink,
            int maxPageSize) {
        return doGetFieldValues(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId,
            Map<String, FieldToUpdate> requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture"}, new Object[]{culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<CreateEntryResult> importDocument(String repoId, Integer parentEntryId, String fileName,
            Boolean autoRename, String culture, File file, PostEntryWithEdocMetadataRequest requestBody) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "parentEntryId", "fileName"},
                new Object[]{repoId, parentEntryId, fileName});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("multipart/form-data")
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId,
            Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkValuesFromEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", repoId, entryId,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> doGetLinkValuesFromEntry(String url,
            String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip,
            Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink,
            int maxPageSize) {
        return doGetLinkValuesFromEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId,
            List<PutLinksRequest> requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links")
                .routeParam(pathParameters)
                .contentType("application/json")
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId,
            PutTemplateRequest requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"culture"}, new Object[]{culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() == 423) {
                        throw new RuntimeException("Entry is locked.");
                    }
                    if (httpResponse.getStatus() == 429) {
                        throw new RuntimeException("Rate limit is reached.");
                    }
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> deleteAssignedTemplate(String repoId, Integer entryId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId,
            GetEdocWithAuditReasonRequest requestBody, String range) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<File> exportDocument(String repoId, Integer entryId, String range) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deleteDocument(String repoId, Integer entryId) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueOfBoolean> deletePages(String repoId, Integer entryId, String pageRange) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"pageRange"}, new Object[]{pageRange});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId,
            Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture,
            String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetEntryListing(
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children", repoId,
                entryId, groupByEntryType, fields, formatFields, prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntry> doGetEntryListing(String url, String repoId,
            Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer,
            String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"groupByEntryType", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{groupByEntryType, fields, formatFields, culture, select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink,
            int maxPageSize) {
        return doGetEntryListing(nextLink, null, null, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null),
                null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId,
            PostEntryChildrenRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Map<String, String[]>> getDynamicFieldValues(String repoId, Integer entryId,
            GetDynamicFieldLogicValueRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId,
            CopyAsyncRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId,
            String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTagsAssignedToEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", repoId, entryId,
                prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWTagInfo> doGetTagsAssignedToEntry(String url, String repoId,
            Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{select, orderby, top, skip, count});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink,
            int maxPageSize) {
        return doGetTagsAssignedToEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null,
                null, null, null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId,
            PutTagRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags")
                .routeParam(pathParameters)
                .contentType("application/json")
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> getEntry(String repoId, Integer entryId, String select) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"$select"}, new Object[]{select});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest requestBody,
            Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[]{"autoRename", "culture"},
                new Object[]{autoRename, culture});
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }

    @Override
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId,
            DeleteEntryWithAuditReason requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[]{"repoId", "entryId"},
                new Object[]{repoId, entryId});
        return httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam(pathParameters)
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
                    if (httpResponse.getStatus() >= 299) {
                        throw new RuntimeException(httpResponse.getStatusText());
                    }
                    return httpResponse.getBody();
                });
    }
}
