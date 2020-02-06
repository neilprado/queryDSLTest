package com.example.querydsl.test.service;

import com.example.querydsl.test.dto.SaleRequest;
import com.example.querydsl.test.model.Model;
import com.example.querydsl.test.model.Sale;
import com.example.querydsl.test.repository.ModelRepository;
import com.example.querydsl.test.repository.SaleRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SaleService {
    private SaleRepository saleRepository;
    private ModelRepository modelRepository;

    public SaleService(SaleRepository saleRepository, ModelRepository modelRepository) {
        this.saleRepository = saleRepository;
        this.modelRepository = modelRepository;
    }

    public Sale cadastrarSale(SaleRequest request){
        Model model = this.modelRepository.findById(request.getModelId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));

        Sale sale = new Sale();
        sale.setCode(request.getCode());
        sale.setManufactureYear(request.getManufactureYear());
        sale.setModel(model);

        return this.saleRepository.save(sale);
    }

    public Sale buscarSale(Long id){
        Sale sale = this.saleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
        return sale;
    }

    public Sale atualizarSale(Long id, SaleRequest request){
        Model model = this.modelRepository.findById(request.getModelId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));

        Sale sale = this.saleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));

        sale.setCode(request.getCode());
        sale.setManufactureYear(request.getManufactureYear());
        sale.setModel(model);

        return this.saleRepository.save(sale);
    }

    public void removerSale(Long id){
        Sale sale = this.saleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));

        this.saleRepository.delete(sale);
    }

    public Page<Sale> listarSales(Pageable pageable, Predicate predicate){
        return this.saleRepository.findAll(predicate, pageable);
    }
}
