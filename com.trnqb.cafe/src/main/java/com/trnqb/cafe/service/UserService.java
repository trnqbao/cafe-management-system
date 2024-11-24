package com.trnqb.cafe.service;


import com.trnqb.cafe.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface UserService {
//    UserDetailsService userDetailsService();

    ResponseEntity<String> signup(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserDTO>> getAllUser();

    ResponseEntity<String> update(Map<String, String> requestMap);


//    User getUserDetails();


}
