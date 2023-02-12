package com.example.demo.dto;

import com.example.demo.entities.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteDto {
    private Long id;
    private String description;
    private Integer vote;
    private Users user;
}
