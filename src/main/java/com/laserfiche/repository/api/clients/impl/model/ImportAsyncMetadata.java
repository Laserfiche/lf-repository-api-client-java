package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.FieldToUpdate;
import com.laserfiche.api.client.model.LinkToUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class ImportAsyncMetadata {

    @JsonProperty("fields")
    public Map<String, FieldToUpdate> fields = null;

    @JsonProperty("tags")
    public List<String> tags = null;

    @JsonProperty("links")
    public List<LinkToUpdate> links = null;
}
