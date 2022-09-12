package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HitType {

    @JsonProperty("PageContent")
    PAGECONTENT("PageContent"),
    @JsonProperty("Note")
    NOTE("Note"),
    @JsonProperty("Callout")
    CALLOUT("Callout"),
    @JsonProperty("TextBox")
    TEXTBOX("TextBox"),
    @JsonProperty("Edoc")
    EDOC("Edoc"),
    @JsonProperty("Prop")
    PROP("Prop"),
    @JsonProperty("Name")
    NAME("Name"),
    @JsonProperty("Extension")
    EXTENSION("Extension"),
    @JsonProperty("VersionGroupNote")
    VERSIONGROUPNOTE("VersionGroupNote"),
    @JsonProperty("VersionComment")
    VERSIONCOMMENT("VersionComment"),
    @JsonProperty("Field")
    FIELD("Field"),
    @JsonProperty("SignatureComment")
    SIGNATURECOMMENT("SignatureComment"),
    @JsonProperty("CertificateSubject")
    CERTIFICATESUBJECT("CertificateSubject"),
    @JsonProperty("TagComment")
    TAGCOMMENT("TagComment"),
    @JsonProperty("AnnotationComment")
    ANNOTATIONCOMMENT("AnnotationComment"),
    @JsonProperty("Attachment")
    ATTACHMENT("Attachment");

    public String value;

    HitType(String value) {
        this.value = value;
    }
}
