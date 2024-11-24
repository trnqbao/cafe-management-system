package com.trnqb.cafe.repository;

import com.trnqb.cafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    Integer updateProductStatus(@Param("status") String status, @Param("id") int id);

    List<Product> findAllByCategoryIdAndStatus(Integer id, String status);


}
