package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.SignInRequest;
import com.trnqb.cafe.dto.SignUpRequest;
import com.trnqb.cafe.entities.Role;
import com.trnqb.cafe.entities.User;
import com.trnqb.cafe.jwt.CustomerUserDetailsService;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.UserRepository;
import com.trnqb.cafe.service.AuthenticationService;
import com.trnqb.cafe.service.JWTService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final JwtFilter jwtFilter;
    private final CustomerUserDetailsService customerUserDetailsService;


    public ResponseEntity<String> signup(SignUpRequest signUpRequest) {
        try {
            if (validateSignUp(signUpRequest)) {
                User user = userRepository.findByEmail(signUpRequest.getEmail());

                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromSignUpRequest(signUpRequest));
                    return CafeUtils.getResponseEntity(CafeConstants.REGISTERED, HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity(CafeConstants.EXISTED_EMAIL, HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUp(SignUpRequest signUpRequest) {
        // todo
        return true;
    }

    private User getUserFromSignUpRequest(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        user.setStatus("false");
        return user;
    }

    public String hello() {
        return "Hello";
    }

    public ResponseEntity<String> login(SignInRequest signInRequest) {
        log.info("Inside login");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getEmail(),
                    signInRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                if (customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\": \"" +
                            jwtService.generateToken(customerUserDetailsService.getUserDetails().getEmail(),
                                    String.valueOf(customerUserDetailsService.getUserDetails().getRole())) + "\"}",
                            HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval."+"\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials."+"\"}", HttpStatus.BAD_REQUEST);

//            var user = userRepository.findByEmail(signInRequest.getEmail());
//
//            var token = jwtService.generateToken(user.getEmail(), user.getRole().name());
//            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user, user.getRole().name());
//
//            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//            jwtAuthenticationResponse.setToken(token);
//            jwtAuthenticationResponse.setRefreshToken(refreshToken);
//            return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String email = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(email);
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var token = jwtService.generateToken(user.getEmail(), user.getRole().name());
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(token);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User user = userRepository.findByEmail(jwtFilter.getCurrentUser());
            if (user != null) {
                System.out.println("Old Password: " + passwordEncoder.encode(requestMap.get("oldPassword")));
                if (passwordEncoder.matches(requestMap.get("oldPassword"), user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(requestMap.get("newPassword")));
                    userRepository.save(user);
                    return CafeUtils.getResponseEntity(CafeConstants.UPDATED_PWD, HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstants.INCORRECT_OLD_PASSWORD, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
