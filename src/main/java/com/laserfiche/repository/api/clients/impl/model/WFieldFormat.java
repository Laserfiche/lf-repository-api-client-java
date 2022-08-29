package com.laserfiche.repository.api.clients.impl.model;

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
