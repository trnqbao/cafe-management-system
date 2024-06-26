package com.trnqb.cafe.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
}
