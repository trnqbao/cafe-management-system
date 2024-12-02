package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.ProductCount;
import com.trnqb.cafe.dto.RevenueDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public interface RevenueService {
    ResponseEntity<List<RevenueDTO>> getRevenues();

//    ResponseEntity<String> addRevenue(Map<String, String> requestMap);

    ResponseEntity<String> updateRevenue(Map<String, String> requestMap);

    ResponseEntity<Map<String, Object>> getDailyRevenue(LocalDate date);

    ResponseEntity<Map<String, Object>> getMonthlyRevenue();

    ResponseEntity<List<Map<String, Object>>> getWeeklyRevenue(LocalDate date);

    ResponseEntity<Integer> getMonthlyPrediction();

    ResponseEntity<List<Map<String, Object>>> getProductFrequencyLast7Days();
}
