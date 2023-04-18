package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MergeMaxSizeIntoPrefer {
    private int maxSize = 1;
    private String prefer = "maxpagesize";
    @Test
    void mergeMaxSizeIntoPrefer_ZeroMaxSize_NullPrefer(){
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(0, null);
        assertNull(result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_MaxSizeGreaterThanZero_NullPrefer(){
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(maxSize, null);
        assertEquals(String.format("maxpagesize=%d", maxSize),result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_MaxSizeGreaterThanZero_StringPrefer(){
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(maxSize, prefer);
        assertEquals(String.format("maxpagesize=%d", maxSize),result);
    }

    @Test
    void mergeMaxSizeIntoPrefer_ZeroMaxSize_StringPrefer(){
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(0, prefer);
        assertEquals(prefer,result);
    }
}
