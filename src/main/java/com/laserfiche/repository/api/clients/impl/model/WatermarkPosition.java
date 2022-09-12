package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WatermarkPosition {

    @JsonProperty("TopLeft")
    TOPLEFT("TopLeft"),

    @JsonProperty("TopCenter")
    TOPCENTER("TopCenter"),

    @JsonProperty("TopRight")
    TOPRIGHT("TopRight"),

    @JsonProperty("MiddleLeft")
    MIDDLELEFT("MiddleLeft"),

    @JsonProperty("DeadCenter")
    DEADCENTER("DeadCenter"),

    @JsonProperty("MiddleRight")
    MIDDLERIGHT("MiddleRight"),

    @JsonProperty("BottomLeft")
    BOTTOMLEFT("BottomLeft"),

    @JsonProperty("BottomCenter")
    BOTTOMCENTER("BottomCenter"),

    @JsonProperty("BottomRight")
    BOTTOMRIGHT("BottomRight");

    public String value;

    WatermarkPosition(String value) {
        this.value = value;
    }
}
