package com.trnqb.cafe.rest;
import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "")
@CrossOrigin(origins = "http://localhost:4200")
public interface AuthenticationRest {
    @PostMapping(path = "/signup")
    ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest);

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody SignInRequest signInRequest);

    @PostMapping(path = "/refresh") ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @GetMapping(path = "/hello")
    ResponseEntity<String> hello();

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
}
