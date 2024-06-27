package com.trnqb.cafe.service;


import com.trnqb.cafe.dto.UserDto;
import com.trnqb.cafe.entities.Role;
import com.trnqb.cafe.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;


public interface UserService {
    UserDetailsService userDetailsService();

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserDto>> getAllUser();

    ResponseEntity<String> update(Map<String, String> requestMap);


}
