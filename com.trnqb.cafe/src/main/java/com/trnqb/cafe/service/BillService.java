package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.BillDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<BillDTO>> getBills();

    ResponseEntity<String> deleteBill(Integer id);

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<List<BillDTO>> getBillsFrom(LocalDate date);

    ResponseEntity<List<BillDTO>> getBillsByPayment(String payment);

    ResponseEntity<List<Map<String, Object>>> getWeeklyOrderDistribution(LocalDate date);

    ResponseEntity<List<Map<String, Object>>> getTotalOrdersByDay(LocalDate date);
}
