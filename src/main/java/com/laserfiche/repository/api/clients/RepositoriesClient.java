package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;

public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.
     *
     * @return RepositoryInfo[] The return value
     */
    RepositoryInfo[] getRepositoryList();
}
