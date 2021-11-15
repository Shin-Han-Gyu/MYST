package com.example.demo.controller;

import com.example.demo.dto.TaskReqDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teamtask")
public class TeamTaskController {

    @ApiOperation(value = "todo 생성")
    @PostMapping("/{teamId}")
    public ResponseEntity<String> createGroupTask(@PathVariable("teamId") Long teamId, @RequestBody TaskReqDto reqDto) {
        return null;
    }

    @ApiOperation(value = "todo 업데이트")
    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateGroupTodo(@PathVariable("taskId") Long taskId, @RequestBody TaskReqDto reqDto) {
        return null;
    }

    @ApiOperation(value = "Todo 삭제")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteGroupTodo(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @ApiOperation(value = "Todo 완료")
    @PatchMapping("/{taskId}")
    public ResponseEntity<String> completeGroupTodo(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @ApiOperation(value = "todo 읽기")
    @GetMapping("/{teamId}")
    public ResponseEntity<String> readGroupTodo(@PathVariable("teamId") Long teamId) {
        return null;
    }

}
