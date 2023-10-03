package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Enumeration of formats when exporting the image part of an entry.
 */
public enum ExportEntryRequestImageFormat {

    MULTI_PAGE_TIFF("MultiPageTIFF"), SINGLE_PAGE_TIFF("SinglePageTIFF"), PNG("PNG"), PDF("PDF"), JPEG("JPEG");

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
