package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.repository.BillRepository;
import com.trnqb.cafe.repository.CategoryRepository;
import com.trnqb.cafe.repository.ProductRepository;
import com.trnqb.cafe.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BillRepository billRepository;
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("product", productRepository.count());
        map.put("category", categoryRepository.count());
        map.put("bill", billRepository.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
