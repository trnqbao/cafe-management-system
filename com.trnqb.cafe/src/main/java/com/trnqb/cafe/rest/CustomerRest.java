package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/customer")
@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerRest {
    @GetMapping(path = "/get")
    ResponseEntity<List<CustomerDTO>> getAllCustomers();

    @GetMapping(path = "/getById/{id}")
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer id);

    @GetMapping(path = "/getByPhoneNumber/{phoneNumber}")
    ResponseEntity<CustomerDTO> getCustomerByPhoneNumber(@PathVariable String phoneNumber);

    @PostMapping(path = "/add")
    ResponseEntity<String> addCustomer(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateCustomer(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteCustomer(@PathVariable Integer id);

    @PostMapping(path = "/updateDiscount")
    ResponseEntity<String> updateCustomerDiscount(@RequestBody Map<String, String> requestMap);
}
