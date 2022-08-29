package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WFieldFormat {
    
    NONE("None"),
    SHORTDATE("ShortDate"),
    LONGDATE("LongDate"),
    SHORTDATETIME("ShortDateTime"),
    LONGDATETIME("LongDateTime"),
    SHORTTIME("ShortTime"),
    LONGTIME("LongTime"),
    GENERALNUMBER("GeneralNumber"),
    CURRENCY("Currency"),
    PERCENT("Percent"),
    SCIENTIFIC("Scientific"),
    CUSTOM("Custom");

    public String value;

    WFieldFormat(String value) {
        this.value = value;
    }
}
