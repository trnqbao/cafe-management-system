package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.UserDto;
import com.trnqb.cafe.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUser() {
        try {
            return userService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserDto>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
