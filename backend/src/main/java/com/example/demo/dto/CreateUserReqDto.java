package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserReqDto {
    private String userId;
    private String password;
    private String name;
}
