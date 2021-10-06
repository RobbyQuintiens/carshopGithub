package com.carshop.admin.dto;

import com.carshop.common.entity.*;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ProductDto {

    private final Long id;
    private final String conditie;
    private final ModelDto model;
    private final int bouwjaar;
    private final String koetswerkkleur;
    private final int aantalDeuren;
    private final String transmissie;
    private final int aantalKm;
    private final int prijs;
    private final byte[] afbeelding;
    private final ProductStatus productStatus;
    LocalDateTime lockedAt;
    LocalDateTime confirmedAt;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.conditie = product.getConditie().getLabel();
        this.model = new ModelDto(product.getModel());
        this.bouwjaar = product.getBouwjaar();
        this.koetswerkkleur = product.getKoetswerkkleur();
        this.aantalDeuren = product.getAantalDeuren();
        this.transmissie = product.getTransmissie().getLabel();
        this.aantalKm = product.getAantalKm();
        this.prijs = product.getPrijs();
        this.afbeelding = product.getAfbeelding();
        this.productStatus = product.getProductStatus();
        this.lockedAt = product.getLockedAt();
        this.confirmedAt = product.getConfirmedAt();
    }

    public Long getId() {
        return id;
    }

    public String getConditie() {
        return conditie;
    }

    public ModelDto getModel() {
        return model;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }

    public String getKoetswerkkleur() {
        return koetswerkkleur;
    }

    public int getAantalDeuren() {
        return aantalDeuren;
    }

    public String getTransmissie() {
        return transmissie;
    }

    public int getAantalKm() {
        return aantalKm;
    }

    public int getPrijs() {
        return prijs;
    }

    public byte[] getAfbeelding() {
        return afbeelding;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }
}
