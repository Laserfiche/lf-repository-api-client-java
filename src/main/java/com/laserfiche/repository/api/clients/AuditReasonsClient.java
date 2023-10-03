package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

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
    void listAuditReasonsForEach(
            Function<AuditReasonCollectionResponse, Boolean> callback,
            Integer maxPageSize,
            ParametersForListAuditReasons parameters);
}
