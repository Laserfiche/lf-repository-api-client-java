package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.Rule;
import com.laserfiche.api.client.model.WFieldFormat;
import com.laserfiche.api.client.model.WFieldInfo;
import com.laserfiche.api.client.model.WFieldType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class TemplateFieldInfo extends WFieldInfo {

    @JsonProperty("rule")
    public Rule rule = null;

    @JsonProperty("groupId")
    public Integer groupId = null;

    @JsonProperty("groupName")
    public String groupName = null;
}
