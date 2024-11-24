package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    ResponseEntity<List<CustomerDTO>> getAllCustomers();

    ResponseEntity<CustomerDTO> getCustomerById(Integer id);

    ResponseEntity<String> addCustomer(Map<String, String> requestMap);

    ResponseEntity<String> updateCustomer(Map<String, String> requestMap);

    ResponseEntity<String> updateCustomerDiscount(Map<String, String> requestMap);

    ResponseEntity<String> deleteCustomer(Integer id);
}
