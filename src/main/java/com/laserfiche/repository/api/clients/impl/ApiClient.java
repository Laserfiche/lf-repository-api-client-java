package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.oauth.TokenClientObjectMapper;
import com.laserfiche.repository.api.OAuthInterceptor;
import kong.unirest.Interceptor;
import kong.unirest.Unirest;

public class ApiClient {
    public String baseUrl;

    public ApiClient(String baseUrl, Interceptor interceptor) {
        this.baseUrl = baseUrl;
        Unirest.config()
               .setObjectMapper(new TokenClientObjectMapper())
               .interceptor(interceptor);
    }
}
