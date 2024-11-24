package com.trnqb.cafe.rest;

import com.trnqb.cafe.dto.UserDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRest {

//    @PostMapping("/signup")
//    ResponseEntity<String> signup(@RequestBody Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<UserDTO>> getAllUser();
}
