package com.trnqb.cafe.service;

import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import com.trnqb.cafe.entities.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> signup(SignUpRequest signUpRequest);

    String hello();

    JwtAuthenticationResponse login(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
