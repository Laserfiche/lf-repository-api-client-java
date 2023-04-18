package unit.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GetHeaders {
    private Map<String, String> headerParametersWithStringTypeValue = new HashMap<String, String>();
    @Test
    void beforeSend_AllNullParameters(){
        headerParametersWithStringTypeValue = ApiClientUtils.getHeadersMap(null);
        assertNull(headerParametersWithStringTypeValue);
    }
}
