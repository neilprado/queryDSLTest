package com.example.querydsl.test.service;

import com.example.querydsl.test.dto.ModelRequest;
import com.example.querydsl.test.model.Model;
import com.example.querydsl.test.repository.ModelRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ModelService {
    private ModelRepository repository;

    public ModelService(ModelRepository repository) {
        this.repository = repository;
    }

    public Model cadastrarModelo(ModelRequest request){
        Model model = new Model();

        model.setBrand(request.getBrand());
        model.setDescription(request.getDescription());

        return this.repository.save(model);
    }

    public Model buscarModelo(Long id){
        Model model = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
        return model;
    }

    public Model atualizarModelo(Long id, ModelRequest request){
        Model model = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));

        model.setBrand(request.getBrand());
        model.setDescription(request.getDescription());

        return this.repository.save(model);
    }

    public void removerModelo(Long id){
        Model model = this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
        this.repository.delete(model);
    }

    public Page<Model> listarModelos(Pageable pageable, Predicate predicate){
        return this.repository.findAll(predicate, pageable);
    }
}
