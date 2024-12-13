package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.BillDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/bill")
@CrossOrigin(origins = "http://localhost:4200")
public interface BillRest {

    @PostMapping(path = "/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);

    @GetMapping(path = "/getBills")
    ResponseEntity<List<BillDTO>> getBills();

    @GetMapping(path = "/getBills/{payment}")
    ResponseEntity<List<BillDTO>> getBillsByPayment(@PathVariable String payment);

    @GetMapping(path = "/getBillsFrom/{startDate}")
    ResponseEntity<List<BillDTO>> getBillsFrom(@PathVariable LocalDate startDate);

    @GetMapping(path = "/getWeeklyOrderDistribution")
    ResponseEntity<List<Map<String, Object>>> getWeeklyOrderDistribution(@RequestParam(required = false) LocalDate date);

    @GetMapping(path = "/getTotalOrderByDay")
    ResponseEntity<List<Map<String, Object>>> getTotalOrderByDay(@PathVariable(required = false) LocalDate date);

    @PostMapping(path = "/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteBill(@PathVariable Integer id);

}
