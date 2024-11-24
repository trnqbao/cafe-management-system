package com.trnqb.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDTO {
    private Integer id;
    private String productName;
    private Integer quantity;
    private Integer total;
    private LocalDate date;
}
