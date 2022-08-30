package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.oauth.TokenClientObjectMapper;
import kong.unirest.Unirest;

public class ApiClient {
    public String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        Unirest.config().setObjectMapper(new TokenClientObjectMapper());
    }
}
