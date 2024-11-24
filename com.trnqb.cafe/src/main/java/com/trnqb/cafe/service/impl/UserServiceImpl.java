package com.trnqb.cafe.service.impl;


import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.UserDTO;
import com.trnqb.cafe.dto.Role;
import com.trnqb.cafe.entity.User;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.UserRepository;
import com.trnqb.cafe.service.UserService;

import com.trnqb.cafe.utils.CafeUtils;
import com.trnqb.cafe.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailUtils emailUtils;
    private final JwtFilter jwtFilter;
    private final PasswordEncoder passwordEncoder;
//    @Getter
//    private com.trnqb.cafe.entities.User userDetails;

//    @Override
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            userDetails = userRepository.findByEmail(username);
//            if (!Objects.isNull(userDetails)) {
//                return new org.springframework.security.core.userdetails.User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>());
//            } else {
//                throw new UsernameNotFoundException("User not found.");
//            }
//        };
//    }

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
        try {
            if (validateSignUp(requestMap)) {
                System.out.println("ValidateSignUp worked!");
                User user = userRepository.findByEmail(requestMap.get("email"));

                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromRequest(requestMap));
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

    private User getUserFromRequest(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setEmail(requestMap.get("email"));
        user.setPhoneNumber(requestMap.get("phoneNumber"));
        user.setPassword(passwordEncoder.encode(requestMap.get("password")));
        user.setRole(Role.USER);
        user.setStatus("false");
        return user;
    }

    private boolean validateSignUp(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("phoneNumber") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUser() {
        try {
            return new ResponseEntity<>(userRepository.findAllByRole(Role.USER)
                    .stream().map(user -> mapToDTO(user, new UserDTO()))
                    .toList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            System.out.println("ID: " + String.valueOf(requestMap.get("id")));
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userRepository.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent()) {
                    userRepository.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailtoUser(requestMap.get("status"), optional.get().getEmail());
                    return CafeUtils.getResponseEntity(CafeConstants.UPDATED_STATUS, HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity(CafeConstants.NOT_EXISTED_USER, HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDto) {
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    private void sendMailtoUser(String status, String email) {

        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(email,
                    "Account Approved",
                    "User: " + email + "\n is approved by Admin: BaoTran");

        } else {
            emailUtils.sendSimpleMessage(email,
                    "Account Disabled",
                    "User: " + email + "\n is disabled by Admin: BaoTran");
        }
    }

}
