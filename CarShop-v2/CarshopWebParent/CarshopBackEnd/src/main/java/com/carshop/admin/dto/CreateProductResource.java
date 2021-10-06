package com.carshop.admin.dto;

import com.carshop.common.entity.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateProductResource {

    @NotNull
    private final Conditie conditie;
    @NotNull
    private final Model model;
    @NotNull
    private final int bouwjaar;
    @NotNull
    private final String koetswerkkleur;
    @NotNull
    private final int aantalDeuren;
    @NotNull
    private final Transmissie transmissie;
    @NotNull
    private int aantalKm;
    @NotNull
    private final int prijs;
    private final byte[] afbeelding;
    private ProductStatus productStatus;
    LocalDateTime lockedAt;
    LocalDateTime confirmedAt;


    public CreateProductResource(@NotNull Conditie conditie,
                                 @NotNull Model model, @NotNull int bouwjaar,
                                 @NotNull String koetswerkkleur, @NotNull int aantalDeuren,
                                 @NotNull Transmissie transmissie, @NotNull int aantalKm, @NotNull int prijs, byte[] afbeelding) {
        this.conditie = conditie;
        this.model = model;
        this.bouwjaar = bouwjaar;
        this.koetswerkkleur = koetswerkkleur;
        this.aantalDeuren = aantalDeuren;
        this.transmissie = transmissie;
        this.aantalKm = aantalKm;
        this.prijs = prijs;
        this.afbeelding = afbeelding;
    }

    public Conditie getConditie() {
        return conditie;
    }

    public Model getModel() {
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

    public Transmissie getTransmissie() {
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

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }
}
