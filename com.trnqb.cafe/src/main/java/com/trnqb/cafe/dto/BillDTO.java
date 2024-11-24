package com.trnqb.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date date;

}
