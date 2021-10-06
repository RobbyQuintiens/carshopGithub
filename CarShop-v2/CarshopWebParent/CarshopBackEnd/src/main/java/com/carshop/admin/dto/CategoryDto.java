package com.carshop.admin.dto;

import com.carshop.common.entity.Category;

public class CategoryDto {

    private final Long id;
    private final String name;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.id = category.getId();
    }

    public String getName() {
        return name;
    }

    public Long getId(){ return id;}
}
