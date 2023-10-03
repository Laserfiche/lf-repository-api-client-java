package com.laserfiche.repository.api;

import com.laserfiche.repository.api.clients.*;

import java.util.Map;

/**
 * The Laserfiche Repository API client.
 */
public interface RepositoryApiClient extends AutoCloseable {
    /**
     * Returns the Laserfiche Repository Attributes API client.
     *
     * @return The Laserfiche Repository Attributes API client.
     */
    AttributesClient getAttributesClient();

    /**
     * Returns the Laserfiche Repository Audit Reasons API client.
     *
     * @return The Laserfiche Repository Audit Reasons API client.
     */
    AuditReasonsClient getAuditReasonsClient();

    /**
     * Returns the Laserfiche Repository Entries API client.
     *
     * @return The Laserfiche Repository Entries API client.
     */
    EntriesClient getEntriesClient();

    /**
     * Returns the Laserfiche Repository Field Definitions API client.
     *
     * @return The Laserfiche Repository Field Definitions API client.
     */
    FieldDefinitionsClient getFieldDefinitionsClient();

    /**
     * Returns the Laserfiche Repository Repositories API client.
     *
     * @return The Laserfiche Repository Repositories API client.
     */
    RepositoriesClient getRepositoryClient();

    /**
     * Returns the Laserfiche Repository Link Definitions API client.
     *
     * @return The Laserfiche Repository Link Definitions API client.
     */
    LinkDefinitionsClient getLinkDefinitionsClient();

    /**
     * Returns the Laserfiche Repository Searches API client.
     *
     * @return The Laserfiche Repository Searches API client.
     */
    SearchesClient getSearchesClient();

    /**
     * Returns the Laserfiche Repository Simple Searches API client.
     *
     * @return The Laserfiche Repository Simple Searches API client.
     */
    SimpleSearchesClient getSimpleSearchesClient();

    /**
     * Returns the Laserfiche Repository Tag Definitions API client.
     *
     * @return The Laserfiche Repository Tag Definitions API client.
     */
    TagDefinitionsClient getTagDefinitionsClient();

    /**
     * Returns the Laserfiche Repository Tasks API client.
     *
     * @return The Laserfiche Repository Tasks API client.
     */
    TasksClient getTasksClient();

    /**
     * Returns the Laserfiche Repository Template Definitions API client.
     *
     * @return The Laserfiche Repository Template Definitions API client.
     */
    TemplateDefinitionsClient getTemplateDefinitionClient();

    /**
     * Set default headers that will be used for all requests.
     *
     * @param defaultHeaders A key value pair of header name and value.
     */
    void setDefaultRequestHeaders(Map<String, String> defaultHeaders);

    /**
     * Returns default request headers that will be used for all requests.
     *
     * @return Default request headers that will be used for all requests.
     */
    Map<String, String> getDefaultRequestHeaders();

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
