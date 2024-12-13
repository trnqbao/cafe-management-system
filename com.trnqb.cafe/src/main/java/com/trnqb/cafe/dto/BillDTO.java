package com.trnqb.cafe.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private String phoneNumber;
    private String paymentMethod;
    private Integer total;
    private String productDetails;
    private String createBy;
//    private LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;
    private String shiftTime;

}
