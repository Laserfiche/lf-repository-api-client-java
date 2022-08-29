package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class WFieldInfo {

    @JsonProperty("name")
    public String name = null;

    @JsonProperty("displayName")
    public String displayName = null;

    @JsonProperty("id")
    public Integer id = null;

    @JsonProperty("description")
    public String description = null;

    @JsonProperty("fieldType")
    public WFieldType fieldType = null;

    @JsonProperty("length")
    public Integer length = null;

    @JsonProperty("defaultValue")
    public String defaultValue = null;

    @JsonProperty("isMultiValue")
    public Boolean isMultiValue = null;

    @JsonProperty("isRequired")
    public Boolean isRequired = null;

    @JsonProperty("constraint")
    public String constraint = null;

    @JsonProperty("constraintError")
    public String constraintError = null;

    @JsonProperty("listValues")
    public List<String> listValues = null;

    @JsonProperty("format")
    public WFieldFormat format = null;

    @JsonProperty("currency")
    public String currency = null;

    @JsonProperty("formatPattern")
    public String formatPattern = null;
}
