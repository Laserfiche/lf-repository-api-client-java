package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class EntryFieldValue {

    @JsonProperty("fieldName")
    public String fieldName = null;

    @JsonProperty("values")
    public List<Map<String, Object>> values = null;

    @JsonProperty("fieldType")
    public WFieldType fieldType = null;

    @JsonProperty("fieldId")
    public Integer fieldId = null;

    @JsonProperty("isMultiValue")
    public Boolean isMultiValue = null;

    @JsonProperty("isRequired")
    public Boolean isRequired = null;

    @JsonProperty("hasMoreValues")
    public Boolean hasMoreValues = null;
}
