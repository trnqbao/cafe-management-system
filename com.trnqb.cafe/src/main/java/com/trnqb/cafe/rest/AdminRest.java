package com.trnqb.cafe.rest;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.utils.CafeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/admin")
public interface AdminRest {

    @GetMapping(path = "")
    ResponseEntity<String> sayHello();

    @PostMapping(path = "/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);
}
