package com.trnqb.cafe.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String extractUsername(String token);

    Claims extractAllClaims(String token);

    String generateToken(String email, String role);

    Boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails, String role);
}
