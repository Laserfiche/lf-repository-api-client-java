// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.unit.ApiClientUtils;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MergeMaxSizeIntoPreferTest {
    private final static int maxSize = 1;
    private final static String prefer = "maxpagesize";
    private final static int negativeMaxSize = -1;

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
        String result = ApiClientUtils.mergeMaxSizeIntoPrefer(negativeMaxSize, prefer);
        assertEquals(String.format("%s; maxpagesize=%d", prefer, negativeMaxSize), result);
    }
}