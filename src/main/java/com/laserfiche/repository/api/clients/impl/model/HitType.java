package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type of context hit.
 */
public enum HitType {

    PAGE_CONTENT("PageContent"),
    NOTE("Note"),
    CALLOUT("Callout"),
    TEXT_BOX("TextBox"),
    EDOC("Edoc"),
    PROP("Prop"),
    NAME("Name"),
    EXTENSION("Extension"),
    VERSION_GROUP_NOTE("VersionGroupNote"),
    VERSION_COMMENT("VersionComment"),
    FIELD("Field"),
    SIGNATURE_COMMENT("SignatureComment"),
    CERTIFICATE_SUBJECT("CertificateSubject"),
    TAG_COMMENT("TagComment"),
    ANNOTATION_COMMENT("AnnotationComment"),
    ATTACHMENT("Attachment");

    private final String value;

    HitType(String value) {
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
    public static HitType fromValue(String input) {
        for (HitType b : HitType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
