// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

/**
 * The Laserfiche Repository Repositories API client.
 */
public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.
     *
     * @return {@link RepositoryInfo[]} The return value
     */
    RepositoryInfo[] getRepositoryList();
}
