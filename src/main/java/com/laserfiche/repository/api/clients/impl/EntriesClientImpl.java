package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import kong.unirest.*;
import kong.unirest.json.JSONObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntriesClientImpl extends ApiClient implements EntriesClient {

    public EntriesClientImpl(String baseUrl, UnirestInstance httpClient, ObjectMapper objectMapper) {
        super(baseUrl, httpClient, objectMapper);
    }

    /**
     * - Returns the fields assigned to an entry.
     * - Provide an entry ID, and get a paged listing of all fields assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type ParametersForGetFieldValues which encapsulates the parameters of getFieldValues method.
     * @return ODataValueContextOfIListOfFieldValue The return value
     */
    @Override
    public ODataValueContextOfIListOfFieldValue getFieldValues(ParametersForGetFieldValues parameters) {
        return doGetFieldValues(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields", parameters);
    }

    private ODataValueContextOfIListOfFieldValue doGetFieldValues(String url, ParametersForGetFieldValues parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"boolean", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"formatValue", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isFormatValue(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfFieldValue.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfFieldValue getFieldValuesNextLink(String nextLink, int maxPageSize) {
        return doGetFieldValues(nextLink,
                new ParametersForGetFieldValues().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getFieldValuesForEach(Function<ODataValueContextOfIListOfFieldValue, Boolean> callback,
            Integer maxPageSize, ParametersForGetFieldValues parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfFieldValue response = getFieldValues(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getFieldValuesNextLink(nextLink, maxPageSize);
        }
    }

    /**
     * - Update the field values assigned to an entry.
     * - Provide the new field values to assign to the entry, and remove/reset all previously assigned field values.
     * - This is an overwrite action. The request body must include all desired field values, including any existing field values that should remain assigned to the entry. Field values that are not included in the request will be deleted from the entry. If the field value that is not included is part of a template, it will still be assigned (as required by the template), but its value will be reset.
     *
     * @param parameters An object of type ParametersForAssignFieldValues which encapsulates the parameters of assignFieldValues method.
     * @return ODataValueOfIListOfFieldValue The return value
     */
    @Override
    public ODataValueOfIListOfFieldValue assignFieldValues(ParametersForAssignFieldValues parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"culture"}, new Object[]{parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfIListOfFieldValue.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Requested entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Creates a new document in the specified folder with file (no more than 100 MB).
     * - Optionally sets metadata and electronic document component.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed. With this route, partial success is possible. The response returns multiple operation (entryCreate operation, setEdoc operation, setLinks operation, etc..) objects, which contain information about any errors that may have occurred during the creation. As long as the entryCreate operation succeeds, the entry will be created, even if all other operations fail.
     *
     * @param parameters An object of type ParametersForImportDocument which encapsulates the parameters of importDocument method.
     * @return CreateEntryResult The return value
     */
    @Override
    public CreateEntryResult importDocument(ParametersForImportDocument parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean", "String"},
                new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int", "String"},
                new String[]{"repoId", "parentEntryId", "fileName"},
                new Object[]{parameters.getRepoId(), parameters.getParentEntryId(), parameters.getFileName()});
        HttpResponse<Object> httpResponse = httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{parentEntryId}/{fileName}")
                .field("electronicDocument", parameters.getInputStream(), parameters.getFileName())
                .field("request", toJson(parameters.getRequestBody()))
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 201 || httpResponse.getStatus() == 409) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, CreateEntryResult.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Parent entry is not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 500)
                throw new ApiException(decideErrorMessage(problemDetails, "Document creation is complete failure."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns the links assigned to an entry.
     * - Provide an entry ID, and get a paged listing of links assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type ParametersForGetLinkValuesFromEntry which encapsulates the parameters of getLinkValuesFromEntry method.
     * @return ODataValueContextOfIListOfWEntryLinkInfo The return value
     */
    @Override
    public ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntry(
            ParametersForGetLinkValuesFromEntry parameters) {
        return doGetLinkValuesFromEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links", parameters);
    }

    private ODataValueContextOfIListOfWEntryLinkInfo doGetLinkValuesFromEntry(String url,
            ParametersForGetLinkValuesFromEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWEntryLinkInfo.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfWEntryLinkInfo getLinkValuesFromEntryNextLink(String nextLink, int maxPageSize) {
        return doGetLinkValuesFromEntry(nextLink,
                new ParametersForGetLinkValuesFromEntry().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getLinkValuesFromEntryForEach(Function<ODataValueContextOfIListOfWEntryLinkInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetLinkValuesFromEntry parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWEntryLinkInfo response = getLinkValuesFromEntry(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getLinkValuesFromEntryNextLink(nextLink, maxPageSize);
        }
    }

    /**
     * - Assign links to an entry.
     * - Provide an entry ID and a list of links to assign to that entry.
     * - This is an overwrite action. The request must include all links to assign to the entry, including existing links that should remain assigned to the entry.
     *
     * @param parameters An object of type ParametersForAssignEntryLinks which encapsulates the parameters of assignEntryLinks method.
     * @return ODataValueOfIListOfWEntryLinkInfo The return value
     */
    @Override
    public ODataValueOfIListOfWEntryLinkInfo assignEntryLinks(ParametersForAssignEntryLinks parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/links")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfIListOfWEntryLinkInfo.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Assign a template to an entry.
     * - Provide an entry ID, template name, and a list of template fields to assign to that entry.
     * - Only template values will be modified. Any existing independent fields on the entry will not be modified, nor will they be added if included in the request. The only modification to fields will only occur on templated fields. If the previously assigned template includes common template fields as the newly assigned template, the common field values will not be modified.
     *
     * @param parameters An object of type ParametersForWriteTemplateValueToEntry which encapsulates the parameters of writeTemplateValueToEntry method.
     * @return Entry The return value
     */
    @Override
    public Entry writeTemplateValueToEntry(ParametersForWriteTemplateValueToEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"culture"}, new Object[]{parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Entry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Remove the currently assigned template from the specified entry.
     * - Provide an entry ID to clear template value on.
     * - If the entry does not have a template assigned, no change will be made.
     *
     * @param parameters An object of type ParametersForDeleteAssignedTemplate which encapsulates the parameters of deleteAssignedTemplate method.
     * @return Entry The return value
     */
    @Override
    public Entry deleteAssignedTemplate(ParametersForDeleteAssignedTemplate parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/template")
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Entry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns dynamic field logic values with the current values of the fields in the template.
     * - Provide an entry ID and field values in the JSON body to get dynamic field logic values.
     * Independent and non-dynamic fields in the request body will be ignored, and only related dynamic field logic values for the assigned template will be returned.
     *
     * @param parameters An object of type ParametersForGetDynamicFieldValues which encapsulates the parameters of getDynamicFieldValues method.
     * @return Map&lt;String,String[]&gt; The return value
     */
    @Override
    public Map<String, String[]> getDynamicFieldValues(ParametersForGetDynamicFieldValues parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/fields/GetDynamicFieldLogicValue")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject((new HashMap<String, String[]>()).getClass());
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, new HashMap<String, String[]>().getClass());
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns a single entry object using the entry path.
     * - Optional query parameter: fallbackToClosestAncestor. Use the fallbackToClosestAncestor query parameter to return the closest existing ancestor if the initial entry path is not found.
     *
     * @param parameters An object of type ParametersForGetEntryByPath which encapsulates the parameters of getEntryByPath method.
     * @return FindEntryResult The return value
     */
    @Override
    public FindEntryResult getEntryByPath(ParametersForGetEntryByPath parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String", "boolean"},
                new String[]{"fullPath", "fallbackToClosestAncestor"},
                new Object[]{parameters.getFullPath(), parameters.isFallbackToClosestAncestor()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/ByPath")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, FindEntryResult.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Requested entry path not found"),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Copy a new child entry in the designated folder async, and potentially return an operationToken.
     * - Provide the parent folder ID, and copy an entry as a child of the designated folder.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     * - The status of the operation can be checked via the Tasks/{operationToken} route.
     *
     * @param parameters An object of type ParametersForCopyEntryAsync which encapsulates the parameters of copyEntryAsync method.
     * @return AcceptedOperation The return value
     */
    @Override
    public AcceptedOperation copyEntryAsync(ParametersForCopyEntryAsync parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean", "String"},
                new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/CopyAsync")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 201) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, AcceptedOperation.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Operation limit or request limit reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns a single entry object.
     * - Provide an entry ID, and get the entry associated with that ID. Useful when detailed information about the entry is required, such as metadata, path information, etc.
     * - Allowed OData query options: Select. If the entry is a subtype (Folder, Document, or Shortcut), the entry will automatically be converted to include those model-specific properties.
     *
     * @param parameters An object of type ParametersForGetEntry which encapsulates the parameters of getEntry method.
     * @return Entry The return value
     */
    @Override
    public Entry getEntry(ParametersForGetEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"$select"}, new Object[]{parameters.getSelect()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Entry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Requested entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Moves and/or renames an entry.
     * - Move and/or rename an entry by passing in the new parent folder ID or name in the JSON body.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     *
     * @param parameters An object of type ParametersForMoveOrRenameEntry which encapsulates the parameters of moveOrRenameEntry method.
     * @return Entry The return value
     */
    @Override
    public Entry moveOrRenameEntry(ParametersForMoveOrRenameEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean", "String"},
                new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .patch(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Entry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 409)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry name conflicts."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Begins a task to delete an entry, and returns an operationToken.
     * - Provide an entry ID, and queue a delete task to remove it from the repository (includes nested objects if the entry is a Folder type). The entry will not be deleted immediately.
     * - Optionally include an audit reason ID and comment in the JSON body. This route returns an operationToken, and will run as an asynchronous operation. Check the progress via the Tasks/{operationToken} route.
     *
     * @param parameters An object of type ParametersForDeleteEntryInfo which encapsulates the parameters of deleteEntryInfo method.
     * @return AcceptedOperation The return value
     */
    @Override
    public AcceptedOperation deleteEntryInfo(ParametersForDeleteEntryInfo parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 201) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, AcceptedOperation.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Operation limit or request limit reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns an entry's edoc resource in a stream format while including an audit reason.
     * - Provide an entry ID and audit reason/comment in the request body, and get the edoc resource as part of the response content.
     * - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc. This route is identical to the GET edoc route, but allows clients to include an audit reason when downloading the edoc.
     *
     * @param parameters An object of type ParametersForExportDocumentWithAuditReason which encapsulates the parameters of exportDocumentWithAuditReason method.
     */
    @Override
    public void exportDocumentWithAuditReason(ParametersForExportDocumentWithAuditReason parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            httpClient
                    .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/GetEdocWithAuditReason")
                    .routeParam(pathParameters)
                    .headers(headerParametersWithStringTypeValue)
                    .contentType("application/json")
                    .body(parameters.getRequestBody())
                    .thenConsume(rawResponse -> {
                        if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                            parameters
                                    .getInputStreamConsumer()
                                    .accept(rawResponse.getContent());
                        } else {
                            ProblemDetails problemDetails = null;
                            Map<String, String> headersMap = getHeadersMap(rawResponse.getHeaders());
                            try {
                                String jsonString = rawResponse.getContentAsString();
                                problemDetails = deserializeToProblemDetails(jsonString);
                            } catch (IllegalStateException e) {
                                exception[0] = new ApiException(rawResponse.getStatusText(), rawResponse.getStatus(),
                                        rawResponse.getContentAsString(), headersMap, null);
                            }
                            if (rawResponse.getStatus() == 400)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Invalid or bad request."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 401)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 403)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access denied for the operation."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 404)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Request entry id not found."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 423)
                                exception[0] = new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 429)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Rate limit is reached."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else
                                exception[0] = new RuntimeException(rawResponse.getStatusText());
                        }
                    });
            if (exception[0] != null) {
                throw exception[0];
            }
        }
    }

    /**
     * - Returns an entry's edoc resource in a stream format.
     * - Provide an entry ID, and get the edoc resource as part of the response content.
     * - Optional header: Range. Use the Range header (single range with byte unit) to retrieve partial content of the edoc, rather than the entire edoc.
     *
     * @param parameters An object of type ParametersForExportDocument which encapsulates the parameters of exportDocument method.
     */
    @Override
    public void exportDocument(ParametersForExportDocument parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"range"}, new Object[]{parameters.getRange()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        {
            final RuntimeException[] exception = {null};
            httpClient
                    .get(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                    .routeParam(pathParameters)
                    .headers(headerParametersWithStringTypeValue)
                    .thenConsume(rawResponse -> {
                        if (rawResponse.getStatus() == 200 || rawResponse.getStatus() == 206) {
                            parameters
                                    .getInputStreamConsumer()
                                    .accept(rawResponse.getContent());
                        } else {
                            ProblemDetails problemDetails = null;
                            Map<String, String> headersMap = getHeadersMap(rawResponse.getHeaders());
                            try {
                                String jsonString = rawResponse.getContentAsString();
                                problemDetails = deserializeToProblemDetails(jsonString);
                            } catch (IllegalStateException e) {
                                exception[0] = new ApiException(rawResponse.getStatusText(), rawResponse.getStatus(),
                                        rawResponse.getContentAsString(), headersMap, null);
                            }
                            if (rawResponse.getStatus() == 400)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Invalid or bad request."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 401)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 403)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Access denied for the operation."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 404)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Request entry id not found."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 423)
                                exception[0] = new ApiException(decideErrorMessage(problemDetails, "Entry is locked."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else if (rawResponse.getStatus() == 429)
                                exception[0] = new ApiException(
                                        decideErrorMessage(problemDetails, "Rate limit is reached."),
                                        rawResponse.getStatus(), rawResponse.getStatusText(), headersMap,
                                        problemDetails);
                            else
                                exception[0] = new RuntimeException(rawResponse.getStatusText());
                        }
                    });
            if (exception[0] != null) {
                throw exception[0];
            }
        }
    }

    /**
     * - Delete the edoc associated with the provided entry ID.
     *
     * @param parameters An object of type ParametersForDeleteDocument which encapsulates the parameters of deleteDocument method.
     * @return ODataValueOfBoolean The return value
     */
    @Override
    public ODataValueOfBoolean deleteDocument(ParametersForDeleteDocument parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfBoolean.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns information about the edoc content of an entry, without downloading the edoc in its entirety.
     * - Provide an entry ID, and get back the Content-Type and Content-Length in the response headers.
     * - This route does not provide a way to download the actual edoc. Instead, it just gives metadata information about the edoc associated with the entry.
     *
     * @param parameters An object of type ParametersForGetDocumentContentType which encapsulates the parameters of getDocumentContentType method.
     * @return Map&lt;String,String&gt; The return value
     */
    @Override
    public Map<String, String> getDocumentContentType(ParametersForGetDocumentContentType parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .head(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/edoc")
                .routeParam(pathParameters)
                .asObject(new HashMap<String, String>().getClass());
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            return httpResponse
                    .getHeaders()
                    .all()
                    .stream()
                    .collect(Collectors.toMap(Header::getName, Header::getValue));
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Delete the pages associated with the provided entry ID. If no pageRange is specified, all pages will be deleted.
     * - Optional parameter: pageRange (default empty). The value should be a comma-seperated string which contains non-overlapping single values, or page ranges. Ex: &quot;1,2,3&quot;, &quot;1-3,5&quot;, &quot;2-7,10-12.&quot;
     *
     * @param parameters An object of type ParametersForDeletePages which encapsulates the parameters of deletePages method.
     * @return ODataValueOfBoolean The return value
     */
    @Override
    public ODataValueOfBoolean deletePages(ParametersForDeletePages parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"pageRange"}, new Object[]{parameters.getPageRange()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .delete(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Document/pages")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfBoolean.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns the children entries of a folder in the repository.
     * - Provide an entry ID (must be a folder), and get a paged listing of entries in that folder. Used as a way of navigating through the repository.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer. OData $OrderBy syntax should follow: &quot;PropertyName direction,PropertyName2 direction&quot;. Sort order can be either value &quot;asc&quot; or &quot;desc&quot;. Optional query parameters: groupByOrderType (bool). This query parameter decides if results are returned in groups based on their entry type. Entries returned in the listing are not automatically converted to their subtype (Folder, Shortcut, Document), so clients who want model-specific information should request it via the GET entry by ID route.
     * - Optionally returns field values for the entries in the folder. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     * @param parameters An object of type ParametersForGetEntryListing which encapsulates the parameters of getEntryListing method.
     * @return ODataValueContextOfIListOfEntry The return value
     */
    @Override
    public ODataValueContextOfIListOfEntry getEntryListing(ParametersForGetEntryListing parameters) {
        return doGetEntryListing(
                baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children",
                parameters);
    }

    private ODataValueContextOfIListOfEntry doGetEntryListing(String url, ParametersForGetEntryListing parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"boolean", "String[]", "boolean", "String", "String", "String", "int", "int", "boolean"},
                new String[]{"groupByEntryType", "fields", "formatFields", "culture", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isGroupByEntryType(), parameters.getFields(), parameters.isFormatFields(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString("fields", (queryParameters.get("fields") != null) ? (queryParameters.get(
                        "fields") instanceof String ? Arrays.asList(
                        queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList())
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfEntry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfEntry getEntryListingNextLink(String nextLink, int maxPageSize) {
        return doGetEntryListing(nextLink,
                new ParametersForGetEntryListing().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getEntryListingForEach(Function<ODataValueContextOfIListOfEntry, Boolean> callback, Integer maxPageSize,
            ParametersForGetEntryListing parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfEntry response = getEntryListing(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getEntryListingNextLink(nextLink, maxPageSize);
        }
    }

    /**
     * - Create/copy a new child entry in the designated folder.
     * - Provide the parent folder ID, and based on the request body, copy or create a folder/shortcut as a child entry of the designated folder.
     * - Optional parameter: autoRename (default false). If an entry already exists with the given name, the entry will be automatically renamed.
     *
     * @param parameters An object of type ParametersForCreateOrCopyEntry which encapsulates the parameters of createOrCopyEntry method.
     * @return Entry The return value
     */
    @Override
    public Entry createOrCopyEntry(ParametersForCreateOrCopyEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean", "String"},
                new String[]{"autoRename", "culture"},
                new Object[]{parameters.isAutoRename(), parameters.getCulture()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .post(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/Laserfiche.Repository.Folder/children")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 201) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Entry.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 409)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry name conflicts."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     * - Returns the tags assigned to an entry.
     * - Provide an entry ID, and get a paged listing of tags assigned to that entry.
     * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top | SkipToken | Prefer.
     *
     * @param parameters An object of type ParametersForGetTagsAssignedToEntry which encapsulates the parameters of getTagsAssignedToEntry method.
     * @return ODataValueContextOfIListOfWTagInfo The return value
     */
    @Override
    public ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntry(ParametersForGetTagsAssignedToEntry parameters) {
        return doGetTagsAssignedToEntry(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags", parameters);
    }

    private ODataValueContextOfIListOfWTagInfo doGetTagsAssignedToEntry(String url,
            ParametersForGetTagsAssignedToEntry parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"String", "String", "int", "int", "boolean"},
                new String[]{"$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
        HttpResponse<Object> httpResponse = httpClient
                .get(url)
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .headers(headerParametersWithStringTypeValue)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfWTagInfo.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request entry id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfIListOfWTagInfo getTagsAssignedToEntryNextLink(String nextLink, int maxPageSize) {
        return doGetTagsAssignedToEntry(nextLink,
                new ParametersForGetTagsAssignedToEntry().setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTagsAssignedToEntryForEach(Function<ODataValueContextOfIListOfWTagInfo, Boolean> callback,
            Integer maxPageSize, ParametersForGetTagsAssignedToEntry parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfIListOfWTagInfo response = getTagsAssignedToEntry(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTagsAssignedToEntryNextLink(nextLink, maxPageSize);
        }
    }

    /**
     * - Assign tags to an entry.
     * - Provide an entry ID and a list of tags to assign to that entry.
     * - This is an overwrite action. The request must include all tags to assign to the entry, including existing tags that should remain assigned to the entry.
     *
     * @param parameters An object of type ParametersForAssignTags which encapsulates the parameters of assignTags method.
     * @return ODataValueOfIListOfWTagInfo The return value
     */
    @Override
    public ODataValueOfIListOfWTagInfo assignTags(ParametersForAssignTags parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "int"},
                new String[]{"repoId", "entryId"}, new Object[]{parameters.getRepoId(), parameters.getEntryId()});
        HttpResponse<Object> httpResponse = httpClient
                .put(baseUrl + "/v1/Repositories/{repoId}/Entries/{entryId}/tags")
                .routeParam(pathParameters)
                .contentType("application/json")
                .body(parameters.getRequestBody())
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfIListOfWTagInfo.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Request id not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 423)
                throw new ApiException(decideErrorMessage(problemDetails, "Entry is locked."), httpResponse.getStatus(),
                        httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
