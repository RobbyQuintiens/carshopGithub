package com.carshop.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Conditie {

    NIEUW("Nieuw"), OCCASIE("Occasie");

    private String label;

    private Conditie(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
