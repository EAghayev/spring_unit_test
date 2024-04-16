package com.testingapp.repository;

import com.testingapp.entity.Product;
import com.testingapp.exception.EntityDublicateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository productJpaRepository;


    @Test
    public void save_ShouldReturnSavedEntity(){

        //Arrange
        Product entity = Product.builder()
                .name("pr 1")
                .price(100D)
                .expireDate(LocalDateTime.now()).build();

        //Act
        var savedEntity = productJpaRepository.save(entity);

        //Assert
        assertThat(savedEntity).isNotNull();
        assertThat(savedEntity.getId()).isGreaterThan(0);
    }

    @Test
    public void existByName_ShouldReturnTrue(){
        //Arrange
        Product entity = Product.builder()
                .name("pr 1")
                .price(100D)
                .expireDate(LocalDateTime.now()).build();

        productJpaRepository.save(entity);

        String name = "pr 1";

        //Act
        var result = productJpaRepository.existsByName(name);

        //Assert
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void existByName_ShouldReturnFalse(){
        //Arrange
        String name = "pr 1";

        //Act
        var result = productJpaRepository.existsByName(name);

        //Assert
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void findById_ShouldReturnEntity(){
        //Arrange
        Product entity = Product.builder()
                .name("pr 1")
                .price(100D)
                .expireDate(LocalDateTime.now()).build();

        productJpaRepository.save(entity);

        Long id = entity.getId();

        //Act
        var entityOpt = productJpaRepository.findById(id);

        //Assert
        assertThat(entityOpt.isPresent()).isEqualTo(true);
    }

    @Test
    public void findById_WithWrongId_ShouldReturnEmpty(){
        //Arrange
        Long id = 1L;

        //Act
        var entityOpt = productJpaRepository.findById(id);

        //Assert
        assertThat(entityOpt.isPresent()).isEqualTo(false);
    }
}
