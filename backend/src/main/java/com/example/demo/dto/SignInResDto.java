package com.example.demo.dto;

import lombok.Getter;

@Getter
public class SignInResDto {
    private String jwt;
    private String name;

    public SignInResDto(String jwt, String name) {
        this.jwt = jwt;
        this.name = name;
    }
}
