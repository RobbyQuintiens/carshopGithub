package com.carshop.admin.dto;

import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Model;

import java.util.Set;


public class BrandDto {

    private final Long id;
    private final String name;
    private final String country;
    private Set<Model> models;

    public BrandDto(Brand brand){
        this.id = brand.getId();
        this.name = brand.getName();
        this.country = brand.getCountry();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Long getId() {
        return id;
    }

    public Set<Model> getModels(){
        return models;
    }
}
