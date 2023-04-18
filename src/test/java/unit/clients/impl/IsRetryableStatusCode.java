package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import kong.unirest.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsRetryableStatusCode {
    private HttpMethod requestMethod;
    @Test
    void isRetryableStatusCode_NullRequestMethod_InvalidStatusCode(){
        boolean result = ApiClientUtils.isRetryableStatusCode(0,null);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_NullRequestMethod_NotFoundStatusCode(){
        boolean result = ApiClientUtils.isRetryableStatusCode(404,null);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_NullRequestMethod_TimeoutStatusCode(){
        boolean result = ApiClientUtils.isRetryableStatusCode(408,null);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_NullRequestMethod_ServerErrorCode() {
        boolean result = ApiClientUtils.isRetryableStatusCode(500, null);
        assertTrue(result);
    }

    @Test
    void isRetryableStatusCode_PostRequestMethod_InvalidStatusCode(){
        requestMethod = HttpMethod.POST;
        boolean result = ApiClientUtils.isRetryableStatusCode(0,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_PostRequestMethod_NotFoundStatusCode(){
        requestMethod = HttpMethod.POST;
        boolean result = ApiClientUtils.isRetryableStatusCode(404,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_PostRequestMethod_TimeoutStatusCode(){
        requestMethod = HttpMethod.POST;
        boolean result = ApiClientUtils.isRetryableStatusCode(408,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_PostRequestMethod_ServerErrorCode() {
        requestMethod = HttpMethod.POST;
        boolean result = ApiClientUtils.isRetryableStatusCode(500, requestMethod);
        assertTrue(result);
    }

    @Test
    void isRetryableStatusCode_GetRequestMethod_InvalidStatusCode(){
        requestMethod = HttpMethod.GET;
        boolean result = ApiClientUtils.isRetryableStatusCode(0,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_GetRequestMethod_NotFoundStatusCode(){
        requestMethod = HttpMethod.GET;
        boolean result = ApiClientUtils.isRetryableStatusCode(404,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_GetRequestMethod_TimeoutStatusCode(){
        requestMethod = HttpMethod.GET;
        boolean result = ApiClientUtils.isRetryableStatusCode(408,requestMethod);
        assertTrue(result);
    }
    @Test
    void isRetryableStatusCode_GetRequestMethod_ServerErrorCode() {
        requestMethod = HttpMethod.GET;
        boolean result = ApiClientUtils.isRetryableStatusCode(500, requestMethod);
        assertTrue(result);
    }
}
