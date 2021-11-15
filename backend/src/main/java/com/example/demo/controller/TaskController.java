package com.example.demo.controller;

import com.example.demo.dto.TaskReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    @ApiOperation(value = "todo 생성")
    @PostMapping("/")
    public ResponseEntity<String> createPersonalTask(@RequestBody TaskReqDto reqDto) {
        return null;
    }

    @ApiOperation(value = "todo 업데이트")
    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTodo(@PathVariable("taskId") Long taskId, @RequestBody TaskReqDto reqDto) {
        return null;
    }

    @ApiOperation(value = "Todo 삭제")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @ApiOperation(value = "Todo 완료")
    @PatchMapping("/{taskId}")
    public ResponseEntity<String> completeTodo(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @ApiOperation(value = "todo 읽기")
    @GetMapping("/")
    public ResponseEntity<String> readTodo() {
        return null;
    }

}
