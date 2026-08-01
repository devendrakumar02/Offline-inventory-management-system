package com.deva.inventory_management.repository;

import com.deva.inventory_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByBarcode(String barcode);

    Optional<Product> findByProductName(String productName);

}