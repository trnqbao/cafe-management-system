package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.CategoryDTO;
import com.trnqb.cafe.dto.CustomerDTO;
import com.trnqb.cafe.entity.Customer;
import com.trnqb.cafe.entity.Product;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.CustomerRepository;
import com.trnqb.cafe.service.CustomerService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtFilter jwtFilter;

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            return new ResponseEntity<>(customerRepository.findAll()
                    .stream().map(c -> mapToDTO(c, new CustomerDTO()))
                    .toList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<CustomerDTO> getCustomerById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<String> addCustomer(Map<String, String> requestMap) {
        try {
            if (validateCustomerMap(requestMap)) {
                Customer customer = customerRepository.findByPhoneNumber(requestMap.get("phoneNumber"));
                if (!Objects.isNull(customer)) {
                    customer.setPoint(customer.getPoint() + Integer.parseInt(requestMap.get("point")));
                    if (!customer.getIsDiscount() && customer.getPoint() < 20)
                    customer.setLastOrder(LocalDate.now());
                    customerRepository.save(customer);
                    return CafeUtils.getResponseEntity("Customer updated successfully", HttpStatus.OK);
                }
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
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Customer> optional = customerRepository.findById(id);
                if (optional.isPresent()) {
                    customerRepository.deleteById(id);
                    return CafeUtils.getResponseEntity("Customer has been deleted.", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Customer Id does not exist.", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private CustomerDTO mapToDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setPoint(customer.getPoint());
        customerDTO.setIsDiscount(customer.getIsDiscount());
        customerDTO.setLastOrder(customer.getLastOrder());
        return customerDTO;
    }

    private Customer mapToEntity(Map<String, String> requestMap) {
        Customer customer = new Customer();
        customer.setName(requestMap.get("name"));
        customer.setEmail(requestMap.get("email"));
        customer.setPhoneNumber(requestMap.get("phoneNumber"));
        customer.setPoint(Integer.parseInt(requestMap.get("point")));
        customer.setIsDiscount(customer.getPoint() >= 10);
        customer.setLastOrder(LocalDate.now());
        return customer;
    }

    private Boolean validateCustomerMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("phoneNumber");
    }
}
