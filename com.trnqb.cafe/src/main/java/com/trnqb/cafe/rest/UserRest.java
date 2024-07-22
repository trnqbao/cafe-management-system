package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRest {
    @GetMapping("/get")
    ResponseEntity<List<UserDTO>> getAllUser();
}
