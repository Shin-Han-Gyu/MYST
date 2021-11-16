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
@CrossOrigin("*")
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

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입")
    public ResponseEntity<String> singUp(CreateUserReqDto userInfo) {
        userService.CreateUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    public ResponseEntity<SignInResDto> signIn(@RequestBody @ApiParam(value = "userId, password") SignInReqDto signInInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(signInInfo));
    }

}
