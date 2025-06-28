package com.ecommerce.productcatalogservice.repository;

import com.ecommerce.productcatalogservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameAndRecordStatus(String productName, boolean b);

    Product findByIdAndRecordStatus(Long id, Boolean recordStatus);
}
