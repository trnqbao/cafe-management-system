package com.trnqb.cafe.rest.impl;

import com.trnqb.cafe.dto.UserDTO;
import com.trnqb.cafe.rest.UserRest;
import com.trnqb.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestImpl implements UserRest {
    private final UserService userService;

    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        try {
            return userService.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<UserDTO>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
