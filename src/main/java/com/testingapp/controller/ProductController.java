package com.testingapp.controller;

import com.testingapp.dto.ProductCreateDto;
import com.testingapp.dto.ProductGetDto;
import com.testingapp.entity.Product;
import com.testingapp.exception.EntityDublicateException;
import com.testingapp.exception.EntityNotFoundException;
import com.testingapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductGetDto> create(@RequestBody ProductCreateDto product){
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDto> getById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductGetDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }
}
