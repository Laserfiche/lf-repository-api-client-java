package com.laserfiche.repository.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class BaseClient<T, S> {
    protected T generatedClient;
    protected S extensionClient;
    protected Retrofit.Builder clientBuilder;
    protected OkHttpClient.Builder okBuilder;

    public BaseClient(Retrofit.Builder clientBuilder, OkHttpClient.Builder okBuilder) {
        this.clientBuilder = clientBuilder;
        this.okBuilder = okBuilder;
    }

    protected void setupClients(Class<T> generatedClientClass, Class<S> extensionClientClass) {
        this.generatedClient = createClient(generatedClientClass);
        // Not all API paths have extension client (meaning, they don't have custom methods such as nextLink or forEach)
        if (extensionClientClass != null) {
            this.extensionClient = createClient(extensionClientClass);
        }
    }

    private <C> C createClient(Class<C> clientInterface) {
        return clientBuilder.client(okBuilder.build())
                .build()
                .create(clientInterface);
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
