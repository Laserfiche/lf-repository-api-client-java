package com.laserfiche.repository.api.clients;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import kong.unirest.*;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.laserfiche.repository.api.clients.impl.model.*;

public interface RepositoriesClient {

    /**
     * - Returns the repository resource list that current user has access to.
     * - Only available in Laserfiche Cloud.
     *
     * @return CompletableFuture<RepositoryInfo [ ]> The return value
     */
    CompletableFuture<RepositoryInfo[]> getRepositoryList();
}
