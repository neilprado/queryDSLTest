package com.example.querydsl.test.repository;

import com.example.querydsl.test.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SaleRepository extends JpaRepository<Sale, Long>, QuerydslPredicateExecutor<Sale> {
}
