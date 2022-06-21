package com.laserfiche.repository.api;

import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class OAuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
