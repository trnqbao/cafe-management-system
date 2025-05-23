package com.trnqb.cafe.rest.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import com.trnqb.cafe.rest.AuthenticationRest;
import com.trnqb.cafe.service.AuthenticationService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationRestImpl implements AuthenticationRest {
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        try {
            return authenticationService.signup(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return authenticationService.login(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> refresh(RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @Override
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(authenticationService.hello());
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try {
            authenticationService.checkToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            return authenticationService.changePassword(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            return authenticationService.forgotPassword(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
//        try {
//            return authenticationService.signup(signUpRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody SignInRequest signInRequest) {
//        try {
//            return authenticationService.login(signInRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/refresh")
//    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<String> hello() {
//        return ResponseEntity.ok(authenticationService.hello());
//    }
//
//    @GetMapping("/checkToken")
//    public ResponseEntity<String> checkToken() {
//        try {
//            authenticationService.checkToken();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/changePassword")
//    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap) {
//        try {
//            return authenticationService.changePassword(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/forgotPassword")
//    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
//        try {
//            return authenticationService.forgotPassword(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
