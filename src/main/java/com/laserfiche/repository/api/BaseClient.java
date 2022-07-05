package com.laserfiche.repository.api;

public class BaseClient<T> {
    protected T client;

    protected void setClient(T client) {
        this.client = client;
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
