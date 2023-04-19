package unit.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import kong.unirest.Headers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GetHeaders {
    private Map<String, String> headerParametersWithStringTypeValue = new HashMap<String, String>();
    @Test
    void beforeSend_AllNullParameters(){
        Assertions.assertThrows(
                NullPointerException.class, () -> ApiClientUtils.getHeadersMap(null));
    }

    @Test
    void beforeSend_ValidMap(){
        headerParametersWithStringTypeValue.put("test", "test");
        Headers header = new Headers();
        header.add(headerParametersWithStringTypeValue);
        Map<String, String> result = ApiClientUtils.getHeadersMap(header);
        assertNotNull(headerParametersWithStringTypeValue);
        assertEquals(result, headerParametersWithStringTypeValue);
        headerParametersWithStringTypeValue.clear();
        header.clear();
    }
}
