package com.carshop.common.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Conditie conditie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "bouwjaar", length = 4, nullable = false)
    private int bouwjaar;

    @Column(name = "koetswerkkleur", nullable = false)
    private String koetswerkkleur;

    @Column(name = "aantal_deuren", length = 1, nullable = false)
    private int aantalDeuren;

    @Column(name = "transmissie", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Transmissie transmissie;

    @Column(name = "aantal_km", nullable = false)
    private int aantalKm;

    @Column(name = "prijs", nullable = false)
    private int prijs;

    @Lob
    @Column(name = "afbeelding")
    private byte[] afbeelding;

    private ProductStatus productStatus;

    LocalDateTime lockedAt;
    LocalDateTime confirmedAt;

    public Product(){

    }

    public Product(Conditie conditie, Model model,
                   int bouwjaar, String koetswerkkleur, int aantalDeuren,
                   Transmissie transmissie, int aantalKm, int prijs, byte[] afbeelding) {
        this.conditie = conditie;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.koetswerkkleur = koetswerkkleur;
        this.aantalDeuren = aantalDeuren;
        this.transmissie = transmissie;
        this.aantalKm = aantalKm;
        this.prijs = prijs;
        this.afbeelding = afbeelding;
        this.productStatus = ProductStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conditie getConditie() {
        return conditie;
    }

    public void setConditie(Conditie conditie) {
        this.conditie = conditie;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public String getKoetswerkkleur() {
        return koetswerkkleur;
    }

    public void setKoetswerkkleur(String koetswerkkleur) {
        this.koetswerkkleur = koetswerkkleur;
    }

    public int getAantalDeuren() {
        return aantalDeuren;
    }

    public void setAantalDeuren(int aantalDeuren) {
        this.aantalDeuren = aantalDeuren;
    }

    public Transmissie getTransmissie() {
        return transmissie;
    }

    public void setTransmissie(Transmissie transmissie) {
        this.transmissie = transmissie;
    }

    public int getAantalKm() {
        return aantalKm;
    }

    public void setAantalKm(int aantalKm) {
        this.aantalKm = aantalKm;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public byte[] getAfbeelding() {
        return afbeelding;
    }

    public void setAfbeelding(byte[] afbeelding) {
        this.afbeelding = afbeelding;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }
}
