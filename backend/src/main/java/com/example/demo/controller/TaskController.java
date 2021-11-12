package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    @ApiOperation(value = "todo 생성")
    @PostMapping("/group-task")
    public ResponseEntity<String> createGroupTask() {
        return null;
    }

    @ApiOperation(value = "todo 생성")
    @PostMapping("/personal-task")
    public ResponseEntity<String> createPersonalTask() {
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
