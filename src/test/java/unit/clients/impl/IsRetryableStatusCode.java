package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import kong.unirest.HttpMethod;
import kong.unirest.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class IsRetryableStatusCode {
    @ParameterizedTest
    @MethodSource("statusCodes")
    void isRetryableStatusCode_NullRequestMethod(int statusCode) {
        Assertions.assertThrows(
                NullPointerException.class, () -> ApiClientUtils.isRetryableStatusCode(statusCode, null));
    }

    @ParameterizedTest
    @MethodSource("statusCodes")
    void isRetryableStatusCode_PostRequestMethod(int statusCode) {
        boolean result = ApiClientUtils.isRetryableStatusCode(statusCode, HttpMethod.POST);
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("statusCodes")
    void isRetryableStatusCode_GetRequestMethod(int statusCode) {
        boolean result = ApiClientUtils.isRetryableStatusCode(statusCode, HttpMethod.GET);
        if (statusCode == 408 || statusCode == 500) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    private static Stream<Arguments> statusCodes() {
        return Stream.of(arguments(HttpStatus.FORBIDDEN),
                arguments(HttpStatus.REQUEST_TIMEOUT),
                arguments(HttpStatus.NOT_FOUND),
                arguments(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
