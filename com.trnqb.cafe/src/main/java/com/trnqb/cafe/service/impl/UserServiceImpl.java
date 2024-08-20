package com.trnqb.cafe.service.impl;


import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.UserDTO;
import com.trnqb.cafe.entities.Role;
import com.trnqb.cafe.entities.User;
import com.trnqb.cafe.jwt.JwtFilter;
import com.trnqb.cafe.repository.UserRepository;
import com.trnqb.cafe.service.UserService;

import com.trnqb.cafe.utils.CafeUtils;
import com.trnqb.cafe.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailUtils emailUtils;
    private final JwtFilter jwtFilter;
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
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
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
