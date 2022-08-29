package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.Edoc;
import com.laserfiche.api.client.model.Entry;
import com.laserfiche.api.client.model.EntryFieldValue;
import com.laserfiche.api.client.model.EntryType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
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
