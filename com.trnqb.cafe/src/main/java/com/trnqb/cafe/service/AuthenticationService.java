package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthenticationService {
    ResponseEntity<String> signup(SignUpRequest signUpRequest);

    String hello();

    ResponseEntity<String> login(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String, String> requestMap);
}
