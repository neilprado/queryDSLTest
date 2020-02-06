package com.example.querydsl.test.dto;

import com.example.querydsl.test.model.Model;
import lombok.Data;
import org.springframework.data.domain.Page;

public class ModelResponse {

    private Long id;
    private String brand;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static ModelResponse from(Model model){
        ModelResponse response = new ModelResponse();
        response.setId(model.getId());
        response.setBrand(model.getBrand());
        response.setDescription(model.getDescription());

        return response;
    }

    public static Page<ModelResponse> from(Page<Model> models){
        Page<ModelResponse> responses = models.map(model -> {
            ModelResponse response = new ModelResponse();
            response.setId(model.getId());
            response.setBrand(model.getBrand());
            response.setDescription(model.getDescription());

            return response;
        });

        return responses;
    }
}
