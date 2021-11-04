package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    @ApiOperation(value = "todo 생성")
    @PostMapping("/")
    public ResponseEntity<String> createTodo() {
        return null;
    }

    @ApiOperation(value = "todo 읽기")
    @GetMapping("/")
    public ResponseEntity<String> readTodo() {
        return null;
    }

    @ApiOperation(value = "todo 업데이트")
    @PatchMapping("/")
    public ResponseEntity<String> updateTodo() {
        return null;
    }

    @ApiOperation(value = "Todo 삭제")
    @DeleteMapping("/")
    public ResponseEntity<String> deleteTodo() {
        return null;
    }

}
