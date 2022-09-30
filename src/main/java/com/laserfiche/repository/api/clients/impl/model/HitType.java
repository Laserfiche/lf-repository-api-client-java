package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum HitType {

    PAGECONTENT("PageContent"),
    NOTE("Note"),
    CALLOUT("Callout"),
    TEXTBOX("TextBox"),
    EDOC("Edoc"),
    PROP("Prop"),
    NAME("Name"),
    EXTENSION("Extension"),
    VERSIONGROUPNOTE("VersionGroupNote"),
    VERSIONCOMMENT("VersionComment"),
    FIELD("Field"),
    SIGNATURECOMMENT("SignatureComment"),
    CERTIFICATESUBJECT("CertificateSubject"),
    TAGCOMMENT("TagComment"),
    ANNOTATIONCOMMENT("AnnotationComment"),
    ATTACHMENT("Attachment");

    private String value;

    HitType(String value) {
        this.value = value;
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

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
