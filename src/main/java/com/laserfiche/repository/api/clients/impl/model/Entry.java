package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.EntryFieldValue;
import com.laserfiche.api.client.model.EntryType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class Entry {

    @JsonProperty("id")
    public Integer id = null;

    @JsonProperty("name")
    public String name = null;

    @JsonProperty("parentId")
    public Integer parentId = null;

    @JsonProperty("fullPath")
    public String fullPath = null;

    @JsonProperty("folderPath")
    public String folderPath = null;

    @JsonProperty("creator")
    public String creator = null;

    @JsonProperty("creationTime")
    public OffsetDateTime creationTime = null;

    @JsonProperty("lastModifiedTime")
    public OffsetDateTime lastModifiedTime = null;

    @JsonProperty("entryType")
    public EntryType entryType = null;

    @JsonProperty("isContainer")
    public Boolean isContainer = null;

    @JsonProperty("isLeaf")
    public Boolean isLeaf = null;

    @JsonProperty("templateName")
    public String templateName = null;

    @JsonProperty("templateId")
    public Integer templateId = null;

    @JsonProperty("templateFieldNames")
    public List<String> templateFieldNames = null;

    @JsonProperty("volumeName")
    public String volumeName = null;

    @JsonProperty("rowNumber")
    public Integer rowNumber = null;

    @JsonProperty("fields")
    public List<EntryFieldValue> fields = null;
}
