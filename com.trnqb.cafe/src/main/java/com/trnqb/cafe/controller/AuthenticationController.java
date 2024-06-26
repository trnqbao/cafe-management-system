package com.trnqb.cafe.controller;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import com.trnqb.cafe.entities.User;
import com.trnqb.cafe.service.AuthenticationService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        try {
            return authenticationService.signup(signUpRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(authenticationService.hello());
    }
}
