package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

/** The Laserfiche Repository AuditReasons API client. */
public interface AuditReasonsClient {

    /**
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons
     * are included.<br>
     * - Only includes audit reasons associated with available API functionalities, like delete
     * entry and export document.<br>
     * - If the authenticated user does not have the appropriate Laserfiche feature right, the audit
     * reasons associated with that feature right will not be included.
     *
     * @param parameters An object of type {@link ParametersForGetAuditReasons} which encapsulates
     *     the parameters of {@link #getAuditReasons getAuditReasons} method.
     * @return {@link AuditReasons} The return value
     */
    AuditReasons getAuditReasons(ParametersForGetAuditReasons parameters);
}
