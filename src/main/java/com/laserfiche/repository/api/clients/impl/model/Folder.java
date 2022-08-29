package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class Folder extends Entry {

    @JsonProperty("isRecordFolder")
    public Boolean isRecordFolder = null;

    @JsonProperty("isUnderRecordSeries")
    public Boolean isUnderRecordSeries = null;

    @JsonProperty("children")
    public List<Entry> children = null;
}
