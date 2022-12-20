package com.laserfiche.repository.api.clients;

import kong.unirest.Interceptor;

/**
 * An HTTP interceptor interface.
 */
public interface RepositoryApiClientInterceptor extends AutoCloseable, Interceptor {
    /*
     * Since the underlying resource (the HTTP client) won't throw any exception during its close() invocation.
     * We override the signature of the close() to not include any checked exception.
     */

    /**
     * An implementation of {@link AutoCloseable} that does not throw any checked exceptions.
     * {@inheritDoc}
     */
    @Override
    void close();
}
