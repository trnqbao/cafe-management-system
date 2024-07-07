package com.trnqb.cafe.service;


import com.trnqb.cafe.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

    ResponseEntity<List<ProductDTO>> getAllProduct();

    ResponseEntity<String> updateProduct(Map<String, String> requestMap);
}
