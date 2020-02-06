package com.example.querydsl.test.controller;

import com.example.querydsl.test.dto.ModelResponse;
import com.example.querydsl.test.dto.SaleRequest;
import com.example.querydsl.test.dto.SaleResponse;
import com.example.querydsl.test.model.Sale;
import com.example.querydsl.test.service.SaleService;
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
@RequestMapping(value = "api/sale")
public class SaleController {
    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> insertSale(@Valid @RequestBody SaleRequest request){
        Sale sale = this.saleService.cadastrarSale(request);
        return ResponseEntity.ok(SaleResponse.from(sale));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSale(@Valid @PathVariable Long id){
        Sale sale = this.saleService.buscarSale(id);
        return ResponseEntity.ok(SaleResponse.from(sale));
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAll(Pageable pageable,
                                                      @QuerydslPredicate(root = Sale.class) Predicate predicate){
        Page<Sale> page = this.saleService.listarSales(pageable, predicate);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "api/sale");
        return new ResponseEntity(page.getContent(), headers, HttpStatus.OK);
    }
}
