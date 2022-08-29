package com.laserfiche.repository.api.clients.impl.model;

public enum FuzzyType {
    
    NUMBER_0(0),
    NUMBER_1(1),
    NUMBER_2(2);

    public Integer value;

    FuzzyType(Integer value) {
        this.value = value;
    }
}
