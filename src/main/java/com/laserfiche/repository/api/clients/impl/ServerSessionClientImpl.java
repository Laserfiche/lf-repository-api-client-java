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
import com.laserfiche.repository.api.clients.ServerSessionClient;

public class ServerSessionClientImpl extends ApiClient implements ServerSessionClient {

    public ServerSessionClientImpl(String baseUrl, UnirestInstance httpClient) {
        super(baseUrl, httpClient);
    }

    /**
     *  - Deprecated.
     * - Invalidates the server session.
     * - Acts as a &quot;logout&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForInvalidateServerSession which encapsulates the parameters of invalidateServerSession method.
     *  @return ODataValueOfBoolean The return value
     */
    @Override
    public ODataValueOfBoolean invalidateServerSession(ParametersForInvalidateServerSession parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        HttpResponse<Object> httpResponse = httpClient.post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Invalidate").routeParam(pathParameters).asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfBoolean.class);
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
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     *  - Deprecated. This function is a no-op, always returns 200.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForCreateServerSession which encapsulates the parameters of createServerSession method.
     *  @return ODataValueOfBoolean The return value
     */
    @Override
    public ODataValueOfBoolean createServerSession(ParametersForCreateServerSession parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        HttpResponse<Object> httpResponse = httpClient.post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Create").routeParam(pathParameters).asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfBoolean.class);
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
            if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    /**
     *  - Deprecated.
     * - Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires.
     * - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForRefreshServerSession which encapsulates the parameters of refreshServerSession method.
     *  @return ODataValueOfDateTime The return value
     */
    @Override
    public ODataValueOfDateTime refreshServerSession(ParametersForRefreshServerSession parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[] { "String" }, new String[] { "repoId" }, new Object[] { parameters.getRepoId() });
        HttpResponse<Object> httpResponse = httpClient.post(baseUrl + "/v1/Repositories/{repoId}/ServerSession/Refresh").routeParam(pathParameters).asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, ODataValueOfDateTime.class);
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
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."), httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }
}
