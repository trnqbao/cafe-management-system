package com.trnqb.cafe.dto;

import com.trnqb.cafe.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private String status;
    private Role role;
}
