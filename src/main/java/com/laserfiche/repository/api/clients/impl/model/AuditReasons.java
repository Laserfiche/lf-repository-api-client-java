package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditReasons {

    @JsonProperty("deleteEntry")
    public List<WAuditReason> deleteEntry = null;

    @JsonProperty("exportDocument")
    public List<WAuditReason> exportDocument = null;
}
