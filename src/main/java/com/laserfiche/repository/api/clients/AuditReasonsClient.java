package com.laserfiche.repository.api.clients;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.*;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;

public interface AuditReasonsClient {

    /**
     *  - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included.
     * - Only includes audit reasons associated with available API functionalities, like delete entry and export document.
     * - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     *
     *  @param repoId The requested repository ID.
     *  @return CompletableFuture<AuditReasons> The return value
     */
    CompletableFuture<AuditReasons> getAuditReasons(String repoId);
}
