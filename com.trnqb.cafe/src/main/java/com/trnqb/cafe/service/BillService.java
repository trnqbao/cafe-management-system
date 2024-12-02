package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.BillDTO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<BillDTO>> getBills();

    ResponseEntity<String> deleteBill(Integer id);

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<Integer> getDailyRevenue(Date date);

    ResponseEntity<List<BillDTO>> getBillsFrom(Date date);

    ResponseEntity<List<BillDTO>> getBillsByPayment(String payment);
}
