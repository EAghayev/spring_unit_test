package com.testingapp.repository;

import com.testingapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product,Long> {
    boolean existsByName(String name);
    Optional<Product> findById(Long id);
}
