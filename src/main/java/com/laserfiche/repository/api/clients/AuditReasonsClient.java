package com.laserfiche.repository.api.clients;

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
import kong.unirest.HttpMethod;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import kong.unirest.ContentType;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.ResponseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;

/**
 * The Laserfiche Repository AuditReasons API client.
 */
public interface AuditReasonsClient {

    /**
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included.<br>- Only includes audit reasons associated with available API functionalities, like delete entry and export document.<br>- If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.<br>- Required OAuth scope: repository.Read
     *
     * @param parameters An object of type {@link ParametersForListAuditReasons} which encapsulates the parameters of {@link #listAuditReasons listAuditReasons} method.
     * @return {@link AuditReasonCollectionResponse} The return value
     */
    AuditReasonCollectionResponse listAuditReasons(ParametersForListAuditReasons parameters);

    /**
     * Returns the next subset of the requested collection, using a nextlink url.
     *
     * @param nextLink A url that allows retrieving the next subset of the requested collection.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @return {@link AuditReasonCollectionResponse} The return value
     */
    AuditReasonCollectionResponse listAuditReasonsNextLink(String nextLink, int maxPageSize);

    /**
     * Provides the functionality to iteratively (i.e. through paging) call {@link #listAuditReasons listAuditReasons}, and apply a function on the response of each iteration.
     *
     * @param callback A delegate that will be called each time new data is retrieved. Returns false to stop receiving more data; returns true to be called again if there's more data.
     * @param maxPageSize Optionally specify the maximum number of items to retrieve.
     * @param parameters An object of type {@link ParametersForListAuditReasons} which encapsulates the parameters of {@link #listAuditReasons listAuditReasons} method.
     */
    void listAuditReasonsForEach(Function<AuditReasonCollectionResponse, Boolean> callback, Integer maxPageSize, ParametersForListAuditReasons parameters);
}
