package com.laserfiche.repository.api.clients.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.laserfiche.repository.api.clients.impl.deserialization.OffsetDateTimeDeserializer;
import kong.unirest.Header;
import kong.unirest.Headers;
import kong.unirest.UnirestInstance;
import org.threeten.bp.OffsetDateTime;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiClient {

    protected String baseUrl;

    protected UnirestInstance httpClient;

    protected ObjectMapper objectMapper;

    public ApiClient(String baseUrl, UnirestInstance httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
        this.objectMapper = JsonMapper
                .builder()
                .addModule(module)
                .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
    }

    protected String mergeMaxSizeIntoPrefer(Integer maxSize, String prefer) {
        if (maxSize == null)
            return prefer;
        else
            return prefer == null ? String.format("maxpagesize=%d", maxSize) : String.format("%s; maxpagesize=%d",
                    prefer, maxSize);
    }

    protected Map<String, Object> getNonNullParameters(String[] parameterNames, Object[] parameters) {
        if (parameterNames == null || parameters == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (parameterNames.length != parameters.length) {
            throw new IllegalArgumentException("The array for parameter name and value should have the same length.");
        }
        Map<String, Object> paramKeyValuePairs = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] != null) {
                paramKeyValuePairs.put(parameterNames[i],
                        parameters[i] instanceof String ? parameters[i] : String.valueOf(parameters[i]));
            }
        }
        return paramKeyValuePairs;
    }

    protected String toJson(Object object) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return json;
    }

    protected Map<String, String> getHeadersMap(Headers headers) {
        return headers
                .all()
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue));
    }

    protected void saveFile(InputStream inputStream, File outputFile) {
        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            byte[] buffer = new byte[1024];
            while (true) {
                int readCount = inputStream.read(buffer);
                if (readCount <= 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, readCount);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
