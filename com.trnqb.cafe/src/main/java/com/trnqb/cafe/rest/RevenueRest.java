package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.RevenueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/revenue")
@CrossOrigin(origins = "http://localhost:4200")
public interface RevenueRest {
    @GetMapping(path = "/get")
    ResponseEntity<List<RevenueDTO>> getRevenues();

    @GetMapping(path = "/getDailyProducts")
    ResponseEntity<List<Map<String, Object>>> getDailyProducts(@RequestParam(required = false) LocalDate date);

    @GetMapping(path = "/getDailyRevenue")
    ResponseEntity<Map<String, Object>> getDailyRevenue(@RequestParam(required = false) LocalDate date);

    @GetMapping(path = "/getWeeklyRevenue")
    ResponseEntity<List<Map<String, Object>>> getWeeklyRevenue(@RequestParam(required = false) LocalDate date);

    @GetMapping(path = "/getMonthlyRevenue")
    ResponseEntity<Map<String, Object>> getMonthlyRevenue();

    @GetMapping(path = "/getMonthlyPrediction")
    ResponseEntity<Integer> getMonthlyPrediction();

    @GetMapping(path = "/getProductFrequencyLast7Days")
    ResponseEntity<List<Map<String, Object>>> getProductFrequencyLast7Days();

    @PostMapping(path = "/add")
    ResponseEntity<String> addRevenue(@RequestBody Map<String, String> requestMap);

}
