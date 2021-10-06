package com.carshop.admin.dto;

import com.carshop.common.entity.*;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ModelDto {

    private final Long id;
    private final String name;
    private final String generatie;
    private final String category;
    private final String brand;
    private final String brandstof;
    private final String brandstofScore;
    private final int aantalPk;
    private final String aandrijving;
    private final int co2Emissie;
    private final String emissieKlasse;
    private final String code;

    public ModelDto(Model model){
        this.id = model.getId();
        this.name = model.getName();
        this.generatie = model.getGeneratie();
        this.category = model.getCategory().getName();
        this.brand = model.getBrand().getName();
        this.brandstof = model.getBrandstof().getBrandstofType();
        this.brandstofScore = model.getBrandstofScore();
        this.aantalPk = model.getAantalPk();
        this.aandrijving = model.getAandrijving().getLabel();
        this.co2Emissie = model.getCo2Emissie();
        this.emissieKlasse = model.getEmissieKlasse();
        this.code = model.getCode();
    }

    public String getName() {
        return name;
    }

    public String getGeneratie() {
        return generatie;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getBrandstof() {
        return brandstof;
    }

    public String getBrandstofScore() {
        return brandstofScore;
    }

    public int getAantalPk() {
        return aantalPk;
    }

    public String getAandrijving() {
        return aandrijving;
    }

    public int getCo2Emissie() {
        return co2Emissie;
    }

    public String getEmissieKlasse() {
        return emissieKlasse;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
