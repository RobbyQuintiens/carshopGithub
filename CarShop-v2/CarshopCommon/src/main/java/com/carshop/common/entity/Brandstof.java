package com.carshop.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "brandstof")
public class Brandstof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String brandstofType;

    public Brandstof(String brandstofType) {
        this.brandstofType = brandstofType;
    }

    public Brandstof(){

    }

    public Brandstof(Long id){
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBrandstofType() {
        return brandstofType;
    }

    public void setBrandstofType(String brandstofType) {
        this.brandstofType = brandstofType;
    }

    @Override
    public String toString() {
        return this.brandstofType;
    }
}
