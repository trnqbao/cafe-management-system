package com.trnqb.cafe.service;

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

    ResponseEntity<Integer> getDailyRevenue(LocalDate date);

    ResponseEntity<Integer> getMonthlyRevenue(int month, int year);

    ResponseEntity<List<Map<String, Object>>> getWeeklyRevenue(LocalDate date);

    ResponseEntity<Integer> getMonthlyPrediction();
}
