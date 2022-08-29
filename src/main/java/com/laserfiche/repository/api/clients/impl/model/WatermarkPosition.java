package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WatermarkPosition {
    
    TOPLEFT("TopLeft"),
    TOPCENTER("TopCenter"),
    TOPRIGHT("TopRight"),
    MIDDLELEFT("MiddleLeft"),
    DEADCENTER("DeadCenter"),
    MIDDLERIGHT("MiddleRight"),
    BOTTOMLEFT("BottomLeft"),
    BOTTOMCENTER("BottomCenter"),
    BOTTOMRIGHT("BottomRight");

    public String value;

    WatermarkPosition(String value) {
        this.value = value;
    }
}
