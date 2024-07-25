package com.productservice.productservice.repositories;

import com.productservice.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    @Query(value = "select * from product;",nativeQuery = true)
    List<Product> findAll();
}
