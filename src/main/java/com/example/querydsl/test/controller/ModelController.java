package com.example.querydsl.test.controller;

import com.example.querydsl.test.dto.ModelRequest;
import com.example.querydsl.test.dto.ModelResponse;
import com.example.querydsl.test.model.Model;
import com.example.querydsl.test.service.ModelService;
import com.example.querydsl.test.util.PaginationUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/model")
public class ModelController {
    private ModelService service;

    public ModelController(ModelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ModelResponse> insertModel(@Valid @RequestBody ModelRequest request){
        Model model = this.service.cadastrarModelo(request);
        return ResponseEntity.ok(ModelResponse.from(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getModel(@Valid @PathVariable Long id){
        Model model = this.service.buscarModelo(id);
        return ResponseEntity.ok(ModelResponse.from(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> updateModel(@Valid @PathVariable Long id,
                                                     @Valid @RequestBody ModelRequest request){
        Model model = this.service.atualizarModelo(id, request);
        return ResponseEntity.ok(ModelResponse.from(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@Valid @PathVariable Long id){
        this.service.removerModelo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAll(Pageable pageable,
                                                      @QuerydslPredicate(root = Model.class) Predicate predicate){
        Page<Model> page = this.service.listarModelos(pageable, predicate);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/model");
        return new ResponseEntity(page.getContent(), headers, HttpStatus.OK);
    }
}
