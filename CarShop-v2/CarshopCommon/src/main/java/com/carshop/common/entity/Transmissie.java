package com.carshop.common.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Transmissie {
    MANUEEL("Manueel"), AUTOMATISCH("Automatisch");

    private String label;

    Transmissie(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
