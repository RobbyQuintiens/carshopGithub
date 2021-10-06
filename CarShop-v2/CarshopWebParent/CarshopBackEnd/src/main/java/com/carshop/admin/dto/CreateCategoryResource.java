package com.carshop.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CreateCategoryResource {

    @NotNull
    @JsonProperty("name")
    private final String name;

    public CreateCategoryResource(@NotNull @JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
