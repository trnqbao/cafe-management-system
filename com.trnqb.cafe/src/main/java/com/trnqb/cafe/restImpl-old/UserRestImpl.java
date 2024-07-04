//package com.trnqb.cafe.restImpl;
//
//import com.trnqb.cafe.constants.CafeConstants;
//import com.trnqb.cafe.rest.UserRest;
//import com.trnqb.cafe.service.UserService;
//import com.trnqb.cafe.utils.CafeUtils;
//import com.trnqb.cafe.wrapper.UserWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class UserRestImpl implements UserRest {
//    @Autowired
//    UserService userService;
//
//    @Override
//    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
//        try {
//            return userService.signUp(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> login(Map<String, String> requestMap) {
//        try {
//            return userService.login(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<UserWrapper>> getAllUser() {
//        try {
//            return userService.getAllUser();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> update(Map<String, String> requestMap) {
//        try {
//            return userService.update(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}