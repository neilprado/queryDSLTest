package com.example.querydsl.test.repository;

import com.example.querydsl.test.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ModelRepository extends JpaRepository<Model, Long>, QuerydslPredicateExecutor<Model> {
}
