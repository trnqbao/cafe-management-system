package com.trnqb.cafe.repository;

import com.trnqb.cafe.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
