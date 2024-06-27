package com.trnqb.cafe.rest;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.service.UserService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResource {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi Admin");
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return userService.update(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
