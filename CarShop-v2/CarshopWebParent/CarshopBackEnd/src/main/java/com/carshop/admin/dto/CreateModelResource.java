package com.carshop.admin.dto;

import com.carshop.common.entity.Aandrijving;
import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Brandstof;
import com.carshop.common.entity.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.DataInput;
import java.io.IOException;

public class CreateModelResource {

    @NotNull
    @JsonProperty("name")
    private final String name;
    private final String generatie;
    @NotNull
    private final Category category;
    @NotNull
    private final Brand brand;
    @NotNull
    private final Brandstof brandstof;
    @NotNull
    private final String brandstofScore;
    private final Aandrijving aandrijving;
    private final int aantalPk;
    @NotNull
    private final int co2Emissie;
    @NotNull
    private final String emissieKlasse;

    private ObjectMapper objectMapper;


    public CreateModelResource(@NotNull @JsonProperty("name") String name, String generatie, @NotNull Category category,
                               @NotNull Brand brand, @NotNull Brandstof brandstof,
                               @NotNull String brandstofScore, @NotNull int aantalPk, Aandrijving aandrijving,
                               @NotNull int co2Emissie, @NotNull String emissieKlasse){
        this.name = name;
        this.generatie = generatie;
        this.category = category;
        this.brand = brand;
        this.brandstof = brandstof;
        this.brandstofScore = brandstofScore;
        this.aantalPk = aantalPk;
        this.aandrijving = aandrijving;
        this.co2Emissie = co2Emissie;
        this.emissieKlasse = emissieKlasse;
    }

    public String getName() {
        return name;
    }

    public String getGeneratie() {
        return generatie;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public Brandstof getBrandstof() {
        return brandstof;
    }

    public String getBrandstofScore() {
        return brandstofScore;
    }

    public int getAantalPk() {
        return aantalPk;
    }

    public Aandrijving getAandrijving() {
        return aandrijving;
    }

    public int getCo2Emissie() {
        return co2Emissie;
    }

    public String getEmissieKlasse() {
        return emissieKlasse;
    }
}
