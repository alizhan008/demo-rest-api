package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private boolean enabled;
}
