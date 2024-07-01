//package com.trnqb.cafe.serviceImpl;
//
//import com.trnqb.cafe.JWT.CustomerUserDetailsService;
//import com.trnqb.cafe.JWT.JwtFilter;
//import com.trnqb.cafe.JWT.JwtService;
//import com.trnqb.cafe.entities.Role;
//import com.trnqb.cafe.entities.User;
//import com.trnqb.cafe.constants.CafeConstants;
//import com.trnqb.cafe.repository.UserRepository;
//import com.trnqb.cafe.service.UserService;
//import com.trnqb.cafe.utils.CafeUtils;
//import com.trnqb.cafe.utils.EmailUtils;
//import com.trnqb.cafe.wrapper.UserWrapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    CustomerUserDetailsService customerUserDetailsService;
//
//    @Autowired
//    JwtService jwtService;
//
//    @Autowired
//    JwtFilter jwtFilter;
//
//    @Autowired
//    EmailUtils emailUtils;
//
//    @Override
//    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
//        log.info("Inside signup {}", requestMap);
//        try {
//            if (validateSignUp(requestMap)) {
//                User user = userRepository.findByEmail(requestMap.get("email"));
//
//                if (Objects.isNull(user)) {
//                    userRepository.save(getUserFromMap(requestMap));
//                    return CafeUtils.getResponseEntity(CafeConstants.REGISTERED, HttpStatus.OK);
//                } else {
//                    return CafeUtils.getResponseEntity(CafeConstants.EXISTED_EMAIL, HttpStatus.BAD_REQUEST);
//                }
//            } else {
//                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> login(Map<String, String> requestMap) {
//        log.info("Inside login");
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
//            );
//
//            if (authentication.isAuthenticated()) {
//                if (customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
//                    return new ResponseEntity<String>("{\"token\": \"" +
//                            jwtService.generateToken(customerUserDetailsService.getUserDetail().getEmail(),
//                                    String.valueOf(customerUserDetailsService.getUserDetail().getRole())) + "\"}",
//                    HttpStatus.OK);
//                }
//                else {
//                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval."+"\"}",
//                            HttpStatus.BAD_REQUEST);
//                }
//            }
//        } catch (Exception e) {
//            log.error("{}", e);
//        }
//        return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials."+"\"}",
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public ResponseEntity<List<UserWrapper>> getAllUser() {
//        try {
//            if (jwtFilter.isAdmin()) {
//                return new ResponseEntity<>(userRepository.getAllUser(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> update(Map<String, String> requestMap) {
//        try {
//            if (jwtFilter.isAdmin()) {
//                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
//                System.out.println("Hello, admin");
//                if (optional.isPresent()) {
//                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
//
//                    sendMailtoAllAdmin(requestMap.get("status"), optional.get().getEmail(), userRepository.getAllAdmin());
//
//                    return CafeUtils.getResponseEntity(CafeConstants.UPDATED_STATUS, HttpStatus.OK);
//
//                } else {
//                    return CafeUtils.getResponseEntity(CafeConstants.NOT_EXISTED_USER, HttpStatus.OK);
//                }
//
//            } else {
//                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    private void sendMailtoAllAdmin(String status, String email, List<String> allAdmin) {
//        allAdmin.remove(jwtFilter.getCurrentUser());
//
//        if (status != null && status.equalsIgnoreCase("true")) {
//            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),
//                    "Account Approved",
//                    "USER:- " + email + "\n is approved by \n ADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
//
//        } else {
//            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),
//                    "Account Disabled",
//                    "USER:- " + email + "\n is disabled by \n ADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
//        }
//    }
//
//    private boolean validateSignUp(Map<String, String> requestMap) {
//        if (requestMap.containsKey("name") && requestMap.containsKey("phoneNumber")
//                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
//            return true;
//        }
//        return false;
//    }
//
//    private User getUserFromMap(Map<String, String> requestMap) {
//        User user = new User();
//        var bCryptEncoder = new BCryptPasswordEncoder();
//
//        user.setName(requestMap.get("name"));
//        user.setPhoneNumber(requestMap.get("phoneNumber"));
//        user.setEmail(requestMap.get("email"));
//        user.setPassword(bCryptEncoder.encode(requestMap.get("password")));
//        user.setStatus("false");
//        user.setRole(Role.USER);
//        return user;
//    }
//}
