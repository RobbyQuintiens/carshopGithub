package com.carshop.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brandstof_id")
    private Brandstof brandstof;

    @Column(name = "brandstof_score", length = 1, nullable = false)
    private String brandstofScore;

    @Column(name = "aantal_pk", length = 3, nullable = false)
    private int aantalPk;

    @Enumerated
    private Aandrijving aandrijving;

    @Column(name = "co2_emissie", length = 3, nullable = false)
    private int co2Emissie;

    @Column(name = "emissie_klasse", nullable = false)
    private String emissieKlasse;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "generatie")
    private String generatie;

    public Model(String name, String generatie, Category category,
                 Brand brand, Brandstof brandstof,
                 String brandstofScore, int aantalPk,
                 Aandrijving aandrijving, int co2Emissie, String emissieKlasse) {
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
        this.code = name + " " + generatie;
    }

    public Model(Long id) {
        this.id = id;
    }

    public Model() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneratie() {
        return generatie;
    }

    public void setGeneratie(String generatie) {
        this.generatie = generatie;
    }

    public Brandstof getBrandstof() {
        return brandstof;
    }

    public void setBrandstof(Brandstof brandstof) {
        this.brandstof = brandstof;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getBrandstofScore() {
        return brandstofScore;
    }

    public void setBrandstofScore(String brandstof_score) {
        this.brandstofScore = brandstof_score;
    }

    public int getAantalPk() {
        return aantalPk;
    }

    public void setAantalPk(int aantalPk) {
        this.aantalPk = aantalPk;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Aandrijving getAandrijving() {
        return aandrijving;
    }

    public void setAandrijving(Aandrijving aandrijving) {
        this.aandrijving = aandrijving;
    }

    public int getCo2Emissie() {
        return co2Emissie;
    }

    public void setCo2Emissie(int co2Emissie) {
        this.co2Emissie = co2Emissie;
    }

    public String getEmissieKlasse() {
        return emissieKlasse;
    }

    public void setEmissieKlasse(String emissieKlasse) {
        this.emissieKlasse = emissieKlasse;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
