package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateGroupReqDto;
import com.example.demo.dto.TaskReqDto;
import com.example.demo.service.GroupService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/")
    @ApiOperation(value = "그룹 생성")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> createGroup(@ApiIgnore final Authentication authentication, @RequestBody CreateGroupReqDto createGroupReqDto) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Long userId = jwt_to_userId(authentication);
        groupService.createGroup(userId, createGroupReqDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    private boolean check_Auth(Authentication authentication) {
        if(authentication==null || !authentication.isAuthenticated()) return false;
        else return true;
    }
    private Long jwt_to_userId(Authentication authentication) {
        return ((User)authentication.getPrincipal()).getId();
    }

}
