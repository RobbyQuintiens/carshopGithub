package com.carshop.admin.dto;

import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Brandstof;


public class BrandstofDto {

    private final Long id;
    private final String brandstofType;

    public BrandstofDto(Brandstof brandstof){
        this.id = brandstof.getId();
        this.brandstofType = brandstof.getBrandstofType();
    }

    public String getBrandstofType() {
        return brandstofType;
    }

    public Long getId() {
        return id;
    }
}
