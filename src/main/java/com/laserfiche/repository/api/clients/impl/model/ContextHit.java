package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class ContextHit {

    @JsonProperty("hitType")
    public HitType hitType = null;

    @JsonProperty("isAnnotationHit")
    public Boolean isAnnotationHit = null;

    @JsonProperty("annotationId")
    public Integer annotationId = null;

    @JsonProperty("pageNumber")
    public Integer pageNumber = null;

    @JsonProperty("pageOffset")
    public Integer pageOffset = null;

    @JsonProperty("context")
    public String context = null;

    @JsonProperty("highlight1Offset")
    public Integer highlight1Offset = null;

    @JsonProperty("highlight1Length")
    public Integer highlight1Length = null;

    @JsonProperty("highlight2Offset")
    public Integer highlight2Offset = null;

    @JsonProperty("highlight2Length")
    public Integer highlight2Length = null;

    @JsonProperty("hitWidth")
    public Integer hitWidth = null;

    @JsonProperty("edocHitCount")
    public Integer edocHitCount = null;

    @JsonProperty("fieldHitCount")
    public Integer fieldHitCount = null;

    @JsonProperty("fieldName")
    public String fieldName = null;

    @JsonProperty("hitNumber")
    public Integer hitNumber = null;
}
