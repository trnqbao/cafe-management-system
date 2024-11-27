package com.trnqb.cafe.rest.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.RevenueDTO;
import com.trnqb.cafe.rest.RevenueRest;
import com.trnqb.cafe.service.RevenueService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RevenueRestImpl implements RevenueRest {
    private final RevenueService revenueService;
    @Override
    public ResponseEntity<List<RevenueDTO>> getRevenues() {
        try {
            return revenueService.getRevenues();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDailyRevenue(LocalDate date) {
        try {
            return revenueService.getDailyRevenue(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getWeeklyRevenue(LocalDate date) {
        try {
            return revenueService.getWeeklyRevenue(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getMonthlyRevenue() {
        try {
            return revenueService.getMonthlyRevenue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Integer> getMonthlyPrediction() {
        try {
            return revenueService.getMonthlyPrediction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addRevenue(Map<String, String> requestMap) {
        try {
            return revenueService.updateRevenue(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    public ResponseEntity<String> updateRevenue(Map<String, String> requestMap) {
//        try {
//            return revenueService.updateRevenue(requestMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
