package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.TaskReqDto;
import com.example.demo.dto.TaskResDto;
import com.example.demo.service.TaskService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @ApiOperation(value = "todo 생성")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping("/")
    public ResponseEntity<String> createPersonalTask(@ApiIgnore final Authentication authentication, @RequestBody TaskReqDto reqDto) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(taskService.createPersonalTask(jwt_to_userId(authentication), reqDto.getTaskName())) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

    @ApiOperation(value = "todo 업데이트")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTodo(@ApiIgnore final Authentication authentication, @PathVariable("taskId") Long taskId, @RequestBody TaskReqDto reqDto) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(taskService.updateTodo(jwt_to_userId(authentication),taskId, reqDto.getTaskName())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ACCEPTED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

    @ApiOperation(value = "Todo 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTodo(@ApiIgnore final Authentication authentication, @PathVariable("taskId") Long taskId) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(taskService.deleteTodo(jwt_to_userId(authentication),taskId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ACCEPTED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

    @ApiOperation(value = "Todo 완료")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PatchMapping("/{taskId}")
    public ResponseEntity<String> completeTodo(@ApiIgnore final Authentication authentication, @PathVariable("taskId") Long taskId) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(taskService.completeTodo(jwt_to_userId(authentication),taskId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("ACCEPTED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

    @ApiOperation(value = "todo 읽기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/")
    public ResponseEntity<List<TaskResDto>> readTodo(@ApiIgnore final Authentication authentication) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        List<TaskResDto> ret = taskService.readTodo(jwt_to_userId(authentication));

        if(ret == null) return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    private boolean check_Auth(Authentication authentication) {
        if(authentication==null || !authentication.isAuthenticated()) return false;
        else return true;
    }

    private Long jwt_to_userId(Authentication authentication) {
        return ((User)authentication.getPrincipal()).getId();
    }

}
