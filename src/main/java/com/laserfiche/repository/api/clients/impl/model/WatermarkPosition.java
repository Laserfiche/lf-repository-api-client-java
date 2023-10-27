// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;

/**
 * Gets or Sets WatermarkPosition
 */
public enum WatermarkPosition {
    TOP_LEFT("TopLeft"),
    TOP_CENTER("TopCenter"),
    TOP_RIGHT("TopRight"),
    MIDDLE_LEFT("MiddleLeft"),
    DEAD_CENTER("DeadCenter"),
    MIDDLE_RIGHT("MiddleRight"),
    BOTTOM_LEFT("BottomLeft"),
    BOTTOM_CENTER("BottomCenter"),
    BOTTOM_RIGHT("BottomRight");

    private String value;

    WatermarkPosition(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static WatermarkPosition fromValue(String input) {
        for (WatermarkPosition b : WatermarkPosition.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
