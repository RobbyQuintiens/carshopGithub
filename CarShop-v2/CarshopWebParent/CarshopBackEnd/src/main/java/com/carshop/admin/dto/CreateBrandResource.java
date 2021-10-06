package com.carshop.admin.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateBrandResource {

    @NotNull
    private final String name;

    private final String country;

    public CreateBrandResource(@NotNull String name, String country){
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
