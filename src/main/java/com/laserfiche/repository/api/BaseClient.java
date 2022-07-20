package com.laserfiche.repository.api;

public class BaseClient<T, S> {
    protected T generatedClient;
    protected S extensionClient;

    protected void setGeneratedClient(T generatedClient) {
        this.generatedClient = generatedClient;
    }

    protected void setExtensionClient(S extensionClient) {
        this.extensionClient = extensionClient;
    }

    protected void setClients(T generatedClient, S extensionClient) {
        this.generatedClient = generatedClient;
        this.extensionClient = extensionClient;
    }

    protected String mergeMaxPageSizeIntoPrefer(Integer maxPageSize, String prefer)
    {
        if (maxPageSize == null) {
            return prefer;
        } else if (prefer == null) {
            return String.format("maxpagesize=%d", maxPageSize);
        } else {
            // Prefer's format: https://tools.ietf.org/id/draft-snell-http-prefer-16.html#prefer
            // Based on prefer's format, we can just append maxpagesize
            return prefer + String.format("; maxpagesize=%d", maxPageSize);
        }
    }
}
