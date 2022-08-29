package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    public String value;

    HitType(String value) {
        this.value = value;
    }
}
