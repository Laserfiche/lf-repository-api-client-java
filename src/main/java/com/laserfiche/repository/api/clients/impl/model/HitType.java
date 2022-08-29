package com.laserfiche.repository.api.clients.impl.model;

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
