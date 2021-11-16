package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateGroupReqDto;
import com.example.demo.dto.GroupDetailResDto;
import com.example.demo.dto.TaskReqDto;
import com.example.demo.dto.TeamResDto;
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

import java.util.List;

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
    @GetMapping("/detail/{teamId}")
    @ApiOperation(value = "그룹 상세보기(1개) ")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<GroupDetailResDto> getGroupDetails(@ApiIgnore final Authentication authentication, @PathVariable Long teamId) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        //사실 유효성검증해야하는데 안함
        //Long userId = jwt_to_userId(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupDetails(teamId));
    }
    @GetMapping("/")
    @ApiOperation(value = "가입된 그룹 리스트")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<List<TeamResDto>> getGroupList(@ApiIgnore final Authentication authentication) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Long userId = jwt_to_userId(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupList(userId));
    }

    @PostMapping("/join/{teamId}")
    @ApiOperation(value = "그룹 가입 신청")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> createJoin(@ApiIgnore final Authentication authentication, @PathVariable Long teamId) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Long userId = jwt_to_userId(authentication);
        groupService.createJoin(userId, teamId);

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @PatchMapping("/join/{teamId}/{userId}")
    @ApiOperation(value = "그룹 가입 승인")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> acceptJoin(@ApiIgnore final Authentication authentication, @PathVariable Long teamId, @PathVariable Long userId) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Long leaderId = jwt_to_userId(authentication);
        try {
            groupService.acceptJoin(userId, teamId, leaderId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
    @DeleteMapping("/join/{teamId}/{userId}")
    @ApiOperation(value = "그룹 가입 거절")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<String> deleteJoin(@ApiIgnore final Authentication authentication, @PathVariable Long teamId, @PathVariable Long userId) {
        if(!check_Auth(authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Long leaderId = jwt_to_userId(authentication);
        try {
            groupService.deleteJoin(userId, teamId, leaderId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NO_CONTENT");
    }



    private boolean check_Auth(Authentication authentication) {
        if(authentication==null || !authentication.isAuthenticated()) return false;
        else return true;
    }
    private Long jwt_to_userId(Authentication authentication) {
        return ((User)authentication.getPrincipal()).getId();
    }

}
