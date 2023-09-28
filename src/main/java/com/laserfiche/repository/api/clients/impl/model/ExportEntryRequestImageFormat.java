package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Enumeration of formats when exporting the image part of an entry.
 */
public enum ExportEntryRequestImageFormat {

    MULTI_PAGE_T_I_F_F("MultiPageTIFF"), SINGLE_PAGE_T_I_F_F("SinglePageTIFF"), P_N_G("PNG"), P_D_F("PDF"), J_P_E_G("JPEG");

    private String value;

    ExportEntryRequestImageFormat(String value) {
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
    public static ExportEntryRequestImageFormat fromValue(String input) {
        for (ExportEntryRequestImageFormat b : ExportEntryRequestImageFormat.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
