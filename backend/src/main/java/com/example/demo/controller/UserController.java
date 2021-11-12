package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserReqDto;
import com.example.demo.dto.SignInReqDto;
import com.example.demo.dto.SignInResDto;
import com.example.demo.dto.UserResDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResDto> signIn(SignInReqDto signInInfo) {

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
