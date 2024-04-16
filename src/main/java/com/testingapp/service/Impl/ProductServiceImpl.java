package com.testingapp.service.Impl;

import com.testingapp.dto.ProductCreateDto;
import com.testingapp.dto.ProductGetDto;
import com.testingapp.entity.Product;
import com.testingapp.exception.EntityDublicateException;
import com.testingapp.exception.EntityNotFoundException;
import com.testingapp.repository.ProductJpaRepository;
import com.testingapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public ProductGetDto create(ProductCreateDto createDto) throws EntityDublicateException {
        if(productJpaRepository.existsByName(createDto.getName())) throw new EntityDublicateException();

        Product entity = Product.builder()
                .name(createDto.getName())
                .price(createDto.getPrice())
                .expireDate(LocalDateTime.now()).build();

        var savedEntity = productJpaRepository.save(entity);

        return ProductGetDto.builder().id(savedEntity.getId()).name(savedEntity.getName()).price(savedEntity.getPrice()).expireDate(savedEntity.getExpireDate()).build();
    }

    @Override
    public ProductGetDto getById(Long id) throws EntityNotFoundException {
        Product entity = productJpaRepository.findById(id).orElseThrow(()->new EntityNotFoundException());

        return ProductGetDto.builder().id(entity.getId()).name(entity.getName()).price(entity.getPrice()).expireDate(entity.getExpireDate()).build();
    }

    @Override
    public List<ProductGetDto> getAll() {
        return productJpaRepository.findAll().stream().map((entity)->ProductGetDto.builder().id(entity.getId()).name(entity.getName()).price(entity.getPrice()).expireDate(entity.getExpireDate()).build()).toList();
    }
}
