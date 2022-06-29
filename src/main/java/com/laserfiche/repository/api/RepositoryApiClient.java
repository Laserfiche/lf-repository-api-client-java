package com.laserfiche.repository.api;

import com.laserfiche.repository.api.client.*;

/**
 * The Laserfiche Repository API client.
 */
public interface RepositoryApiClient {
    /**
     * @return The Laserfiche Repository Attributes API client.
     */
    AttributesApi getAttributesClient();

    /**
     * @return The Laserfiche Repository Audit Reasons API client.
     */
    AuditReasonsApi getAuditReasonsClient();

    /**
     * @return The Laserfiche Repository Entries API client.
     */
    EntriesApi getEntriesClient();

    /**
     * @return The Laserfiche Repository Field Definitions API client.
     */
    FieldDefinitionsApi getFieldDefinitionsClient();

    /**
     * @return The Laserfiche Repository Repositories API client.
     */
    RepositoriesApi getRepositoryClient();

    /**
     * @return The Laserfiche Repository Searches API client.
     */
    SearchesApi getSearchesClient();

    /**
     * @return The Laserfiche Repository Simple Searches API client.
     */
    SimpleSearchesApi getSimpleSearchesClient();

    /**
     * @return The Laserfiche Repository Tag Definitions API client.
     */
    TagDefinitionsApi getTagDefinitionsClient();

    /**
     * @return The Laserfiche Repository Tasks API client.
     */
    TasksApi getTasksClient();

    /**
     * @return The Laserfiche Repository Template Definitions API client.
     */
    TemplateDefinitionsApi getTemplateDefinitionClient();
}
