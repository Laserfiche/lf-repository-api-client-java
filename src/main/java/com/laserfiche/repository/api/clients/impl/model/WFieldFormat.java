package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WFieldFormat {

    @JsonProperty("None")
    NONE("None"),

    @JsonProperty("ShortDate")
    SHORTDATE("ShortDate"),

    @JsonProperty("LongDate")
    LONGDATE("LongDate"),

    @JsonProperty("ShortDateTime")
    SHORTDATETIME("ShortDateTime"),

    @JsonProperty("LongDateTime")
    LONGDATETIME("LongDateTime"),

    @JsonProperty("ShortTime")
    SHORTTIME("ShortTime"),

    @JsonProperty("LongTime")
    LONGTIME("LongTime"),

    @JsonProperty("GeneralNumber")
    GENERALNUMBER("GeneralNumber"),

    @JsonProperty("Currency")
    CURRENCY("Currency"),

    @JsonProperty("Percent")
    PERCENT("Percent"),

    @JsonProperty("Scientific")
    SCIENTIFIC("Scientific"),

    @JsonProperty("Custom")
    CUSTOM("Custom");

    public String value;

    WFieldFormat(String value) {
        this.value = value;
    }
}
