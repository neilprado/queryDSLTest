package com.example.querydsl.test.dto;

import lombok.Data;


public class ModelRequest {
    private String brand;
    private String description;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
