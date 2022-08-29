package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FuzzyType {
    
    NUMBER_0(0),
    NUMBER_1(1),
    NUMBER_2(2);

    public Integer value;

    FuzzyType(Integer value) {
        this.value = value;
    }
}
