package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

/**
 * The Laserfiche Repository Repositories API client.
 */
public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.<br>- Required OAuth scope: repository.Read
     *
     * @return {@link RepositoryCollectionResponse} The return value
     */
    RepositoryCollectionResponse listRepositories();
}
