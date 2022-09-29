package com.laserfiche.repository.api.clients.impl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.EntriesClient;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    @Override
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValues(String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetFieldValues(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", repoId, entryId, prefer, formatValue, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfFieldValue> doGetFieldValues(String url, String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "formatValue", "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { formatValue, culture, select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfFieldValue.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfFieldValue> getFieldValuesNextLink(String nextLink, Integer maxPageSize) {
        return doGetFieldValues(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getFieldValuesForEach(Function<CompletableFuture<ODataValueContextOfIListOfFieldValue>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, Boolean formatValue, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfFieldValue> response = getFieldValues(repoId, entryId, prefer, formatValue, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getAtOdataNextLink();
            response = getFieldValuesNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfFieldValue> assignFieldValues(String repoId, Integer entryId, Map<String, FieldToUpdate> requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture" }, new Object[] { culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields").queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(ODataValueOfIListOfFieldValue.class).thenApply(httpResponse -> {
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
    public CompletableFuture<CreateEntryResult> importDocument(String repoId, Integer parentEntryId, String fileName, Boolean autoRename, String culture, File file, PostEntryWithEdocMetadataRequest requestBody) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "autoRename", "culture" }, new Object[] { autoRename, culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "parentEntryId", "fileName" }, new Object[] { repoId, parentEntryId, fileName });
        return httpClient.post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .queryString(queryParameters).routeParam(pathParameters).body(requestBody)
                .field("electronicDocument", file).asObjectAsync(CreateEntryResult.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetLinkValuesFromEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", repoId, entryId, prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> doGetLinkValuesFromEntry(String url, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfWEntryLinkInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> getLinkValuesFromEntryNextLink(String nextLink, Integer maxPageSize) {
        return doGetLinkValuesFromEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getLinkValuesFromEntryForEach(Function<CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWEntryLinkInfo> response = getLinkValuesFromEntry(repoId, entryId, prefer, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getAtOdataNextLink();
            response = getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWEntryLinkInfo> assignEntryLinks(String repoId, Integer entryId, List<PutLinksRequest> requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links").routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(ODataValueOfIListOfWEntryLinkInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<Entry> writeTemplateValueToEntry(String repoId, Integer entryId, PutTemplateRequest requestBody, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "culture" }, new Object[] { culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template").queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(Entry.class).thenApply(httpResponse -> {
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
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template").routeParam(pathParameters).asObjectAsync(Entry.class).thenApply(httpResponse -> {
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
    public CompletableFuture<File> exportDocumentWithAuditReason(String repoId, Integer entryId, GetEdocWithAuditReasonRequest requestBody, String range) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "range" }, new Object[] { range });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason").routeParam(pathParameters).headers(headerParametersWithStringTypeValue).contentType("application/json").body(requestBody).asObjectAsync(File.class).thenApply(httpResponse -> {
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
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "range" }, new Object[] { range });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc").routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(File.class).thenApply(httpResponse -> {
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
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc").routeParam(pathParameters).asObjectAsync(ODataValueOfBoolean.class).thenApply(httpResponse -> {
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
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "pageRange" }, new Object[] { pageRange });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(ODataValueOfBoolean.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListing(String repoId, Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetEntryListing(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children", repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfEntry> doGetEntryListing(String url, String repoId, Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "groupByEntryType", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { groupByEntryType, fields, formatFields, culture, select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfEntry.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfEntry> getEntryListingNextLink(String nextLink, Integer maxPageSize) {
        return doGetEntryListing(nextLink, null, null, null, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getEntryListingForEach(Function<CompletableFuture<ODataValueContextOfIListOfEntry>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, Boolean groupByEntryType, String[] fields, Boolean formatFields, String prefer, String culture, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfEntry> response = getEntryListing(repoId, entryId, groupByEntryType, fields, formatFields, prefer, culture, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getAtOdataNextLink();
            response = getEntryListingNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Entry> createOrCopyEntry(String repoId, Integer entryId, PostEntryChildrenRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "autoRename", "culture" }, new Object[] { autoRename, culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children").queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(Entry.class).thenApply(httpResponse -> {
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
    public CompletableFuture<Map<String, String[]>> getDynamicFieldValues(String repoId, Integer entryId, GetDynamicFieldLogicValueRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue").routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync((new HashMap<String, String[]>()).getClass()).thenApply(httpResponse -> {
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
    public CompletableFuture<AcceptedOperation> copyEntryAsync(String repoId, Integer entryId, CopyAsyncRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "autoRename", "culture" }, new Object[] { autoRename, culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync").queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(AcceptedOperation.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntry(String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        return doGetTagsAssignedToEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", repoId, entryId, prefer, select, orderby, top, skip, count);
    }

    private CompletableFuture<ODataValueContextOfIListOfWTagInfo> doGetTagsAssignedToEntry(String url, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "$select", "$orderby", "$top", "$skip", "$count" }, new Object[] { select, orderby, top, skip, count });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        Map<String, Object> headerParameters = getNonNullParameters(new String[] { "prefer" }, new Object[] { prefer });
        Map<String, String> headerParametersWithStringTypeValue = headerParameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        return httpClient.get(url).queryString(queryParameters).routeParam(pathParameters).headers(headerParametersWithStringTypeValue).asObjectAsync(ODataValueContextOfIListOfWTagInfo.class).thenApply(httpResponse -> {
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
    public CompletableFuture<ODataValueContextOfIListOfWTagInfo> getTagsAssignedToEntryNextLink(String nextLink, Integer maxPageSize) {
        return doGetTagsAssignedToEntry(nextLink, null, null, mergeMaxSizeIntoPrefer(maxPageSize, null), null, null, null, null, null);
    }

    @Override
    public CompletableFuture<Void> getTagsAssignedToEntryForEach(Function<CompletableFuture<ODataValueContextOfIListOfWTagInfo>, CompletableFuture<Boolean>> callback, Integer maxPageSize, String repoId, Integer entryId, String prefer, String select, String orderby, Integer top, Integer skip, Boolean count) throws InterruptedException, ExecutionException {
        prefer = mergeMaxSizeIntoPrefer(maxPageSize, prefer);
        CompletableFuture<ODataValueContextOfIListOfWTagInfo> response = getTagsAssignedToEntry(repoId, entryId, prefer, select, orderby, top, skip, count);
        while (response != null && callback.apply(response).get()) {
            String nextLink = response.get().getAtOdataNextLink();
            response = getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ODataValueOfIListOfWTagInfo> assignTags(String repoId, Integer entryId, PutTagRequest requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags").routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(ODataValueOfIListOfWTagInfo.class).thenApply(httpResponse -> {
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
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "$select" }, new Object[] { select });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}").queryString(queryParameters).routeParam(pathParameters).asObjectAsync(Entry.class).thenApply(httpResponse -> {
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
    public CompletableFuture<Entry> moveOrRenameDocument(String repoId, Integer entryId, PatchEntryRequest requestBody, Boolean autoRename, String culture) {
        Map<String, Object> queryParameters = getNonNullParameters(new String[] { "autoRename", "culture" }, new Object[] { autoRename, culture });
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}").queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(Entry.class).thenApply(httpResponse -> {
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
    public CompletableFuture<AcceptedOperation> deleteEntryInfo(String repoId, Integer entryId, DeleteEntryWithAuditReason requestBody) {
        Map<String, Object> pathParameters = getNonNullParameters(new String[] { "repoId", "entryId" }, new Object[] { repoId, entryId });
        return httpClient.delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}").routeParam(pathParameters).contentType("application/json").body(requestBody).asObjectAsync(AcceptedOperation.class).thenApply(httpResponse -> {
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
