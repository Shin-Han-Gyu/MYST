package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.TaskReqDto;
import com.example.demo.dto.TaskResDto;
import com.example.demo.service.GroupTaskService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/teamtask")
public class GroupTaskController {

    private final GroupTaskService groupTaskService;

    @ApiOperation(value = "todo 생성")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping("/{teamId}")
    public ResponseEntity<String> createGroupTask(@ApiIgnore final Authentication authentication, @PathVariable("teamId") Long groupId, @RequestBody TaskReqDto reqDto) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(groupTaskService.createGroupTask(jwt_to_userId(authentication), groupId , reqDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAIL");
        }
    }

    @ApiOperation(value = "todo 읽기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping("/{teamId}")
    public ResponseEntity<List<TaskResDto>> readGroupTodo(@ApiIgnore final Authentication authentication, @PathVariable("teamId") Long groupId) {
        if(!check_Auth(authentication)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        List<TaskResDto> ret = groupTaskService.readTodo(jwt_to_userId(authentication), groupId);

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
