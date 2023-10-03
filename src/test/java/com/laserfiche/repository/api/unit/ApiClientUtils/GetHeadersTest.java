package com.laserfiche.repository.api.unit.ApiClientUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import java.util.HashMap;
import java.util.Map;
import kong.unirest.Headers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetHeadersTest {
    private final Map<String, String> headerParametersWithStringTypeValue = new HashMap<String, String>();
    private Headers header;

    @BeforeEach
    void headerSetup() {
        header = new Headers();
    }

    @AfterEach
    void clearHeaders() {
        headerParametersWithStringTypeValue.clear();
        header.clear();
    }

    @Test
    void getHeadersMap_AllNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> ApiClientUtils.getHeadersMap(null));
    }

    @Test
    void getHeadersMap_SingleKeyValueHeaderMap() {
        headerParametersWithStringTypeValue.put("test", "test");
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
    }

    @Test
    void getHeadersMap_MultipleKeyValueHeaderMap() {
        for (int i = 0; i < 5; i++) {
            headerParametersWithStringTypeValue.put(String.format("test%s", i), "test");
        }
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
    }

    @Test
    void getHeadersMap_DuplicateKeys() {
        String headerKey = "test";
        String expectedValue = "0, 1, 2, 3, 4";
        for (int i = 0; i < 5; i++) {
            header.add(headerKey, Integer.toString(i));
        }
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(expectedValue, result.get(headerKey));
    }

    @Test
    void getHeadersMap_NullHeaderMap() {
        headerParametersWithStringTypeValue.put(null, null);
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, new HashMap<String, String>());
    }

    @Test
    void getHeadersMap_EmptyStringHeaderMap() {
        headerParametersWithStringTypeValue.put("", "");
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
    }

    @Test
    void getHeaderMap_AddKeyToHeaderMap() {
        for (int i = 0; i < 5; i++) {
            headerParametersWithStringTypeValue.put(String.format("test%s", i), "test");
        }
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
        header.clear();
        headerParametersWithStringTypeValue.put("test6", "test");
        header.add(headerParametersWithStringTypeValue);
        result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
    }

    @Test
    void getHeaderMap_DeleteKeyToHeaderMap() {
        for (int i = 0; i < 5; i++) {
            headerParametersWithStringTypeValue.put(String.format("test%s", i), "test");
        }
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
        header.clear();
        headerParametersWithStringTypeValue.remove(Integer.toString(1));
        header.add(headerParametersWithStringTypeValue);
        result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
    }
}
