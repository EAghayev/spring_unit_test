package com.testingapp.service;

import com.testingapp.dto.ProductCreateDto;
import com.testingapp.dto.ProductGetDto;
import com.testingapp.entity.Product;
import com.testingapp.exception.EntityDublicateException;
import com.testingapp.exception.EntityNotFoundException;

import java.util.List;

public interface ProductService {
    ProductGetDto create(ProductCreateDto createDto) throws EntityDublicateException;

    ProductGetDto getById(Long id) throws EntityNotFoundException;

    List<ProductGetDto> getAll();
}
