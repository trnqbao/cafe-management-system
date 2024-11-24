package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.CustomerDTO;
import com.trnqb.cafe.entity.Customer;
import com.trnqb.cafe.repository.CustomerRepository;
import com.trnqb.cafe.service.CustomerService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<String> addCustomer(Map<String, String> requestMap) {
        try {
            if (validateCustomerMap(requestMap)) {
                customerRepository.save(mapToEntity(requestMap));
                return CafeUtils.getResponseEntity("Customer added successfully", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCustomer(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCustomerDiscount(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        return null;
    }

    private CustomerDTO mapToDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setPoint(customer.getPoint());
        customerDTO.setIsDiscount(customer.getIsDiscount());
        customerDTO.setLatestDateOrder(customer.getLatestDateOrder());
        return customerDTO;
    }

    private Customer mapToEntity(Map<String, String> requestMap) {
        Customer customer = new Customer();
        customer.setName(requestMap.get("name"));
        customer.setEmail(requestMap.get("email"));
        customer.setPhoneNumber(requestMap.get("phoneNumber"));
        customer.setPoint(1);
        customer.setIsDiscount(false);
        customer.setLatestDateOrder(LocalDate.now());
        return customer;
    }

    private Boolean validateCustomerMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("phoneNumber");
    }
}
