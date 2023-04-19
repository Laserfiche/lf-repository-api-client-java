package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import kong.unirest.Headers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetHeaders {
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
        Assertions.assertThrows(
                NullPointerException.class, () -> ApiClientUtils.getHeadersMap(null));
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
}
