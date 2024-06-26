package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.config.JwtFilter;
import com.trnqb.cafe.repository.UserRepository;
import com.trnqb.cafe.service.UserService;
import com.trnqb.cafe.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);

            }
        };
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        return null;
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        return null;
    }

}
