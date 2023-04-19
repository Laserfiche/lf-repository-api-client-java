package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MergeMaxSizeIntoPrefer {
    private int maxSize = 1;
    private String prefer = "maxpagesize";

    @Test
    void mergeMaxSizeIntoPrefer_ZeroMaxSize_NullPrefer() {
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(0, null);
        assertNull(result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_MaxSizeGreaterThanZero_NullPrefer() {
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(maxSize, null);
        assertEquals(String.format("maxpagesize=%d", maxSize), result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_MaxSizeGreaterThanZero_StringPrefer() {
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(maxSize, prefer);
        assertEquals(String.format("%s; maxpagesize=%d", prefer, maxSize), result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_ZeroMaxSize_StringPrefer() {
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(0, prefer);
        assertEquals(prefer, result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_NegativeMaxSize_StringPrefer() {
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(-1, prefer);
        assertEquals(String.format("%s; maxpagesize=%d", prefer, -1), result);
    }
}
