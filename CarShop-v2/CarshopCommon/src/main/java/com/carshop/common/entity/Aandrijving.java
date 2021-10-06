package com.carshop.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Aandrijving {
    VOORWIEL("Voorwiel"), ACHTERWIEL("Achterwiel"), VIERWIEL("Vierwiel");

    private String label;

    private Aandrijving(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
