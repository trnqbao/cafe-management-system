package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.JwtAuthenticationResponse;
import com.trnqb.cafe.dto.RefreshTokenRequest;
import com.trnqb.cafe.dto.Role;
import com.trnqb.cafe.entity.User;
import com.trnqb.cafe.jwt.CustomerUserDetailsService;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.UserRepository;
import com.trnqb.cafe.service.AuthenticationService;
import com.trnqb.cafe.service.JWTService;
import com.trnqb.cafe.utils.CafeUtils;

import com.trnqb.cafe.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    private final EmailUtils emailUtils;
    private final CustomerUserDetailsService customerUserDetailsService;


    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        try {
            if (validateSignUp(requestMap)) {
                User user = userRepository.findByEmail(requestMap.get("email"));

                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromSignUp(requestMap));
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

    private boolean validateSignUp(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("phoneNumber") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromSignUp(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPhoneNumber(requestMap.get("phoneNumber"));
        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
        user.setRole(Role.USER);
        user.setStatus("false");
        return user;
    }

    public String hello() {
        return "Hello";
    }

    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        log.info("Inside login {}", requestMap.get("email"));
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestMap.get("email"), requestMap.get("password")));

            if (authentication.isAuthenticated()) {
                if (customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\": \"" +
                            jwtService.generateToken(customerUserDetailsService.getUserDetails().getEmail(),
                                    String.valueOf(customerUserDetailsService.getUserDetails().getRole())) + "\"}",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}", HttpStatus.BAD_REQUEST);

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
                String oldPwd = requestMap.get("oldPassword");
                String newPwd = requestMap.get("newPassword");
                if (passwordEncoder.matches(oldPwd, user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(newPwd));
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

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
//            Optional<User> user = userRepository.findById(Integer.parseInt(requestMap.get("id")));
            User user = userRepository.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user)) {
                emailUtils.forgotMail(user.getEmail(), "Credentials by Cafe Management System", user.getName());

                return CafeUtils.getResponseEntity("Check your mail for Credentials", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("This email does not exist.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
