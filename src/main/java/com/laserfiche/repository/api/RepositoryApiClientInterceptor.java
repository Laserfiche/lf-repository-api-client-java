package com.laserfiche.repository.api;

import kong.unirest.Interceptor;

public interface RepositoryApiClientInterceptor extends AutoCloseable, Interceptor {
    /**
     * Since the underlying resource (the HTTP client) won't throw any exception during its close() invocation.
     * We override the signature of the close() to not include any checked exception.
     */
    @Override
    void close();
}
