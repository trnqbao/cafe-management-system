//package com.trnqb.cafe.JWT;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private final JwtService jwtService;
//
//    @Autowired
//    private final CustomerUserDetailsService userDetailsService;
//
//    Claims claims = null;
//    private String userName = null;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//
//        if (authHeader == null || !authHeader.startsWith("Bear ")) {
//            filterChain.doFilter(request, response);
//        } else {
//            jwt = authHeader.substring(7);
//            userEmail = jwtService.extractUsername(jwt);
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//            filterChain.doFilter(request, response);
//        }
//    }
//
//    public boolean isAdmin() {
//        return "admin".equalsIgnoreCase((String) claims.get("role"));
//    }
//
//    public boolean isUser() {
//        return "user".equalsIgnoreCase((String) claims.get("role"));
//    }
//
//    public String getCurrentUser() {
//        return userName;
//    }
//
//}
//
////if (request.getServletPath().matches("/user/login|/user/forgotPassword|/user/signup")) {
////            filterChain.doFilter(request, response);
////        } else {
////            String authorizationHeader = request.getHeader("Authorization");
////            String token = null;
////
////            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
////                token = authorizationHeader.substring(7);
////                userName = jwtUtil.extractUsername(token);
////                claims = jwtUtil.extractAllClaims(token);
////            }
////
////            if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
////                UserDetails userDetails = service.loadUserByUsername(userName);
////                if (jwtUtil.validateToken(token, userDetails)) {
////                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
////                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
////
////                    usernamePasswordAuthenticationToken.setDetails(
////                            new WebAuthenticationDetailsSource().buildDetails(request)
////                    );
////                    SecurityContextHolder.getContext()
////                            .setAuthentication(usernamePasswordAuthenticationToken);
////                }
////            }
////            filterChain.doFilter(request, response);
////        }
//
//
//
////final String authHeader = request.getHeader("Authorization");
////final String jwt;
////final String userEmail;
////
////        if (authHeader == null || !authHeader.startsWith("Bear ")) {
////        filterChain.doFilter(request, response);
////
////        } else {
////jwt = authHeader.substring(7);
////userEmail = jwtService.extractUsername(jwt);
////
////            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
////                if (jwtService.isTokenValid(jwt, userDetails)) {
////UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
////        userDetails, null, userDetails.getAuthorities()
////);
////                    authenticationToken.setDetails(
////                            new WebAuthenticationDetailsSource().buildDetails(request)
////                    );
////                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////                }
////                        }
////                        filterChain.doFilter(request, response);
////        }