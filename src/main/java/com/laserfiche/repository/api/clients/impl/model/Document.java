package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document extends Entry {

    @JsonProperty("elecDocumentSize")
    public Long elecDocumentSize = null;

    @JsonProperty("extension")
    public String extension = null;

    @JsonProperty("isElectronicDocument")
    public Boolean isElectronicDocument = null;

    @JsonProperty("isRecord")
    public Boolean isRecord = null;

    @JsonProperty("mimeType")
    public String mimeType = null;

    @JsonProperty("pageCount")
    public Integer pageCount = null;

    @JsonProperty("isCheckedOut")
    public Boolean isCheckedOut = null;

    @JsonProperty("isUnderVersionControl")
    public Boolean isUnderVersionControl = null;

    @JsonProperty("edoc")
    public Edoc edoc = null;
}
