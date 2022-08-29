package com.laserfiche.repository.api.clients.impl.model;

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
