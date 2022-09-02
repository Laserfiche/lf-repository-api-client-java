package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentEntryIdFileNameBody {

    @JsonProperty("electronicDocument")
    public File electronicDocument = null;

    @JsonProperty("request")
    public PostEntryWithEdocMetadataRequest request = null;
}
