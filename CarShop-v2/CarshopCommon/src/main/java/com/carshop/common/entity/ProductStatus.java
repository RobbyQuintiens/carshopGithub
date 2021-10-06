package com.carshop.common.entity;

public enum ProductStatus {
    AVAILABLE("Available"), RESERVED("Reserved"), SOLD("Sold");

    private final String label;

    private ProductStatus(String label){
        this.label = label;
    }
}
