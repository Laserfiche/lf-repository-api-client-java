package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.ODataValueOfIListOfWEntryLinkInfo;
import com.laserfiche.api.client.model.WEntryLinkInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class ODataValueContextOfIListOfWEntryLinkInfo extends ODataValueOfIListOfWEntryLinkInfo {

    @JsonProperty("@odata.nextLink")
    public String _atOdataNextLink = null;

    @JsonProperty("@odata.count")
    public Integer _atOdataCount = null;
}
