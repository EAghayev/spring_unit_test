package com.testingapp.service;

import com.testingapp.dto.ProductCreateDto;
import com.testingapp.entity.Product;
import com.testingapp.exception.EntityDublicateException;
import com.testingapp.exception.EntityNotFoundException;
import com.testingapp.repository.ProductJpaRepository;
import com.testingapp.service.Impl.ProductServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductJpaRepository productJpaRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;


    @Test
    public void create_Success_ShouldReturnDto() {
        //Arrange
        ProductCreateDto createDto = ProductCreateDto.builder()
                .name("new pr 1")
                .price(1001D)
                .build();

        Product savedEntity = Product.builder()
                        .id(1L).build();

        when(productJpaRepository.existsByName(createDto.getName())).thenReturn(false);
        when(productJpaRepository.save(any(Product.class))).thenReturn(savedEntity);

        //Act
        var getDto = productService.create(createDto);

        //Assert
        assertThat(getDto).isNotNull();
        assertThat(getDto.getId()).isGreaterThan(0);
    }
    @Test
    public void save_WithDublicateName_ShouldThrowExp(){
        //Arrange
        ProductCreateDto createDto = ProductCreateDto.builder()
                .name("pr 1")
                .price(1001D)
                .build();

        when(productJpaRepository.existsByName(createDto.getName())).thenReturn(true);

        //Act & Assert
        assertThrows(EntityDublicateException.class,()->productService.create(createDto));
    }

    @Test
    public void getById_ShouldReturnDto(){
        //Arrange
        Product entity = Product.builder()
                .id(1L)
                .name("pr 1")
                .price(1001D)
                .build();

       Long id = 1L;

       when(productJpaRepository.findById(id)).thenReturn(Optional.of(entity));

       //Act
       var getDto = productService.getById(id);

       //Assert
      assertThat(getDto).isNotNull();
      assertThat(getDto.getId()).isEqualTo(id);
    }

    @Test
    public void getById_WithWrongId_ShouldThrowExp(){
        //Arrange
        Long id = 1L;

        when(productJpaRepository.findById(id)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(EntityNotFoundException.class,()->productService.getById(id));
    }

}
