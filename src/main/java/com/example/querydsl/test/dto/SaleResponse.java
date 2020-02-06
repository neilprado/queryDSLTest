package com.example.querydsl.test.dto;

import com.example.querydsl.test.model.Sale;


public class SaleResponse {
    private Long id;
    private String code;
    private int manufactureYear;
    private long modelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public static SaleResponse from(Sale sale){
        SaleResponse response = new SaleResponse();
        response.setId(sale.getId());
        response.setCode(sale.getCode());
        response.setManufactureYear(sale.getManufactureYear());
        response.setModelId(sale.getModel().getId());

        return response;
    }
}
