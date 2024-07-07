package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/user")
public interface UserRest {
    @GetMapping("/get")
    ResponseEntity<List<UserDTO>> getAllUser();
}
