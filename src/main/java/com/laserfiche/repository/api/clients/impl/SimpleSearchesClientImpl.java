package com.laserfiche.repository.api.clients.impl;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;
import com.laserfiche.repository.api.clients.SimpleSearchesClient;

public class SimpleSearchesClientImpl extends ApiClient implements SimpleSearchesClient {

    public SimpleSearchesClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    /**
     * - Runs a &quot;simple&quot; search operation on the repository.
     * - Returns a truncated search result listing.
     * - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     *  @param parameters An object of type ParametersForCreateSimpleSearchOperation which encapsulates the parameters of createSimpleSearchOperation method.
     *  @return ODataValueContextOfIListOfEntry The return value
     */
    @Override
    public ODataValueContextOfIListOfEntry createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[] { "String[]", "boolean", "String", "String", "String", "boolean" }, new String[] { "fields", "formatFields", "culture", "$select", "$orderby", "$count" }, new Object[] { parameters.getFields(), parameters.isFormatFields(), parameters.getCulture(), parameters.getSelect(), parameters.getOrderby(), parameters.isCount() });
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        HttpResponse<Object> httpResponse = httpClient.post(baseUrl + "/v1/Repositories/{repoId}/SimpleSearches").queryString("fields", (queryParameters.get("fields") != null) ? (queryParameters.get("fields") instanceof String ? Arrays.asList(queryParameters.remove("fields")) : (List) queryParameters.remove("fields")) : new ArrayList()).queryString(queryParameters).routeParam(pathParameters).contentType("application/json").body(parameters.getRequestBody()).asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200 || httpResponse.getStatus() == 204 || httpResponse.getStatus() == 206) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueContextOfIListOfEntry.class);
            } catch (JsonProcessingException | IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (JsonProcessingException | IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(), (parsingException.isPresent() ? parsingException.get().getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Operation limit or request limit reached."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
