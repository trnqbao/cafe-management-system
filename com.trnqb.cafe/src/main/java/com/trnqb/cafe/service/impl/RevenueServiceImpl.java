package com.trnqb.cafe.service.impl;

import com.trnqb.cafe.constants.CafeConstants;
import com.trnqb.cafe.dto.RevenueDTO;
import com.trnqb.cafe.entity.Revenue;
import com.trnqb.cafe.repository.RevenueRepository;
import com.trnqb.cafe.service.RevenueService;
import com.trnqb.cafe.utils.CafeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {
//    private final JwtFilter jwtFilter;
    private final RevenueRepository revenueRepository;
    @Override
    public ResponseEntity<List<RevenueDTO>> getRevenues() {
        return new ResponseEntity<>(revenueRepository
                .findAll()
                .stream()
                .map(revenue -> mapToDTO(revenue, new RevenueDTO()))
                .toList(), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<String> addRevenue(Map<String, String> requestMap) {
//        try {
//            if (validateRevenueMap(requestMap)) {
//                revenueRepository.save(mapToEntity(requestMap));
//                return CafeUtils.getResponseEntity("Revenue has been added successfully", HttpStatus.OK);
//            }
//            return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    public ResponseEntity<String> updateRevenue(Map<String, String> requestMap) {
        try {
            Revenue revenue = revenueRepository.findByProductNameAndDate(requestMap.get("productName"), LocalDate.now());
            if (!Objects.isNull(revenue)) {
                revenue.setQuantity(revenue.getQuantity() + Integer.parseInt(requestMap.get("quantity")));
                revenue.setTotal(revenue.getTotal() + Integer.parseInt(requestMap.get("total")));
                revenueRepository.save(revenue);
                return CafeUtils.getResponseEntity("Revenue has been updated successfully", HttpStatus.OK);
            } else {
                revenueRepository.save(mapToEntity(requestMap));
                return CafeUtils.getResponseEntity("Revenue has been added successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.ST_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Integer> getDailyRevenue(LocalDate date) {
        try {
            if (date != null) {
                return new ResponseEntity<>(this.getRevenueByDay(date), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(this.getRevenueByDay(LocalDate.now()), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Integer> getMonthlyRevenue(int month, int year) {
        try {
            int total = revenueRepository.getMonthlyRevenue(month, year);
            System.out.println(total);
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getWeeklyRevenue(LocalDate date) {
        try {
            if (date == null) {
                return new ResponseEntity<>(this.getWeeklyRevenueFrom(LocalDate.now()), HttpStatus.OK);
            }
            return new ResponseEntity<>(this.getWeeklyRevenueFrom(date), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Integer> getMonthlyPrediction() {
        try {
            return new ResponseEntity<>(0, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Revenue mapToEntity(Map<String, String> requestMap) {
        Revenue revenue = new Revenue();
        revenue.setProductName(requestMap.get("productName"));
        revenue.setQuantity(Integer.valueOf(requestMap.get("quantity")));
        revenue.setTotal(Integer.valueOf(requestMap.get("total")));
        revenue.setDate(LocalDate.now());
        return revenue;
    }

    private boolean validateRevenueMap(Map<String, String> requestMap) {
        return requestMap.containsKey("productName") &&
                requestMap.containsKey("quantity") &&
                requestMap.containsKey("total");
    }

    private RevenueDTO mapToDTO(Revenue revenue, RevenueDTO revenueDTO) {
        revenueDTO.setId(revenue.getId());
        revenueDTO.setProductName(revenue.getProductName());
        revenueDTO.setQuantity(revenue.getQuantity());
        revenueDTO.setTotal(revenue.getTotal());
        revenueDTO.setDate(revenue.getDate());
        return revenueDTO;
    }

    private Integer getRevenueByDay(LocalDate date) {
        int total = 0;
        List<RevenueDTO> revenues = revenueRepository.findAllByDate(date).stream().map(revenue -> mapToDTO(revenue, new RevenueDTO())).toList();
        for (RevenueDTO revenue : revenues) {
                total += revenue.getTotal();
        }
        return total;
    }

    private List<Map<String, Object>> getWeeklyRevenueFrom(LocalDate date) {
        int total = 0;
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            Map<String, Object> map = new HashMap<>();

            int lastTotal = this.getRevenueByDay(date.minusDays(i));

            map.put("date", date.minusDays(i));
            map.put("revenue", lastTotal);
            mapList.add(map);

            total += lastTotal;
            System.out.println("Revenue at " + date.minusDays(i) + ": " + lastTotal);
        }

        Map<String, Object> weeklyRevenueMap = new HashMap<>();
        weeklyRevenueMap.put("revenue", total);
        weeklyRevenueMap.put("avg_revenue", total/7);

        mapList.add(weeklyRevenueMap);
        return mapList;
    }
}
