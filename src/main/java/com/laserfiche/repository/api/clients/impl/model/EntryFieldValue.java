package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.WFieldType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
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
