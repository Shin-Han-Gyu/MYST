package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/id-duplication")
    @ApiOperation(value = "아이디 중복검사")
    public ResponseEntity<String> isDuplication(@RequestBody @ApiParam(value = "확인할 아이디") IdReqDto idReqDto) {
        if(!userService.idDuplicationCheck(idReqDto.getUserId())) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("duplication");
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResDto> signIn(@RequestBody @ApiParam(value = "userId, password") SignInReqDto signInInfo) {
        return null;
    }


    @PostMapping()
    public ResponseEntity<SignInResDto> singUp(CreateUserReqDto userInfo) {
        User user = userService.CreateUser(userInfo);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResDto> getUserInfo(@PathVariable Long userId) {
        User user = userService.readUserById(userId);
        return ResponseEntity.status(200).body(UserResDto.of(user));
    }
}
