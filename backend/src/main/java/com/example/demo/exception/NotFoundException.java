package com.example.demo.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() { super("조회할 수 없는 정보입니다.");}
}
